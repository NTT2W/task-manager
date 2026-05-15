package org.example.taskmanager.model;

import org.example.taskmanager.annotations.OrderType;
import org.example.taskmanager.annotations.Validate;

public class Order {
    @Validate(message = "ID заказа не может быть null")
    private String id;

    @OrderType
    private String type;

    private String description;

    public Order(String id, String type, String description) {
        this.id = id;
        this.type = type;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return String.format("Order{id='%s', type='%s', desc='%s'}", id, type, description);
    }
}