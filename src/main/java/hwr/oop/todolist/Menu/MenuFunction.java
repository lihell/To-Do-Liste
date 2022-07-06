package hwr.oop.todolist.Menu;

import hwr.oop.todolist.Account;
import hwr.oop.todolist.FileLoading;
import hwr.oop.todolist.FileSaving;
import hwr.oop.todolist.Load;
import hwr.oop.todolist.Save;
import hwr.oop.todolist.ToDoList;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

class MenuFunction {

    Save saving = new FileSaving();
    Load loading = new FileLoading();
    private final ToDoList todo;
    private final Scanner reader;
    private final DateFormat formatter;
    Account acc;



    MenuFunction() {
        todo = new ToDoList();
        reader = new Scanner(System.in);
        formatter = new SimpleDateFormat("yyyy-MM-dd");
        formatter.setLenient(false);
        acc = new Account();
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

    void chooseLogIn() throws IOException, ParseException {
        logInDisplay();
        int choice = reader.nextInt();
        if (choice == 1) {
            CreateNewAccount newAccount = new CreateNewAccountFunction();
            newAccount.createNewAccount();
            chooseLogIn();
        } else if (choice == 2) {
            LogIn login = new LogInFunction();
            login.logIn();
            displayOptionsList();
        }
    }
    void displayOptionsList() {
        System.out.println("---------------------------------------------------------------------------------------------------------------------");
        System.out.println("                To Do List          ");
        System.out.println("---------------------------------------------------------------------------------------------------------------------");
        System.out.println("    1- Display List            ");
        System.out.println("    2- Add New Task            ");
        System.out.println("    3- Change Status           ");
        System.out.println("    4- Delete Task             ");
        System.out.println("    5- Exit                    ");
        System.out.println("---------------------------------------------------------------------------------------------------------------------");
        System.out.println(" Enter correct option");

    }

    void displayChoice() throws IOException, ParseException {
        int choice = reader.nextInt();
        if (choice == 1) {
            DisplayListFromAccount display = new DisplayListFromAccountFunction();
            display.displayListFromAccount();
        } else if (choice == 2) {
            Add add = new AddFunction();
            loading.loadFromFile(acc);
            add.addFunction();
        } else if (choice == 3) {
            ChangeStatus status = new ChangeStatusFunction();
            status.changeStatus();
        } else if (choice == 4) {
            DeleteTask delete = new DeleteTaskFunction();
            loading.loadFromFile(acc);
            delete.deleteFunction();
            saving.writeToDoListToFile(todo, acc);
        } else if (choice == 5) {
            System.exit(0);
        } else {
            displayOptionsList();
        }
    }
}
