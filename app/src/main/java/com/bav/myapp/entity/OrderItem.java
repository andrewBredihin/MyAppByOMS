package com.bav.myapp.entity;

import java.io.Serializable;

public interface OrderItem extends Serializable {

    double getPrice();

    Long getId();

    String getTitle();
}
