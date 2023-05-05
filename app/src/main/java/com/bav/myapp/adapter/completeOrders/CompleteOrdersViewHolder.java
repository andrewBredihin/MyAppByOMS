package com.bav.myapp.adapter.completeOrders;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bav.myapp.R;
import com.bav.myapp.adapter.OrderRecyclerViewHolderInterface;
import com.bav.myapp.entity.Order;

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
