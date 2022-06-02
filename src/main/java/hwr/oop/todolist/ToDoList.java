package hwr.oop.todolist;

import java.util.ArrayList;
import java.util.List;

class ToDoList {
    private List<Task> toDoList;

    public ToDoList() {
        toDoList = new ArrayList<>();
    }

    public void addTask(Task task) {
        this.toDoList.add(task);
    }

    public void deleteTask(int index) throws IndexOutOfBoundsException {
        this.toDoList.remove(index);
    }

    public void editTask()  {
        //TODO
    }

    public void deleteAll() {
        this.toDoList.clear();
    }

    public List<Task> getToDoList() {
        return toDoList;
    }

    public void setToDoList(List<Task> newTasks) {
        this.toDoList = newTasks;
    }

    public Task getTaskFromList(int index) {
        return this.toDoList.get(index);
    }
}
