package com.bav.ordermanagementsystem.adapter.orderItems.info;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bav.ordermanagementsystem.entity.OrderItem;

import java.util.List;

public class OrderItemsInfoAdapter extends RecyclerView.Adapter<OrderItemsInfoViewHolder> {

    private final LayoutInflater inflater;
    private final List<OrderItem> items;
    private final int layout;

    public OrderItemsInfoAdapter(Context context, List<OrderItem> items, int layout){
        this.inflater = LayoutInflater.from(context);
        this.items = items;
        this.layout = layout;
    }

    @NonNull
    @Override
    public OrderItemsInfoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(layout, parent, false);
        return new OrderItemsInfoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderItemsInfoViewHolder holder, int position) {
        holder.setOrderItem(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
