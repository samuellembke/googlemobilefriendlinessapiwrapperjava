package com.yoyaba.google.api.wrapper.mobilefriendliness;

import okhttp3.*;
import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class GoogleMobileFriendlinessTest {

    private OkHttpClient httpClient;

    private String API_URL;

    GoogleMobileFriendlinessTest(long timeout, TimeUnit timeUnit, String API_URL) {
        httpClient = new OkHttpClient.Builder().connectTimeout(timeout, timeUnit).writeTimeout(timeout, timeUnit).readTimeout(timeout, timeUnit).build();
        this.API_URL = API_URL;
    }

    @Nullable
    public MobileFriendlinessTestResponse execute(MobileFriendlinessTestRequest request) throws IOException {

        RequestBody formBody = new FormBody.Builder()
                .add("url",request.url)
                .add("requestScreenshot", String.valueOf(request.requestScreenshot))
                .build();


        Request httpRequest = new Request.Builder().url(API_URL+"?key="+request.key).post(formBody).build();

        try(Response response = httpClient.newCall(httpRequest).execute()) {
            if(response.isSuccessful()) {
                MobileFriendlinessTestResponse testResponse = parseHttpResponse(request, response);
                return testResponse;
            }else System.out.println(response.code());
        }
        return null;
    }

    @Nullable
    private MobileFriendlinessTestResponse parseHttpResponse(MobileFriendlinessTestRequest request, Response response) throws IOException {
        JSONObject jsonObject = new JSONObject(response.body().string());
        if(jsonObject.has("testStatus") && jsonObject.has("mobileFriendliness")) {
            MobileFriendlinessTestResponse testResponse = new MobileFriendlinessTestResponse();

            JSONObject testStatusObject = jsonObject.getJSONObject("testStatus");
            if(testStatusObject != null) {
                String testStatusStr = testStatusObject.getString("status");
                TestStatusEnum testStatusEnum = TestStatusEnum.valueOf(testStatusStr);
                TestStatus testStatus = new TestStatus();
                if(testStatusObject.has("details")) {
                    String details = testStatusObject.getString("details");
                    testStatus.details = details;

                }
                testStatus.status = testStatusEnum;
                testResponse.testStatus = testStatus;
            }
            String mobileFriendlinessStr = jsonObject.getString("mobileFriendliness");
            MobileFriendlyTestResult mobileFriendliness = MobileFriendlyTestResult.valueOf(mobileFriendlinessStr);



            testResponse.mobileFriendliness = mobileFriendliness;

            if(jsonObject.has("mobileFriendlyIssues")) {
                JSONArray mobileFriendlyIssuesArrayJson = jsonObject.getJSONArray("mobileFriendlyIssues");
                int size = 0;

                List<String> rules = new ArrayList<>();
                for(int i = 0; i < mobileFriendlyIssuesArrayJson.length(); i++) {
                    if(mobileFriendlyIssuesArrayJson.getJSONObject(i) != null) {
                        JSONObject object = mobileFriendlyIssuesArrayJson.getJSONObject(i);
                        if(object.has("rule")) {
                            String ruleStr = object.getString("rule");
                            rules.add(ruleStr);
                            size++;
                        }
                    }
                }

                int i = 0;
                MobileFriendlyRule[] mobileFriendlyIssues = new MobileFriendlyRule[size];
                for(String ruleStr : rules) {
                    mobileFriendlyIssues[i] = MobileFriendlyRule.valueOf(ruleStr);
                    i++;
                }
                testResponse.mobileFriendlyIssues = mobileFriendlyIssues;

            }

            if(jsonObject.has("resourceIssues")) {

                JSONArray resourceIssuesArrayJson = jsonObject.getJSONArray("resourceIssues");
                int size = 0;
                List<String> urls = new ArrayList<>();
                for(int i = 0; i < resourceIssuesArrayJson.length(); i++) {
                    if(resourceIssuesArrayJson.getJSONObject(i) != null) {
                        JSONObject object = resourceIssuesArrayJson.getJSONObject(i);

                        if(object.has("blockedResource")) {
                            JSONObject blockedResourceObject = object.getJSONObject("blockedResource");
                            if(blockedResourceObject.has("url")) {
                                size++;
                                String url = blockedResourceObject.getString("url");
                                urls.add(url);
                            }
                        }
                    }
                }
                ResourceIssue[] resourceIssues = new ResourceIssue[size];
                int i = 0;
                for(String url : urls) {
                    ResourceIssue resourceIssue = new ResourceIssue();
                    BlockedResource blockedResource = new BlockedResource();
                    blockedResource.url = url;
                    resourceIssue.blockedResource = blockedResource;
                    resourceIssues[i] = resourceIssue;
                    i++;
                }
            }

            if(request.isRequestingScreenshot() && jsonObject.has("screenshot")) {
                JSONObject screenshotObject = jsonObject.getJSONObject("screenshot");
                if(screenshotObject.has("data") && screenshotObject.has("mimeType")) {
                    Image image = new Image();
                    String dataStr = screenshotObject.getString("data");
                    String mimeType = screenshotObject.getString("mimeType");
                    image.dataStr = dataStr;
                    image.mimeType = mimeType;
                    testResponse.screenshot = image;
                }
            }

            return testResponse;
        }
        return null;
    }

    public static class Builder {

        private String api_url = "https://searchconsole.googleapis.com/v1/urlTestingTools/mobileFriendlyTest:run";

        private long timeout = 240;

        private TimeUnit timeUnit = TimeUnit.SECONDS;

        public Builder timeout(long timeout, TimeUnit timeUnit) {
            this.timeout = timeout;
            this.timeUnit = timeUnit;

            return this;
        }

        public Builder api(String url) {
            this.api_url = url;
            return this;
        }

        public GoogleMobileFriendlinessTest build() {
            return new GoogleMobileFriendlinessTest(this.timeout, this.timeUnit, this.api_url);
        }
    }
}

