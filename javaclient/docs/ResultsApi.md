# ResultsApi

All URIs are relative to */v1*

Method | HTTP request | Description
------------- | ------------- | -------------
[**deleteTest**](ResultsApi.md#deleteTest) | **DELETE** /tests/{testId} | Deletes a test result
[**getTest**](ResultsApi.md#getTest) | **GET** /tests/{testId} | Test result description
[**getTestElements**](ResultsApi.md#getTestElements) | **GET** /tests/{testId}/elements | Test elements
[**getTestElementsPoints**](ResultsApi.md#getTestElementsPoints) | **GET** /tests/{testId}/elements/{elementId}/points | Tests elements points since the beginning of the test
[**getTestElementsSla**](ResultsApi.md#getTestElementsSla) | **GET** /tests/{testId}/elements/{elementId}/sla | Test elements SLA status since the beginning of the test
[**getTestElementsValues**](ResultsApi.md#getTestElementsValues) | **GET** /tests/{testId}/elements/{elementId}/values | Test elements values
[**getTestMonitors**](ResultsApi.md#getTestMonitors) | **GET** /tests/{testId}/monitors | Test monitors
[**getTestMonitorsPoints**](ResultsApi.md#getTestMonitorsPoints) | **GET** /tests/{testId}/monitors/{counterId}/points | Tests monitors points
[**getTestMonitorsValues**](ResultsApi.md#getTestMonitorsValues) | **GET** /tests/{testId}/monitors/{counterId}/values | Tests monitors values
[**getTestStatistics**](ResultsApi.md#getTestStatistics) | **GET** /tests/{testId}/statistics | Test result main statistics
[**getTests**](ResultsApi.md#getTests) | **GET** /tests | Lists test results
[**postTestMonitors**](ResultsApi.md#postTestMonitors) | **POST** /tests/{testId}/monitors | Create custom monitors
[**updateTest**](ResultsApi.md#updateTest) | **PUT** /tests/{testId} | Update a test result




<a name="deleteTest"></a>
# **deleteTest**
> deleteTest(testId)

Deletes a test result

Deletes a test result and all the associated statistics. This action cannot be undone.

### Example
```java
// Import classes:
//import com.neotys.ascode.swagger.client.ApiException;
//import ResultsApi;



ResultsApi apiInstance = new ResultsApi();

String testId = "testId_example"; // String | Unique identifier representing a specific test.

try {
    apiInstance.deleteTest(testId);
} catch (ApiException e) {
    System.err.println("Exception when calling ResultsApi#deleteTest");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **testId** | **String**| Unique identifier representing a specific test. |


### Return type

null (empty response body)

### Authorization

[NeoloadAuthorizer](../README.md#NeoloadAuthorizer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json


<a name="getTest"></a>
# **getTest**
> TestDefinition getTest(testId)

Test result description

Provides a test result description using a unique test identifier. Provides name, dates, owner ...

### Example
```java
// Import classes:
//import com.neotys.ascode.swagger.client.ApiException;
//import ResultsApi;



ResultsApi apiInstance = new ResultsApi();

String testId = "testId_example"; // String | Unique identifier representing a specific test.

try {
    TestDefinition result = apiInstance.getTest(testId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ResultsApi#getTest");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **testId** | **String**| Unique identifier representing a specific test. |


### Return type

[**TestDefinition**](TestDefinition.md)

### Authorization

[NeoloadAuthorizer](../README.md#NeoloadAuthorizer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json


<a name="getTestElements"></a>
# **getTestElements**
> ArrayOfElementDefinition getTestElements(testId, category)

Test elements

Provides the tests elements of a test result. The elements type must be provided.

### Example
```java
// Import classes:
//import com.neotys.ascode.swagger.client.ApiException;
//import ResultsApi;



ResultsApi apiInstance = new ResultsApi();

String testId = "testId_example"; // String | Unique identifier representing a specific test.

String category = "category_example"; // String | Category of the elements to return. Available categories are: TRANSACTION, PAGE, REQUEST.

try {
    ArrayOfElementDefinition result = apiInstance.getTestElements(testId, category);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ResultsApi#getTestElements");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **testId** | **String**| Unique identifier representing a specific test. |
 **category** | **String**| Category of the elements to return. Available categories are: TRANSACTION, PAGE, REQUEST. |


### Return type

[**ArrayOfElementDefinition**](ArrayOfElementDefinition.md)

### Authorization

[NeoloadAuthorizer](../README.md#NeoloadAuthorizer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json


<a name="getTestElementsPoints"></a>
# **getTestElementsPoints**
> Points getTestElementsPoints(testId, elementId, statistics)

Tests elements points since the beginning of the test

Provides all the ponits of a test element for the selected statistics.

### Example
```java
// Import classes:
//import com.neotys.ascode.swagger.client.ApiException;
//import ResultsApi;



ResultsApi apiInstance = new ResultsApi();

String testId = "testId_example"; // String | Unique identifier representing a specific test.

String elementId = "elementId_example"; // String | Unique identifier representing a specific element.

String statistics = "statistics_example"; // String | Comma-separated list of statistics to get. Available statistics are: AVG_DURATION, MIN_DURATION, MAX_DURATION, COUNT, THROUGHPUT, ELEMENTS_PER_SECOND, ERRORS, ERRORS_PER_SECOND, ERROR_RATE, AVG_TTFB, MIN_TTFB, MAX_TTFB

try {
    Points result = apiInstance.getTestElementsPoints(testId, elementId, statistics);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ResultsApi#getTestElementsPoints");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **testId** | **String**| Unique identifier representing a specific test. |
 **elementId** | **String**| Unique identifier representing a specific element. |
 **statistics** | **String**| Comma-separated list of statistics to get. Available statistics are: AVG_DURATION, MIN_DURATION, MAX_DURATION, COUNT, THROUGHPUT, ELEMENTS_PER_SECOND, ERRORS, ERRORS_PER_SECOND, ERROR_RATE, AVG_TTFB, MIN_TTFB, MAX_TTFB |


### Return type

[**Points**](Points.md)

### Authorization

[NeoloadAuthorizer](../README.md#NeoloadAuthorizer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json


<a name="getTestElementsSla"></a>
# **getTestElementsSla**
> Sla getTestElementsSla(testId, elementId)

Test elements SLA status since the beginning of the test

Provides the SLA status of a test element.

### Example
```java
// Import classes:
//import com.neotys.ascode.swagger.client.ApiException;
//import ResultsApi;



ResultsApi apiInstance = new ResultsApi();

String testId = "testId_example"; // String | Unique identifier representing a specific test.

String elementId = "elementId_example"; // String | Unique identifier representing a specific element.

try {
    Sla result = apiInstance.getTestElementsSla(testId, elementId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ResultsApi#getTestElementsSla");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **testId** | **String**| Unique identifier representing a specific test. |
 **elementId** | **String**| Unique identifier representing a specific element. |


### Return type

[**Sla**](Sla.md)

### Authorization

[NeoloadAuthorizer](../README.md#NeoloadAuthorizer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json


<a name="getTestElementsValues"></a>
# **getTestElementsValues**
> ElementValues getTestElementsValues(testId, elementId)

Test elements values

Provides the values of a test element.

### Example
```java
// Import classes:
//import com.neotys.ascode.swagger.client.ApiException;
//import ResultsApi;



ResultsApi apiInstance = new ResultsApi();

String testId = "testId_example"; // String | Unique identifier representing a specific test.

String elementId = "elementId_example"; // String | Unique identifier representing a specific element.

try {
    ElementValues result = apiInstance.getTestElementsValues(testId, elementId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ResultsApi#getTestElementsValues");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **testId** | **String**| Unique identifier representing a specific test. |
 **elementId** | **String**| Unique identifier representing a specific element. |


### Return type

[**ElementValues**](ElementValues.md)

### Authorization

[NeoloadAuthorizer](../README.md#NeoloadAuthorizer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json


<a name="getTestMonitors"></a>
# **getTestMonitors**
> CounterDefinition getTestMonitors(testId)

Test monitors

Provides all the tests counters of all monitors for a test result.

### Example
```java
// Import classes:
//import com.neotys.ascode.swagger.client.ApiException;
//import ResultsApi;



ResultsApi apiInstance = new ResultsApi();

String testId = "testId_example"; // String | Unique identifier representing a specific test.

try {
    CounterDefinition result = apiInstance.getTestMonitors(testId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ResultsApi#getTestMonitors");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **testId** | **String**| Unique identifier representing a specific test. |


### Return type

[**CounterDefinition**](CounterDefinition.md)

### Authorization

[NeoloadAuthorizer](../README.md#NeoloadAuthorizer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json


<a name="getTestMonitorsPoints"></a>
# **getTestMonitorsPoints**
> Points getTestMonitorsPoints(testId, counterId)

Tests monitors points

Provides all the points of a test counter. The values are the average on the specified interval.

### Example
```java
// Import classes:
//import com.neotys.ascode.swagger.client.ApiException;
//import ResultsApi;



ResultsApi apiInstance = new ResultsApi();

String testId = "testId_example"; // String | Unique identifier representing a specific test.

String counterId = "counterId_example"; // String | Unique identifier representing a specific counter.

try {
    Points result = apiInstance.getTestMonitorsPoints(testId, counterId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ResultsApi#getTestMonitorsPoints");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **testId** | **String**| Unique identifier representing a specific test. |
 **counterId** | **String**| Unique identifier representing a specific counter. |


### Return type

[**Points**](Points.md)

### Authorization

[NeoloadAuthorizer](../README.md#NeoloadAuthorizer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json


<a name="getTestMonitorsValues"></a>
# **getTestMonitorsValues**
> CounterValues getTestMonitorsValues(testId, counterId)

Tests monitors values

Provides the values of a test counter.

### Example
```java
// Import classes:
//import com.neotys.ascode.swagger.client.ApiException;
//import ResultsApi;



ResultsApi apiInstance = new ResultsApi();

String testId = "testId_example"; // String | Unique identifier representing a specific test.

String counterId = "counterId_example"; // String | Unique identifier representing a specific counter.

try {
    CounterValues result = apiInstance.getTestMonitorsValues(testId, counterId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ResultsApi#getTestMonitorsValues");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **testId** | **String**| Unique identifier representing a specific test. |
 **counterId** | **String**| Unique identifier representing a specific counter. |


### Return type

[**CounterValues**](CounterValues.md)

### Authorization

[NeoloadAuthorizer](../README.md#NeoloadAuthorizer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json


<a name="getTestStatistics"></a>
# **getTestStatistics**
> TestStatistics getTestStatistics(testId)

Test result main statistics

Provides the main statistics of a test result. For a runnning test, these statistics are live, for a finished test, those are average for the all test.

### Example
```java
// Import classes:
//import com.neotys.ascode.swagger.client.ApiException;
//import ResultsApi;



ResultsApi apiInstance = new ResultsApi();

String testId = "testId_example"; // String | Unique identifier representing a specific test.

try {
    TestStatistics result = apiInstance.getTestStatistics(testId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ResultsApi#getTestStatistics");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **testId** | **String**| Unique identifier representing a specific test. |


### Return type

[**TestStatistics**](TestStatistics.md)

### Authorization

[NeoloadAuthorizer](../README.md#NeoloadAuthorizer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json


<a name="getTests"></a>
# **getTests**
> ArrayOfTestDefinition getTests(status, project, author, limit, offset, fields, pretty)

Lists test results

Lists the test results of the Account according to the method parameters.

### Example
```java
// Import classes:
//import com.neotys.ascode.swagger.client.ApiException;
//import ResultsApi;



ResultsApi apiInstance = new ResultsApi();

String status = "status_example"; // String | Returns only the test with the specified status. Available status are: STARTING, RUNNING, TERMINATED.

String project = "project_example"; // String | Project name. Returns only the tests of the specified project.

String author = "author_example"; // String | The author of the test. Returns only the tests launched by the specified author.

Integer limit = 56; // Integer | The maximum number of test results returned by this call.

Integer offset = 56; // Integer | The offset of the first test to return. Starting at this offset the query will return a maximum of 'limit' tests.

String fields = "fields_example"; // String | Comma-separated list of fields to include in the json test definition.

Boolean pretty = true; // Boolean | If true the Json returned is human readable.

try {
    ArrayOfTestDefinition result = apiInstance.getTests(status, project, author, limit, offset, fields, pretty);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ResultsApi#getTests");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **status** | **String**| Returns only the test with the specified status. Available status are: STARTING, RUNNING, TERMINATED. | [optional]
 **project** | **String**| Project name. Returns only the tests of the specified project. | [optional]
 **author** | **String**| The author of the test. Returns only the tests launched by the specified author. | [optional]
 **limit** | **Integer**| The maximum number of test results returned by this call. | [optional] [enum: ]
 **offset** | **Integer**| The offset of the first test to return. Starting at this offset the query will return a maximum of &#x27;limit&#x27; tests. | [optional]
 **fields** | **String**| Comma-separated list of fields to include in the json test definition. | [optional]
 **pretty** | **Boolean**| If true the Json returned is human readable. | [optional]


### Return type

[**ArrayOfTestDefinition**](ArrayOfTestDefinition.md)

### Authorization

[NeoloadAuthorizer](../README.md#NeoloadAuthorizer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json


<a name="postTestMonitors"></a>
# **postTestMonitors**
> postTestMonitors(body, testId)

Create custom monitors

Pushes monitoring data (external data) for a given running test. &lt;br/&gt;&lt;br/&gt; &lt;b&gt;Resolution limitation&lt;/b&gt;: &lt;ul&gt;   &lt;li&gt;Maximum resolution for a monitor value is &lt;b&gt;1 per second&lt;/b&gt;, otherwise &lt;b&gt;the first value is kept&lt;/b&gt;.&lt;/li&gt; &lt;/ul&gt; &lt;b&gt;Maximum paths and monitors&lt;/b&gt;: &lt;ul&gt;   &lt;li&gt;Maximum 100 different &lt;b&gt;unique&lt;/b&gt; paths.&lt;/li&gt;   &lt;li&gt;Maximum 50 different monitors for a given path.&lt;/li&gt;   &lt;li&gt;Maximum length is 10 for a path.&lt;/li&gt; &lt;/ul&gt; &lt;b&gt;Other requirements&lt;/b&gt;: &lt;ul&gt;   &lt;li&gt;The timestamp is an epoch time in seconds. It must be after the beginning of the test and not more than 10 minutes in the future.&lt;/li&gt;   &lt;li&gt;&#x27;|&#x27; character is not allowed in name and path element.&lt;/li&gt;   &lt;li&gt;The test must be &lt;b&gt;running&lt;/b&gt; while pushing the data.&lt;/li&gt;   &lt;li&gt;For a given timestamp, all monitors relative to a path must be sent in the same request.&lt;/li&gt;   &lt;li&gt;These elements must not be empty: &lt;b&gt;monitor name&lt;/b&gt;, &lt;b&gt;a path element&lt;/b&gt; and &lt;b&gt;values&lt;/b&gt;.&lt;/li&gt; &lt;/ul&gt; 

### Example
```java
// Import classes:
//import com.neotys.ascode.swagger.client.ApiException;
//import ResultsApi;



ResultsApi apiInstance = new ResultsApi();

MonitorPostRequest body = new MonitorPostRequest(); // MonitorPostRequest | List of custom monitors

String testId = "testId_example"; // String | Unique identifier representing a specific test.

try {
    apiInstance.postTestMonitors(body, testId);
} catch (ApiException e) {
    System.err.println("Exception when calling ResultsApi#postTestMonitors");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**MonitorPostRequest**](MonitorPostRequest.md)| List of custom monitors |
 **testId** | **String**| Unique identifier representing a specific test. |


### Return type

null (empty response body)

### Authorization

[NeoloadAuthorizer](../README.md#NeoloadAuthorizer)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json


<a name="updateTest"></a>
# **updateTest**
> TestDefinition updateTest(body, testId)

Update a test result

Updates a test result with new name, description or quality status.

### Example
```java
// Import classes:
//import com.neotys.ascode.swagger.client.ApiException;
//import ResultsApi;



ResultsApi apiInstance = new ResultsApi();

TestUpdateRequest body = new TestUpdateRequest(); // TestUpdateRequest | 

String testId = "testId_example"; // String | Unique identifier representing a specific test.

try {
    TestDefinition result = apiInstance.updateTest(body, testId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ResultsApi#updateTest");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**TestUpdateRequest**](TestUpdateRequest.md)|  |
 **testId** | **String**| Unique identifier representing a specific test. |


### Return type

[**TestDefinition**](TestDefinition.md)

### Authorization

[NeoloadAuthorizer](../README.md#NeoloadAuthorizer)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json



