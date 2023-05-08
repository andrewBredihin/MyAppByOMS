package com.bav.myapp.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

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
    public String toString() {
        return "MyService{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyService myService = (MyService) o;
        return Objects.equals(id, myService.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getTitle() {
        return title;
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
