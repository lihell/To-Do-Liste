package hwr.oop.todolist;

import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.Scanner;

public class FileVerify {


    boolean verifyAccount(@NotNull Account account) {
        String filenamePassword = account.getName() + "'s Password.txt";
        File directory = new File(account.getName());
        File passwordFile = new File(directory, filenamePassword);
        boolean exists = false;

        try (Scanner scan = new Scanner(passwordFile)) {
            if (directory.exists() && directory.isDirectory()) {
                if (passwordFile.exists() && passwordFile.isFile()) {
                    while (scan.hasNextLine()) {
                        String line = scan.next();
                        if (line.contains(account.getPassword())) {
                            exists = true;
                        }
                    }
                }
            }
        } catch (FileNotFoundException e){
            System.out.println("The Account doesn't exist");
        }
        return exists;
    }
}

