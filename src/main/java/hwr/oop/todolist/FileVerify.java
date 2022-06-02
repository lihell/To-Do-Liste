package hwr.oop.todolist;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.Scanner;

public class FileVerify implements Serializable {

    private String filename = "accounts.txt";
    Account account;
    public boolean verifyAccount(Account account) {

        boolean found = false;
        String tempUsername;
        String tempPassword;

        try {
            Scanner x = new Scanner(new File(String.valueOf(filename)));
            x.useDelimiter("[,\n]");

            while(x.hasNext() && !found) {
                tempUsername = x.next();
                tempPassword = x.next();

                if(tempUsername.trim().equals(this.account.getNewName().trim()) && tempPassword.trim().equals(this.account.getNewPassword().trim())) {
                    found = true;
                }
            }
            x.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return found;
    }
}
