package com.bav.ordermanagementsystem.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.bav.ordermanagementsystem.entity.Employee;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Maybe;

@Dao
public interface EmployeeDao {

    @Query("SELECT * FROM employee")
    Flowable<List<Employee>> getAll();

    @Query("SELECT * FROM employee WHERE id = :employeeId")
    Maybe<Employee> getById(Long employeeId);

    @Query("SELECT * FROM employee WHERE login = :login AND password = :password")
    Maybe<Employee> getByLoginAndPassword(String login, String password);

    @Query("SELECT * FROM employee WHERE login = :login")
    Maybe<Employee> getByLogin(String login);

    @Insert
    void insert(Employee employee);

    @Delete
    void delete(Employee employee);

    @Update
    void update(Employee employee);
}
