package org.example.taskmanager;

import org.example.taskmanager.model.Order;
import org.example.taskmanager.service.Consumer;
import org.example.taskmanager.service.Producer;

import java.util.concurrent.*;

public class TaskManager {
    public static void main(String[] args) {
        System.out.println("Запуск системы обработки заказов");

        BlockingQueue<Order> orderQueue = new LinkedBlockingQueue<>(10);
        ConcurrentMap<String, Order> processedOrders = new ConcurrentHashMap<>();


        try (ExecutorService executorService = Executors.newFixedThreadPool(3)) {

            Producer producer = new Producer(orderQueue, 8);
            Consumer consumer1 = new Consumer(orderQueue, processedOrders, "Consumer-1");
            Consumer consumer2 = new Consumer(orderQueue, processedOrders, "Consumer-2");

            executorService.submit(producer);
            executorService.submit(consumer1);
            executorService.submit(consumer2);

        }

        System.out.println("всего обработано и сохранено заказов: " + processedOrders.size());
    }
}