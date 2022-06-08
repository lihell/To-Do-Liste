package hwr.oop.todolist;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

class FileLoading  {

    FileSaving save;
    MenuFunction menu;

    public List<Task> loadFromFile(Account account) throws IOException, ClassNotFoundException, ParseException {
            try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(account.getNewName() + "/ToDoList.txt"))) {
                List<Task> loadedList = (List<Task>) input.readObject();
                return loadedList;
            } catch (IOException e) {
                System.out.println("File is empty. First thing you need to do is create a task!");
                menu.displayOptions();
            }
        return null;
        }
    }
