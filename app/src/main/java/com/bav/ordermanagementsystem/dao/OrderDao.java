package com.bav.ordermanagementsystem.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.bav.ordermanagementsystem.entity.Order;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface OrderDao {

    @Query("SELECT * FROM `order`")
    Flowable<List<Order>> getAll();

    @Query("SELECT * FROM `order` WHERE id = :id")
    Flowable<Order> getById(Long id);

    @Query("SELECT * FROM `order` WHERE client_id = :clientId")
    Flowable<List<Order>> getByClientId(Long clientId);

    @Query("SELECT * FROM `order` WHERE client_id = :clientId AND status = :status")
    Flowable<List<Order>> getByClientIdAndStatus(Long clientId, String status);

    @Query("SELECT * FROM `order` WHERE employee_id = :employeeId")
    Flowable<List<Order>> getByEmployeeId(Long employeeId);

    @Query("SELECT * FROM `order` WHERE status = :status")
    Flowable<List<Order>> getByStatusId(String status);

    @Query("SELECT * FROM `order` WHERE employee_id = :employeeId AND status = :status")
    Flowable<List<Order>> getByEmployeeIdAndStatusId(Long employeeId, String status);


    @Insert
    void insert(Order order);

    @Delete
    void delete(Order order);

    @Update
    void update(Order order);
}
