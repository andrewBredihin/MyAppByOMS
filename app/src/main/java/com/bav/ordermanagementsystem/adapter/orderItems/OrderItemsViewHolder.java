package com.bav.ordermanagementsystem.adapter.orderItems;

import android.view.View;
import android.widget.Button;

import androidx.recyclerview.widget.RecyclerView;

import com.bav.ordermanagementsystem.R;
import com.bav.ordermanagementsystem.adapter.OrderItemRecyclerViewHolderInterface;
import com.bav.ordermanagementsystem.entity.OrderItem;
import com.bav.ordermanagementsystem.ui.createOrder.OrderItemsStore;

public class OrderItemsViewHolder extends RecyclerView.ViewHolder implements OrderItemRecyclerViewHolderInterface {
    final Button orderItemButton;
    private View view;

    private OrderItemsStore store;

    public OrderItemsViewHolder(View view) {
        super(view);
        this.view = view;
        orderItemButton = view.findViewById(R.id.orderItemButton);
        store = OrderItemsStore.getInstance(view.getContext());
    }

    @Override
    public void setOrderItem(OrderItem item) {
        orderItemButton.setText(item.getTitle());
        orderItemButton.setOnClickListener(v -> {
            store.addItem(item);
        });
    }
}
