package com.neotys.ascode.swagger.client.model;


import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2020-03-13T10:37:42.246Z[GMT]")
public class ZoneDefinition {
    @SerializedName("id")
    private String id = null;

    @SerializedName("name")
    private String name = null;

    /**
     * Type of the zone
     */
    @JsonAdapter(TypeEnum.Adapter.class)
    public enum TypeEnum {
        STATIC("STATIC"),
        DYNAMIC("DYNAMIC");

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

    @SerializedName("controllers")
    private List<SimpleResourceApiDefinition> controllers = null;

    @SerializedName("loadgenerators")
    private List<SimpleResourceApiDefinition> loadgenerators = null;

    public ZoneDefinition id(String id) {
        this.id = id;
        return this;
    }

    /**
     * Unique identifier of the zone
     * @return id
     **/
    @Schema(example = "hIugt", description = "Unique identifier of the zone")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ZoneDefinition name(String name) {
        this.name = name;
        return this;
    }

    /**
     * Name of the zone
     * @return name
     **/
    @Schema(example = "zone name", description = "Name of the zone")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ZoneDefinition type(TypeEnum type) {
        this.type = type;
        return this;
    }

    /**
     * Type of the zone
     * @return type
     **/
    @Schema(description = "Type of the zone")
    public TypeEnum getType() {
        return type;
    }

    public void setType(TypeEnum type) {
        this.type = type;
    }

    public ZoneDefinition controllers(List<SimpleResourceApiDefinition> controllers) {
        this.controllers = controllers;
        return this;
    }

    public ZoneDefinition addControllersItem(SimpleResourceApiDefinition controllersItem) {
        if (this.controllers == null) {
            this.controllers = new ArrayList<SimpleResourceApiDefinition>();
        }
        this.controllers.add(controllersItem);
        return this;
    }

    /**
     * Get controllers
     * @return controllers
     **/
    @Schema(description = "")
    public List<SimpleResourceApiDefinition> getControllers() {
        return controllers;
    }

    public void setControllers(List<SimpleResourceApiDefinition> controllers) {
        this.controllers = controllers;
    }

    public ZoneDefinition loadgenerators(List<SimpleResourceApiDefinition> loadgenerators) {
        this.loadgenerators = loadgenerators;
        return this;
    }

    public ZoneDefinition addLoadgeneratorsItem(SimpleResourceApiDefinition loadgeneratorsItem) {
        if (this.loadgenerators == null) {
            this.loadgenerators = new ArrayList<SimpleResourceApiDefinition>();
        }
        this.loadgenerators.add(loadgeneratorsItem);
        return this;
    }

    /**
     * Get loadgenerators
     * @return loadgenerators
     **/
    @Schema(description = "")
    public List<SimpleResourceApiDefinition> getLoadgenerators() {
        return loadgenerators;
    }

    public void setLoadgenerators(List<SimpleResourceApiDefinition> loadgenerators) {
        this.loadgenerators = loadgenerators;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ZoneDefinition zoneDefinition = (ZoneDefinition) o;
        return Objects.equals(this.id, zoneDefinition.id) &&
                Objects.equals(this.name, zoneDefinition.name) &&
                Objects.equals(this.type, zoneDefinition.type) &&
                Objects.equals(this.controllers, zoneDefinition.controllers) &&
                Objects.equals(this.loadgenerators, zoneDefinition.loadgenerators);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, type, controllers, loadgenerators);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ZoneDefinition {\n");

        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    name: ").append(toIndentedString(name)).append("\n");
        sb.append("    type: ").append(toIndentedString(type)).append("\n");
        sb.append("    controllers: ").append(toIndentedString(controllers)).append("\n");
        sb.append("    loadgenerators: ").append(toIndentedString(loadgenerators)).append("\n");
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