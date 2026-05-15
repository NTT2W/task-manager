package org.example.taskmanager.service;

import org.example.taskmanager.model.Order;

import java.util.UUID;
import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {
    private final BlockingQueue<Order> queue;
    private final int ordersCount;

    public Producer(BlockingQueue<Order> queue, int ordersCount) {
        this.queue = queue;
        this.ordersCount = ordersCount;
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= ordersCount; i++) {
                String type = (i % 3 == 0) ? "СРОЧНЫЙ" : "ОБЫЧНЫЙ";

                //один заказ невалидный для проверки аннотации

                String id = (i == 4) ? null : UUID.randomUUID().toString().substring(0, 8);

                Order order = new Order(id, type, "Товар #" + i);

                System.out.println("Producer создал заказ: " + order);
                queue.put(order);

                Thread.sleep(300);
            }
            queue.put(new Order("POISON_PILL", "NONE", "Остановка системы"));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Producer был прерван.");
        }
    }
}