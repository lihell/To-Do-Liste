package hwr.oop.todolist;

import java.util.Date;
import java.util.List;

class MenuFunction {

    Task task;
    ToDoList todo = new ToDoList();
    private List<Task> listOfTasks;
    //List<> filteredList = new ArrayList();

    void displayOptions() {
        System.out.println("---------------------------------------------------------------------------------------------------------------------");
        System.out.println(String.format("  %55s", "                To do List          "));
        System.out.println("---------------------------------------------------------------------------------------------------------------------");
        System.out.println(String.format("%45s","      1- Display List      "));
        System.out.println(String.format("%45s","      2- Add New Task      "));
        System.out.println(String.format("%45s","      3- Edit Task         "));
        System.out.println(String.format("%45s","      4- Delete Task       "));
        System.out.println(String.format("%45s","      5- Save & Exit       "));
        System.out.println("---------------------------------------------------------------------------------------------------------------------");
        System.out.println(" Enter correct option");
    }

  /*  Task pickTaskByTitle(String title) {
        listOfTasks.stream().filter(task -> task.getTitle().toLowerCase().trim().equals(title)).forEach(this::listOfTasks);
        return System.out.println("Titel: " + task.getTitle(),
                                  " Status: " + task.getStatus(),
                                  " Datum: " + task.getDate());
    }

    Task filterTasksByDate(Date date) {
        return System.out.println("Titel: " + task.getTitle(),
                " Status: " + task.getStatus(),
                " Datum: " + task.getDate());
    } */
}
