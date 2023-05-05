package com.bav.ordermanagementsystem.entity;

import java.io.Serializable;

public interface OrderItem extends Serializable {

    double getPrice();

    Long getId();

    String getTitle();
}
