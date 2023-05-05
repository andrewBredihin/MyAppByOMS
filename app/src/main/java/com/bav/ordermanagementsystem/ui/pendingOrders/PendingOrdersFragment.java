package com.bav.ordermanagementsystem.ui.pendingOrders;

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
import com.bav.ordermanagementsystem.adapter.pendingOrders.PendingOrdersAdapter;
import com.bav.ordermanagementsystem.databinding.FragmentPendingOrdersBinding;
import com.bav.ordermanagementsystem.db.DatabaseClient;
import com.bav.ordermanagementsystem.entity.OrderStatus;
import com.bav.ordermanagementsystem.service.UserService;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class PendingOrdersFragment extends Fragment {

    private View view;
    private UserService userService;
    private FragmentPendingOrdersBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentPendingOrdersBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        Context context = getContext();
        userService = UserService.getInstance(context);

        RecyclerView recyclerView = binding.pendingOrdersList;

        DatabaseClient.getInstance(context).getAppDatabase().orderDao().getByStatusId(OrderStatus.PENDING)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(orders -> {
                    PendingOrdersAdapter adapter;
                    adapter = new PendingOrdersAdapter(context, orders, R.layout.fragment_pending_order);
                    recyclerView.setAdapter(adapter);
                });
        return root;
    }
}
