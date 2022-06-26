package hwr.oop.todolist;

import java.io.*;
import java.text.ParseException;
import java.util.List;

class FileLoading  {



    List<Task> loadFromFile(Account account) throws IOException, ParseException {
            try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(account.getName() + "/" + account.getName() + "'s ToDoList.txt"))) {
                return (List<Task>)input.readObject();
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("File is empty. First thing you need to do is create a task!");
            }
        return null;
        }
    }
