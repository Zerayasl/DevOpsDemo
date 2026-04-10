package ch.zhaw.iwi.devops.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class ToDoTest {

    @Test
    public void testToDo() {
        var toDo1 = new ToDo(1, "title", "description");
        assertEquals("title", toDo1.getTitle());
        assertEquals("description", toDo1.getDescription());
        assertEquals(1, toDo1.getId());
    }

    @Test
    void testToDoNotNull() {
        var toDo = new ToDo(1, "titel", "beschreibung");
        assertNotNull(toDo);
    }

    @Test
    void testToDoDifferentIds() {
        var toDo1 = new ToDo(1, "a", "b");
        var toDo2 = new ToDo(2, "a", "b");
        assertNotEquals(toDo1.getId(), toDo2.getId());
    }
    
}
