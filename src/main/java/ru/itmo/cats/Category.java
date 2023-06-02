package ru.itmo.cats;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.*;

import java.io.IOException;

public class Category {
    private String name;
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public static class CategorySerializer extends JsonSerializer<Category> {

        @Override
        public void serialize(Category category, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            category.name = category.name.toLowerCase();
            category.description = category.description.toLowerCase();
            jsonGenerator.writeObject(category);
        }
    }

    public static class CategoryDeserializer extends JsonDeserializer<Category> {

        @Override
        public Category deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
            JsonNode node = deserializationContext.readTree(jsonParser);

            String name = node.get("name").asText().toUpperCase();
            String description = node.get("description").asText().toUpperCase();

            Category category = new Category();
            category.name = name;
            category.description = description;

            return category;
        }
    }
}