package hwr.oop.todolist;

import java.util.ArrayList;
import java.util.List;

public class ToDoList {
    private List<Task> toDoList = new ArrayList<>();

    public void addTask(Task task) {
        if (task.getTitle() == null) {
            throw new NullPointerException("You can't have a Task without a name");
        }
        if (task.getDate() == null) {
            throw new NullPointerException("You can't have a Task without a Date");
        }
        toDoList.add(task);
    }

    public void deleteTask(int index) throws IndexOutOfBoundsException {
        this.toDoList.remove(index);
    }

    public List<Task> getToDoList() {
        return toDoList;
    }

    public void setToDoList(List<Task> newTasks) {
        this.toDoList = newTasks;
    }

    public Task getTaskFromList(int index) {
        return toDoList.get(index);
    }

}

