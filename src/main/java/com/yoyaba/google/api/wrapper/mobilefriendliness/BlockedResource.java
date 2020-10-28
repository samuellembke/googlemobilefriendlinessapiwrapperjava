package com.yoyaba.google.api.wrapper.mobilefriendliness;

import java.io.Serializable;

public class BlockedResource implements Serializable {

    protected String url;

    protected BlockedResource() { }

    public String getUrl() {
        return url;
    }
}
