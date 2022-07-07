package hwr.oop.todolist.Menu;

import hwr.oop.todolist.*;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class MenuFunction implements MenuFunctions {



    public MenuFunction() {
    }


    void logInDisplay() {
        System.out.println("---------------------------------------------------------------------------------------------------------------------");
        System.out.println("        Account            ");
        System.out.println("---------------------------------------------------------------------------------------------------------------------\"");
        System.out.println("    1- Create A New Account");
        System.out.println("    2- Sign In             ");
        System.out.println("---------------------------------------------------------------------------------------------------------------------\"");
        System.out.println("Please enter your Choice");
    }

    public void chooseLogIn() throws IOException {
        logInDisplay();
        int choice = Integer.parseInt(reader.nextLine());
        if (choice == 1) {
            CreateNewAccount newAccount = new CreateNewAccountFunction();
            newAccount.createNewAccount();
            System.out.println("Please logIn with your Account now");
            chooseLogIn();
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
        System.out.println("    3- Edit A Task             ");
        System.out.println("    4- Change Status           ");
        System.out.println("    5- Delete Task             ");
        System.out.println("    6- Exit                    ");
        System.out.println("---------------------------------------------------------------------------------------------------------------------");
        System.out.println(" Enter correct option");
        displayChoice();
    }

    void returnMenu() throws IOException {
        System.out.println("Do you want to go back to the Main Menu (Type 1)");
        System.out.println("Do you want to go back to the Account menu (Type 2)");
        System.out.println("Would you like to exit the program (Type anything else)");
        int choice = Integer.parseInt(reader.nextLine());
        if (choice == 1) {
            displayOptionsList();
        } else if (choice == 2) {
            logInDisplay();
        } else {
            System.exit(0);
        }
    }

    void displayChoice() throws IOException {
        int choice = Integer.parseInt(reader.nextLine());
        if (choice == 1) {
            DisplayListFromAccount display = new DisplayListFromAccountFunction();
            display.displayListFromAccount();
            returnMenu();
        } else if (choice == 2) {
            Add add = new AddFunction();
            add.addFunction();
            returnMenu();
        }else if (choice == 3){
            EditTask edit = new EditTaskFunction();
            edit.editTask();
            returnMenu();
        } else if (choice == 4) {
            ChangeStatus status = new ChangeStatusFunction();
            status.changeStatus();
            returnMenu();
        } else if (choice == 5) {
            DeleteTask delete = new DeleteTaskFunction();
            delete.deleteFunction();
            returnMenu();
        } else if (choice == 6) {
            System.exit(0);
        } else {
            displayOptionsList();
        }
    }
}
