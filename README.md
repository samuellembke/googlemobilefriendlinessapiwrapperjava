# Mobile Friendliness API Wrapper JAVA

## Installation

### Using Maven

Add the following to your pom.xml:
```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```
Then add the following to your dependencies:
```xml
<dependency>
    <groupId>com.github.samuellembke</groupId>
    <artifactId>googlemobilefriendlinessapiwrapperjava</artifactId>
    <version>1.1.0-RELEASE</version>
</dependency>
```

## Quickstart
You need to create a GoogleMobileFriendlinessTest Instance:
```java
GoogleMobileFriendlinessTest mobileFriendlinessTest = new GoogleMobileFriendlinessTest
    .Builder()
    .build();
```
You can specify the network timeout for the API request and a custom api server url
```java
GoogleMobileFriendlinessTest mobileFriendlinessTest = new GoogleMobileFriendlinessTest
    .Builder()
    .timeoout(120, TimeUnit.SECONDS)
    .api("https://api.example.com")
    .build();
```

### MobileFriendlinessTestRequest
You now need to create a MobileFriendlinessTestRequest
```java
MobileFriendlinessTestRequest request = new MobileFriendlinessTestRequest.Builder()
    .key("API-KEY")
    .url("https://example.page.to.test.com")
    .screenshot(true)
    .build();
```
### Executing
Now you can execute the request and retrieve a MobileFriendlinessTestResponse
```java
MobileFriendlinessTestResponse response = mobileFriendlinessTest.execute(request);
```

### Retrieve screenshot
```java
Image screenshot = response.getScreenshot();

Files.write(Path.of("screenshots/screenshot_01."+screenshot.getFileEnding()),
     screenshot.decode());
```

### Retrieve TestStatus
```java
TestStatus testStatus = response.getTestStatus();

System.out.println(testStatus.getStatus());
System.out.println(testStatus.getDetails());
```

### Retrieve MobileFriendliness
```java
System.out.println(response.getMobileFriendliness()); 
// REPORTS: MOBILE_FRIENDLY, NOT_MOBILE_FRIENDLY, MOBILE_FRIENDLY_TEST_RESULT_UNSPECIFIED
```

### Retrieve MobileFriendlyIssues
```java
MobileFriendlyRule[] issues = response.getMobileFriendlyIssues();

for(MobileFriendlyRule rule : issues) {
    System.out.println(rule);
}
/*
Rules:
        MOBILE_FRIENDLY_RULE_UNSPECIFIED, // Unknown rule. Sorry, we don't have any description for the rule that was broken.
        USES_INCOMPATIBLE_PLUGINS, //  	Plugins incompatible with mobile devices are being used.
        CONFIGURE_VIEWPORT, // Viewport is not specified using the meta viewport tag.
        FIXED_WIDTH_VIEWPORT, // Viewport defined to a fixed width.
        SIZE_CONTENT_TO_VIEWPORT, // Content not sized to viewport.
        USE_LEGIBLE_FONT_SIZES, // Font size is too small for easy reading on a small screen.
        TAP_TARGETS_TOO_CLOSE, // Touch elements are too close to each other.

*/
```

### Retrieve ResourceIssues
```java
ResourceIssue[] issues = response.getMobileFriendlyIssues();

for(ResourceIssue issue : issues) {
    System.out.println(issue.getBlockedResource().getUrl());
}
/*
Prints out blocked recourses.

*/
```