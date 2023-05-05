package com.bav.myapp.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class MyService implements OrderItem{

    @PrimaryKey(autoGenerate = true)
    private Long id;

    private String title;

    private String description;

    private double price;

    public MyService() {}

    public MyService(String title, String description, double price){
        this.title = title;
        this.description = description;
        this.price = price;
    }

    @Override
    public double getPrice() {
        return 0;
    }

    @Override
    public Long getId() {
        return null;
    }

    @Override
    public String getTitle() {
        return null;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
