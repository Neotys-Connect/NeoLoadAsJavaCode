/*
 * NeoLoad API
 * No description provided (generated by Swagger Codegen https://github.com/swagger-api/swagger-codegen)
 *
 * OpenAPI spec version: 3.0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */

package com.neotys.ascode.api.v3.client.model;

import java.util.Objects;
import java.util.Arrays;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.IOException;
/**
 * Existing error codes are: - HTTP 401 | Code: 101 | Message : Unauthorized, please provide an API token. - HTTP 429 | Code: 102 | Message : Too many requests, you reached your maximum number of requests per minute. - HTTP 403 | Code: 103 | Message : Unauthorized operation for the given API token, please provide an API token with sufficient privilege - HTTP 400 | Code: 104 | Message : Error while parsing json body. - HTTP 400 | Code: 105 | Message : Entity not in the specified Workspace. - HTTP 404 | Code: 201 | Message : Test result not found. - HTTP 400 | Code: 202 | Message : Error while requesting the test result - HTTP 400 | Code: 203 | Message : Error while searching the test result - HTTP 400 | Code: 204 | Message : Type is mandatory as request parameter. - HTTP 400 | Code: 205 | Message : Invalid element type in request. - HTTP 400 | Code: 206 | Message : Error requesting aggregated values. - HTTP 400 | Code: 207 | Message : Error requesting elements. - HTTP 400 | Code: 208 | Message : Element ID is mandatory in request path. - HTTP 400 | Code: 209 | Message : Invalid element id in request. - HTTP 400 | Code: 210 | Message : Statistics is mandatory in request path. - HTTP 400 | Code: 211 | Message : Invalid statistic in request. - HTTP 400 | Code: 212 | Message : Error getting monitors. - HTTP 400 | Code: 213 | Message : Error getting monitor values. - HTTP 400 | Code: 214 | Message : Error getting monitor points. - HTTP 400 | Code: 215 | Message : Monitor ID is mandatory in request path. - HTTP 400 | Code: 216 | Message : Test ID is mandatory in request path. - HTTP 400 | Code: 217 | Message : Error while deleting the test. This test is not TERMINATED. - HTTP 400 | Code: 218 | Message : Error while deleting the test. - HTTP 400 | Code: 219 | Message : Error requesting aggregated points. - HTTP 400 | Code: 220 | Message : Error requesting SLA status for element. - HTTP 400 | Code: 221 | Message : Error updating a test. - HTTP 404 | Code: 222 | Message : Error updating a test. - HTTP 204 | Code: 223 | Message : Update successful, no content. - HTTP 400 | Code: 224 | Message : Error while parsing json body for updating a test. - HTTP 400 | Code: 225 | Message : Error while parsing json body for custom monitors. - HTTP 400 | Code: 226 | Message : Error while creating custom monitors. - HTTP 400 | Code: 227 | Message : Path too long. - HTTP 400 | Code: 228 | Message : Clock Synchronization Issue: timestamp received before the beginning of the test. - HTTP 400 | Code: 229 | Message : Clock Synchronization Issue: timestamp received more than 10 minutes in the future. - HTTP 400 | Code: 230 | Message : Duplicate key: same timestamp and same path received in multiple requests. - HTTP 400 | Code: 231 | Message : Max number of Paths reached. - HTTP 400 | Code: 232 | Message : Max number of Monitors reached. - HTTP 400 | Code: 233 | Message : &#x27;|&#x27; character is not allowed. - HTTP 400 | Code: 234 | Message : A test is already launching. - HTTP 400 | Code: 235 | Message : Error while parsing the LG zones parameter. - HTTP 400 | Code: 236 | Message : NeoLoad project file ID does not exist. - HTTP 400 | Code: 237 | Message : NeoLoad project not found. - HTTP 400 | Code: 238 | Message : Upload project URL not found. - HTTP 400 | Code: 239 | Message : The scenario does not exist. - HTTP 400 | Code: 240 | Message : The size of the image to generate is invalid. - HTTP 400 | Code: 241 | Message : Reservation parameters are invalid. - HTTP 400 | Code: 242 | Message : Reservation duration must be at least 60 seconds. - HTTP 400 | Code: 243 | Message : Reservation not found. - HTTP 400 | Code: 244 | Message : Reservation is failed or not ready to use. - HTTP 400 | Code: 245 | Message : Reservation not matching scenario requirements: Number of virtual users. - HTTP 400 | Code: 246 | Message : Unauthorized Reservation - HTTP 400 | Code: 247 | Message : Error requesting SLA Per-Run of the test result. - HTTP 400 | Code: 248 | Message : Error requesting SLA Per-Run while the test is not terminated. - HTTP 400 | Code: 249 | Message : As code file extension must be yaml, yml or json. - HTTP 400 | Code: 250 | Message : Error getting test results events. - HTTP 400 | Code: 251 | Message : Error requesting element. - HTTP 400 | Code: 252 | Message : Error requesting counter. - HTTP 400 | Code: 253 | Message : Invalid monitor id in request. - HTTP 400 | Code: 254 | Message : Invalid as code file path. - HTTP 400 | Code: 255 | Message : Error requesting all zones. - HTTP 400 | Code: 256 | Message : Error requesting all reservations. - HTTP 400 | Code: 257 | Message : The date is not in the right format. - HTTP 400 | Code: 260 | Message : Requested statistics are not valid, PERCENTILES_DURATION must not be mixed with other element statistics neither with monitor statistic. - HTTP 400 | Code: 261 | Message : A test with the same name already exists. - HTTP 400 | Code: 262 | Message : Error while processing json body. It is not a valid json. - HTTP 400 | Code: 263 | Message : The name property is required. - HTTP 400 | Code: 264 | Message : The property lgZones is a bad format. - HTTP 404 | Code: 265 | Message : Test not found. - HTTP 400 | Code: 275 | Message : Unknown stopPolicy value. Allowed are TERMINATE or GRACEFUL. - HTTP 400 | Code: 276 | Message : Error stopping the test. - HTTP 400 | Code: 277 | Message : Error stopping the test. The test is not running. - HTTP 400 | Code: 278 | Message : Zone does not exist. - HTTP 400 | Code: 279 | Message : Error starting the test. - HTTP 503 | Code: 300 | Message : An unexpected error occurred while processing the request. - HTTP 501 | Code: 301 | Message : Not supported when the reservation mode is enabled. - HTTP 501 | Code: 302 | Message : Not supported when the reservation mode is OFF. 
 */
