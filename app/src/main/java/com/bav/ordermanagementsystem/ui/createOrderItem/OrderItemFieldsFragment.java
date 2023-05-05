package com.bav.ordermanagementsystem.ui.createOrderItem;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bav.ordermanagementsystem.databinding.FragmentOrderItemFieldsBinding;
import com.bav.ordermanagementsystem.entity.OrderItem;

public class OrderItemFieldsFragment extends Fragment {

    private FragmentOrderItemFieldsBinding binding;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentOrderItemFieldsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        return root;
    }

    public OrderItem getOrderItem(){
        //Прописать создание объекта с данными из fragment_order_item_fields
        return null;
    }
}
