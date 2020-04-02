# TestRasterConfiguration

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**width** | **Integer** | The width of the generated graph. |  [optional]
**height** | **Integer** | The height of the generated graph. |  [optional]
**title** | **String** | The main title of the generated graph. |  [optional]
**rasterType** | [**RasterTypeEnum**](#RasterTypeEnum) | The type of the generated graph. Can be PNG, TIFF or JPEG. Default is PNG. |  [optional]
**xAxisLabel** | **String** | The xAxis label of the generated graph. |  [optional]
**yAxisLabel** | **String** | The yAxis label of the generated graph. |  [optional]
**legend** | **Boolean** | If true the legend is displayed. Default is true. |  [optional]
**multiYAxis** | **Boolean** | If true, display one axis per serie. Default is false. |  [optional]
**theme** | [**ThemeEnum**](#ThemeEnum) | The theme of the graph. Default is TRANSPARENT. |  [optional]
**elementIds** | [**List&lt;ElementIdDefinition&gt;**](ElementIdDefinition.md) | The list of elements. |  [optional]
**counterIds** | **List&lt;String&gt;** | The list of counters. |  [optional]

<a name="RasterTypeEnum"></a>
## Enum: RasterTypeEnum
Name | Value
---- | -----
PNG | &quot;PNG&quot;
TIFF | &quot;TIFF&quot;
JPEG | &quot;JPEG&quot;

<a name="ThemeEnum"></a>
## Enum: ThemeEnum
Name | Value
---- | -----
DARK | &quot;DARK&quot;
LIGHT | &quot;LIGHT&quot;
TRANSPARENT | &quot;TRANSPARENT&quot;
