package clover;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskListTest {

    @Test
    void addStoresTaskAndIncreasesSize() {
        TaskList list = new TaskList();
        Task t = new ToDo("read book");
        list.add(t);

        assertEquals(1, list.size());
        assertSame(t, list.get(1)); //
    }

    @Test
    void deleteRemovesCorrectTask() throws DukeException {
        TaskList list = new TaskList();
        Task a = new ToDo("A");
        Task b = new ToDo("B");
        list.add(a); list.add(b);

        Task removed = list.remove(2);
        assertSame(b, removed);
        assertEquals(1, list.size());
        assertSame(a, list.get(1));
    }
}