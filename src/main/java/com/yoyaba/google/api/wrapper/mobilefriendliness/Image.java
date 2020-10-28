package com.yoyaba.google.api.wrapper.mobilefriendliness;

import org.jetbrains.annotations.Nullable;

import java.util.Base64;

public class Image {

    protected byte[] data;

    protected String dataStr;

    protected String mimeType;

    public String getMimeType() {
        return mimeType;
    }

    @Nullable
    public byte[] decode() {
        this.data = Base64.getDecoder().decode(dataStr);
        return data;
    }

    public String getFileEnding() {
        if(mimeType.contains("png")) {
            return ".png";
        }else if(mimeType.contains("jpeg") || mimeType.contains("jpg")) {
            return ".jpeg";
        }
        return "";
    }
}
