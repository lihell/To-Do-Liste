package hwr.oop.todolist.Menu;

import hwr.oop.todolist.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;


public class EditTaskFunction implements EditTask {

    @Override
    public void editTask() throws IOException {
        System.out.println("Welche Task moechtest du veraendern? \n");
        try {
            todo.setToDoList(loading.loadFromFile(acc));
            List<Task> listOfToDos = todo.getToDoList();
            for (Task list : listOfToDos) {
                System.out.println(listOfToDos.indexOf(list) + 1 + ". ");
                System.out.println(String.format("%1$-25s", list.getTitle()));
            }
        } catch (IOException e) {
            System.out.println("You are not logged into a Account");
            e.printStackTrace();
            menu.returnMenu();
        }
        formatter.setLenient(false);
        System.out.println("\n Enter the number in front of the task that you want to edit (0 -> return to Menu Selection)");
        boolean check = true;
        while (check) {
            try {
                int taskToChange = Integer.parseInt(userInput());

                if (taskToChange != 0) {
                    Task changingTask = todo.getTaskFromList(taskToChange - 1);
                    System.out.println("Der neue Titel: ");
                    changingTask.setTitle(userInput());
                    System.out.println("Das neue Datum: ");
                    try {
                        changingTask.setDate(formatter.parse(userInput()));
                        check = false;
                    } catch (ParseException | NullPointerException e) {
                        todo.setToDoList(loading.loadFromFile(acc));
                        System.out.println("Input of date was in wrong format or not given. REQUIRED FORMAT: (yyyy-MM-dd)");

                        check = false;
                    }
                    check = false;
                } else {
                    menu.returnMenu();
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Task with selected index does not exist.");
                e.printStackTrace();
                check = false;
            } catch (IOException e) {
                e.printStackTrace();
                check = false;
            }
        }
        try {
            saving.writeToDoListToFile(todo, acc);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public String userInput() {
        return EditTask.super.userInput();
    }
}
