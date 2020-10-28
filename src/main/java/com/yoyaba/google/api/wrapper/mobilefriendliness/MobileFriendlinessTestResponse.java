package com.yoyaba.google.api.wrapper.mobilefriendliness;


import com.yoyaba.google.api.wrapper.mobilefriendliness.enums.MobileFriendlyRule;
import com.yoyaba.google.api.wrapper.mobilefriendliness.enums.MobileFriendlyTestResult;

import java.io.Serializable;

public class MobileFriendlinessTestResponse implements Serializable {

    protected TestStatus testStatus;

    protected MobileFriendlyTestResult mobileFriendliness;

    protected MobileFriendlyRule[] mobileFriendlyIssues;

    protected ResourceIssue[] resourceIssues;

    protected Image screenshot;

    protected MobileFriendlinessTestResponse() { }

    public TestStatus getTestStatus() {
        return testStatus;
    }

    public MobileFriendlyTestResult getMobileFriendliness() {
        return mobileFriendliness;
    }

    public MobileFriendlyRule[] getMobileFriendlyIssues() {
        return mobileFriendlyIssues;
    }

    public ResourceIssue[] getResourceIssues() {
        return resourceIssues;
    }

    public Image getScreenshot() {
        return screenshot;
    }
}
