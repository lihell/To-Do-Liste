package hwr.oop.todolist;

import java.io.IOException;
import java.text.ParseException;

public class AddFunction implements Add {
    public void addFunction() {
        formatter.setLenient(false);
        System.out.println("Add New Task To List \n");
        System.out.println("name of Task:");

        Task task = new Task();
        task.setTitle(userInput());
        task.setStatus(Status.INCOMPLETE);
        System.out.println("What is the Due Date? format (yyyy-MM-dd)");
        try {
            task.setDate(formatter.parse(userInput()));
        } catch (ParseException | NullPointerException e) {
            System.out.println("Input of date was in wrong format. REQUIRED FORMAT: (yyyy-MM-dd)");
            e.printStackTrace();
        }
        todo.addTask(task);
        try {
            saving.writeToDoListToFile(todo, acc);
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String userInput() {
        return Add.super.userInput();
    }

}
