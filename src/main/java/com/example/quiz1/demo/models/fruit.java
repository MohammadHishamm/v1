package com.example.quiz1.demo.models;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class fruit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)// auto increment y7ot el id lw7do
    private int ID;
    private String name;
    private String color;


    public fruit() {
    }

    public fruit(int ID, String name, String color) {
        this.ID = ID;
        this.name = name;
        this.color = color;
    }

    public int getID() {
        return this.ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return this.color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public fruit ID(int ID) {
        setID(ID);
        return this;
    }

    public fruit name(String name) {
        setName(name);
        return this;
    }

    public fruit color(String color) {
        setColor(color);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof fruit)) {
            return false;
        }
        fruit fruit = (fruit) o;
        return ID == fruit.ID && Objects.equals(name, fruit.name) && Objects.equals(color, fruit.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, name, color);
    }

    @Override
    public String toString() {
        return "{" +
            " ID='" + getID() + "'" +
            ", name='" + getName() + "'" +
            ", color='" + getColor() + "'" +
            "}";
    }
    
}
