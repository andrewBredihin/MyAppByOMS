package com.bav.ordermanagementsystem.ui.activeOrders;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bav.ordermanagementsystem.R;
import com.bav.ordermanagementsystem.adapter.activeOrders.ActiveOrdersAdapter;
import com.bav.ordermanagementsystem.databinding.FragmentActiveOrdersMainBinding;
import com.bav.ordermanagementsystem.db.DatabaseClient;
import com.bav.ordermanagementsystem.entity.OrderStatus;
import com.bav.ordermanagementsystem.service.UserService;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class ActiveOrdersFragment extends Fragment {

    private View view;
    private UserService userService;
    private FragmentActiveOrdersMainBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentActiveOrdersMainBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        Context context = getContext();
        userService = UserService.getInstance(context);

        RecyclerView recyclerView = binding.activeOrdersList;

        DatabaseClient.getInstance(context).getAppDatabase().orderDao().getByEmployeeIdAndStatusId(userService.getUserDetails().getValue().getId(), OrderStatus.ACTIVE)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(orders -> {
                    ActiveOrdersAdapter adapter = new ActiveOrdersAdapter(context, orders, R.layout.fragment_active_orders_order);
                    recyclerView.setAdapter(adapter);
                });
        return root;
    }
}
