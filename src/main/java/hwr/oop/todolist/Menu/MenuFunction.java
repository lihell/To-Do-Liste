package hwr.oop.todolist.Menu;

import hwr.oop.todolist.Account;
import hwr.oop.todolist.FileLoading;
import hwr.oop.todolist.FileSaving;
import hwr.oop.todolist.Load;
import hwr.oop.todolist.Save;
import hwr.oop.todolist.ToDoList;

import java.io.IOException;
import java.text.DateFormat;
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

    void logInDisplay() throws IOException {
        System.out.println("---------------------------------------------------------------------------------------------------------------------");
        System.out.println("        Account            ");
        System.out.println("---------------------------------------------------------------------------------------------------------------------\"");
        System.out.println("    1- Create A New Account");
        System.out.println("    2- Sign In             ");
        System.out.println("---------------------------------------------------------------------------------------------------------------------\"");
        System.out.println("Please enter your Choice");
        chooseLogIn();
    }

    void chooseLogIn() throws IOException {
        logInDisplay();
        int choice = reader.nextInt();
        if (choice == 1) {
            CreateNewAccount newAccount = new CreateNewAccountFunction();
            newAccount.createNewAccount();
            logInDisplay();
        } else if (choice == 2) {
            LogIn login = new LogInFunction();
            login.logIn();
            displayOptionsList();
        }
    }
    void displayOptionsList() throws IOException {
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
        displayChoice();
    }

    void returnMenu() throws IOException {
        System.out.println("Do you want to go back to the Main Menu (Type 1)");
        System.out.println("Would you like to exit the program (Type anything else");
        int choice = reader.nextInt();
        if (choice == 1) {
            displayOptionsList();
        } else {
            System.exit(0);
        }
    }

    void displayChoice() throws IOException {
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
