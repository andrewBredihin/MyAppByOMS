package com.bav.myapp.ui.createOrder;

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
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.bav.myapp.R;
import com.bav.myapp.adapter.orderItems.OrderItemsAdapter;
import com.bav.myapp.databinding.FragmentCreateOrderGetItemsBinding;
import com.bav.myapp.db.DatabaseClient;
import com.bav.myapp.entity.MyService;
import com.bav.myapp.entity.OrderItem;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class CreateOrderGetItemsFragment extends Fragment {

    private Button continueBtn;

    private FragmentCreateOrderGetItemsBinding binding;
    private OrderItemsStore store;

    @SuppressLint("CheckResult")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCreateOrderGetItemsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        Context context = getContext();
        store = OrderItemsStore.getInstance(context);

        DatabaseClient.getInstance(context).getAppDatabase().myServiceDao().getAll()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(items -> {
                    List<OrderItem> list = new ArrayList<>(items);
                    OrderItemsAdapter adapter = new OrderItemsAdapter(getContext(),
                            list,
                            R.layout.order_item_fragment);
                    binding.orderItemsList.setAdapter(adapter);
                });

        continueBtn = binding.continueButton;
        continueBtn.setOnClickListener(v -> {
            if (store.getSize() > 0)
                Navigation.findNavController(root).navigate(R.id.nav_create_order);
            else
                Toast.makeText(getContext(), R.string.error_get_order_items, Toast.LENGTH_SHORT).show();
        });

        return root;
    }
}
