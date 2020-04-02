# ResultsApi

All URIs are relative to */v2*

Method | HTTP request | Description
------------- | ------------- | -------------
[**deleteTestResult**](ResultsApi.md#deleteTestResult) | **DELETE** /test-results/{resultId} | Deletes a test result
[**getTest**](ResultsApi.md#getTest) | **GET** /test-results/{resultId} | Test result description
[**getTestElementDefinition**](ResultsApi.md#getTestElementDefinition) | **GET** /test-results/{resultId}/elements/{elementId} | Test result element definition
[**getTestElements**](ResultsApi.md#getTestElements) | **GET** /test-results/{resultId}/elements | Test elements
[**getTestElementsPercentiles**](ResultsApi.md#getTestElementsPercentiles) | **GET** /test-results/{resultId}/elements/{elementId}/percentiles | Test result percentiles transaction since the beginning of the test result
[**getTestElementsPoints**](ResultsApi.md#getTestElementsPoints) | **GET** /test-results/{resultId}/elements/{elementId}/points | Test results elements points since the beginning of the test result
[**getTestElementsSla**](ResultsApi.md#getTestElementsSla) | **GET** /test-results/{resultId}/elements/{elementId}/sla | Test result elements SLA status since the beginning of the test result
[**getTestElementsValues**](ResultsApi.md#getTestElementsValues) | **GET** /test-results/{resultId}/elements/{elementId}/values | Test result elements values
[**getTestEvents**](ResultsApi.md#getTestEvents) | **GET** /test-results/{resultId}/events | Test result events
[**getTestGraph**](ResultsApi.md#getTestGraph) | **POST** /test-results/{resultId}/graph | Test result Graph
[**getTestMonitorDefinition**](ResultsApi.md#getTestMonitorDefinition) | **GET** /test-results/{resultId}/monitors/{counterId} | Test result counter definition
[**getTestMonitors**](ResultsApi.md#getTestMonitors) | **GET** /test-results/{resultId}/monitors | Test result monitors
[**getTestMonitorsPoints**](ResultsApi.md#getTestMonitorsPoints) | **GET** /test-results/{resultId}/monitors/{counterId}/points | Test result monitors points
[**getTestMonitorsValues**](ResultsApi.md#getTestMonitorsValues) | **GET** /test-results/{resultId}/monitors/{counterId}/values | Test result monitors values
[**getTestMultiGraph**](ResultsApi.md#getTestMultiGraph) | **POST** /test-results/graph | Test results MultiGraph
[**getTestSLAGlobalIndicators**](ResultsApi.md#getTestSLAGlobalIndicators) | **GET** /test-results/{resultId}/slas/statistics | SLAs global indicators
[**getTestSLAPerInterval**](ResultsApi.md#getTestSLAPerInterval) | **GET** /test-results/{resultId}/slas/per-interval | SLAs per time interval
[**getTestSLAPerTest**](ResultsApi.md#getTestSLAPerTest) | **GET** /test-results/{resultId}/slas/per-test | SLAs per test
[**getTestStatistics**](ResultsApi.md#getTestStatistics) | **GET** /test-results/{resultId}/statistics | Test result main statistics
[**getTests**](ResultsApi.md#getTests) | **GET** /test-results | Lists test results
[**postTestMonitors**](ResultsApi.md#postTestMonitors) | **POST** /test-results/{resultId}/monitors | Create custom monitors
[**stopTestResult**](ResultsApi.md#stopTestResult) | **POST** /test-results/{resultId}/stop | Stop a running test result
[**updateTest**](ResultsApi.md#updateTest) | **PUT** /test-results/{resultId} | Update a test result

<a name="deleteTestResult"></a>
# **deleteTestResult**
> deleteTestResult(resultId)

Deletes a test result

Deletes a test result and all the associated statistics. This action cannot be undone.

### Example
```java
// Import classes:
//import ApiClient;
//import ApiException;
//import Configuration;
//import io.swagger.client.auth.*;
//import ResultsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: NeoloadAuthorizer
ApiKeyAuth NeoloadAuthorizer = (ApiKeyAuth) defaultClient.getAuthentication("NeoloadAuthorizer");
NeoloadAuthorizer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//NeoloadAuthorizer.setApiKeyPrefix("Token");

ResultsApi apiInstance = new ResultsApi();
String resultId = "resultId_example"; // String | Unique identifier representing a specific test result.
try {
    apiInstance.deleteTestResult(resultId);
} catch (ApiException e) {
    System.err.println("Exception when calling ResultsApi#deleteTestResult");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **resultId** | **String**| Unique identifier representing a specific test result. |

### Return type

null (empty response body)

### Authorization

[NeoloadAuthorizer](../README.md#NeoloadAuthorizer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="getTest"></a>
# **getTest**
> TestDefinition getTest(resultId)

Test result description

Provides a test result description using a unique test result identifier. Provides name, dates, owner ...

### Example
```java
// Import classes:
//import ApiClient;
//import ApiException;
//import Configuration;
//import io.swagger.client.auth.*;
//import ResultsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: NeoloadAuthorizer
ApiKeyAuth NeoloadAuthorizer = (ApiKeyAuth) defaultClient.getAuthentication("NeoloadAuthorizer");
NeoloadAuthorizer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//NeoloadAuthorizer.setApiKeyPrefix("Token");

ResultsApi apiInstance = new ResultsApi();
String resultId = "resultId_example"; // String | Unique identifier representing a specific test result.
try {
    TestDefinition result = apiInstance.getTest(resultId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ResultsApi#getTest");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **resultId** | **String**| Unique identifier representing a specific test result. |

### Return type

[**TestDefinition**](TestDefinition.md)

### Authorization

[NeoloadAuthorizer](../README.md#NeoloadAuthorizer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="getTestElementDefinition"></a>
# **getTestElementDefinition**
> ElementDefinition getTestElementDefinition(resultId, elementId)

Test result element definition

Provides a test result element definition.

### Example
```java
// Import classes:
//import ApiClient;
//import ApiException;
//import Configuration;
//import io.swagger.client.auth.*;
//import ResultsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: NeoloadAuthorizer
ApiKeyAuth NeoloadAuthorizer = (ApiKeyAuth) defaultClient.getAuthentication("NeoloadAuthorizer");
NeoloadAuthorizer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//NeoloadAuthorizer.setApiKeyPrefix("Token");

ResultsApi apiInstance = new ResultsApi();
String resultId = "resultId_example"; // String | Unique identifier representing a specific test result.
String elementId = "elementId_example"; // String | Unique identifier representing a specific element.
try {
    ElementDefinition result = apiInstance.getTestElementDefinition(resultId, elementId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ResultsApi#getTestElementDefinition");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **resultId** | **String**| Unique identifier representing a specific test result. |
 **elementId** | **String**| Unique identifier representing a specific element. |

### Return type

[**ElementDefinition**](ElementDefinition.md)

### Authorization

[NeoloadAuthorizer](../README.md#NeoloadAuthorizer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="getTestElements"></a>
# **getTestElements**
> ArrayOfElementDefinition getTestElements(resultId, category)

Test elements

Provides the tests elements of a test result. The elements type must be provided.

### Example
```java
// Import classes:
//import ApiClient;
//import ApiException;
//import Configuration;
//import io.swagger.client.auth.*;
//import ResultsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: NeoloadAuthorizer
ApiKeyAuth NeoloadAuthorizer = (ApiKeyAuth) defaultClient.getAuthentication("NeoloadAuthorizer");
NeoloadAuthorizer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//NeoloadAuthorizer.setApiKeyPrefix("Token");

ResultsApi apiInstance = new ResultsApi();
String resultId = "resultId_example"; // String | Unique identifier representing a specific test result.
String category = "TRANSACTION"; // String | Category of the elements to return.
try {
    ArrayOfElementDefinition result = apiInstance.getTestElements(resultId, category);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ResultsApi#getTestElements");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **resultId** | **String**| Unique identifier representing a specific test result. |
 **category** | **String**| Category of the elements to return. | [default to TRANSACTION] [enum: TRANSACTION, PAGE, REQUEST]

### Return type

[**ArrayOfElementDefinition**](ArrayOfElementDefinition.md)

### Authorization

[NeoloadAuthorizer](../README.md#NeoloadAuthorizer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="getTestElementsPercentiles"></a>
# **getTestElementsPercentiles**
> Percentiles getTestElementsPercentiles(resultId, elementId)

Test result percentiles transaction since the beginning of the test result

Provides the percentiles of a test result transaction.

### Example
```java
// Import classes:
//import ApiClient;
//import ApiException;
//import Configuration;
//import io.swagger.client.auth.*;
//import ResultsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: NeoloadAuthorizer
ApiKeyAuth NeoloadAuthorizer = (ApiKeyAuth) defaultClient.getAuthentication("NeoloadAuthorizer");
NeoloadAuthorizer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//NeoloadAuthorizer.setApiKeyPrefix("Token");

ResultsApi apiInstance = new ResultsApi();
String resultId = "resultId_example"; // String | Unique identifier representing a specific test result.
String elementId = "elementId_example"; // String | Unique identifier representing a specific element.
try {
    Percentiles result = apiInstance.getTestElementsPercentiles(resultId, elementId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ResultsApi#getTestElementsPercentiles");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **resultId** | **String**| Unique identifier representing a specific test result. |
 **elementId** | **String**| Unique identifier representing a specific element. |

### Return type

[**Percentiles**](Percentiles.md)

### Authorization

[NeoloadAuthorizer](../README.md#NeoloadAuthorizer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="getTestElementsPoints"></a>
# **getTestElementsPoints**
> Points getTestElementsPoints(resultId, elementId, statistics)

Test results elements points since the beginning of the test result

Provides all the points of a test result element for the selected statistics.

### Example
```java
// Import classes:
//import ApiClient;
//import ApiException;
//import Configuration;
//import io.swagger.client.auth.*;
//import ResultsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: NeoloadAuthorizer
ApiKeyAuth NeoloadAuthorizer = (ApiKeyAuth) defaultClient.getAuthentication("NeoloadAuthorizer");
NeoloadAuthorizer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//NeoloadAuthorizer.setApiKeyPrefix("Token");

ResultsApi apiInstance = new ResultsApi();
String resultId = "resultId_example"; // String | Unique identifier representing a specific test result.
String elementId = "elementId_example"; // String | Unique identifier representing a specific element.
String statistics = "statistics_example"; // String | Comma-separated list of statistics to get. Available statistics are: AVG_DURATION (ms), MIN_DURATION (ms), MAX_DURATION (ms), COUNT, THROUGHPUT (Byte/s), ELEMENTS_PER_SECOND, ERRORS, ERRORS_PER_SECOND, ERROR_RATE (%), AVG_TTFB (ms), MIN_TTFB (ms), MAX_TTFB (ms).<br/> Example: AVG_DURATION,ELEMENTS_PER_SECOND
try {
    Points result = apiInstance.getTestElementsPoints(resultId, elementId, statistics);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ResultsApi#getTestElementsPoints");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **resultId** | **String**| Unique identifier representing a specific test result. |
 **elementId** | **String**| Unique identifier representing a specific element. |
 **statistics** | **String**| Comma-separated list of statistics to get. Available statistics are: AVG_DURATION (ms), MIN_DURATION (ms), MAX_DURATION (ms), COUNT, THROUGHPUT (Byte/s), ELEMENTS_PER_SECOND, ERRORS, ERRORS_PER_SECOND, ERROR_RATE (%), AVG_TTFB (ms), MIN_TTFB (ms), MAX_TTFB (ms).&lt;br/&gt; Example: AVG_DURATION,ELEMENTS_PER_SECOND |

### Return type

[**Points**](Points.md)

### Authorization

[NeoloadAuthorizer](../README.md#NeoloadAuthorizer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="getTestElementsSla"></a>
# **getTestElementsSla**
> Sla getTestElementsSla(resultId, elementId)

Test result elements SLA status since the beginning of the test result

Provides the SLA status of a test result element.

### Example
```java
// Import classes:
//import ApiClient;
//import ApiException;
//import Configuration;
//import io.swagger.client.auth.*;
//import ResultsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: NeoloadAuthorizer
ApiKeyAuth NeoloadAuthorizer = (ApiKeyAuth) defaultClient.getAuthentication("NeoloadAuthorizer");
NeoloadAuthorizer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//NeoloadAuthorizer.setApiKeyPrefix("Token");

ResultsApi apiInstance = new ResultsApi();
String resultId = "resultId_example"; // String | Unique identifier representing a specific test result.
String elementId = "elementId_example"; // String | Unique identifier representing a specific element.
try {
    Sla result = apiInstance.getTestElementsSla(resultId, elementId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ResultsApi#getTestElementsSla");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **resultId** | **String**| Unique identifier representing a specific test result. |
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
> ElementValues getTestElementsValues(resultId, elementId)

Test result elements values

Provides the values of a test result element.

### Example
```java
// Import classes:
//import ApiClient;
//import ApiException;
//import Configuration;
//import io.swagger.client.auth.*;
//import ResultsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: NeoloadAuthorizer
ApiKeyAuth NeoloadAuthorizer = (ApiKeyAuth) defaultClient.getAuthentication("NeoloadAuthorizer");
NeoloadAuthorizer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//NeoloadAuthorizer.setApiKeyPrefix("Token");

ResultsApi apiInstance = new ResultsApi();
String resultId = "resultId_example"; // String | Unique identifier representing a specific test result.
String elementId = "elementId_example"; // String | Unique identifier representing a specific element.
try {
    ElementValues result = apiInstance.getTestElementsValues(resultId, elementId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ResultsApi#getTestElementsValues");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **resultId** | **String**| Unique identifier representing a specific test result. |
 **elementId** | **String**| Unique identifier representing a specific element. |

### Return type

[**ElementValues**](ElementValues.md)

### Authorization

[NeoloadAuthorizer](../README.md#NeoloadAuthorizer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="getTestEvents"></a>
# **getTestEvents**
> ArrayOfEventDefinition getTestEvents(resultId, types, limit, offset, sort)

Test result events

List the events of the specified test result according to the method parameters. ___ *Sortable fields :*   - offset   - fullname   - code   - source 

### Example
```java
// Import classes:
//import ApiClient;
//import ApiException;
//import Configuration;
//import io.swagger.client.auth.*;
//import ResultsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: NeoloadAuthorizer
ApiKeyAuth NeoloadAuthorizer = (ApiKeyAuth) defaultClient.getAuthentication("NeoloadAuthorizer");
NeoloadAuthorizer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//NeoloadAuthorizer.setApiKeyPrefix("Token");

ResultsApi apiInstance = new ResultsApi();
String resultId = "resultId_example"; // String | Unique identifier representing a specific test result.
List<EventType> types = Arrays.asList(new EventType()); // List<EventType> | The types of the events you expect to get. It will return all the types if the field is left empty. (Ctrl+click to select multiple values) 
Integer limit = 50; // Integer | The maximum number of elements returned by this call. The maximum must be less than or equal to 200.
Integer offset = 0; // Integer | The offset of the first element to return. Starting at this offset, the query will return a maximum of 'limit' elements.
String sort = "sort_example"; // String | The key to sort the elements on. It may begin with a '+' or a '-' to specify an ascending or descending sort order. The list of available keys can be found in the endpoint description.
try {
    ArrayOfEventDefinition result = apiInstance.getTestEvents(resultId, types, limit, offset, sort);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ResultsApi#getTestEvents");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **resultId** | **String**| Unique identifier representing a specific test result. |
 **types** | [**List&lt;EventType&gt;**](EventType.md)| The types of the events you expect to get. It will return all the types if the field is left empty. (Ctrl+click to select multiple values)  | [optional]
 **limit** | **Integer**| The maximum number of elements returned by this call. The maximum must be less than or equal to 200. | [optional] [default to 50] [enum: ]
 **offset** | **Integer**| The offset of the first element to return. Starting at this offset, the query will return a maximum of &#x27;limit&#x27; elements. | [optional] [default to 0]
 **sort** | **String**| The key to sort the elements on. It may begin with a &#x27;+&#x27; or a &#x27;-&#x27; to specify an ascending or descending sort order. The list of available keys can be found in the endpoint description. | [optional]

### Return type

[**ArrayOfEventDefinition**](ArrayOfEventDefinition.md)

### Authorization

[NeoloadAuthorizer](../README.md#NeoloadAuthorizer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="getTestGraph"></a>
# **getTestGraph**
> File getTestGraph(resultId, body)

Test result Graph

Provides a graph from some stats of a test result.  Element statistics can be mixed with monitor statistics. Only limitation is around PERCENTILES_DURATION element statistic that cannot be mixed with other element statistics neither with monitor statistics. 

### Example
```java
// Import classes:
//import ApiClient;
//import ApiException;
//import Configuration;
//import io.swagger.client.auth.*;
//import ResultsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: NeoloadAuthorizer
ApiKeyAuth NeoloadAuthorizer = (ApiKeyAuth) defaultClient.getAuthentication("NeoloadAuthorizer");
NeoloadAuthorizer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//NeoloadAuthorizer.setApiKeyPrefix("Token");

ResultsApi apiInstance = new ResultsApi();
String resultId = "resultId_example"; // String | Unique identifier representing a specific test result.
TestRasterConfiguration body = new TestRasterConfiguration(); // TestRasterConfiguration | 
try {
    File result = apiInstance.getTestGraph(resultId, body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ResultsApi#getTestGraph");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **resultId** | **String**| Unique identifier representing a specific test result. |
 **body** | [**TestRasterConfiguration**](TestRasterConfiguration.md)|  | [optional]

### Return type

[**File**](File.md)

### Authorization

[NeoloadAuthorizer](../README.md#NeoloadAuthorizer)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: image/png, image/jpeg, image/tiff, application/json

<a name="getTestMonitorDefinition"></a>
# **getTestMonitorDefinition**
> CounterDefinition getTestMonitorDefinition(resultId, counterId)

Test result counter definition

Provides the definition of a test result counter.

### Example
```java
// Import classes:
//import ApiClient;
//import ApiException;
//import Configuration;
//import io.swagger.client.auth.*;
//import ResultsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: NeoloadAuthorizer
ApiKeyAuth NeoloadAuthorizer = (ApiKeyAuth) defaultClient.getAuthentication("NeoloadAuthorizer");
NeoloadAuthorizer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//NeoloadAuthorizer.setApiKeyPrefix("Token");

ResultsApi apiInstance = new ResultsApi();
String resultId = "resultId_example"; // String | Unique identifier representing a specific test result.
String counterId = "counterId_example"; // String | Unique identifier representing a specific counter.
try {
    CounterDefinition result = apiInstance.getTestMonitorDefinition(resultId, counterId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ResultsApi#getTestMonitorDefinition");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **resultId** | **String**| Unique identifier representing a specific test result. |
 **counterId** | **String**| Unique identifier representing a specific counter. |

### Return type

[**CounterDefinition**](CounterDefinition.md)

### Authorization

[NeoloadAuthorizer](../README.md#NeoloadAuthorizer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="getTestMonitors"></a>
# **getTestMonitors**
> CounterDefinitionArray getTestMonitors(resultId)

Test result monitors

Provides all the test result counters of all monitors for a test result.

### Example
```java
// Import classes:
//import ApiClient;
//import ApiException;
//import Configuration;
//import io.swagger.client.auth.*;
//import ResultsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: NeoloadAuthorizer
ApiKeyAuth NeoloadAuthorizer = (ApiKeyAuth) defaultClient.getAuthentication("NeoloadAuthorizer");
NeoloadAuthorizer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//NeoloadAuthorizer.setApiKeyPrefix("Token");

ResultsApi apiInstance = new ResultsApi();
String resultId = "resultId_example"; // String | Unique identifier representing a specific test result.
try {
    CounterDefinitionArray result = apiInstance.getTestMonitors(resultId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ResultsApi#getTestMonitors");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **resultId** | **String**| Unique identifier representing a specific test result. |

### Return type

[**CounterDefinitionArray**](CounterDefinitionArray.md)

### Authorization

[NeoloadAuthorizer](../README.md#NeoloadAuthorizer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="getTestMonitorsPoints"></a>
# **getTestMonitorsPoints**
> Points getTestMonitorsPoints(resultId, counterId)

Test result monitors points

Provides all the points of a test result counter. The values are the average on the specified interval.

### Example
```java
// Import classes:
//import ApiClient;
//import ApiException;
//import Configuration;
//import io.swagger.client.auth.*;
//import ResultsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: NeoloadAuthorizer
ApiKeyAuth NeoloadAuthorizer = (ApiKeyAuth) defaultClient.getAuthentication("NeoloadAuthorizer");
NeoloadAuthorizer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//NeoloadAuthorizer.setApiKeyPrefix("Token");

ResultsApi apiInstance = new ResultsApi();
String resultId = "resultId_example"; // String | Unique identifier representing a specific test result.
String counterId = "counterId_example"; // String | Unique identifier representing a specific counter.
try {
    Points result = apiInstance.getTestMonitorsPoints(resultId, counterId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ResultsApi#getTestMonitorsPoints");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **resultId** | **String**| Unique identifier representing a specific test result. |
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
> CounterValues getTestMonitorsValues(resultId, counterId)

Test result monitors values

Provides the values of a test result counter.

### Example
```java
// Import classes:
//import ApiClient;
//import ApiException;
//import Configuration;
//import io.swagger.client.auth.*;
//import ResultsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: NeoloadAuthorizer
ApiKeyAuth NeoloadAuthorizer = (ApiKeyAuth) defaultClient.getAuthentication("NeoloadAuthorizer");
NeoloadAuthorizer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//NeoloadAuthorizer.setApiKeyPrefix("Token");

ResultsApi apiInstance = new ResultsApi();
String resultId = "resultId_example"; // String | Unique identifier representing a specific test result.
String counterId = "counterId_example"; // String | Unique identifier representing a specific counter.
try {
    CounterValues result = apiInstance.getTestMonitorsValues(resultId, counterId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ResultsApi#getTestMonitorsValues");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **resultId** | **String**| Unique identifier representing a specific test result. |
 **counterId** | **String**| Unique identifier representing a specific counter. |

### Return type

[**CounterValues**](CounterValues.md)

### Authorization

[NeoloadAuthorizer](../README.md#NeoloadAuthorizer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="getTestMultiGraph"></a>
# **getTestMultiGraph**
> File getTestMultiGraph(body)

Test results MultiGraph

Provides a graph from some stats of some tests result.

### Example
```java
// Import classes:
//import ApiClient;
//import ApiException;
//import Configuration;
//import io.swagger.client.auth.*;
//import ResultsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: NeoloadAuthorizer
ApiKeyAuth NeoloadAuthorizer = (ApiKeyAuth) defaultClient.getAuthentication("NeoloadAuthorizer");
NeoloadAuthorizer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//NeoloadAuthorizer.setApiKeyPrefix("Token");

ResultsApi apiInstance = new ResultsApi();
TestRasterMultiConfiguration body = new TestRasterMultiConfiguration(); // TestRasterMultiConfiguration | 
try {
    File result = apiInstance.getTestMultiGraph(body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ResultsApi#getTestMultiGraph");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**TestRasterMultiConfiguration**](TestRasterMultiConfiguration.md)|  | [optional]

### Return type

[**File**](File.md)

### Authorization

[NeoloadAuthorizer](../README.md#NeoloadAuthorizer)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: image/png, image/jpeg, image/tiff, application/json

<a name="getTestSLAGlobalIndicators"></a>
# **getTestSLAGlobalIndicators**
> ArrayOfSLAGlobalIndicatorDefinition getTestSLAGlobalIndicators(resultId, status)

SLAs global indicators

Provides the SLAs global indicators of the test result.

### Example
```java
// Import classes:
//import ApiClient;
//import ApiException;
//import Configuration;
//import io.swagger.client.auth.*;
//import ResultsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: NeoloadAuthorizer
ApiKeyAuth NeoloadAuthorizer = (ApiKeyAuth) defaultClient.getAuthentication("NeoloadAuthorizer");
NeoloadAuthorizer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//NeoloadAuthorizer.setApiKeyPrefix("Token");

ResultsApi apiInstance = new ResultsApi();
String resultId = "resultId_example"; // String | Unique identifier representing a specific test result.
String status = "status_example"; // String | The status of the element you expect to get the SLA for. It will return all the statuses if the field is left empty.
try {
    ArrayOfSLAGlobalIndicatorDefinition result = apiInstance.getTestSLAGlobalIndicators(resultId, status);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ResultsApi#getTestSLAGlobalIndicators");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **resultId** | **String**| Unique identifier representing a specific test result. |
 **status** | **String**| The status of the element you expect to get the SLA for. It will return all the statuses if the field is left empty. | [optional] [enum: PASSED, WARNING, FAILED]

### Return type

[**ArrayOfSLAGlobalIndicatorDefinition**](ArrayOfSLAGlobalIndicatorDefinition.md)

### Authorization

[NeoloadAuthorizer](../README.md#NeoloadAuthorizer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="getTestSLAPerInterval"></a>
# **getTestSLAPerInterval**
> ArrayOfSLAPerIntervalDefinition getTestSLAPerInterval(resultId, status, category)

SLAs per time interval

Provides the SLAs per time interval of a specific test result.

### Example
```java
// Import classes:
//import ApiClient;
//import ApiException;
//import Configuration;
//import io.swagger.client.auth.*;
//import ResultsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: NeoloadAuthorizer
ApiKeyAuth NeoloadAuthorizer = (ApiKeyAuth) defaultClient.getAuthentication("NeoloadAuthorizer");
NeoloadAuthorizer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//NeoloadAuthorizer.setApiKeyPrefix("Token");

ResultsApi apiInstance = new ResultsApi();
String resultId = "resultId_example"; // String | Unique identifier representing a specific test result.
String status = "status_example"; // String | The status of the element you expect to get the SLA for. It will return all the statuses if the field is left empty.
String category = "category_example"; // String | The category of the element you expect to get the SLA for. It will return all the categories if the field is left empty.
try {
    ArrayOfSLAPerIntervalDefinition result = apiInstance.getTestSLAPerInterval(resultId, status, category);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ResultsApi#getTestSLAPerInterval");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **resultId** | **String**| Unique identifier representing a specific test result. |
 **status** | **String**| The status of the element you expect to get the SLA for. It will return all the statuses if the field is left empty. | [optional] [enum: PASSED, WARNING, FAILED]
 **category** | **String**| The category of the element you expect to get the SLA for. It will return all the categories if the field is left empty. | [optional] [enum: TRANSACTION, PAGE, REQUEST]

### Return type

[**ArrayOfSLAPerIntervalDefinition**](ArrayOfSLAPerIntervalDefinition.md)

### Authorization

[NeoloadAuthorizer](../README.md#NeoloadAuthorizer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="getTestSLAPerTest"></a>
# **getTestSLAPerTest**
> ArrayOfSLAPerTestDefinition getTestSLAPerTest(resultId, status, category)

SLAs per test

Provides the SLAs per test of a specific test result.

### Example
```java
// Import classes:
//import ApiClient;
//import ApiException;
//import Configuration;
//import io.swagger.client.auth.*;
//import ResultsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: NeoloadAuthorizer
ApiKeyAuth NeoloadAuthorizer = (ApiKeyAuth) defaultClient.getAuthentication("NeoloadAuthorizer");
NeoloadAuthorizer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//NeoloadAuthorizer.setApiKeyPrefix("Token");

ResultsApi apiInstance = new ResultsApi();
String resultId = "resultId_example"; // String | Unique identifier representing a specific test result.
String status = "status_example"; // String | The status of the element you expect to get the SLA for. It will return all the statuses if the field is left empty.
String category = "category_example"; // String | The category of the element you expect to get the SLA for. It will return all the categories if the field is left empty.
try {
    ArrayOfSLAPerTestDefinition result = apiInstance.getTestSLAPerTest(resultId, status, category);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ResultsApi#getTestSLAPerTest");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **resultId** | **String**| Unique identifier representing a specific test result. |
 **status** | **String**| The status of the element you expect to get the SLA for. It will return all the statuses if the field is left empty. | [optional] [enum: PASSED, WARNING, FAILED]
 **category** | **String**| The category of the element you expect to get the SLA for. It will return all the categories if the field is left empty. | [optional] [enum: TRANSACTION, PAGE, REQUEST]

### Return type

[**ArrayOfSLAPerTestDefinition**](ArrayOfSLAPerTestDefinition.md)

### Authorization

[NeoloadAuthorizer](../README.md#NeoloadAuthorizer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="getTestStatistics"></a>
# **getTestStatistics**
> TestStatistics getTestStatistics(resultId)

Test result main statistics

Provides the main statistics of a test result. For a runnning test, these statistics are live, for a test result, those are average for the all test result.

### Example
```java
// Import classes:
//import ApiClient;
//import ApiException;
//import Configuration;
//import io.swagger.client.auth.*;
//import ResultsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: NeoloadAuthorizer
ApiKeyAuth NeoloadAuthorizer = (ApiKeyAuth) defaultClient.getAuthentication("NeoloadAuthorizer");
NeoloadAuthorizer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//NeoloadAuthorizer.setApiKeyPrefix("Token");

ResultsApi apiInstance = new ResultsApi();
String resultId = "resultId_example"; // String | Unique identifier representing a specific test result.
try {
    TestStatistics result = apiInstance.getTestStatistics(resultId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ResultsApi#getTestStatistics");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **resultId** | **String**| Unique identifier representing a specific test result. |

### Return type

[**TestStatistics**](TestStatistics.md)

### Authorization

[NeoloadAuthorizer](../README.md#NeoloadAuthorizer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="getTests"></a>
# **getTests**
> ArrayOfTestDefinition getTests(status, project, author, limit, offset, sort, fields, pretty)

Lists test results

Lists the test results of the Account according to the method parameters. ___ *Sortable fields :*   - name   - project   - startDate   - qualityStatus 

### Example
```java
// Import classes:
//import ApiClient;
//import ApiException;
//import Configuration;
//import io.swagger.client.auth.*;
//import ResultsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: NeoloadAuthorizer
ApiKeyAuth NeoloadAuthorizer = (ApiKeyAuth) defaultClient.getAuthentication("NeoloadAuthorizer");
NeoloadAuthorizer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//NeoloadAuthorizer.setApiKeyPrefix("Token");

ResultsApi apiInstance = new ResultsApi();
String status = "status_example"; // String | Returns only the test results with the specified status.
String project = "project_example"; // String | Project name. Returns only the test results of the specified project.
String author = "author_example"; // String | The author of the test result. Returns only the test results launched by the specified author.
Integer limit = 50; // Integer | The maximum number of elements returned by this call. The maximum must be less than or equal to 200.
Integer offset = 0; // Integer | The offset of the first element to return. Starting at this offset, the query will return a maximum of 'limit' elements.
String sort = "sort_example"; // String | The key to sort the elements on. It may begin with a '+' or a '-' to specify an ascending or descending sort order. The list of available keys can be found in the endpoint description.
String fields = "fields_example"; // String | Comma-separated list of fields to include in the json test result definition.
Boolean pretty = true; // Boolean | If true the Json returned is human readable.
try {
    ArrayOfTestDefinition result = apiInstance.getTests(status, project, author, limit, offset, sort, fields, pretty);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ResultsApi#getTests");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **status** | **String**| Returns only the test results with the specified status. | [optional] [enum: STARTING, RUNNING, TERMINATED]
 **project** | **String**| Project name. Returns only the test results of the specified project. | [optional]
 **author** | **String**| The author of the test result. Returns only the test results launched by the specified author. | [optional]
 **limit** | **Integer**| The maximum number of elements returned by this call. The maximum must be less than or equal to 200. | [optional] [default to 50] [enum: ]
 **offset** | **Integer**| The offset of the first element to return. Starting at this offset, the query will return a maximum of &#x27;limit&#x27; elements. | [optional] [default to 0]
 **sort** | **String**| The key to sort the elements on. It may begin with a &#x27;+&#x27; or a &#x27;-&#x27; to specify an ascending or descending sort order. The list of available keys can be found in the endpoint description. | [optional]
 **fields** | **String**| Comma-separated list of fields to include in the json test result definition. | [optional]
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
> postTestMonitors(body, resultId)

Create custom monitors

Pushes monitoring data (external data) for a given running test. &lt;br/&gt;&lt;br/&gt; &lt;b&gt;Resolution limitation&lt;/b&gt;: &lt;ul&gt;   &lt;li&gt;Maximum resolution for a monitor value is &lt;b&gt;1 per second&lt;/b&gt;, otherwise &lt;b&gt;the first value is kept&lt;/b&gt;.&lt;/li&gt; &lt;/ul&gt; &lt;b&gt;Maximum paths and monitors&lt;/b&gt;: &lt;ul&gt;   &lt;li&gt;Maximum 100 different &lt;b&gt;unique&lt;/b&gt; paths.&lt;/li&gt;   &lt;li&gt;Maximum 50 different monitors for a given path.&lt;/li&gt;   &lt;li&gt;Maximum length is 10 for a path.&lt;/li&gt; &lt;/ul&gt; &lt;b&gt;Other requirements&lt;/b&gt;: &lt;ul&gt;   &lt;li&gt;&#x27;/&#x27; character is not allowed in path element.&lt;/li&gt;   &lt;li&gt;The test must be &lt;b&gt;running&lt;/b&gt; while pushing the data.&lt;/li&gt;   &lt;li&gt;For a given timestamp, all monitors relative to a path must be sent in the same request.&lt;/li&gt;   &lt;li&gt;These elements must not be empty: &lt;b&gt;monitor name&lt;/b&gt;, &lt;b&gt;a path element&lt;/b&gt; and &lt;b&gt;values&lt;/b&gt;.&lt;/li&gt; &lt;/ul&gt; 

### Example
```java
// Import classes:
//import ApiClient;
//import ApiException;
//import Configuration;
//import io.swagger.client.auth.*;
//import ResultsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: NeoloadAuthorizer
ApiKeyAuth NeoloadAuthorizer = (ApiKeyAuth) defaultClient.getAuthentication("NeoloadAuthorizer");
NeoloadAuthorizer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//NeoloadAuthorizer.setApiKeyPrefix("Token");

ResultsApi apiInstance = new ResultsApi();
MonitorPostRequest body = new MonitorPostRequest(); // MonitorPostRequest | List of custom monitors
String resultId = "resultId_example"; // String | Unique identifier representing a specific test result.
try {
    apiInstance.postTestMonitors(body, resultId);
} catch (ApiException e) {
    System.err.println("Exception when calling ResultsApi#postTestMonitors");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**MonitorPostRequest**](MonitorPostRequest.md)| List of custom monitors |
 **resultId** | **String**| Unique identifier representing a specific test result. |

### Return type

null (empty response body)

### Authorization

[NeoloadAuthorizer](../README.md#NeoloadAuthorizer)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="stopTestResult"></a>
# **stopTestResult**
> InlineResponse200 stopTestResult(body, resultId)

Stop a running test result

Stop the running test result with the specified id

### Example
```java
// Import classes:
//import ApiClient;
//import ApiException;
//import Configuration;
//import io.swagger.client.auth.*;
//import ResultsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: NeoloadAuthorizer
ApiKeyAuth NeoloadAuthorizer = (ApiKeyAuth) defaultClient.getAuthentication("NeoloadAuthorizer");
NeoloadAuthorizer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//NeoloadAuthorizer.setApiKeyPrefix("Token");

ResultsApi apiInstance = new ResultsApi();
TestResultStopRequest body = new TestResultStopRequest(); // TestResultStopRequest | 
String resultId = "resultId_example"; // String | Unique identifier representing a specific test result.
try {
    InlineResponse200 result = apiInstance.stopTestResult(body, resultId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ResultsApi#stopTestResult");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**TestResultStopRequest**](TestResultStopRequest.md)|  |
 **resultId** | **String**| Unique identifier representing a specific test result. |

### Return type

[**InlineResponse200**](InlineResponse200.md)

### Authorization

[NeoloadAuthorizer](../README.md#NeoloadAuthorizer)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="updateTest"></a>
# **updateTest**
> TestDefinition updateTest(body, resultId)

Update a test result

Updates a test result with new name, description or quality status.

### Example
```java
// Import classes:
//import ApiClient;
//import ApiException;
//import Configuration;
//import io.swagger.client.auth.*;
//import ResultsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: NeoloadAuthorizer
ApiKeyAuth NeoloadAuthorizer = (ApiKeyAuth) defaultClient.getAuthentication("NeoloadAuthorizer");
NeoloadAuthorizer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//NeoloadAuthorizer.setApiKeyPrefix("Token");

ResultsApi apiInstance = new ResultsApi();
TestUpdateRequest body = new TestUpdateRequest(); // TestUpdateRequest | 
String resultId = "resultId_example"; // String | Unique identifier representing a specific test result.
try {
    TestDefinition result = apiInstance.updateTest(body, resultId);
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
 **resultId** | **String**| Unique identifier representing a specific test result. |

### Return type

[**TestDefinition**](TestDefinition.md)

### Authorization

[NeoloadAuthorizer](../README.md#NeoloadAuthorizer)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

