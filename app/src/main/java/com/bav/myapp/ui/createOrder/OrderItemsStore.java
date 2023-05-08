package com.bav.myapp.ui.createOrder;

import android.content.Context;

import com.bav.myapp.entity.OrderItem;

import java.util.ArrayList;
import java.util.List;

public class OrderItemsStore {

    private final Context mCtx;
    private static OrderItemsStore mInstance;
    private final List<OrderItem> items;

    private OrderItemsStore(Context mCtx) {
        this.mCtx = mCtx;
        items = new ArrayList<>();
    }

    public static synchronized OrderItemsStore getInstance(Context mCtx) {
        if (mInstance == null) {
            mInstance = new OrderItemsStore(mCtx);
        }
        return mInstance;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public int getSize(){
        return items.size();
    }

    public void addItem(OrderItem item) {
        items.add(item);
    }

    public void deleteItem(OrderItem item){
        items.remove(item);
    }

    public boolean checkItem(OrderItem item){
        return items.contains(item);
    }
}
