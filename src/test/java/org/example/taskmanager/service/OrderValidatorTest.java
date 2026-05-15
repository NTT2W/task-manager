package org.example.taskmanager.service;

import org.example.taskmanager.model.Order;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class OrderValidatorTest {

    @Test
    @DisplayName("Должен подтвердить валидность корректного заказа")
    void shouldValidateCorrectOrder() {
        Order order = new Order("123", "ОБЫЧНЫЙ", "Тест");
        assertTrue(OrderValidator.isValid(order), "Заказ должен быть валидным");
    }

    @Test
    @DisplayName("Должен отклонить заказ с null ID")
    void shouldRejectOrderWithNullId() {
        Order order = new Order(null, "СРОЧНЫЙ", "Тест");
        assertFalse(OrderValidator.isValid(order), "Заказ с null ID не должен пройти валидацию");
    }
}