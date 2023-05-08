package com.bav.myapp.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.bav.myapp.entity.MyService;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface MyServiceDao {

    @Query("SELECT * FROM myservice")
    Flowable<List<MyService>> getAll();

    @Query("SELECT * FROM myservice WHERE id = :serviceId")
    Flowable<List<MyService>> getById(Long serviceId);

    @Query("SELECT a.* FROM myservice AS a " +
            "INNER JOIN order_and_order_items AS b ON a.id = b.order_item_id " +
            "WHERE b.order_id = :orderId")
    Flowable<List<MyService>> getByOrderId(Long orderId);

    @Insert
    void insert(MyService order);

    @Delete
    void delete(MyService order);

    @Update
    void update(MyService order);
}
