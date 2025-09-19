package clover;

import java.util.*;

/**
 * Represents a list of tasks in the Clover application.
 * <p>
 * Provides operations to add, remove, access, and retrieve tasks.
 */
public class TaskList {
    private final List<Task> tasks;

    /**
     * Constructs an empty {@code TaskList}.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a {@code TaskList} initialized with an existing list of tasks.
     * <p>
     * A defensive copy of the provided list is created.
     *
     * @param tasks the list of tasks to initialize with
     */
    public TaskList(List<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    /**
     * Returns the number of tasks in this list.
     *
     * @return the size of the task list
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns the task at the specified index.
     *
     * @param index the index of the task to return
     * @return the task at the specified position
     * @throws IndexOutOfBoundsException if the index is invalid
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Removes the task at the specified index.
     *
     * @param index the index of the task to remove
     * @return the task that was removed
     * @throws IndexOutOfBoundsException if the index is invalid
     */
    public Task remove(int index) {
        return tasks.remove(index);
    }

    /**
     * Adds a task to the end of the list.
     *
     * @param t the task to add
     */
    public void add(Task t) {
        tasks.add(t);
    }

    /**
     * Returns the underlying list of tasks.
     *
     * @return the list of tasks
     */
    public List<Task> asList() {
        return tasks;
    }
}

