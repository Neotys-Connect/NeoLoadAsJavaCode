# RuntimeApi

All URIs are relative to */v1*

Method | HTTP request | Description
------------- | ------------- | -------------
[**getTestsRun**](RuntimeApi.md#getTestsRun) | **POST** /projects/{projectId}/run | Runs a test
[**postUploadProject**](RuntimeApi.md#postUploadProject) | **POST** /projects | Uploads a NeoLoad project zip file




<a name="getTestsRun"></a>
# **getTestsRun**
> RunTestDefinition getTestsRun(name, projectId, scenarioName, description, controllerZoneId, lgZones, publishTestResult)

Runs a test

Runs a test of the Account according to the method parameters.

### Example
```java
// Import classes:
//import com.neotys.ascode.swagger.client.ApiException;
//import RuntimeApi;



RuntimeApi apiInstance = new RuntimeApi();

String name = "name_example"; // String | The name of the test

String projectId = "projectId_example"; // String | The project Id of the test

String scenarioName = "scenarioName_example"; // String | The scenario name of the test

String description = "description_example"; // String | The description of the test

String controllerZoneId = "controllerZoneId_example"; // String | The controller zone Id. If empty, the default zone will be used.

String lgZones = "lgZones_example"; // String | The LG zones with the number of the LGs. Example: \"ZoneId1:10,ZoneId2:5\". If empty, the default zone will be used with one LG.

Boolean publishTestResult = true; // Boolean | When \"true\" and the project is an collaborative project (other than git) then the test result is published onto the server. If empty, the default value is \"false\".

try {
    RunTestDefinition result = apiInstance.getTestsRun(name, projectId, scenarioName, description, controllerZoneId, lgZones, publishTestResult);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling RuntimeApi#getTestsRun");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **name** | **String**| The name of the test |
 **projectId** | **String**| The project Id of the test |
 **scenarioName** | **String**| The scenario name of the test |
 **description** | **String**| The description of the test | [optional]
 **controllerZoneId** | **String**| The controller zone Id. If empty, the default zone will be used. | [optional]
 **lgZones** | **String**| The LG zones with the number of the LGs. Example: \&quot;ZoneId1:10,ZoneId2:5\&quot;. If empty, the default zone will be used with one LG. | [optional]
 **publishTestResult** | **Boolean**| When \&quot;true\&quot; and the project is an collaborative project (other than git) then the test result is published onto the server. If empty, the default value is \&quot;false\&quot;. | [optional]


### Return type

[**RunTestDefinition**](RunTestDefinition.md)

### Authorization

[NeoloadAuthorizer](../README.md#NeoloadAuthorizer)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json


<a name="postUploadProject"></a>
# **postUploadProject**
> ProjectDefinition postUploadProject()

Uploads a NeoLoad project zip file

Uploads a NeoLoad project of the account corresponding to the parameters. The maximum size file is 100 MB

### Example
```java
// Import classes:
//import com.neotys.ascode.swagger.client.ApiException;
//import RuntimeApi;



RuntimeApi apiInstance = new RuntimeApi();

try {
    ProjectDefinition result = apiInstance.postUploadProject();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling RuntimeApi#postUploadProject");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.


### Return type

[**ProjectDefinition**](ProjectDefinition.md)

### Authorization

[NeoloadAuthorizer](../README.md#NeoloadAuthorizer)

### HTTP request headers

 - **Content-Type**: multipart/form-data
 - **Accept**: application/json



