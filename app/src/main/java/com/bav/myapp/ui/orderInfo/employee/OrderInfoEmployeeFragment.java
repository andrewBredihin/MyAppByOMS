package com.bav.myapp.ui.orderInfo.employee;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.bav.myapp.R;
import com.bav.myapp.adapter.orderItems.info.OrderItemsInfoAdapter;
import com.bav.myapp.databinding.FragmentOrderInfoEmployeeBinding;
import com.bav.myapp.databinding.OrderInfoActiveBinding;
import com.bav.myapp.databinding.OrderInfoPendingBinding;
import com.bav.myapp.db.DatabaseClient;
import com.bav.myapp.entity.Client;
import com.bav.myapp.entity.OrderItem;
import com.bav.myapp.entity.OrderStatus;
import com.bav.myapp.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableMaybeObserver;
import io.reactivex.schedulers.Schedulers;

public class OrderInfoEmployeeFragment extends Fragment {

    private FragmentOrderInfoEmployeeBinding binding;
    private UserService userService;
    private DatabaseClient databaseClient;

    @SuppressLint("CheckResult")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentOrderInfoEmployeeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        Context context = getContext();
        userService = UserService.getInstance(context);
        databaseClient = DatabaseClient.getInstance(context);

        databaseClient.getAppDatabase().orderDao().getById(getArguments().getLong("orderId"))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(order -> {
                    binding.setOrder(order);

                    databaseClient.getAppDatabase().clientDao().getById(order.getClient_id())
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new DisposableMaybeObserver<Client>() {
                                @Override
                                public void onSuccess(Client client) {
                                    binding.orderInfoClient.setText(client.getFullName());
                                }

                                @Override
                                public void onError(Throwable e) {

                                }

                                @Override
                                public void onComplete() {

                                }
                            });

                    if (Objects.equals(order.getEmployee_id(), userService.getUserDetails().getValue().getId())){
                        binding.orderInfoOrderActive.setOnInflateListener(new ViewStub.OnInflateListener() {
                            @Override
                            public void onInflate(ViewStub viewStub, View view) {
                                OrderInfoActiveBinding binding = DataBindingUtil.bind(view);
                                binding.orderInfoComplete.setOnClickListener(v -> {
                                    order.setStatus(OrderStatus.COMPLETED);
                                    Completable.fromAction(() -> databaseClient.getAppDatabase().orderDao().update(order))
                                            .subscribeOn(Schedulers.io())
                                            .observeOn(AndroidSchedulers.mainThread())
                                            .subscribe(new DisposableCompletableObserver() {
                                                @Override
                                                public void onComplete() {
                                                    Toast.makeText(context, R.string.orderComplete, Toast.LENGTH_SHORT).show();
                                                    Navigation.findNavController(container).navigate(R.id.nav_active_orders);
                                                }

                                                @Override
                                                public void onError(Throwable e) {

                                                }
                                            });
                                });
                                binding.orderInfoCanceled.setOnClickListener(v -> {
                                    order.setStatus(OrderStatus.PENDING);
                                    order.setEmployee_id(null);
                                    Completable.fromAction(() -> databaseClient.getAppDatabase().orderDao().update(order))
                                            .subscribeOn(Schedulers.io())
                                            .observeOn(AndroidSchedulers.mainThread())
                                            .subscribe(new DisposableCompletableObserver() {
                                                @Override
                                                public void onComplete() {
                                                    Toast.makeText(context, R.string.orderCanceled, Toast.LENGTH_SHORT).show();
                                                    Navigation.findNavController(container).navigate(R.id.nav_active_orders);
                                                }

                                                @Override
                                                public void onError(Throwable e) {

                                                }
                                            });
                                });
                            }
                        });
                        if (!binding.orderInfoOrderActive.isInflated()) {
                            binding.orderInfoOrderActive.getViewStub().inflate();
                        }
                    }
                    else if (Objects.equals(order.getEmployee_id(), null)){
                        binding.orderInfoOrderPending.setOnInflateListener(new ViewStub.OnInflateListener() {
                            @Override
                            public void onInflate(ViewStub viewStub, View view) {
                                OrderInfoPendingBinding binding = DataBindingUtil.bind(view);
                                binding.orderInfoToActive.setOnClickListener(v -> {
                                    order.setStatus(OrderStatus.ACTIVE);
                                    order.setEmployee_id(userService.getUserDetails().getValue().getId());
                                    Completable.fromAction(() -> DatabaseClient.getInstance(view.getContext()).getAppDatabase().orderDao().update(order))
                                            .subscribeOn(Schedulers.io())
                                            .observeOn(AndroidSchedulers.mainThread())
                                            .subscribe(new DisposableCompletableObserver() {
                                                @Override
                                                public void onComplete() {
                                                    Toast.makeText(context, R.string.orderStartActive, Toast.LENGTH_SHORT).show();
                                                    Navigation.findNavController(container).navigate(R.id.nav_active_orders);
                                                }

                                                @Override
                                                public void onError(Throwable e) {

                                                }
                                            });
                                });
                            }
                        });
                        if (!binding.orderInfoOrderPending.isInflated()) {
                            binding.orderInfoOrderPending.getViewStub().inflate();
                        }
                    }
                    else {
                        Toast.makeText(context, R.string.orderAlreadyActive, Toast.LENGTH_SHORT).show();
                        Navigation.findNavController(container).navigate(R.id.nav_pending_orders);
                    }
                });

        //Прописать получение списка своих OrderItem по `getArguments().getLong("orderId")` и таблице `order_and_order_items`
        /*List<OrderItem> items;
        OrderItemsInfoAdapter adapter = new OrderItemsInfoAdapter(getContext(), items, R.layout.order_item_info_fragment);
        binding.orderItemsList.setAdapter(adapter);*/

        DatabaseClient.getInstance(context).getAppDatabase().myServiceDao().getByOrderId(getArguments().getLong("orderId"))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(items -> {
                    List<OrderItem> list = new ArrayList<>();
                    list.addAll(items);
                    OrderItemsInfoAdapter adapter = new OrderItemsInfoAdapter(getContext(), list, R.layout.order_item_info_fragment);
                    binding.orderItemsList.setAdapter(adapter);
                });

        return root;
    }
}
