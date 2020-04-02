# TestSettingsCreated

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**id** | **String** | Unique identifier of the test. |  [optional]
**name** | **String** | Name of the test. |  [optional]
**description** | **String** | Description of the test. |  [optional]
**userModifierName** | **String** | Identifier of the user who modified the test settings. |  [optional]
**creationDate** | **Long** | Timestamp when the test settings was created. Number of seconds since January 1, 1970. |  [optional]
**lastUpdateDate** | **Long** | Timestamp when the test settings was updated for the last time. Number of seconds since January 1, 1970. |  [optional]
**controllerZoneId** | **String** | The identifier of the controller. |  [optional]
**lgZoneIds** | **Map&lt;String, Integer&gt;** | The LG zones with the number of the LGs. |  [optional]
**nextRunId** | [**BigDecimal**](BigDecimal.md) | The next run number. The value is incremented at each execution of the test. |  [optional]
**testResultNamingPattern** | **String** | Pattern of the test results name. |  [optional]
