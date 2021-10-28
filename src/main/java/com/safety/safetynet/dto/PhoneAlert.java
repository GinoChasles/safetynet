package com.safety.safetynet.dto;

import java.util.List;

public class PhoneAlert {
    private List<String> phoneList;

    public PhoneAlert(List<String> phoneList) {
        this.phoneList = phoneList;
    }

    public PhoneAlert() {
    }

    public List<String> getPhoneList() {
        return phoneList;
    }

    public void setPhoneList(List<String> phoneList) {
        this.phoneList = phoneList;
    }

    @Override
    public String toString() {
        return "PhoneAlert{" +
                "phoneList=" + phoneList +
                '}';
    }
}
