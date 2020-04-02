# ZoneDefinition

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**id** | **String** | Unique identifier of the zone |  [optional]
**name** | **String** | Name of the zone |  [optional]
**type** | [**TypeEnum**](#TypeEnum) | Type of the zone |  [optional]
**controllers** | [**List&lt;SimpleResourceApiDefinition&gt;**](SimpleResourceApiDefinition.md) |  |  [optional]
**loadgenerators** | [**List&lt;SimpleResourceApiDefinition&gt;**](SimpleResourceApiDefinition.md) |  |  [optional]

<a name="TypeEnum"></a>
## Enum: TypeEnum
Name | Value
---- | -----
STATIC | &quot;STATIC&quot;
DYNAMIC | &quot;DYNAMIC&quot;
