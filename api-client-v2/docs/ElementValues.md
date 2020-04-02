# ElementValues

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**count** | **Long** | Count statistics are the number of full executions of an element of a User Path. If the element is interrupted (because of error or end of test), then the count number is not incremented. |  [optional]
**elementPerSecond** | **Float** | Number of iterations of the element per second. |  [optional]
**minDuration** | **Long** | Shortest response time, in milliseconds. |  [optional]
**maxDuration** | **Long** | Longest response time, in milliseconds. |  [optional]
**sumDuration** | **Long** | Sum of response time of all iterations, in milliseconds. |  [optional]
**avgDuration** | **Float** | Average response time, in milliseconds. |  [optional]
**minTTFB** | **Long** | Shortest time to first byte, in milliseconds. |  [optional]
**maxTTFB** | **Long** | Longest time to first byte, in milliseconds. |  [optional]
**sumTTFB** | **Long** | Sum of time to first byte of all iterations, in milliseconds. |  [optional]
**avgTTFB** | **Float** | Average time to first byte, in milliseconds. |  [optional]
**sumDownloadedBytes** | **Long** | Total size of network traffic for the element, in bytes. |  [optional]
**downloadedBytesPerSecond** | **Float** | Average size of network traffic for the element, in bytes per seconds. |  [optional]
**successCount** | **Long** | Count of succeeded iterations. |  [optional]
**successPerSecond** | **Float** | Count of succeeded iterations per second. |  [optional]
**successRate** | **Float** | Percentage of succeeded iterations out of count. |  [optional]
**failureCount** | **Long** | Count of failed iterations. |  [optional]
**failurePerSecond** | **Float** | Count of failed iterations per second. |  [optional]
**failureRate** | **Float** | Percentage of failed iterations out of count. |  [optional]
**percentile50** | **Float** | 50th percentile of the element duration, in milliseconds. Requires at least NeoLoad 7.1. Only available when test is terminated. |  [optional]
**percentile90** | **Float** | 90th percentile of the element duration, in milliseconds. Requires at least NeoLoad 7.1. Only available when test is terminated. |  [optional]
**percentile95** | **Float** | 95th percentile of the element duration, in milliseconds. Requires at least NeoLoad 7.1. Only available when test is terminated. |  [optional]
**percentile99** | **Float** | 99th percentile of the element duration, in milliseconds. Requires at least NeoLoad 7.1. Only available when test is terminated. |  [optional]
