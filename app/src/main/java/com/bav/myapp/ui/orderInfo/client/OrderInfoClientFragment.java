package com.bav.myapp.ui.orderInfo.client;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.bav.myapp.R;
import com.bav.myapp.databinding.FragmentOrderInfoClientBinding;
import com.bav.myapp.databinding.OrderEmployeeTextviewBinding;
import com.bav.myapp.db.DatabaseClient;
import com.bav.myapp.entity.Employee;
import com.bav.myapp.entity.OrderStatus;
import com.bav.myapp.service.UserService;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableMaybeObserver;
import io.reactivex.schedulers.Schedulers;

public class OrderInfoClientFragment extends Fragment {

    private FragmentOrderInfoClientBinding binding;
    private UserService userService;
    private DatabaseClient databaseClient;

    private Button canceledOrder;

    @SuppressLint("CheckResult")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentOrderInfoClientBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        Context context = getContext();
        userService = UserService.getInstance(context);
        databaseClient = DatabaseClient.getInstance(context);

        canceledOrder = binding.orderInfoCanceled;

        databaseClient.getAppDatabase().orderDao().getById(getArguments().getLong("orderId"))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(order -> {
                    binding.setOrder(order);
                    canceledOrder.setOnClickListener(v -> {
                        if (!order.getStatus().equals(OrderStatus.ACTIVE)){
                            Completable.fromAction(() -> DatabaseClient.getInstance(context).getAppDatabase().orderDao().delete(order))
                                    .subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(new DisposableCompletableObserver() {
                                        @Override
                                        public void onComplete() {
                                            Toast.makeText(context, R.string.orderCanceled, Toast.LENGTH_SHORT).show();
                                            Navigation.findNavController(container).navigate(R.id.nav_my_orders);
                                        }

                                        @Override
                                        public void onError(Throwable e) {

                                        }
                                    });
                        } else {
                            Toast.makeText(context, R.string.orderInExecution, Toast.LENGTH_SHORT).show();
                        }
                    });
                });

        if (getArguments().getLong("employeeId") != 0){
            databaseClient.getAppDatabase().employeeDao().getById(getArguments().getLong("employeeId"))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new DisposableMaybeObserver<Employee>() {
                        @Override
                        public void onSuccess(Employee employee) {
                            binding.orderInfoEmployee.setOnInflateListener((viewStub, view) -> {
                                OrderEmployeeTextviewBinding binding = DataBindingUtil.bind(view);
                                binding.setEmployee(employee);
                            });
                            if (!binding.orderInfoEmployee.isInflated()) {
                                binding.orderInfoEmployee.getViewStub().inflate();
                            }
                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }

        //Прописать получение списка своих OrderItem по `getArguments().getLong("orderId")` и таблице `order_and_order_items`
        /*List<OrderItem> items;
        OrderItemsInfoAdapter adapter = new OrderItemsInfoAdapter(getContext(), items, R.layout.order_item_info_fragment);
        binding.orderItemsList.setAdapter(adapter);*/

        return root;
    }
}
