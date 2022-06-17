package hwr.oop.todolist;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

class MenuFunction {

    List<Task> listOfToDos;
    ToDoList todo = new ToDoList();
    Scanner reader = new Scanner(System.in);
    DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    public String userInput() {
        return reader.nextLine();
    }


    public void displayOptions() {
        System.out.println("---------------------------------------------------------------------------------------------------------------------");
        System.out.println(String.format("  %55s", "                To do List          "));
        System.out.println("---------------------------------------------------------------------------------------------------------------------");
        System.out.println(String.format("%45s","    1- Create A New Account"));
        System.out.println(String.format("%45s","    2- Display Options     "));
        System.out.println(String.format("%45s","    3- Add New Task        "));
        System.out.println(String.format("%45s","    4- Edit Task           "));
        System.out.println(String.format("%45s","    5- Delete Task         "));
        System.out.println(String.format("%45s","    6- Save & Exit         "));
        System.out.println("---------------------------------------------------------------------------------------------------------------------");
        System.out.println(" Enter correct option");
    }

    public void createNewAccount() throws IOException {
        System.out.println("Create A New Account \n");
        System.out.println("Name of the Account:");

        Account account = new Account();
        account.setName(userInput());
        System.out.println("What is your new password?");
        account.setPassword(userInput());
        FileVerify verify = new FileVerify();
        if (verify.verifyAccount(account)) {
            System.out.println("An Account with these parameters already exists, please choose a different name/password");
            createNewAccount();
        } else {
            System.out.println("You have successfully made an Account");
            FileSaving save = new FileSaving();
            save.writeAccountToFile(account);
            save.createFolderForAccount(account);
        }
    }

    public void callAccountForActions() {
        System.out.println("Please tell us your Account");
        System.out.println("The name of the Account: ");

        Account acc = new Account();
        acc.setName(userInput());
        System.out.println("Your Password: ");
        acc.setPassword(userInput());
        FileVerify verify = new FileVerify();
        if (verify.verifyAccount(acc) == true) {
            System.out.println("Welcome back, " + acc.getName() + ", " + acc.getPassword());
        }
    }

    public void addFunction() throws ParseException {
        System.out.println("Add New Task To List \n");
        System.out.println("name of Task:");

        Task task = new Task();
        task.setTitle(userInput());
        task.setStatus(Status.INCOMPLETE);
        System.out.println("What is the Due Date? format (yyyy-MM-dd)");
        try {
            task.setDate(formatter.parse(userInput()));
        } catch (ParseException e){
            System.out.println("Input of date was in wrong format. REQUIRED FORMAT: (yyyy-MM-dd)");
        }
        todo.addTask(task);
    }

    public void changeStatus(ToDoList todo) {
        System.out.println("What task do you want to mark as done? \n ");
        for (Task list : listOfToDos) {
            System.out.print(listOfToDos.indexOf(list) + 1 + ". ");
            System.out.println(String.format("%1$-25s", list.getTitle()));
        }
        System.out.println("\n Enter the number in front of the task that you want to mark DONE (0 -> return to Menu)");
        Task searched = new Task();

        try {
            int getTaskAtIndex = Integer.parseInt(userInput());

            if (getTaskAtIndex != 0) {
                searched = todo.getTaskFromList(getTaskAtIndex - 1);
            } else {
                displayOptions();
            }
        } catch (Exception e) {
            System.out.println("Task with selected index does not exist. Select number in front of task again:");
        }

        searched.setStatus(Status.DONE);
        System.out.println("Task is now set to DONE");
    }

    public void deleteFunction(List<Task> listOfToDos) {
        System.out.println("Which Task Would You Like To Delete? \n");
        for (Task list : listOfToDos) {
            System.out.print(listOfToDos.indexOf(list) + 1 + ". ");
            System.out.println(String.format("%1$-25s", list.getTitle()));
        }
        System.out.println("Write down the number of which task you want to DELETE (0 -> return to Menu");

        try {
            int removeByTaskNumber = Integer.parseInt(userInput());

            if (removeByTaskNumber != 0) {
                todo.deleteTask(removeByTaskNumber - 1);
            } else {
                displayOptions();
            }
        } catch (Exception e) {
            System.out.println("Task with selected index does not exist. Select number in front of task again:");
        }
        System.out.println("Task is removed");
    }

    public void deleteAllFunction() {
        System.out.println("Do you want to delete all Tasks? \n");
        for (Task list : listOfToDos) {
            System.out.print(listOfToDos.indexOf(list) + 1 + ". ");
            System.out.println(String.format("%1$-25s", list.getTitle()));
        }
        System.out.println("If you want to delete all Tasks press any number but 0 (0 -> return to Menu)");
        try {
            int removeAllTasks = Integer.parseInt(userInput());

            if (removeAllTasks != 0) {
                todo.deleteAll();
            } else {
                displayOptions();
            }
        } catch (Exception e) {
            System.out.println("Please put in a number");
        }
    }


}
