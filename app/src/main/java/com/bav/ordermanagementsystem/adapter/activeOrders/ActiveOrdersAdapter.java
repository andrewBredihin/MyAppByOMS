package com.bav.ordermanagementsystem.adapter.activeOrders;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bav.ordermanagementsystem.entity.Order;

import java.util.List;

public class ActiveOrdersAdapter extends RecyclerView.Adapter<ActiveOrdersViewHolder> {

    private final LayoutInflater inflater;
    private final List<Order> items;
    private final int layout;

    public ActiveOrdersAdapter(Context context, List<Order> items, int layout){
        this.inflater = LayoutInflater.from(context);
        this.items = items;
        this.layout = layout;
    }

    @NonNull
    @Override
    public ActiveOrdersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(layout, parent, false);
        return new ActiveOrdersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ActiveOrdersViewHolder holder, int position) {
        holder.setOrder(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
