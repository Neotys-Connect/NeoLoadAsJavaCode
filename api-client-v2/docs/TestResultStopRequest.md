# TestResultStopRequest

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**stopPolicy** | [**StopPolicyEnum**](#StopPolicyEnum) | How to stop the test result. Default is GRACEFUL.&lt;ul&gt;&lt;li&gt;GRACEFUL stops gently the test result.&lt;/li&gt;&lt;li&gt;TERMINATE terminates the test result (kill it).&lt;/li&gt;&lt;/ul&gt; |  [optional]

<a name="StopPolicyEnum"></a>
## Enum: StopPolicyEnum
Name | Value
---- | -----
GRACEFUL | &quot;GRACEFUL&quot;
TERMINATE | &quot;TERMINATE&quot;
