package com.bav.myapp.adapter.orderItems;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bav.myapp.R;
import com.bav.myapp.adapter.OrderItemRecyclerViewHolderInterface;
import com.bav.myapp.entity.MyService;
import com.bav.myapp.entity.OrderItem;
import com.bav.myapp.ui.createOrder.OrderItemsStore;

public class OrderItemsViewHolder extends RecyclerView.ViewHolder implements OrderItemRecyclerViewHolderInterface {
    final ImageButton add_button;
    final TextView title, price, description;
    private View view;

    private OrderItemsStore store;

    public OrderItemsViewHolder(View view) {
        super(view);
        this.view = view;
        add_button = view.findViewById(R.id.btn_add);
        title = view.findViewById(R.id.item_title);
        price = view.findViewById(R.id.item_price);
        description = view.findViewById(R.id.item_description);
        store = OrderItemsStore.getInstance(view.getContext());
    }

    @Override
    public void setOrderItem(OrderItem item) {
        title.setText(item.getTitle());
        price.setText(String.valueOf(item.getPrice()));
        description.setText(item.getDescription());

        if (store.checkItem(item))
            add_button.setImageResource(R.mipmap.ic_delete_x_foreground);
        else
            add_button.setImageResource(R.mipmap.ic_add_foreground);
        add_button.setOnClickListener(v -> {
            if (store.checkItem(item)){
                store.deleteItem(item);
                add_button.setImageResource(R.mipmap.ic_add_foreground);
            }
            else{
                store.addItem(item);
                add_button.setImageResource(R.mipmap.ic_delete_x_foreground);
            }
        });
    }
}
