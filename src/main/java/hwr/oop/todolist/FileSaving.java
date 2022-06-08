package hwr.oop.todolist;

import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.List;


public class FileSaving  {


    void writeAccountToFile(@NotNull Account account) throws IOException {
        try (FileWriter writer = new FileWriter("account.txt", true)) {
            writer.write(account.getNewName() + "\0" + account.getNewPassword() + "\n");
            System.out.println("Account has been successfully saved");
        } catch (IOException ex) {
            System.out.println("Error: Couldn't create file");
        }
    }

    void createFolderForAccount(@NotNull Account account) throws FileNotFoundException, IOException {
        String filenamePassword = "Password.txt";
        String filenameList = "ToDoList.txt";
        boolean success;
        File directory = new File(account.getNewName());
        if (directory.exists() && directory.isDirectory()) {
            System.out.println("This Account already exists");
        } else {
            System.out.println("Thank you for creating an Account, it will be saved swiftly");
            success = directory.mkdirs();
            if (success) {
                System.out.println("Successfully created your new Account : %s%n" + account.getNewName());
            } else {
                System.out.println("Failed to create the new Account : %s%n" + account.getNewName());
                System.out.println("The Account you have tried to make might not be in our guidelines, please try to make another one");
            }
        }
        File passwordFile = new File(directory, filenamePassword);
        if (passwordFile.exists() && passwordFile.isFile()) {
            System.out.println("Password is already there");
        } else {
            System.out.println("Creating File for password now");
            success = passwordFile.createNewFile();
            try (FileWriter writer = new FileWriter(passwordFile)) {
                writer.write(account.getNewPassword().hashCode());
            } catch (IOException e) {
                System.out.println("No Account has been set, please write use an Account");
            }
            if (success) {
                System.out.println("Successfully created File for Password : %s%n" + filenamePassword);
            } else {
                System.out.println("Failed to create new file for Password : %s&n" + filenamePassword);
            }
        }
        File listFile = new File(directory, filenameList);
        if (listFile.exists() && listFile.isFile()) {
            System.out.println("ToDoList is already there");
        } else {
            System.out.println("Creating File for ToDoList now");
            success = listFile.createNewFile();
            if (success) {
                System.out.println("Successfully created File for ToDoList : %s%n" + filenameList);
            } else {
                System.out.println("Failed to create new file for ToDoList : %s&n" + filenameList);
            }
        }
    }

    void writeToDoListToFile(List<Task> savedTasks, @NotNull Account account) throws IOException {

        try (ObjectOutputStream out = new ObjectOutputStream((new FileOutputStream(  account.getNewName() + "/ToDoList.txt")))) {
            out.writeObject(savedTasks);
        } catch (IOException e) {
            System.out.println("Got IOException, " + e);
        }
        System.out.println("TASKS SAVED: ");
        System.out.println();
        savedTasks.forEach(task -> System.out.println("* " + task.getTitle()));
    }

}