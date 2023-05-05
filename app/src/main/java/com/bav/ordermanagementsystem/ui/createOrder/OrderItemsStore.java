package com.bav.ordermanagementsystem.ui.createOrder;

import android.content.Context;

import com.bav.ordermanagementsystem.entity.OrderItem;

import java.util.ArrayList;
import java.util.List;

public class OrderItemsStore {

    private Context mCtx;
    private static OrderItemsStore mInstance;
    private List<OrderItem> items;

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

    public void addItem(OrderItem item) {
        items.add(item);
    }
}
