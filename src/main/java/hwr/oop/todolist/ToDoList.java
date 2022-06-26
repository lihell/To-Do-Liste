package hwr.oop.todolist;

import java.util.ArrayList;
import java.util.List;

class ToDoList {
    Task task = new Task();
    private List<Task> toDoList = new ArrayList<>();
    void addTask(Task task) {
        toDoList.add(task);
    }

    void deleteTask(int index) throws IndexOutOfBoundsException {
        this.toDoList.remove(index);
    }

    void editTask()  {

    }

    void deleteAll() {
        this.toDoList.clear();
    }

    List<Task> getToDoList() {
        return toDoList;
    }

    void setToDoList(List<Task> newTasks) {
        this.toDoList = newTasks;
    }

    Task getTaskFromList(int index) {
        return this.toDoList.get(index);
    }

}

