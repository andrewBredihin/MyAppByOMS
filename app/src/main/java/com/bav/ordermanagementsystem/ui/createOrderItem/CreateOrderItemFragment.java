package com.bav.ordermanagementsystem.ui.createOrderItem;

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

import com.bav.ordermanagementsystem.R;
import com.bav.ordermanagementsystem.databinding.FragmentCreateOrderItemBinding;
import com.bav.ordermanagementsystem.db.DatabaseClient;
import com.bav.ordermanagementsystem.entity.OrderItem;
import com.bav.ordermanagementsystem.service.UserService;

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
            OrderItem item = fragment.getOrderItem();
            if (item != null){
                //Прописать сохранение в БД

                Navigation.findNavController(root).navigate(R.id.nav_employees_page);
            } else{
                Toast.makeText(getContext(), R.string.create_orderItem_error, Toast.LENGTH_SHORT).show();
            }
        });

        return root;
    }
}
