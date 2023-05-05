package com.bav.ordermanagementsystem.service;

import java.io.Serializable;

public interface UserDetails extends Serializable {

    String getLogin();

    String getPassword();

    String getRole();

    Long getId();

    String getFullName();

    String getAdditionalInformation();

    Long getPhone();

    default String getPhoneMask(){
        String phone = getPhone().toString();
        String mask = "+7(";
        for(int i = 0; i < 10; i++){
            if (i < 2 || (i > 2 && i < 5) || i == 6 || i > 7){
                mask += phone.charAt(i);
            }
            else if (i == 2)
                mask += phone.charAt(i) + ")";
            else if (i == 5 || i == 7){
                mask += phone.charAt(i) + "-";
            }
        }
        return mask;
    }
}
