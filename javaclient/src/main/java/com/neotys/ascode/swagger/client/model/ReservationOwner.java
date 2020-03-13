package com.neotys.ascode.swagger.client.model;

import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.IOException;
import java.util.Objects;

@Schema(description = "Owner of the reservation.")
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2020-03-13T10:37:42.246Z[GMT]")
public class ReservationOwner {
    /**
     * The unique identifier of a zone.
     */
    @JsonAdapter(TypeEnum.Adapter.class)
    public enum TypeEnum {
        USER("USER"),
        GROUP("GROUP");

        private String value;

        TypeEnum(String value) {
            this.value = value;
        }
        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }
        public static TypeEnum fromValue(String text) {
            for (TypeEnum b : TypeEnum.values()) {
                if (String.valueOf(b.value).equals(text)) {
                    return b;
                }
            }
            return null;
        }
        public static class Adapter extends TypeAdapter<TypeEnum> {
            @Override
            public void write(final JsonWriter jsonWriter, final TypeEnum enumeration) throws IOException {
                jsonWriter.value(enumeration.getValue());
            }

            @Override
            public TypeEnum read(final JsonReader jsonReader) throws IOException {
                String value = jsonReader.nextString();
                return TypeEnum.fromValue(String.valueOf(value));
            }
        }
    }  @SerializedName("type")
    private TypeEnum type = null;

    @SerializedName("name")
    private String name = null;

    public ReservationOwner type(TypeEnum type) {
        this.type = type;
        return this;
    }

    /**
     * The unique identifier of a zone.
     * @return type
     **/
    @Schema(description = "The unique identifier of a zone.")
    public TypeEnum getType() {
        return type;
    }

    public void setType(TypeEnum type) {
        this.type = type;
    }

    public ReservationOwner name(String name) {
        this.name = name;
        return this;
    }

    /**
     * The name of the user or the group.
     * @return name
     **/
    @Schema(description = "The name of the user or the group.")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ReservationOwner reservationOwner = (ReservationOwner) o;
        return Objects.equals(this.type, reservationOwner.type) &&
                Objects.equals(this.name, reservationOwner.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, name);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ReservationOwner {\n");

        sb.append("    type: ").append(toIndentedString(type)).append("\n");
        sb.append("    name: ").append(toIndentedString(name)).append("\n");
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