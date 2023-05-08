package com.bav.myapp.adapter.orderItems.info;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bav.myapp.R;
import com.bav.myapp.adapter.OrderItemRecyclerViewHolderInterface;
import com.bav.myapp.entity.OrderItem;

public class OrderItemsInfoViewHolder extends RecyclerView.ViewHolder implements OrderItemRecyclerViewHolderInterface {
    final TextView itemTitle, itemPrice, itemDescription;
    private View view;

    public OrderItemsInfoViewHolder(View view) {
        super(view);
        this.view = view;
        itemTitle = view.findViewById(R.id.item_title);
        itemPrice = view.findViewById(R.id.item_price);
        itemDescription = view.findViewById(R.id.item_description);
    }

    @Override
    public void setOrderItem(OrderItem item) {
        itemTitle.setText(item.getTitle());
        itemDescription.setText(item.getDescription());
        itemPrice.setText(String.valueOf(item.getPrice()));
    }
}
