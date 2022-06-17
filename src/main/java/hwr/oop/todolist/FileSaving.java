package hwr.oop.todolist;

import org.jetbrains.annotations.NotNull;

import java.io.*;


public class FileSaving  {
    FileVerify verify = new FileVerify();


    void writeAccountToFile(@NotNull Account account) throws IOException {
        try (FileWriter writer = new FileWriter("account.txt", true)) {
            if (verify.verifyAccount(account)) {
                System.out.println("Account already exists");
            } else {
                writer.write(account.getName() + "\0" + account.getPassword() + "\n");
                System.out.println("Account has been successfully saved");
            }
        } catch (IOException ex){
            System.out.println("Couldn't find the Account File");
        }
    }

    void createFolderForAccount(@NotNull Account account) throws IOException {
        String filenamePassword = "Password.txt";
        String filenameList = account.getName() + "'s ToDoList.txt";
        boolean success;
        File directory = new File(account.getName());
        if (directory.exists() && directory.isDirectory()) {
            System.out.println("This Account already exists");
        } else {
            System.out.println("Thank you for creating an Account, it will be saved swiftly");
            success = directory.mkdirs();
            if (success) {
                System.out.println("Successfully created your new Account : %s%n" + account.getName());
            } else {
                System.out.println("Failed to create the new Account : %s%n" + account.getName());
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
                writer.write(account.getPassword().hashCode());
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

    void writeToDoListToFile(ToDoList todo, @NotNull Account account) throws IOException {

        try (ObjectOutputStream out = new ObjectOutputStream((new FileOutputStream(  account.getName() + "/" + account.getName() + "'s ToDoList.txt")))) {
            out.writeObject(todo.getToDoList());
        } catch (IOException e) {
            System.out.println("Couldn't find the File to write the ToDoList in, " + e);
            System.out.println("Your problem could be your Account, try to open your Account again");
        }
        System.out.println("TASKS SAVED: ");
        System.out.println();
        todo.getToDoList().forEach(task -> System.out.println("* " + task.getTitle()));
    }

}