package org.example.taskmanager.service;

import org.example.taskmanager.model.Order;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProducerTest {

    @Test
    @DisplayName("Producer должен генерировать заданное количество заказов и POISON_PILL")
    void testProducerGeneratesOrders() {
        BlockingQueue<Order> queue = new LinkedBlockingQueue<>();
        Producer producer = new Producer(queue, 3);

        producer.run();

        assertEquals(4, queue.size(), "В очереди должно быть 4 элемента");
    }
}