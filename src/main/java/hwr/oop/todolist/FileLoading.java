package hwr.oop.todolist;

import java.io.*;
import java.util.List;

public class FileLoading implements Load {


    @Override
    public List<Task> loadFromFile(Account account) throws IOException {
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(account.getName() + "/" + account.getName() + "'s ToDoList.txt"))) {
            return (List<Task>) input.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
