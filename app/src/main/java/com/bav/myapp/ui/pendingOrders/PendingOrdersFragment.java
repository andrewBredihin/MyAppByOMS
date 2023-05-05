package com.bav.myapp.ui.pendingOrders;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bav.myapp.R;
import com.bav.myapp.adapter.pendingOrders.PendingOrdersAdapter;
import com.bav.myapp.databinding.FragmentPendingOrdersBinding;
import com.bav.myapp.db.DatabaseClient;
import com.bav.myapp.entity.OrderStatus;
import com.bav.myapp.service.UserService;

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
