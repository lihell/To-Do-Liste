package hwr.oop.todolist;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

class MenuFunction {

    private final ToDoList todo;
    private final Scanner reader;
    private final DateFormat formatter;
    private FileLoading loading = new FileLoading();
    FileSaving save = new FileSaving();

    MenuFunction()  {
        todo = new ToDoList();
        reader = new Scanner(System.in);
        formatter = new SimpleDateFormat("yyyy-MM-dd");
    }
    
    String userInput() {
        return reader.nextLine();
    }

    void logInDisplay() {
        System.out.println("---------------------------------------------------------------------------------------------------------------------");
        System.out.println("Welcome to Your To Do List App");
        System.out.println("---------------------------------------------------------------------------------------------------------------------\"");
        System.out.println("    1- Create A New Account");
        System.out.println("    2- Sign In             ");
        System.out.println("---------------------------------------------------------------------------------------------------------------------\"");
        System.out.println("Please enter your Choice");
    }

    void chooseLogIn() throws IOException {
        int choice = reader.nextInt();
        if (choice == 1) {
            createNewAccount();
        } else if (choice == 2) {
            logIn();
        }
    }

    void displayOptionsList() throws ParseException, IOException {
        System.out.println("---------------------------------------------------------------------------------------------------------------------");
        System.out.println("                To Do List          ");
        System.out.println("---------------------------------------------------------------------------------------------------------------------");
        System.out.println("    1- Display List        ");
        System.out.println("    2- Add New Task        ");
        System.out.println("    3- Edit Task           ");
        System.out.println("    4- Delete Task         ");
        System.out.println("    5- Delete List         ");
        System.out.println("    6- Save & Exit         ");
        System.out.println("---------------------------------------------------------------------------------------------------------------------");
        System.out.println(" Enter correct option");
        int choice = reader.nextInt();
        if (choice == 1) {
            displayListFromAccount();
        } else if (choice == 2) {
            addFunction();
        } else if (choice == 4) {
            deleteFunction();
        } else if (choice == 5) {
            deleteAllFunction();
        } else {
            displayOptionsList();
        }
    }


    void createNewAccount() throws IOException {
        System.out.println("Create A New Account \n");
        System.out.println("Name of the Account:");

        Account acc = new Account();
        acc.setName(userInput());
        System.out.println("What is your new password?");
        acc.setPassword(userInput());
        FileVerify verify = new FileVerify();
        if (!verify.verifyAccount(acc)) {
            System.out.println("You have successfully made an Account");
        } else {
            System.out.println("An Account with these parameters already exists, please choose a different name/password");
            logInDisplay();
        }
        FileSaving save = new FileSaving();
        save.createFolderForAccount(acc);
        logIn();
    }

    void displayListFromAccount() throws ParseException, IOException {
        try {
        if (!(logIn() == null)) {
            Account acc = logIn();
            todo.setToDoList(loading.loadFromFile(acc));
            todo.getToDoList().forEach(System.out::println);
        }
        } catch (IOException | ParseException e) {
            System.out.println("You are not signed in yet\n");
            System.out.println("Please make an Account first/Sign into your Account before calling a List");
            displayOptionsList();
        }
    }

    Account logIn() throws IOException {
        System.out.println("Please tell us your Account");
        System.out.println("The name of the Account: ");

        Account acc = new Account();
        acc.setName(userInput());
        System.out.println("Your Password: ");
        acc.setPassword(userInput());
        FileVerify verify = new FileVerify();
        if (verify.verifyAccount(acc)) {
            System.out.println("Welcome back, " + acc.getName() + ", " + acc.getPassword());
        }
        return new Account(acc.getName(), acc.getPassword());
    }

    void addFunction() throws ParseException, IOException {
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
        displayOptionsList();
    }

    void changeStatus() throws IOException, ParseException {
        System.out.println("What task do you want to mark as done? \n ");
        todo.setToDoList(loading.loadFromFile(logIn()));
        List<Task> listOfToDos= todo.getToDoList();
        for (Task list :listOfToDos) {
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
                displayOptionsList();
            }
        } catch (Exception e) {
            System.out.println("Task with selected index does not exist. Select number in front of task again:");
        }

        searched.setStatus(Status.DONE);
        System.out.println("Task is now set to DONE");
        displayOptionsList();
    }

    void deleteFunction() throws IOException, ParseException {
        System.out.println("Which Task Would You Like To Delete? \n");
        todo.setToDoList(loading.loadFromFile(logIn()));
        List<Task> listOfToDos= todo.getToDoList();
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
                displayOptionsList();
            }
        } catch (Exception e) {
            System.out.println("Task with selected index does not exist. Select number in front of task again:");
        }
        System.out.println("Task is removed");
        displayOptionsList();
    }

    void deleteAllFunction() throws IOException, ParseException {
        System.out.println("Do you want to delete all Tasks? \n");
        todo.setToDoList(loading.loadFromFile(logIn()));
        List<Task> listOfToDos= todo.getToDoList();
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
                displayOptionsList();
            }
        } catch (Exception e) {
            System.out.println("Please put in a number");
        }
    }


}
