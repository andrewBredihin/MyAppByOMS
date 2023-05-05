package com.bav.ordermanagementsystem.adapter.orderItems.info;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bav.ordermanagementsystem.R;
import com.bav.ordermanagementsystem.adapter.OrderItemRecyclerViewHolderInterface;
import com.bav.ordermanagementsystem.entity.OrderItem;

public class OrderItemsInfoViewHolder extends RecyclerView.ViewHolder implements OrderItemRecyclerViewHolderInterface {
    final TextView itemTitle;
    private View view;

    public OrderItemsInfoViewHolder(View view) {
        super(view);
        this.view = view;
        itemTitle = view.findViewById(R.id.orderItemTitle);
    }

    @Override
    public void setOrderItem(OrderItem item) {
        itemTitle.setText(item.getTitle());
    }
}
