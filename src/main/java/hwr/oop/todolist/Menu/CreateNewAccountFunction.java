package hwr.oop.todolist.Menu;

import hwr.oop.todolist.Account;

import java.io.IOException;

class CreateNewAccountFunction implements CreateNewAccount {

    @Override
    public void createNewAccount() throws IOException {
        System.out.println("Create A New Account \n");
        System.out.println("Name of the Account:");

        Account acc = new Account();
        acc.setName(userInput());
        System.out.println("What is your new password?");
        acc.setPassword(userInput());
        if (!(verify.verifyAccount(acc))) {
            System.out.println("Your Account is being made");
        } else {
            System.out.println("An Account with these parameters already exists, please choose a different name/password");
            menu.returnMenu();
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
