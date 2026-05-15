package org.example.taskmanager.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class OrderTest {
    @Test
    void testOrderGetters() {
        Order order = new Order("id1", "TYPE", "desc");
        assertEquals("id1", order.getId());
        assertEquals("TYPE", order.getType());
    }
}