package com.yoyaba.google.api.wrapper.mobilefriendliness;

import java.io.Serializable;

public class ResourceIssue implements Serializable {

    protected BlockedResource blockedResource;

    protected ResourceIssue() { }

    public BlockedResource getBlockedResource() {
        return blockedResource;
    }
}
