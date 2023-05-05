package com.bav.ordermanagementsystem.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

//Прописать @ForeignKey для своего OrderItem
@Entity(tableName = "order_and_order_items", foreignKeys = {
        @ForeignKey(entity = Order.class, parentColumns = "id", childColumns = "order_id")
})
public class OrderAndOrderItems {

    @PrimaryKey(autoGenerate = true)
    private Long id;

    @ColumnInfo(index = true)
    private Long order_id;

    @ColumnInfo(index = true)
    private Long order_item_id;

    public OrderAndOrderItems(){}

    public OrderAndOrderItems(Long orderId, Long orderItemId){
        this.order_id = orderId;
        this.order_item_id = orderItemId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Long order_id) {
        this.order_id = order_id;
    }

    public Long getOrder_item_id() {
        return order_item_id;
    }

    public void setOrder_item_id(Long order_item_id) {
        this.order_item_id = order_item_id;
    }
}
