package com.neotys.ascode.swagger.client.model;

import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.IOException;
import java.util.Objects;

@Schema(description = "This object can represent a Controller or a Load Generator")
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2020-03-13T10:37:42.246Z[GMT]")
public class SimpleResourceApiDefinition {
    @SerializedName("name")
    private String name = null;

    @SerializedName("version")
    private String version = null;

    /**
     * Status of the resource
     */
    @JsonAdapter(StatusEnum.Adapter.class)
    public enum StatusEnum {
        AVAILABLE("AVAILABLE"),
        RESERVED("RESERVED"),
        BUSY("BUSY");

        private String value;

        StatusEnum(String value) {
            this.value = value;
        }
        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }
        public static StatusEnum fromValue(String text) {
            for (StatusEnum b : StatusEnum.values()) {
                if (String.valueOf(b.value).equals(text)) {
                    return b;
                }
            }
            return null;
        }
        public static class Adapter extends TypeAdapter<StatusEnum> {
            @Override
            public void write(final JsonWriter jsonWriter, final StatusEnum enumeration) throws IOException {
                jsonWriter.value(enumeration.getValue());
            }

            @Override
            public StatusEnum read(final JsonReader jsonReader) throws IOException {
                String value = jsonReader.nextString();
                return StatusEnum.fromValue(String.valueOf(value));
            }
        }
    }  @SerializedName("status")
    private StatusEnum status = null;

    public SimpleResourceApiDefinition name(String name) {
        this.name = name;
        return this;
    }

    /**
     * Name of the controller or address of the load generator
     * @return name
     **/
    @Schema(example = "resource.domain.com:7100", description = "Name of the controller or address of the load generator")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SimpleResourceApiDefinition version(String version) {
        this.version = version;
        return this;
    }

    /**
     * Version of the resource
     * @return version
     **/
    @Schema(example = "7.0.2", description = "Version of the resource")
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public SimpleResourceApiDefinition status(StatusEnum status) {
        this.status = status;
        return this;
    }

    /**
     * Status of the resource
     * @return status
     **/
    @Schema(description = "Status of the resource")
    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SimpleResourceApiDefinition simpleResourceApiDefinition = (SimpleResourceApiDefinition) o;
        return Objects.equals(this.name, simpleResourceApiDefinition.name) &&
                Objects.equals(this.version, simpleResourceApiDefinition.version) &&
                Objects.equals(this.status, simpleResourceApiDefinition.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, version, status);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class SimpleResourceApiDefinition {\n");

        sb.append("    name: ").append(toIndentedString(name)).append("\n");
        sb.append("    version: ").append(toIndentedString(version)).append("\n");
        sb.append("    status: ").append(toIndentedString(status)).append("\n");
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