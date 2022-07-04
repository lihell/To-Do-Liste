package hwr.oop.todolist;

import java.io.IOException;

public class CreateNewAccountFunction implements CreateNewAccount {

    @Override
    public void createNewAccount() {
        System.out.println("Create A New Account \n");
        System.out.println("Name of the Account:");

        Account acc = new Account();
        acc.setName(userInput());
        System.out.println("What is your new password?");
        acc.setPassword(userInput());
        if (!(verify.verifyAccount(acc))) {
            System.out.println("You have successfully made an Account");
        } else {
            System.out.println("An Account with these parameters already exists, please choose a different name/password");
            //logInDisplay();
        }
        try {
            saving.createFolderForAccount(acc);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String userInput() {
        return CreateNewAccount.super.userInput();
    }

}
