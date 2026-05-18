package org.example.taskmanager.service;

import org.example.taskmanager.model.Order;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OrderValidatorTest {

    @Test
    @DisplayName("Должен подтвердить валидность корректного заказа")
    void shouldValidateCorrectOrder() {
        Order order = new Order("ID-100", "ОБЫЧНЫЙ", "Описание товара");
        assertTrue(OrderValidator.isValid(order), "Корректный заказ должен пройти валидацию");
    }

    @Test
    @DisplayName("Тест @Validate: должен отклонить заказ с null ID")
    void shouldRejectOrderWithNullId() {
        Order order = new Order(null, "СРОЧНЫЙ", "Описание товара");
        assertFalse(OrderValidator.isValid(order), "Заказ с null ID должен быть отклонен");
    }

    @Test
    @DisplayName("Тест @OrderType: должен отклонить недопустимый тип заказа")
    void shouldRejectInvalidOrderType() {
        // "ЭКСПРЕСС" не входит в список разрешенных ("СРОЧНЫЙ", "ОБЫЧНЫЙ")
        Order order = new Order("ID-200", "ЭКСПРЕСС", "Описание товара");
        assertFalse(OrderValidator.isValid(order), "Недопустимый тип заказа должен быть отклонен");
    }

    @Test
    @DisplayName("Тест @OrderType: должен отклонить null в поле типа заказа")
    void shouldRejectNullOrderType() {
        Order order = new Order("ID-300", null, "Описание товара");
        assertFalse(OrderValidator.isValid(order), "Null в поле типа заказа должен быть отклонен");
    }
}