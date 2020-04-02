# TestDefinition

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**id** | **String** | Unique identifier of the test result. |  [optional]
**name** | **String** | Name of the test result. |  [optional]
**description** | **String** | Description of the test result. |  [optional]
**author** | **String** | First and Last name of the person who launched the test. |  [optional]
**terminationReason** | [**TerminationReasonEnum**](#TerminationReasonEnum) | Reason that caused the test to end. |  [optional]
**lgCount** | **Integer** | Total number of Load Generators used in the test result. |  [optional]
**project** | **String** | Name of the project. |  [optional]
**scenario** | **String** | Name of the scenario. |  [optional]
**status** | [**StatusEnum**](#StatusEnum) | Status of the test result. |  [optional]
**qualityStatus** | [**QualityStatusEnum**](#QualityStatusEnum) | Quality status of the test result. |  [optional]
**startDate** | **Long** | Timestamp when the test started. |  [optional]
**endDate** | **Long** | Timestamp when the test ended. |  [optional]
**duration** | **Long** | Test duration in seconds. |  [optional]

<a name="TerminationReasonEnum"></a>
## Enum: TerminationReasonEnum
Name | Value
---- | -----
POLICY | &quot;POLICY&quot;
VARIABLE | &quot;VARIABLE&quot;
MANUAL | &quot;MANUAL&quot;
LG_AVAILABILITY | &quot;LG_AVAILABILITY&quot;
LICENSE | &quot;LICENSE&quot;
UNKNOWN | &quot;UNKNOWN&quot;

<a name="StatusEnum"></a>
## Enum: StatusEnum
Name | Value
---- | -----
INIT | &quot;INIT&quot;
STARTING | &quot;STARTING&quot;
RUNNING | &quot;RUNNING&quot;
TERMINATED | &quot;TERMINATED&quot;
UNKNOWN | &quot;UNKNOWN&quot;

<a name="QualityStatusEnum"></a>
## Enum: QualityStatusEnum
Name | Value
---- | -----
PASSED | &quot;PASSED&quot;
FAILED | &quot;FAILED&quot;
COMPUTING | &quot;COMPUTING&quot;
UNKNOWN | &quot;UNKNOWN&quot;
