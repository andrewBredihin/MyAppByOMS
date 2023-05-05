package com.bav.ordermanagementsystem.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.bav.ordermanagementsystem.dao.ClientDao;
import com.bav.ordermanagementsystem.dao.EmployeeDao;
import com.bav.ordermanagementsystem.dao.OrderAndOrderItemsDao;
import com.bav.ordermanagementsystem.dao.OrderDao;
import com.bav.ordermanagementsystem.entity.Client;
import com.bav.ordermanagementsystem.entity.Employee;
import com.bav.ordermanagementsystem.entity.Order;
import com.bav.ordermanagementsystem.entity.OrderAndOrderItems;

@Database(entities = {
        Employee.class,
        Client.class,
        Order.class,
        OrderAndOrderItems.class
}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    public abstract EmployeeDao employeeDao();
    public abstract ClientDao clientDao();
    public abstract OrderDao orderDao();
    public abstract OrderAndOrderItemsDao orderAndOrderItemsDao();
}
