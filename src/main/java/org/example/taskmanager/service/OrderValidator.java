package org.example.taskmanager.service;

import org.example.taskmanager.annotations.Validate;
import org.example.taskmanager.model.Order;

import java.lang.reflect.Field;

public class OrderValidator {

    public static boolean isValid(Order order) {
        if (order == null) return false;

        for (Field field : order.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(Validate.class)) {
                field.setAccessible(true);
                try {
                    Object value = field.get(order);
                    if (value == null) {
                        Validate annotation = field.getAnnotation(Validate.class);
                        System.err.println("Ошибка валидации: " + annotation.message());
                        return false;
                    }
                } catch (IllegalAccessException e) {
                    System.err.println("Ошибка доступа к полю: " + e.getMessage());
                    return false;
                }
            }
        }
        return true;
    }
}