@Schema(description = "Existing error codes are: - HTTP 401 | Code: 101 | Message : Unauthorized, please provide an API token. - HTTP 429 | Code: 102 | Message : Too many requests, you reached your maximum number of requests per minute. - HTTP 403 | Code: 103 | Message : Unauthorized operation for the given API token, please provide an API token with sufficient privilege - HTTP 400 | Code: 104 | Message : Error while parsing json body. - HTTP 400 | Code: 105 | Message : Entity not in the specified Workspace. - HTTP 404 | Code: 201 | Message : Test result not found. - HTTP 400 | Code: 202 | Message : Error while requesting the test result - HTTP 400 | Code: 203 | Message : Error while searching the test result - HTTP 400 | Code: 204 | Message : Type is mandatory as request parameter. - HTTP 400 | Code: 205 | Message : Invalid element type in request. - HTTP 400 | Code: 206 | Message : Error requesting aggregated values. - HTTP 400 | Code: 207 | Message : Error requesting elements. - HTTP 400 | Code: 208 | Message : Element ID is mandatory in request path. - HTTP 400 | Code: 209 | Message : Invalid element id in request. - HTTP 400 | Code: 210 | Message : Statistics is mandatory in request path. - HTTP 400 | Code: 211 | Message : Invalid statistic in request. - HTTP 400 | Code: 212 | Message : Error getting monitors. - HTTP 400 | Code: 213 | Message : Error getting monitor values. - HTTP 400 | Code: 214 | Message : Error getting monitor points. - HTTP 400 | Code: 215 | Message : Monitor ID is mandatory in request path. - HTTP 400 | Code: 216 | Message : Test ID is mandatory in request path. - HTTP 400 | Code: 217 | Message : Error while deleting the test. This test is not TERMINATED. - HTTP 400 | Code: 218 | Message : Error while deleting the test. - HTTP 400 | Code: 219 | Message : Error requesting aggregated points. - HTTP 400 | Code: 220 | Message : Error requesting SLA status for element. - HTTP 400 | Code: 221 | Message : Error updating a test. - HTTP 404 | Code: 222 | Message : Error updating a test. - HTTP 204 | Code: 223 | Message : Update successful, no content. - HTTP 400 | Code: 224 | Message : Error while parsing json body for updating a test. - HTTP 400 | Code: 225 | Message : Error while parsing json body for custom monitors. - HTTP 400 | Code: 226 | Message : Error while creating custom monitors. - HTTP 400 | Code: 227 | Message : Path too long. - HTTP 400 | Code: 228 | Message : Clock Synchronization Issue: timestamp received before the beginning of the test. - HTTP 400 | Code: 229 | Message : Clock Synchronization Issue: timestamp received more than 10 minutes in the future. - HTTP 400 | Code: 230 | Message : Duplicate key: same timestamp and same path received in multiple requests. - HTTP 400 | Code: 231 | Message : Max number of Paths reached. - HTTP 400 | Code: 232 | Message : Max number of Monitors reached. - HTTP 400 | Code: 233 | Message : '|' character is not allowed. - HTTP 400 | Code: 234 | Message : A test is already launching. - HTTP 400 | Code: 235 | Message : Error while parsing the LG zones parameter. - HTTP 400 | Code: 236 | Message : NeoLoad project file ID does not exist. - HTTP 400 | Code: 237 | Message : NeoLoad project not found. - HTTP 400 | Code: 238 | Message : Upload project URL not found. - HTTP 400 | Code: 239 | Message : The scenario does not exist. - HTTP 400 | Code: 240 | Message : The size of the image to generate is invalid. - HTTP 400 | Code: 241 | Message : Reservation parameters are invalid. - HTTP 400 | Code: 242 | Message : Reservation duration must be at least 60 seconds. - HTTP 400 | Code: 243 | Message : Reservation not found. - HTTP 400 | Code: 244 | Message : Reservation is failed or not ready to use. - HTTP 400 | Code: 245 | Message : Reservation not matching scenario requirements: Number of virtual users. - HTTP 400 | Code: 246 | Message : Unauthorized Reservation - HTTP 400 | Code: 247 | Message : Error requesting SLA Per-Run of the test result. - HTTP 400 | Code: 248 | Message : Error requesting SLA Per-Run while the test is not terminated. - HTTP 400 | Code: 249 | Message : As code file extension must be yaml, yml or json. - HTTP 400 | Code: 250 | Message : Error getting test results events. - HTTP 400 | Code: 251 | Message : Error requesting element. - HTTP 400 | Code: 252 | Message : Error requesting counter. - HTTP 400 | Code: 253 | Message : Invalid monitor id in request. - HTTP 400 | Code: 254 | Message : Invalid as code file path. - HTTP 400 | Code: 255 | Message : Error requesting all zones. - HTTP 400 | Code: 256 | Message : Error requesting all reservations. - HTTP 400 | Code: 257 | Message : The date is not in the right format. - HTTP 400 | Code: 260 | Message : Requested statistics are not valid, PERCENTILES_DURATION must not be mixed with other element statistics neither with monitor statistic. - HTTP 400 | Code: 261 | Message : A test with the same name already exists. - HTTP 400 | Code: 262 | Message : Error while processing json body. It is not a valid json. - HTTP 400 | Code: 263 | Message : The name property is required. - HTTP 400 | Code: 264 | Message : The property lgZones is a bad format. - HTTP 404 | Code: 265 | Message : Test not found. - HTTP 400 | Code: 275 | Message : Unknown stopPolicy value. Allowed are TERMINATE or GRACEFUL. - HTTP 400 | Code: 276 | Message : Error stopping the test. - HTTP 400 | Code: 277 | Message : Error stopping the test. The test is not running. - HTTP 400 | Code: 278 | Message : Zone does not exist. - HTTP 400 | Code: 279 | Message : Error starting the test. - HTTP 503 | Code: 300 | Message : An unexpected error occurred while processing the request. - HTTP 501 | Code: 301 | Message : Not supported when the reservation mode is enabled. - HTTP 501 | Code: 302 | Message : Not supported when the reservation mode is OFF. ")
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2020-09-01T07:09:13.021Z[GMT]")
public class Error {
  @SerializedName("code")
  private Integer code = null;

  @SerializedName("message")
  private String message = null;

  public Error code(Integer code) {
    this.code = code;
    return this;
  }

   /**
   * Get code
   * @return code
  **/
  @Schema(description = "")
  public Integer getCode() {
    return code;
  }

  public void setCode(Integer code) {
    this.code = code;
  }

  public Error message(String message) {
    this.message = message;
    return this;
  }

   /**
   * Get message
   * @return message
  **/
  @Schema(description = "")
  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Error error = (Error) o;
    return Objects.equals(this.code, error.code) &&
        Objects.equals(this.message, error.message);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code, message);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Error {\n");
    
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}