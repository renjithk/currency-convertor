package com.currency.convertor.ui.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.currency.convertor.R;
import com.currency.convertor.entity.ProductEntity;
import com.currency.convertor.ui.presenter.impl.ProductListPresenter;
import com.currency.convertor.utils.Utils;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * View adapter for
 * Created by Renjith Kandanatt on 09/04/2017.
 */

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private List<ProductEntity> mData;
    private ProductListPresenter mPresenter;

    @Inject
    public ProductAdapter() {}

    public void setData(List<ProductEntity> data) {
        mData = data;
    }

    public void addPresenter(ProductListPresenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_selectable_two_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return Utils.isEmptyList(mData) ? 0 : mData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @Nullable @Bind(R.id.title_1) protected TextView mName;
        @Nullable @Bind(R.id.title_2) protected TextView mCount;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.layout_parent)
        public void onItemClick() {
            mPresenter.onItemClick(mData.get(getAdapterPosition()).toString());
        }

        public void bind(ProductEntity entity) {
            mName.setText(entity.toString());
            mCount.setText(itemView.getContext().getString(R.string.product_total_transaction_count, entity.getTransactionCount()));
        }
    }
}
