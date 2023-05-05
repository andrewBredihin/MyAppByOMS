package com.bav.myapp.ui.createOrder;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.bav.myapp.R;
import com.bav.myapp.adapter.orderItems.OrderItemsAdapter;
import com.bav.myapp.databinding.FragmentCreateOrderGetItemsBinding;
import com.bav.myapp.entity.OrderItem;

import java.util.ArrayList;
import java.util.List;

public class CreateOrderGetItemsFragment extends Fragment {

    private List<OrderItem> items;
    private Button continueBtn;

    private FragmentCreateOrderGetItemsBinding binding;
    private OrderItemsStore store;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCreateOrderGetItemsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        store = OrderItemsStore.getInstance(getContext());

        //Тут прописывается получение объектов класса, реализующего OrderItem, из БД
        List<OrderItem> items = new ArrayList<>();

        OrderItemsAdapter adapter = new OrderItemsAdapter(getContext(), items, R.layout.order_item_fragment);
        binding.orderItemsList.setAdapter(adapter);

        continueBtn = binding.continueButton;
        continueBtn.setOnClickListener(v -> {
            Navigation.findNavController(root).navigate(R.id.nav_create_order);
        });

        return root;
    }
}
