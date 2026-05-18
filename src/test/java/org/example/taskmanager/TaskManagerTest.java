package org.example.taskmanager;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class TaskManagerTest {

    @Test
    @DisplayName("запуск всей системы через main")
    void testMainMethod() {
        assertDoesNotThrow(() -> TaskManager.main(new String[]{}));
    }
}