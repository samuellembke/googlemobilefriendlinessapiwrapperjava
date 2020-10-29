package com.yoyaba.google.api.wrapper.mobilefriendliness;


import com.yoyaba.google.api.wrapper.mobilefriendliness.enums.TestStatusEnum;

public class TestStatus {

    protected TestStatusEnum status;

    protected String details;

    protected TestStatus() {
        this.details = "";
    }

    public TestStatusEnum getStatus() {
        return status;
    }

    public String getDetails() {
        return details;
    }
}
