package org.example.taskmanager.service;

import org.example.taskmanager.model.Order;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.LinkedBlockingQueue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ConsumerTest {

    @Test
    @DisplayName("Consumer должен обрабатывать только валидные заказы и останавливаться")
    void testConsumerProcessesOrders() throws InterruptedException {
        BlockingQueue<Order> queue = new LinkedBlockingQueue<>();
        ConcurrentMap<String, Order> processedOrders = new ConcurrentHashMap<>();

        queue.put(new Order("good-id", "ОБЫЧНЫЙ", "Хороший"));
        queue.put(new Order(null, "СРОЧНЫЙ", "Плохой"));
        queue.put(new Order("POISON_PILL", "NONE", "Стоп"));

        Consumer consumer = new Consumer(queue, processedOrders, "Test-Consumer");

        consumer.run();

        assertEquals(1, processedOrders.size(), "должен быть сохранен только 1 валидный заказ");
        assertTrue(processedOrders.containsKey("good-id"), "cохраненный заказ должен иметь правильный ID");
    }
}