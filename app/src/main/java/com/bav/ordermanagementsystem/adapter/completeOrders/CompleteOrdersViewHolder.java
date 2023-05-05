package com.bav.ordermanagementsystem.adapter.completeOrders;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bav.ordermanagementsystem.R;
import com.bav.ordermanagementsystem.adapter.OrderRecyclerViewHolderInterface;
import com.bav.ordermanagementsystem.entity.Order;

public class CompleteOrdersViewHolder extends RecyclerView.ViewHolder implements OrderRecyclerViewHolderInterface {
    final TextView title, date;

    public CompleteOrdersViewHolder(View view) {
        super(view);
        title = view.findViewById(R.id.complete_order_title);
        date = view.findViewById(R.id.complete_order_date);
    }

    @Override
    public void setOrder(Order item) {
        title.setText(item.getTitle());
        date.setText(item.getDate());
    }
}
