# ResourcesApi

All URIs are relative to */v2*

Method | HTTP request | Description
------------- | ------------- | -------------
[**getReservations**](ResourcesApi.md#getReservations) | **GET** /resources/reservations | Lists reservations
[**getZones**](ResourcesApi.md#getZones) | **GET** /resources/zones | List all zones

<a name="getReservations"></a>
# **getReservations**
> ArrayOfReservationDefinition getReservations(fromDateTime, toDateTime, limit, offset)

Lists reservations

Lists the reservations.

### Example
```java
// Import classes:
//import ApiClient;
//import ApiException;
//import Configuration;
//import io.swagger.client.auth.*;
//import ResourcesApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: NeoloadAuthorizer
ApiKeyAuth NeoloadAuthorizer = (ApiKeyAuth) defaultClient.getAuthentication("NeoloadAuthorizer");
NeoloadAuthorizer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//NeoloadAuthorizer.setApiKeyPrefix("Token");

ResourcesApi apiInstance = new ResourcesApi();
String fromDateTime = "fromDateTime_example"; // String | Date-time with an offset of the beginning of the search. Format YYYY-MM-DDTHH:mm:ssZ<br/> Example 2020-01-01T09:00:00+00:00
String toDateTime = "toDateTime_example"; // String | Date-time with an offset of the end of the search. Format YYYY-MM-DDTHH:mm:ssZ<br/> Example 2020-01-01T09:00:00+00:00
Integer limit = 50; // Integer | The maximum number of elements returned by this call. The maximum must be less than or equal to 200.
Integer offset = 0; // Integer | The offset of the first element to return. Starting at this offset, the query will return a maximum of 'limit' elements.
try {
    ArrayOfReservationDefinition result = apiInstance.getReservations(fromDateTime, toDateTime, limit, offset);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ResourcesApi#getReservations");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **fromDateTime** | **String**| Date-time with an offset of the beginning of the search. Format YYYY-MM-DDTHH:mm:ssZ&lt;br/&gt; Example 2020-01-01T09:00:00+00:00 | [optional]
 **toDateTime** | **String**| Date-time with an offset of the end of the search. Format YYYY-MM-DDTHH:mm:ssZ&lt;br/&gt; Example 2020-01-01T09:00:00+00:00 | [optional]
 **limit** | **Integer**| The maximum number of elements returned by this call. The maximum must be less than or equal to 200. | [optional] [default to 50] [enum: ]
 **offset** | **Integer**| The offset of the first element to return. Starting at this offset, the query will return a maximum of &#x27;limit&#x27; elements. | [optional] [default to 0]

### Return type

[**ArrayOfReservationDefinition**](ArrayOfReservationDefinition.md)

### Authorization

[NeoloadAuthorizer](../README.md#NeoloadAuthorizer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="getZones"></a>
# **getZones**
> ArrayOfZoneDefinition getZones()

List all zones

Lists the zones.

### Example
```java
// Import classes:
//import ApiClient;
//import ApiException;
//import Configuration;
//import io.swagger.client.auth.*;
//import ResourcesApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: NeoloadAuthorizer
ApiKeyAuth NeoloadAuthorizer = (ApiKeyAuth) defaultClient.getAuthentication("NeoloadAuthorizer");
NeoloadAuthorizer.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//NeoloadAuthorizer.setApiKeyPrefix("Token");

ResourcesApi apiInstance = new ResourcesApi();
try {
    ArrayOfZoneDefinition result = apiInstance.getZones();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ResourcesApi#getZones");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**ArrayOfZoneDefinition**](ArrayOfZoneDefinition.md)

### Authorization

[NeoloadAuthorizer](../README.md#NeoloadAuthorizer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

