package hwr.oop.todolist;

import java.util.ArrayList;
import java.util.List;

class ToDoList {
    Task task = new Task();
    private List<Task> toDoList = new ArrayList<>();


    public void addTask(Task task) {
        toDoList.add(task);
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

