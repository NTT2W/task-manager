package org.example.taskmanager.service;

import org.example.taskmanager.annotations.OrderType;
import org.example.taskmanager.annotations.Validate;
import org.example.taskmanager.model.Order;

import java.lang.reflect.Field;
import java.util.List;

public class OrderValidator {


    private static final List<String> VALID_TYPES = List.of("СРОЧНЫЙ", "ОБЫЧНЫЙ");

    public static boolean isValid(Order order) {
        if (order == null) return false;


        for (Field field : order.getClass().getDeclaredFields()) {
            field.setAccessible(true);

            try {
                Object value = field.get(order);

                //Обработка @Validate
                if (field.isAnnotationPresent(Validate.class)) {
                    if (value == null) {
                        Validate annotation = field.getAnnotation(Validate.class);
                        System.err.println("Ошибка валидации: " + annotation.message());
                        return false;
                    }
                }

                //Обработка @OrderType
                if (field.isAnnotationPresent(OrderType.class)) {
                    if (value == null || !VALID_TYPES.contains(value.toString())) {
                        System.err.println("Ошибка валидации: Недопустимый тип заказа: " + value);
                        return false;
                    }
                }

            } catch (IllegalAccessException e) {
                System.err.println("Ошибка доступа к полю: " + e.getMessage());
                return false;
            }
        }
        return true;
    }
}