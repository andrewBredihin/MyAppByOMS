package com.bav.ordermanagementsystem.ui.managerPage;

import android.annotation.SuppressLint;
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
import com.bav.ordermanagementsystem.adapter.completeOrders.CompleteOrdersAdapter;
import com.bav.ordermanagementsystem.databinding.FragmentManagerEmployeeInfoBinding;
import com.bav.ordermanagementsystem.db.DatabaseClient;
import com.bav.ordermanagementsystem.entity.Employee;
import com.bav.ordermanagementsystem.entity.OrderStatus;
import com.bav.ordermanagementsystem.service.UserService;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableMaybeObserver;
import io.reactivex.schedulers.Schedulers;

public class EmployeeInfoFragment extends Fragment {

    private FragmentManagerEmployeeInfoBinding binding;
    private UserService userService;
    private DatabaseClient databaseClient;

    private Button editEmployee;

    @SuppressLint("CheckResult")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentManagerEmployeeInfoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        Context context = getContext();

        userService = UserService.getInstance(context);
        databaseClient = DatabaseClient.getInstance(context);

        databaseClient.getAppDatabase().employeeDao().getById(getArguments().getLong("id"))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableMaybeObserver<Employee>() {
                    @Override
                    public void onSuccess(Employee employee) {
                        binding.setUser(employee);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                    }
                });

        DatabaseClient.getInstance(context).getAppDatabase().orderDao().getByEmployeeIdAndStatusId(getArguments().getLong("id"), OrderStatus.COMPLETED)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(orders -> {
                    CompleteOrdersAdapter adapter = new CompleteOrdersAdapter(context, orders, R.layout.complete_order_info_fragment);
                    binding.completeOrdersList.setAdapter(adapter);
                });

        editEmployee = binding.btnEdit;
        editEmployee.setOnClickListener(v -> {

        });

        return root;
    }
}
