package clover;

import java.util.*;

public class TaskList {
    private final List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    public int size() {
        return tasks.size();
    }

    public Task get(int index) {
        return tasks.get(index);
    }
    public Task remove(int index) {
        return tasks.remove(index);
    }
    public void add(Task t) {
        tasks.add(t);
    }
    public List<Task> asList() {
        return tasks;
    }
}
