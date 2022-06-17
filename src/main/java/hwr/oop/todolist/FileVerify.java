package hwr.oop.todolist;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class FileVerify {


    public boolean verifyAccount(Account account) {

        boolean found = false;
        String tempUsername;
        String tempPassword;

        try {
            Scanner x = new Scanner(new File("account.txt"));
            x.useDelimiter("[\0\n]");

            while (x.hasNext() && !found) {
                tempUsername = x.next();
                tempPassword = x.next();

                if (tempUsername.trim().equals(account.getName().trim()) && tempPassword.trim().equals(account.getPassword().trim())) {
                    found = true;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Can't find a Account");
        }
        return found;
    }
}
