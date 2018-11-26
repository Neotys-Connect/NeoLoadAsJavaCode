/*
 * NeoLoad API
 * No description provided (generated by Swagger Codegen https://github.com/swagger-api/swagger-codegen)
 *
 * OpenAPI spec version: 1.0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package com.neotys.ascode.swagger.client.model;

import java.util.Objects;

import com.google.gson.annotations.SerializedName;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * CounterValues
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.java.JavaClientCodegen", date = "2018-09-05T15:48:08.071+02:00[Europe/Paris]")
public class CounterValues {

  @SerializedName("count")
  private Long count = null;
  
  @SerializedName("min")
  private Long min = null;
  
  @SerializedName("max")
  private Long max = null;
  
  @SerializedName("sum")
  private Long sum = null;
  
  @SerializedName("avg")
  private Float avg = null;
  
  public CounterValues count(Long count) {
    this.count = count;
    return this;
  }

  
  /**
  * Get count
  * @return count
  **/
  
  
  @Schema(description = "")
  public Long getCount() {
    return count;
  }
  public void setCount(Long count) {
    this.count = count;
  }
  
  public CounterValues min(Long min) {
    this.min = min;
    return this;
  }

  
  /**
  * Get min
  * @return min
  **/
  
  
  @Schema(description = "")
  public Long getMin() {
    return min;
  }
  public void setMin(Long min) {
    this.min = min;
  }
  
  public CounterValues max(Long max) {
    this.max = max;
    return this;
  }

  
  /**
  * Get max
  * @return max
  **/
  
  
  @Schema(description = "")
  public Long getMax() {
    return max;
  }
  public void setMax(Long max) {
    this.max = max;
  }
  
  public CounterValues sum(Long sum) {
    this.sum = sum;
    return this;
  }

  
  /**
  * Get sum
  * @return sum
  **/
  
  
  @Schema(description = "")
  public Long getSum() {
    return sum;
  }
  public void setSum(Long sum) {
    this.sum = sum;
  }
  
  public CounterValues avg(Float avg) {
    this.avg = avg;
    return this;
  }

  
  /**
  * Get avg
  * @return avg
  **/
  
  
  @Schema(description = "")
  public Float getAvg() {
    return avg;
  }
  public void setAvg(Float avg) {
    this.avg = avg;
  }
  
  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CounterValues counterValues = (CounterValues) o;
    return Objects.equals(this.count, counterValues.count) &&
        Objects.equals(this.min, counterValues.min) &&
        Objects.equals(this.max, counterValues.max) &&
        Objects.equals(this.sum, counterValues.sum) &&
        Objects.equals(this.avg, counterValues.avg);
  }

  @Override
  public int hashCode() {
    return java.util.Objects.hash(count, min, max, sum, avg);
  }
  
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CounterValues {\n");
    
    sb.append("    count: ").append(toIndentedString(count)).append("\n");
    sb.append("    min: ").append(toIndentedString(min)).append("\n");
    sb.append("    max: ").append(toIndentedString(max)).append("\n");
    sb.append("    sum: ").append(toIndentedString(sum)).append("\n");
    sb.append("    avg: ").append(toIndentedString(avg)).append("\n");
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


