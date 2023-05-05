package com.bav.ordermanagementsystem.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.bav.ordermanagementsystem.entity.OrderAndOrderItems;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface OrderAndOrderItemsDao {

    @Query("SELECT * FROM `order`")
    Flowable<List<OrderAndOrderItems>> getAll();

    @Query("SELECT * FROM `order_and_order_items` WHERE order_id = :orderId")
    Flowable<List<OrderAndOrderItems>> getByOrderId(Long orderId);

    @Query("SELECT * FROM `order` WHERE id = :id")
    Flowable<OrderAndOrderItems> getById(Long id);

    @Query("DELETE FROM `order_and_order_items` WHERE order_id = :orderId")
    void deleteByOrderId(Long orderId);

    @Insert
    void insert(OrderAndOrderItems order);

    @Insert
    void insertAll(List<OrderAndOrderItems> orders);

    @Delete
    void delete(OrderAndOrderItems order);

    @Delete
    void deleteAll(List<OrderAndOrderItems> orders);

    @Update
    void update(OrderAndOrderItems order);
}
