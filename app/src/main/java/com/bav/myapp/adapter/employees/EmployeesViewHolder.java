package com.bav.myapp.adapter.employees;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bav.myapp.R;
import com.bav.myapp.entity.Employee;

public class EmployeesViewHolder extends RecyclerView.ViewHolder {
    final Button btnEmployee;
    private View view;

    public EmployeesViewHolder(View view) {
        super(view);
        this.view = view;
        btnEmployee = view.findViewById(R.id.employeeName);
    }

    public void setEmployee(Employee employee) {
        btnEmployee.setText(employee.getFullName());
        btnEmployee.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putLong("id", employee.getId());
            Navigation.findNavController(view).navigate(R.id.nav_manager_user_info, bundle);
        });
    }
}
