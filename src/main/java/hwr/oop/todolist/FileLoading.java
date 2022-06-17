package hwr.oop.todolist;

import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

class FileLoading  {

    MenuFunction menu = new MenuFunction();

    public List<Task> loadFromFile(Account account) throws IOException {
            try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(account.getName() + "/" + account.getName() + "'s ToDoList.txt"))) {
                List<Task> loadedList = (List<Task>)input.readObject();
                return loadedList;
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("File is empty. First thing you need to do is create a task!");
                menu.displayOptions();
            }
        return null;
        }
    }
