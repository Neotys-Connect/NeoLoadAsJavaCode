# ReservationDefinition

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**id** | **String** | Unique identifier of the reservation. |  [optional]
**status** | [**StatusEnum**](#StatusEnum) | Status of the reservation. |  [optional]
**startDateTime** | [**BigDecimal**](BigDecimal.md) | Timestamp when the reservation begins. Number of seconds since January 1, 1970. |  [optional]
**endDateTime** | [**BigDecimal**](BigDecimal.md) | Timestamp when the reservation ends. Number of seconds since January 1, 1970. |  [optional]
**reservationWebVUs** | [**BigDecimal**](BigDecimal.md) | The number of Web Virtual Users to be reserved. |  [optional]
**reservationSAPVUs** | [**BigDecimal**](BigDecimal.md) | The number of SAP Virtual Users to be reserved. |  [optional]
**reservationCitrixVUs** | [**BigDecimal**](BigDecimal.md) | The number of Citrix Virtual Users to be reserved. |  [optional]
**controllerZoneId** | **String** | Name of the zone. |  [optional]
**neoloadVersion** | **String** | Neoload version of the selected controller. |  [optional]
**lgZonesResourcesReservation** | [**List&lt;LgByZones&gt;**](LgByZones.md) |  |  [optional]
**author** | **String** | Name of the user who created the reservation. |  [optional]
**owner** | [**ReservationOwner**](ReservationOwner.md) |  |  [optional]
**name** | **String** | Title of the reservation. |  [optional]
**description** | **String** | Description of the reservation. |  [optional]
**type** | [**TypeEnum**](#TypeEnum) | How the reservation has been created. By a user or automatically when a test started. |  [optional]

<a name="StatusEnum"></a>
## Enum: StatusEnum
Name | Value
---- | -----
WAITING | &quot;WAITING&quot;
RUNNING | &quot;RUNNING&quot;
PARTIALLY_RESERVED | &quot;PARTIALLY_RESERVED&quot;
FAILED_TO_RESERVE | &quot;FAILED_TO_RESERVE&quot;
RESERVED | &quot;RESERVED&quot;
STOPPING | &quot;STOPPING&quot;
ENDED | &quot;ENDED&quot;

<a name="TypeEnum"></a>
## Enum: TypeEnum
Name | Value
---- | -----
SCHEDULED | &quot;SCHEDULED&quot;
AUTO_RESERVATION | &quot;AUTO_RESERVATION&quot;
