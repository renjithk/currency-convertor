package com.currency.convertor.ui.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.currency.convertor.R;
import com.currency.convertor.entity.TransactionEntity;
import com.currency.convertor.ui.presenter.impl.TransactionListPresenter;
import com.currency.convertor.utils.Utils;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Renjith Kandanatt on 09/04/2017.
 */
public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.ViewHolder> {
    private List<TransactionEntity> mData;
    private TransactionListPresenter mPresenter;

    @Inject
    public TransactionAdapter() {}

    public void setData(List<TransactionEntity> data) {
        mData = data;
    }

    public void addPresenter(TransactionListPresenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public TransactionAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_selectable_two_item, parent, false);
        TransactionAdapter.ViewHolder viewHolder = new TransactionAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(TransactionAdapter.ViewHolder holder, int position) {
        holder.bind(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return Utils.isEmptyList(mData) ? 0 : mData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @Nullable @Bind(R.id.title_1) protected TextView mName;
        @Nullable @Bind(R.id.title_2) protected TextView mInGBP;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(TransactionEntity entity) {
            mName.setText(entity.amountAsString());
            mInGBP.setText(itemView.getContext().getString(R.string.transaction_row_amount, entity.inGBP()));
        }
    }
}
