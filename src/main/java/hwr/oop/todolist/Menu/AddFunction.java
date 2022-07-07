package hwr.oop.todolist.Menu;

import hwr.oop.todolist.Status;
import hwr.oop.todolist.Task;

import java.io.IOException;
import java.text.ParseException;

class AddFunction implements Add {

    @Override
    public void addFunction() throws IOException {
        try {
            todo.setToDoList(loading.loadFromFile(acc));
        } catch (NullPointerException | IOException e) {
            System.out.println("You need to be logged into an Account first");
            menu.returnMenu();
        }
            formatter.setLenient(false);
            System.out.println("Add New Task To List \n");
            System.out.println("name of Task:");

            Task task = new Task();
            task.setTitle(userInput());
            task.setStatus(Status.INCOMPLETE);
            System.out.println("What is the Due Date? format (yyyy-MM-dd)");
            boolean check = true;
            while (check) {
                try {
                    task.setDate(formatter.parse(userInput()));
                    todo.addTask(task);
                    check = false;
                } catch (ParseException | NullPointerException e) {
                    System.out.println("Input of date was in wrong format. REQUIRED FORMAT: (yyyy-MM-dd)");
                    check = false;
                }
            }
            try {
                saving.writeToDoListToFile(todo, acc);
            } catch (IOException | NullPointerException e) {
                System.out.println("Couldn't add a Task");
                e.printStackTrace();
            }
        }
    @Override
    public String userInput () {
        return Add.super.userInput();
    }
}
