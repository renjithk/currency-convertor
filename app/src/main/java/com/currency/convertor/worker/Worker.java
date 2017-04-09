package com.currency.convertor.worker;

import com.currency.convertor.entity.ProductEntity;
import com.currency.convertor.entity.RateEntity;
import com.currency.convertor.entity.TransactionEntity;
import com.currency.convertor.event.request.ProductRequestEvent;
import com.currency.convertor.event.request.TransactionRequestEvent;
import com.currency.convertor.event.response.ProductResponseEvent;
import com.currency.convertor.event.response.TransactionResponseEvent;
import com.currency.convertor.utils.RatesDataHolder;
import com.currency.convertor.utils.Utils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

/**
 * Worker implementation
 *
 * Created by Renjith Kandanatt on 09/04/2017.
 */

public class Worker {
    private final Gson mGson;

    @Inject
    public Worker(Gson gson) {
        mGson = gson;
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void fetchProducts(ProductRequestEvent event) {
        try{
            //get exchange rates. this has to be available statically
            //Using Enum to set rates data
            Type dataType = new TypeToken<List<RateEntity>>(){}.getType();
            List<RateEntity> rates = mGson.fromJson(event.getRatesJson(), dataType);
            RatesDataHolder.setRates(rates);

            dataType = new TypeToken<List<TransactionEntity>>(){}.getType();
            List<TransactionEntity> transactions = mGson.fromJson(event.getTransactionJson(),
                                                                    dataType);

            //find all transactions of each product
            HashMap<String,List<TransactionEntity>> productTransactions = new HashMap<>();

            for(TransactionEntity transaction : Utils.safeList(transactions)) {
                if(!productTransactions.containsKey(transaction.toString())) {
                    productTransactions.put(transaction.toString(), new ArrayList<TransactionEntity>());
                }
                transaction.toGBP();
                productTransactions.get(transaction.toString()).add(transaction);
            }

            //create list of products with transaction count
            List<ProductEntity> products = new ArrayList<>();
            for (Map.Entry<String, List<TransactionEntity>> entry : productTransactions.entrySet()) {
                ProductEntity product = new ProductEntity(entry.getKey(),entry.getValue().size());
                products.add(product);
            }

            //trigger result event
            ProductResponseEvent responseEvent = new ProductResponseEvent(event.getRequestId(),
                                                                                    products, productTransactions);
            EventBus.getDefault().postSticky(responseEvent);

        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void fetchTransactions(TransactionRequestEvent event) {
        BigDecimal sum = BigDecimal.ZERO;
        for(TransactionEntity transaction : Utils.safeList(event.getTransactions())) {
            sum = sum.add(transaction.inGBPAsBigDecimal());
        }
        TransactionResponseEvent responseEvent = new TransactionResponseEvent(event.getRequestId(),
                                                                                Utils.formatNumber(sum));
        EventBus.getDefault().postSticky(responseEvent);
    }
}
