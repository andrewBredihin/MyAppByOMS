package com.bav.ordermanagementsystem.ui.managerPage;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bav.ordermanagementsystem.R;
import com.bav.ordermanagementsystem.adapter.employees.EmployeesAdapter;
import com.bav.ordermanagementsystem.databinding.FragmentManagerPageBinding;
import com.bav.ordermanagementsystem.db.DatabaseClient;
import com.bav.ordermanagementsystem.service.UserService;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class ManagerPage extends Fragment {

    private FragmentManagerPageBinding binding;
    private UserService userService;

    private Button newEmployee;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentManagerPageBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        Context context = getContext();

        userService = UserService.getInstance(context);

        newEmployee = binding.newEmployee;
        newEmployee.setOnClickListener(v -> {

        });

        DatabaseClient.getInstance(context).getAppDatabase().employeeDao().getAll()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(employees -> {
                    EmployeesAdapter adapter = new EmployeesAdapter(context, employees, R.layout.employee_button);
                    binding.employeeList.setAdapter(adapter);
                });

        return root;
    }
}
