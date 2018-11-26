# swagger-java-client

## Requirements

Building the API client library requires [Maven](https://maven.apache.org/) to be installed.

## Installation

To install the API client library to your local Maven repository, simply execute:

```shell
mvn install
```

To deploy it to a remote Maven repository instead, configure the settings of the repository and execute:

```shell
mvn deploy
```

Refer to the [official documentation](https://maven.apache.org/plugins/maven-deploy-plugin/usage.html) for more information.

### Maven users

Add this dependency to your project's POM:

```xml
<dependency>
    <groupId>io.swagger</groupId>
    <artifactId>swagger-java-client</artifactId>
    <version>1.0.0</version>
    <scope>compile</scope>
</dependency>
```

### Gradle users

Add this dependency to your project's build file:

```groovy
compile "io.swagger:swagger-java-client:1.0.0"
```

### Others

At first generate the JAR by executing:

    mvn package

Then manually install the following JARs:

* target/swagger-java-client-1.0.0.jar
* target/lib/*.jar

## Getting Started

Please follow the [installation](#installation) instruction and execute the following Java code:

```java

import io.swagger.client.*;
import io.swagger.client.auth.*;
import io.swagger.client.model.*;
import ResultsApi;

import java.io.File;
import java.util.*;

public class ResultsApiExample {

    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        
        // Configure API key authorization: NeoloadAuthorizer
        ApiKeyAuth NeoloadAuthorizer = (ApiKeyAuth) defaultClient.getAuthentication("NeoloadAuthorizer");
        NeoloadAuthorizer.setApiKey("YOUR API KEY");
        // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
        //NeoloadAuthorizer.setApiKeyPrefix("Token");
        
        

        ResultsApi apiInstance = new ResultsApi();
        
        String testId = "testId_example"; // String | Unique identifier representing a specific test.
        
        try {
            apiInstance.deleteTest(testId);
        } catch (ApiException e) {
            System.err.println("Exception when calling ResultsApi#deleteTest");
            e.printStackTrace();
        }
    }
}

```

## Documentation for API Endpoints

All URIs are relative to */v1*

Class | Method | HTTP request | Description
------------ | ------------- | ------------- | -------------
*ResultsApi* | [**deleteTest**](docs/ResultsApi.md#deleteTest) | **DELETE** /tests/{testId} | Deletes a test result
*ResultsApi* | [**getTest**](docs/ResultsApi.md#getTest) | **GET** /tests/{testId} | Test result description
*ResultsApi* | [**getTestElements**](docs/ResultsApi.md#getTestElements) | **GET** /tests/{testId}/elements | Test elements
*ResultsApi* | [**getTestElementsPoints**](docs/ResultsApi.md#getTestElementsPoints) | **GET** /tests/{testId}/elements/{elementId}/points | Tests elements points since the beginning of the test
*ResultsApi* | [**getTestElementsSla**](docs/ResultsApi.md#getTestElementsSla) | **GET** /tests/{testId}/elements/{elementId}/sla | Test elements SLA status since the beginning of the test
*ResultsApi* | [**getTestElementsValues**](docs/ResultsApi.md#getTestElementsValues) | **GET** /tests/{testId}/elements/{elementId}/values | Test elements values
*ResultsApi* | [**getTestMonitors**](docs/ResultsApi.md#getTestMonitors) | **GET** /tests/{testId}/monitors | Test monitors
*ResultsApi* | [**getTestMonitorsPoints**](docs/ResultsApi.md#getTestMonitorsPoints) | **GET** /tests/{testId}/monitors/{counterId}/points | Tests monitors points
*ResultsApi* | [**getTestMonitorsValues**](docs/ResultsApi.md#getTestMonitorsValues) | **GET** /tests/{testId}/monitors/{counterId}/values | Tests monitors values
*ResultsApi* | [**getTestStatistics**](docs/ResultsApi.md#getTestStatistics) | **GET** /tests/{testId}/statistics | Test result main statistics
*ResultsApi* | [**getTests**](docs/ResultsApi.md#getTests) | **GET** /tests | Lists test results
*ResultsApi* | [**postTestMonitors**](docs/ResultsApi.md#postTestMonitors) | **POST** /tests/{testId}/monitors | Create custom monitors
*ResultsApi* | [**updateTest**](docs/ResultsApi.md#updateTest) | **PUT** /tests/{testId} | Update a test result
*RuntimeApi* | [**getTestsRun**](docs/RuntimeApi.md#getTestsRun) | **POST** /projects/{projectId}/run | Runs a test
*RuntimeApi* | [**postUploadProject**](docs/RuntimeApi.md#postUploadProject) | **POST** /projects | Uploads a NeoLoad project zip file


## Documentation for Models

 - [ArrayOfElementDefinition](docs/ArrayOfElementDefinition.md)
 - [ArrayOfTestDefinition](docs/ArrayOfTestDefinition.md)
 - [Body](docs/Body.md)
 - [CounterDefinition](docs/CounterDefinition.md)
 - [CounterValues](docs/CounterValues.md)
 - [CustomMonitor](docs/CustomMonitor.md)
 - [CustomMonitorValues](docs/CustomMonitorValues.md)
 - [CustomMonitorValuesInner](docs/CustomMonitorValuesInner.md)
 - [ElementDefinition](docs/ElementDefinition.md)
 - [ElementValues](docs/ElementValues.md)
 - [Empty](docs/Empty.md)
 - [Error](docs/Error.md)
 - [MonitorPostRequest](docs/MonitorPostRequest.md)
 - [Point](docs/Point.md)
 - [Points](docs/Points.md)
 - [ProjectDefinition](docs/ProjectDefinition.md)
 - [RateLimitError](docs/RateLimitError.md)
 - [RunTestDefinition](docs/RunTestDefinition.md)
 - [ScenarioDefinition](docs/ScenarioDefinition.md)
 - [Sla](docs/Sla.md)
 - [TestDefinition](docs/TestDefinition.md)
 - [TestStatistics](docs/TestStatistics.md)
 - [TestUpdateRequest](docs/TestUpdateRequest.md)


## Documentation for Authorization

Authentication schemes defined for the API:
### NeoloadAuthorizer

- **Type**: API key
- **API key parameter name**: accountToken
- **Location**: HTTP header






## Recommendation

It's recommended to create an instance of `ApiClient` per thread in a multithreaded environment to avoid any potential issues.

## Author



