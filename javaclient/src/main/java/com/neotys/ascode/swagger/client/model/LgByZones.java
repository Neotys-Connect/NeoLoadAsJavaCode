package com.neotys.ascode.swagger.client.model;

import java.util.Objects;
import java.util.Arrays;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.IOException;
import java.math.BigDecimal;
/**
 * The number of load generators reserved for a specific zone.
 */
@Schema(description = "The number of load generators reserved for a specific zone.")
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2020-03-13T10:37:42.246Z[GMT]")
public class LgByZones {
    @SerializedName("zoneId")
    private String zoneId = null;

    @SerializedName("value")
    private BigDecimal value = null;

    public LgByZones zoneId(String zoneId) {
        this.zoneId = zoneId;
        return this;
    }

    /**
     * The unique identifier of a zone.
     * @return zoneId
     **/
    @Schema(example = "hIugt", description = "The unique identifier of a zone.")
    public String getZoneId() {
        return zoneId;
    }

    public void setZoneId(String zoneId) {
        this.zoneId = zoneId;
    }

    public LgByZones value(BigDecimal value) {
        this.value = value;
        return this;
    }

    /**
     * The number of load generators.
     * @return value
     **/
    @Schema(example = "10", description = "The number of load generators.")
    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LgByZones lgByZones = (LgByZones) o;
        return Objects.equals(this.zoneId, lgByZones.zoneId) &&
                Objects.equals(this.value, lgByZones.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(zoneId, value);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class LgByZones {\n");

        sb.append("    zoneId: ").append(toIndentedString(zoneId)).append("\n");
        sb.append("    value: ").append(toIndentedString(value)).append("\n");
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