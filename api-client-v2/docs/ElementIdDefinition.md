# ElementIdDefinition

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**id** | **String** | The id of the element. Can be a specific element identifier or one of the following keywords: - all-requests - all-pages - all-transactions |  [optional]
**statistics** | [**List&lt;StatisticsEnum&gt;**](#List&lt;StatisticsEnum&gt;) |  |  [optional]

<a name="List<StatisticsEnum>"></a>
## Enum: List&lt;StatisticsEnum&gt;
Name | Value
---- | -----
AVG_DURATION | &quot;AVG_DURATION&quot;
MIN_DURATION | &quot;MIN_DURATION&quot;
MAX_DURATION | &quot;MAX_DURATION&quot;
COUNT | &quot;COUNT&quot;
THROUGHPUT | &quot;THROUGHPUT&quot;
ELEMENTS_PER_SECOND | &quot;ELEMENTS_PER_SECOND&quot;
ERRORS | &quot;ERRORS&quot;
ERRORS_PER_SECOND | &quot;ERRORS_PER_SECOND&quot;
ERROR_RATE | &quot;ERROR_RATE&quot;
AVG_TTFB | &quot;AVG_TTFB&quot;
MIN_TTFB | &quot;MIN_TTFB&quot;
MAX_TTFB | &quot;MAX_TTFB&quot;
PERCENTILES_DURATION | &quot;PERCENTILES_DURATION&quot;
