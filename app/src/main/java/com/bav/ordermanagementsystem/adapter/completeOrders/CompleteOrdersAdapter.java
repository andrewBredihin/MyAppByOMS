package com.bav.ordermanagementsystem.adapter.completeOrders;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bav.ordermanagementsystem.entity.Order;

import java.util.List;

public class CompleteOrdersAdapter extends RecyclerView.Adapter<CompleteOrdersViewHolder> {

    private final LayoutInflater inflater;
    private final List<Order> items;
    private final int layout;

    public CompleteOrdersAdapter(Context context, List<Order> items, int layout){
        this.inflater = LayoutInflater.from(context);
        this.items = items;
        this.layout = layout;
    }

    @NonNull
    @Override
    public CompleteOrdersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(layout, parent, false);
        return new CompleteOrdersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CompleteOrdersViewHolder holder, int position) {
        holder.setOrder(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
