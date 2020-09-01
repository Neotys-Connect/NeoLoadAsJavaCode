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
import  com.neotys.ascode.api.v3.client.model.CustomMonitor;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/**
 * MonitorPostRequest
 */

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2020-09-01T07:09:13.021Z[GMT]")
public class MonitorPostRequest {
  @SerializedName("monitors")
  private List<CustomMonitor> monitors = null;

  public MonitorPostRequest monitors(List<CustomMonitor> monitors) {
    this.monitors = monitors;
    return this;
  }

  public MonitorPostRequest addMonitorsItem(CustomMonitor monitorsItem) {
    if (this.monitors == null) {
      this.monitors = new ArrayList<CustomMonitor>();
    }
    this.monitors.add(monitorsItem);
    return this;
  }

   /**
   * Get monitors
   * @return monitors
  **/
  @Schema(description = "")
  public List<CustomMonitor> getMonitors() {
    return monitors;
  }

  public void setMonitors(List<CustomMonitor> monitors) {
    this.monitors = monitors;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MonitorPostRequest monitorPostRequest = (MonitorPostRequest) o;
    return Objects.equals(this.monitors, monitorPostRequest.monitors);
  }

  @Override
  public int hashCode() {
    return Objects.hash(monitors);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MonitorPostRequest {\n");
    
    sb.append("    monitors: ").append(toIndentedString(monitors)).append("\n");
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