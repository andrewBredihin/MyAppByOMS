package com.bav.myapp.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.bav.myapp.dao.ClientDao;
import com.bav.myapp.dao.EmployeeDao;
import com.bav.myapp.dao.OrderAndOrderItemsDao;
import com.bav.myapp.dao.OrderDao;
import com.bav.myapp.entity.Client;
import com.bav.myapp.entity.Employee;
import com.bav.myapp.entity.Order;
import com.bav.myapp.entity.OrderAndOrderItems;

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
