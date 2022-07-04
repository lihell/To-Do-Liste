package hwr.oop.todolist;

import org.jetbrains.annotations.NotNull;
import java.io.*;
import java.util.List;


class FileSaving implements Save {
    private List<Task> listOfToDos;

    @Override
    public void createFolderForAccount(@NotNull Account account) throws IOException {
        String filenamePassword = account.getName() + "'s Password.txt";
        String filenameList = account.getName() + "'s ToDoList.txt";
        File directory = new File(account.getName());
        if (directory.exists() && directory.isDirectory()) {
            System.out.println("This Account already exists");
        } else {
            directory.mkdirs();
        }
        File passwordFile = new File(directory, filenamePassword);
        if (passwordFile.exists() && passwordFile.isFile()) {
            System.out.println("Password is already there");
        } else {
            passwordFile.createNewFile();
            try (FileWriter writer = new FileWriter(passwordFile)) {
                writer.write(account.getPassword());
            } catch (NullPointerException | IOException e) {
                System.out.println("No Password has been set");
            }
        }
        File listFile = new File(directory, filenameList);
        if (listFile.exists() && listFile.isFile()) {
            System.out.println("ToDoList is already there");
        } else {
            listFile.createNewFile();
        }
    }

    @Override
    public void writeToDoListToFile(ToDoList todo, @NotNull Account account) {

        try (ObjectOutputStream out = new ObjectOutputStream((new FileOutputStream(  account.getName() + "/" + account.getName() + "'s ToDoList.txt")))) {
            out.writeObject(todo.getToDoList());
        } catch (IOException | NullPointerException e) {
            System.out.println("Couldn't find the File to write the ToDoList in, " + e);
            System.out.println("Your problem could be your Account, try to open your Account again");
        }
        System.out.println("TASKS SAVED: ");
        System.out.println();
        todo.getToDoList().forEach(task -> System.out.println("* " + task.getTitle()));
    }

}