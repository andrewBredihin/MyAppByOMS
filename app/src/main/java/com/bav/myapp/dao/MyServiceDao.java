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

    @Query("SELECT * FROM MyService")
    Flowable<List<MyService>> getAll();

    @Query("SELECT * FROM MyService WHERE id = :serviceId")
    Flowable<List<MyService>> getById(Long serviceId);

    @Insert
    void insert(MyService order);

    @Delete
    void delete(MyService order);

    @Update
    void update(MyService order);
}
