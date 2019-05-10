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
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.IOException;

/**
 * SLAElementDefinition
 */

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2019-05-09T16:44:40.746Z[GMT]")
public class SLAElementDefinition {

  @SerializedName("elementID")
  private String elementID = null;

  @SerializedName("name")
  private String name = null;
  /**
   * Gets or Sets category
   */
  @JsonAdapter(CategoryEnum.Adapter.class)
  public enum CategoryEnum {
    TRANSACTION("TRANSACTION"),
    PAGE("PAGE"),
    REQUEST("REQUEST");

    private String value;

    CategoryEnum(String value) {
      this.value = value;
    }
    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }
    public static CategoryEnum fromValue(String text) {
      for (CategoryEnum b : CategoryEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
    public static class Adapter extends TypeAdapter<CategoryEnum> {
      @Override
      public void write(final JsonWriter jsonWriter, final CategoryEnum enumeration) throws IOException {
        jsonWriter.value(enumeration.getValue());
      }

      @Override
      public CategoryEnum read(final JsonReader jsonReader) throws IOException {
        String value = jsonReader.nextString();
        return CategoryEnum.fromValue(String.valueOf(value));
      }
    }
  }
  @SerializedName("category")
  private CategoryEnum category = null;

  @SerializedName("userPath")
  private String userPath = null;

  @SerializedName("parent")
  private String parent = null;
  public SLAElementDefinition elementID(String elementID) {
    this.elementID = elementID;
    return this;
  }

  

  /**
  * Unique identifier of the element.
  * @return elementID
  **/
  @Schema(description = "Unique identifier of the element.")
  public String getElementID() {
    return elementID;
  }
  public void setElementID(String elementID) {
    this.elementID = elementID;
  }
  public SLAElementDefinition name(String name) {
    this.name = name;
    return this;
  }

  

  /**
  * Name of the element.
  * @return name
  **/
  @Schema(description = "Name of the element.")
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public SLAElementDefinition category(CategoryEnum category) {
    this.category = category;
    return this;
  }

  

  /**
  * Get category
  * @return category
  **/
  @Schema(description = "")
  public CategoryEnum getCategory() {
    return category;
  }
  public void setCategory(CategoryEnum category) {
    this.category = category;
  }
  public SLAElementDefinition userPath(String userPath) {
    this.userPath = userPath;
    return this;
  }

  

  /**
  * The name of the user path in which the element is.
  * @return userPath
  **/
  @Schema(description = "The name of the user path in which the element is.")
  public String getUserPath() {
    return userPath;
  }
  public void setUserPath(String userPath) {
    this.userPath = userPath;
  }
  public SLAElementDefinition parent(String parent) {
    this.parent = parent;
    return this;
  }

  

  /**
  * The parent name of the element.
  * @return parent
  **/
  @Schema(description = "The parent name of the element.")
  public String getParent() {
    return parent;
  }
  public void setParent(String parent) {
    this.parent = parent;
  }
  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SLAElementDefinition slAElementDefinition = (SLAElementDefinition) o;
    return Objects.equals(this.elementID, slAElementDefinition.elementID) &&
        Objects.equals(this.name, slAElementDefinition.name) &&
        Objects.equals(this.category, slAElementDefinition.category) &&
        Objects.equals(this.userPath, slAElementDefinition.userPath) &&
        Objects.equals(this.parent, slAElementDefinition.parent);
  }

  @Override
  public int hashCode() {
    return java.util.Objects.hash(elementID, name, category, userPath, parent);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SLAElementDefinition {\n");
    
    sb.append("    elementID: ").append(toIndentedString(elementID)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    category: ").append(toIndentedString(category)).append("\n");
    sb.append("    userPath: ").append(toIndentedString(userPath)).append("\n");
    sb.append("    parent: ").append(toIndentedString(parent)).append("\n");
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