package ru.itmo.cats;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Cat {
    @JsonProperty(value = "uniqueId")
    private long id;
    private String name;
    @JsonIgnore
    private String additionalInfo;
    private Color color;
    @JsonDeserialize(using = Category.CategoryDeserializer.class)
    private Category category;
    private ArrayList<Habit> habits;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate birth;

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", additionalInfo='" + additionalInfo + '\'' +
                ", color=" + color +
                ", category=" + category +
                ", habits=" + habits +
                ", birth=" + birth +
                '}';
    }

    public enum Color {
        BLACK, WHITE, GINGER, GRAY
    }

    public static class Habit {
        private String name;
        private String description;
        private boolean good;

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

        public boolean isGood() {
            return good;
        }

        public void setGood(boolean good) {
            this.good = good;
        }
    }

}
