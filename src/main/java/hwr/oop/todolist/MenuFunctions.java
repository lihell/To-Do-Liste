package hwr.oop.todolist;

import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public interface MenuFunctions {
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
