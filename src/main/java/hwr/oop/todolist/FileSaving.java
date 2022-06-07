package hwr.oop.todolist;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class FileSaving implements Serializable {


    private static Scanner x;
    private Account account;

    void writeAccountToFile(Account account) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("accounts.txt");
        ObjectOutputStream writer = new ObjectOutputStream(fileOutputStream);
        try {
            writer.writeObject(account.getNewName() + "/0" + account.getNewPassword());
            writer.write('\n');
            System.out.println("Account has been successfully saved");
        } catch (IOException ex) {
            System.out.println("Error: Couldn't create file");
        } finally {
            fileOutputStream.close();
            writer.close();
        }
    }

    void writeToDoListToFile(List<Task> savedTasks) throws IOException {

        try (ObjectOutputStream out = new ObjectOutputStream((new FileOutputStream("account.txt")))) {
            out.writeObject(savedTasks);
        } catch (IOException e) {
            System.out.println("Got IOException, " + e);
        }
        System.out.println("TASKS SAVED: ");
        System.out.println();
        savedTasks.forEach(task -> System.out.println("* " + task.getTitle()));
    }

    void saveToDoListToAccount() throws IOException {
        List<Task> savedTask = new ArrayList<>();
        FileOutputStream input = new FileOutputStream("account.txt");
        BufferedOutputStream buffer = new BufferedOutputStream(input);
        ObjectOutputStream writer = new ObjectOutputStream(buffer);

        boolean found = false;
        String tempUsername;
        String tempPassword;

        try {
            Scanner x = new Scanner(new File("accounts.txt"));
            x.useDelimiter("[,\n]");

            while (x.hasNext() && !found) {
                tempUsername = x.next();
                tempPassword = x.next();

                if (tempUsername.trim().equals(this.account.getNewName().trim()) && tempPassword.trim().equals(this.account.getNewPassword().trim())) {
                    found = true;
                    writer.writeObject("; ");
                    writeToDoListToFile(savedTask);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            x.close();
        }
    }
}