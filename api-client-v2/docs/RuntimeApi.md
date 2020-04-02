# RuntimeApi

All URIs are relative to */v2*

Method | HTTP request | Description
------------- | ------------- | -------------
[**deleteTest**](RuntimeApi.md#deleteTest) | **DELETE** /tests/{testId} | Delete a test
[**getTestList**](RuntimeApi.md#getTestList) | **GET** /tests | Get a test list
[**getTestSettings**](RuntimeApi.md#getTestSettings) | **GET** /tests/{testId} | Get a test
[**getTestsRun**](RuntimeApi.md#getTestsRun) | **POST** /tests/{testId}/start | Starts a test
[**patchTest**](RuntimeApi.md#patchTest) | **PATCH** /tests/{testId} | Partially update a test
[**postCreateTest**](RuntimeApi.md#postCreateTest) | **POST** /tests | Create a new test
[**postUploadProject**](RuntimeApi.md#postUploadProject) | **POST** /tests/{testId}/project | Uploads a NeoLoad project zip file or a standalone as code file
[**putTest**](RuntimeApi.md#putTest) | **PUT** /tests/{testId} | Fully update a test

<a name="deleteTest"></a>
# **deleteTest**
> TestSettingsDefinition deleteTest(testId, deleteResults)

Delete a test

Delete the test with the specified id

### Example
```java
// Import classes:
//import ApiClient;
//import ApiException;
//import Configuration;
//import io.swagger.client.auth.*;
//import RuntimeApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: NeoloadAuthorizer
ApiKeyAuth NeoloadAuthorizer = (ApiKeyAuth) defaultClient.getAuthentication("NeoloadAuthorizer");
NeoloadAuthorizer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//NeoloadAuthorizer.setApiKeyPrefix("Token");

RuntimeApi apiInstance = new RuntimeApi();
String testId = "testId_example"; // String | Unique identifier representing a specific test.
String deleteResults = "deleteResults_example"; // String | Delete also test results linked to the test. Default is true.
try {
    TestSettingsDefinition result = apiInstance.deleteTest(testId, deleteResults);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling RuntimeApi#deleteTest");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **testId** | **String**| Unique identifier representing a specific test. |
 **deleteResults** | **String**| Delete also test results linked to the test. Default is true. | [optional]

### Return type

[**TestSettingsDefinition**](TestSettingsDefinition.md)

### Authorization

[NeoloadAuthorizer](../README.md#NeoloadAuthorizer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="getTestList"></a>
# **getTestList**
> TestSettingsDefinitionList getTestList()

Get a test list

Get the test list

### Example
```java
// Import classes:
//import ApiClient;
//import ApiException;
//import Configuration;
//import io.swagger.client.auth.*;
//import RuntimeApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: NeoloadAuthorizer
ApiKeyAuth NeoloadAuthorizer = (ApiKeyAuth) defaultClient.getAuthentication("NeoloadAuthorizer");
NeoloadAuthorizer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//NeoloadAuthorizer.setApiKeyPrefix("Token");

RuntimeApi apiInstance = new RuntimeApi();
try {
    TestSettingsDefinitionList result = apiInstance.getTestList();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling RuntimeApi#getTestList");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**TestSettingsDefinitionList**](TestSettingsDefinitionList.md)

### Authorization

[NeoloadAuthorizer](../README.md#NeoloadAuthorizer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="getTestSettings"></a>
# **getTestSettings**
> TestSettingsDefinition getTestSettings(testId)

Get a test

Get the test

### Example
```java
// Import classes:
//import ApiClient;
//import ApiException;
//import Configuration;
//import io.swagger.client.auth.*;
//import RuntimeApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: NeoloadAuthorizer
ApiKeyAuth NeoloadAuthorizer = (ApiKeyAuth) defaultClient.getAuthentication("NeoloadAuthorizer");
NeoloadAuthorizer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//NeoloadAuthorizer.setApiKeyPrefix("Token");

RuntimeApi apiInstance = new RuntimeApi();
String testId = "testId_example"; // String | Unique identifier representing a specific test.
try {
    TestSettingsDefinition result = apiInstance.getTestSettings(testId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling RuntimeApi#getTestSettings");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **testId** | **String**| Unique identifier representing a specific test. |

### Return type

[**TestSettingsDefinition**](TestSettingsDefinition.md)

### Authorization

[NeoloadAuthorizer](../README.md#NeoloadAuthorizer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="getTestsRun"></a>
# **getTestsRun**
> RunTestDefinition getTestsRun(testId, testResultName, testResultDescription, asCode, reservationId, reservationDuration, reservationWebVUs, reservationSAPVUs, reservationCitrixVUs, publishTestResult)

Starts a test

Starts a test of the Account according to the method parameters.

### Example
```java
// Import classes:
//import ApiClient;
//import ApiException;
//import Configuration;
//import io.swagger.client.auth.*;
//import RuntimeApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: NeoloadAuthorizer
ApiKeyAuth NeoloadAuthorizer = (ApiKeyAuth) defaultClient.getAuthentication("NeoloadAuthorizer");
NeoloadAuthorizer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//NeoloadAuthorizer.setApiKeyPrefix("Token");

RuntimeApi apiInstance = new RuntimeApi();
String testId = "testId_example"; // String | Unique identifier representing a specific test.
String testResultName = "testResultName_example"; // String | The name of the test result
String testResultDescription = "testResultDescription_example"; // String | The description of the test result
String asCode = "asCode_example"; // String | The comma-separated as-code files to use for the test. Those files must be part of the uploaded project.
String reservationId = "reservationId_example"; // String | The reservation identifier to use for the test that can be retrieved from the NeoLoad Web reservation calendar URL. If the reservation mode is enabled and \"reservationId\" value is defined, \"reservationDuration\", \"reservationWebVUs\" and \"reservationSAPVUs\" values will be ignored, otherwise if the reservation mode is disabled the value will be ignored.
Long reservationDuration = 789L; // Long | The duration of the reservation for the test. If the reservation mode is enabled, this value or \"reservationDuration\", \"reservationWebVUs\", \"reservationSAPVUs\" must be defined, otherwise if the reservation mode is disabled the value will be ignored. The value (in seconds) is optional when the reservation mode is enabled and ignored when reservationId value is defined or if the reservation mode is disabled. The default value is the selected scenario duration + 1200 seconds (20 minutes). All reserved resources will be released when the test ends.
Integer reservationWebVUs = 56; // Integer | The number of Web Virtual Users to be reserved for the test. The value is optional when the reservation mode is enabled and ignored when \"reservationId\" value is defined or if the reservation mode is disabled.
Integer reservationSAPVUs = 56; // Integer | The number of SAP Virtual Users to be reserved for the test. The value is optional when the reservation mode is enabled and ignored when \"reservationId\" value is defined or if the reservation mode is disabled.
Integer reservationCitrixVUs = 56; // Integer | The number of Citrix Virtual Users to be reserved for the test. The value is optional when the reservation mode is enabled and ignored when \"reservationId\" value is defined or if the reservation mode is disabled.
Boolean publishTestResult = true; // Boolean | When \"true\" and the project is a collaborative project (other than git) then the test result is published onto the server. If empty, the default value is \"false\".
try {
    RunTestDefinition result = apiInstance.getTestsRun(testId, testResultName, testResultDescription, asCode, reservationId, reservationDuration, reservationWebVUs, reservationSAPVUs, reservationCitrixVUs, publishTestResult);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling RuntimeApi#getTestsRun");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **testId** | **String**| Unique identifier representing a specific test. |
 **testResultName** | **String**| The name of the test result |
 **testResultDescription** | **String**| The description of the test result | [optional]
 **asCode** | **String**| The comma-separated as-code files to use for the test. Those files must be part of the uploaded project. | [optional]
 **reservationId** | **String**| The reservation identifier to use for the test that can be retrieved from the NeoLoad Web reservation calendar URL. If the reservation mode is enabled and \&quot;reservationId\&quot; value is defined, \&quot;reservationDuration\&quot;, \&quot;reservationWebVUs\&quot; and \&quot;reservationSAPVUs\&quot; values will be ignored, otherwise if the reservation mode is disabled the value will be ignored. | [optional]
 **reservationDuration** | **Long**| The duration of the reservation for the test. If the reservation mode is enabled, this value or \&quot;reservationDuration\&quot;, \&quot;reservationWebVUs\&quot;, \&quot;reservationSAPVUs\&quot; must be defined, otherwise if the reservation mode is disabled the value will be ignored. The value (in seconds) is optional when the reservation mode is enabled and ignored when reservationId value is defined or if the reservation mode is disabled. The default value is the selected scenario duration + 1200 seconds (20 minutes). All reserved resources will be released when the test ends. | [optional]
 **reservationWebVUs** | **Integer**| The number of Web Virtual Users to be reserved for the test. The value is optional when the reservation mode is enabled and ignored when \&quot;reservationId\&quot; value is defined or if the reservation mode is disabled. | [optional]
 **reservationSAPVUs** | **Integer**| The number of SAP Virtual Users to be reserved for the test. The value is optional when the reservation mode is enabled and ignored when \&quot;reservationId\&quot; value is defined or if the reservation mode is disabled. | [optional]
 **reservationCitrixVUs** | **Integer**| The number of Citrix Virtual Users to be reserved for the test. The value is optional when the reservation mode is enabled and ignored when \&quot;reservationId\&quot; value is defined or if the reservation mode is disabled. | [optional]
 **publishTestResult** | **Boolean**| When \&quot;true\&quot; and the project is a collaborative project (other than git) then the test result is published onto the server. If empty, the default value is \&quot;false\&quot;. | [optional]

### Return type

[**RunTestDefinition**](RunTestDefinition.md)

### Authorization

[NeoloadAuthorizer](../README.md#NeoloadAuthorizer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="patchTest"></a>
# **patchTest**
> TestSettingsDefinition patchTest(testId, body)

Partially update a test

Update only the specified fields of the test

### Example
```java
// Import classes:
//import ApiClient;
//import ApiException;
//import Configuration;
//import io.swagger.client.auth.*;
//import RuntimeApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: NeoloadAuthorizer
ApiKeyAuth NeoloadAuthorizer = (ApiKeyAuth) defaultClient.getAuthentication("NeoloadAuthorizer");
NeoloadAuthorizer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//NeoloadAuthorizer.setApiKeyPrefix("Token");

RuntimeApi apiInstance = new RuntimeApi();
String testId = "testId_example"; // String | Unique identifier representing a specific test.
TestSettingsUpdate body = new TestSettingsUpdate(); // TestSettingsUpdate | The fields to update. No field is required, only those supplied will be updated.
try {
    TestSettingsDefinition result = apiInstance.patchTest(testId, body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling RuntimeApi#patchTest");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **testId** | **String**| Unique identifier representing a specific test. |
 **body** | [**TestSettingsUpdate**](TestSettingsUpdate.md)| The fields to update. No field is required, only those supplied will be updated. | [optional]

### Return type

[**TestSettingsDefinition**](TestSettingsDefinition.md)

### Authorization

[NeoloadAuthorizer](../README.md#NeoloadAuthorizer)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="postCreateTest"></a>
# **postCreateTest**
> TestSettingsCreated postCreateTest(body)

Create a new test

Create a new test with the specified name

### Example
```java
// Import classes:
//import ApiClient;
//import ApiException;
//import Configuration;
//import io.swagger.client.auth.*;
//import RuntimeApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: NeoloadAuthorizer
ApiKeyAuth NeoloadAuthorizer = (ApiKeyAuth) defaultClient.getAuthentication("NeoloadAuthorizer");
NeoloadAuthorizer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//NeoloadAuthorizer.setApiKeyPrefix("Token");

RuntimeApi apiInstance = new RuntimeApi();
TestSettingsCreate body = new TestSettingsCreate(); // TestSettingsCreate | 
try {
    TestSettingsCreated result = apiInstance.postCreateTest(body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling RuntimeApi#postCreateTest");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**TestSettingsCreate**](TestSettingsCreate.md)|  |

### Return type

[**TestSettingsCreated**](TestSettingsCreated.md)

### Authorization

[NeoloadAuthorizer](../README.md#NeoloadAuthorizer)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="postUploadProject"></a>
# **postUploadProject**
> ProjectDefinition postUploadProject(file, testId)

Uploads a NeoLoad project zip file or a standalone as code file

Uploads a NeoLoad project of the account corresponding to the parameters. The maximum size file is 250 MB

### Example
```java
// Import classes:
//import ApiClient;
//import ApiException;
//import Configuration;
//import io.swagger.client.auth.*;
//import RuntimeApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: NeoloadAuthorizer
ApiKeyAuth NeoloadAuthorizer = (ApiKeyAuth) defaultClient.getAuthentication("NeoloadAuthorizer");
NeoloadAuthorizer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//NeoloadAuthorizer.setApiKeyPrefix("Token");

RuntimeApi apiInstance = new RuntimeApi();
File file = new File("file_example"); // File | 
String testId = "testId_example"; // String | Unique identifier representing a specific test.
try {
    ProjectDefinition result = apiInstance.postUploadProject(file, testId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling RuntimeApi#postUploadProject");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **file** | **File**|  |
 **testId** | **String**| Unique identifier representing a specific test. |

### Return type

[**ProjectDefinition**](ProjectDefinition.md)

### Authorization

[NeoloadAuthorizer](../README.md#NeoloadAuthorizer)

### HTTP request headers

 - **Content-Type**: multipart/form-data
 - **Accept**: application/json

<a name="putTest"></a>
# **putTest**
> TestSettingsDefinition putTest(body, testId)

Fully update a test

Update all fields of the test

### Example
```java
// Import classes:
//import ApiClient;
//import ApiException;
//import Configuration;
//import io.swagger.client.auth.*;
//import RuntimeApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: NeoloadAuthorizer
ApiKeyAuth NeoloadAuthorizer = (ApiKeyAuth) defaultClient.getAuthentication("NeoloadAuthorizer");
NeoloadAuthorizer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//NeoloadAuthorizer.setApiKeyPrefix("Token");

RuntimeApi apiInstance = new RuntimeApi();
TestSettingsUpdate body = new TestSettingsUpdate(); // TestSettingsUpdate | The fields to update. All fields are required and will be updated.
String testId = "testId_example"; // String | Unique identifier representing a specific test.
try {
    TestSettingsDefinition result = apiInstance.putTest(body, testId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling RuntimeApi#putTest");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**TestSettingsUpdate**](TestSettingsUpdate.md)| The fields to update. All fields are required and will be updated. |
 **testId** | **String**| Unique identifier representing a specific test. |

### Return type

[**TestSettingsDefinition**](TestSettingsDefinition.md)

### Authorization

[NeoloadAuthorizer](../README.md#NeoloadAuthorizer)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

