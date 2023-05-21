package com.bav.myapp.ui.createOrderItem;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bav.myapp.R;
import com.bav.myapp.databinding.FragmentOrderItemFieldsBinding;
import com.bav.myapp.entity.MyService;
import com.bav.myapp.entity.OrderItem;

public class OrderItemFieldsFragment extends Fragment {

    private FragmentOrderItemFieldsBinding binding;

    EditText title, description, price;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentOrderItemFieldsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        title = binding.orderItemTitle;
        description = binding.orderItemDescription;
        price = binding.orderItemPrice;

        return root;
    }

    public OrderItem getOrderItem(){
        if (title.getText().toString().length() == 0){
            Toast.makeText(getContext(), R.string.error_order_item_title, Toast.LENGTH_SHORT).show();
            return null;
        } else if (description.getText().toString().length() < 10){
            Toast.makeText(getContext(), R.string.error_order_item_description, Toast.LENGTH_SHORT).show();
            return null;
        } else if (price.getText().toString().length() == 0){
            Toast.makeText(getContext(), R.string.error_order_item_price, Toast.LENGTH_SHORT).show();
            return null;
        }

        MyService item = new MyService();
        item.setTitle(title.getText().toString().trim());
        item.setDescription(description.getText().toString());
        item.setPrice(Double.parseDouble(price.getText().toString()));

        return item;
    }
}
