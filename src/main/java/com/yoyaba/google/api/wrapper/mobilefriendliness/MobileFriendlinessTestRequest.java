package com.yoyaba.google.api.wrapper.mobilefriendliness;


import com.yoyaba.google.api.wrapper.mobilefriendliness.exceptions.GoogleMobileFriendlinessAPIKeyNotSpecifiedException;
import com.yoyaba.google.api.wrapper.mobilefriendliness.exceptions.GoogleMobileFriendlinessException;
import com.yoyaba.google.api.wrapper.mobilefriendliness.exceptions.GoogleMobileFriendlinessURLNotSpecifiedException;

public final class MobileFriendlinessTestRequest {

    protected String url;

    protected boolean requestScreenshot = false;

    protected String key;

    MobileFriendlinessTestRequest() {
    }

    MobileFriendlinessTestRequest(String url, boolean requestScreenshot, String key) {
        this.url = url;
        this.requestScreenshot = requestScreenshot;
        this.key = key;
    }

    public String getUrl() {
        return url;
    }

    public boolean isRequestingScreenshot() {
        return requestScreenshot;
    }

    public String getKey() {
        return key;
    }

    public static class Builder {

        private MobileFriendlinessTestRequest request;

        public Builder() {
            this.request = new MobileFriendlinessTestRequest();
        }

        public Builder url(String url) {
            this.request.url = url;
            return this;
        }

        public Builder key(String key) {
            this.request.key = key;
            return this;
        }

        public Builder screenshot(boolean screenshot) {
            this.request.requestScreenshot = screenshot;
            return this;
        }

        public MobileFriendlinessTestRequest build() throws GoogleMobileFriendlinessException {
            if (this.request.url == null) {
                throw new GoogleMobileFriendlinessURLNotSpecifiedException();
            }
            if (this.request.key == null) {
                throw new GoogleMobileFriendlinessAPIKeyNotSpecifiedException();
            }
            return this.request;
        }
    }
}
