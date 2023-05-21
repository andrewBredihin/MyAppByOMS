package com.bav.myapp.ui.createOrderItem;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.bav.myapp.R;
import com.bav.myapp.databinding.FragmentCreateOrderItemBinding;
import com.bav.myapp.db.DatabaseClient;
import com.bav.myapp.entity.MyService;
import com.bav.myapp.entity.OrderAndOrderItems;
import com.bav.myapp.entity.OrderItem;
import com.bav.myapp.service.UserService;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.schedulers.Schedulers;

public class CreateOrderItemFragment extends Fragment {

    private UserService userService;
    private FragmentCreateOrderItemBinding binding;
    private DatabaseClient databaseClient;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentCreateOrderItemBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        Context context = getContext();
        userService = UserService.getInstance(context);
        databaseClient = DatabaseClient.getInstance(getContext());

        binding.buttonCreateOrderItem.setOnClickListener(v -> {
            OrderItemFieldsFragment fragment = (OrderItemFieldsFragment) getChildFragmentManager().findFragmentById(R.id.orderItem_fields);
            MyService item = (MyService) fragment.getOrderItem();
            if (item != null){
                Completable.fromAction(() -> DatabaseClient.getInstance(getContext()).getAppDatabase().myServiceDao().insert(item))
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new DisposableCompletableObserver() {
                            @Override
                            public void onComplete() {
                                Navigation.findNavController(root).navigate(R.id.nav_employees_page);
                            }

                            @Override
                            public void onError(Throwable e) {
                                Toast.makeText(getContext(), R.string.create_orderItem_error, Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

        return root;
    }
}
