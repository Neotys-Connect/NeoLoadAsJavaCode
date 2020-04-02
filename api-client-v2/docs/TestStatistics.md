# TestStatistics

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**totalRequestCountSuccess** | **Long** | Total number of requests in the scenario that did not encounter any error. |  [optional]
**totalRequestCountFailure** | **Long** | Total number of requests in the scenario that encountered errors. |  [optional]
**totalRequestDurationAverage** | **Float** | Average time in seconds for all requests of the scenario to be performed (from first byte sent to last byte received). |  [optional]
**totalRequestCountPerSecond** | **Float** | Total number of requests in the scenario that are performed per second. |  [optional]
**totalTransactionCountSuccess** | **Long** | Total number of the scenario Transactions executed without encountering any error. |  [optional]
**totalTransactionCountFailure** | **Long** | Total number of the scenario Transactions executed with errors. |  [optional]
**totalTransactionDurationAverage** | **Float** | Average time in seconds for all Transactions of the scenario to be executed. |  [optional]
**totalTransactionCountPerSecond** | **Float** | Total number of the scenario Transactions executed per second. |  [optional]
**totalIterationCountSuccess** | **Long** | Total number of times when the &#x27;Actions&#x27; Container was run for each Virtual User without encountering any error. |  [optional]
**totalIterationCountFailure** | **Long** | Total number of times when the &#x27;Actions&#x27; Container was run for each Virtual User and encountered an error. |  [optional]
**totalGlobalDownloadedBytes** | **Long** | Total size of http traffic received in bytes. |  [optional]
**totalGlobalDownloadedBytesPerSecond** | **Float** | Average size of http traffic received in bytes per second. |  [optional]
**totalGlobalCountFailure** | **Long** |  |  [optional]
**lastRequestCountPerSecond** | **Float** | Last value received for the number of requests in the scenario that are performed per second. |  [optional]
**lastTransactionDurationAverage** | **Float** | Last value received for the average time in milliseconds for all Transactions of the scenario. |  [optional]
**lastVirtualUserCount** | **Integer** | Last value received for the total number of Virtual Users executed in the test. |  [optional]
