package com.yoyaba.google.api.wrapper.mobilefriendliness.enums;

public enum TestStatusEnum {
    TEST_STATUS_UNSPECIFIED, // Internal error when running this test. Please try running the test again.
    COMPLETE, // Inspection has completed without errors.
    INTERNAL_ERROR, // Inspection terminated in an error state. This indicates a problem in Google's infrastructure, not a user error. Please try again later.
    PAGE_UNREACHABLE // Google cannot access the URL because of a user error such as a robots.txt blockage, a 403 or 500 code etc. Please make sure that the URL provided is accessible by Googlebot and is not password protected.
}
