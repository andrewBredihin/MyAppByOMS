package com.bav.ordermanagementsystem.adapter.employees;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bav.ordermanagementsystem.entity.Employee;

import java.util.List;

public class EmployeesAdapter extends RecyclerView.Adapter<EmployeesViewHolder> {

    private final LayoutInflater inflater;
    private final List<Employee> items;
    private final int layout;

    public EmployeesAdapter(Context context, List<Employee> items, int layout){
        this.inflater = LayoutInflater.from(context);
        this.items = items;
        this.layout = layout;
    }

    @NonNull
    @Override
    public EmployeesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(layout, parent, false);
        return new EmployeesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EmployeesViewHolder holder, int position) {
        holder.setEmployee(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
