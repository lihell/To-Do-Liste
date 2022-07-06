package hwr.oop.todolist.Menu;

import hwr.oop.todolist.*;
import hwr.oop.todolist.ToDoList;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Scanner;

interface MenuFunctions {
    MenuFunction menu = new MenuFunction();
    Save saving = new FileSaving();
    ToDoList todo = new ToDoList();
    Load loading = new FileLoading();
    Account acc = new Account();
    Verify verify = new FileVerify();
    DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    Scanner reader = new Scanner(System.in);
    default String userInput() {
        return reader.nextLine();
    }
}
