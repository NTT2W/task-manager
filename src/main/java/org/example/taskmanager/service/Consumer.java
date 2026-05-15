package org.example.taskmanager.service;

import org.example.taskmanager.model.Order;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentMap;

public class Consumer implements Runnable {
    private final BlockingQueue<Order> queue;
    private final ConcurrentMap<String, Order> processedOrders;
    private final String consumerName;

    public Consumer(BlockingQueue<Order> queue, ConcurrentMap<String, Order> processedOrders, String name) {
        this.queue = queue;
        this.processedOrders = processedOrders;
        this.consumerName = name;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Order order = queue.take();

                if ("POISON_PILL".equals(order.getId())) {
                    queue.put(order);
                    System.out.println( consumerName + " завершает работу.");
                    break;
                }

                if (!OrderValidator.isValid(order)) {
                    System.out.println( consumerName + " отбросил невалидный заказ.");
                    continue;
                }

                System.out.println(consumerName + " взял в работу заказ: " + order.getId());
                long processTime = "СРОЧНЫЙ".equals(order.getType()) ? 500 : 1000;
                Thread.sleep(processTime);

                processedOrders.put(order.getId(), order);
                System.out.println(consumerName + " успешно обработал заказ: " + order.getId());
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println(consumerName + " был прерван.");
        }
    }
}