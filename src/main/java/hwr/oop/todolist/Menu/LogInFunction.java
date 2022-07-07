package hwr.oop.todolist.Menu;

import java.util.Objects;

class LogInFunction implements LogIn {

    @Override
    public void logIn() {
        System.out.println("Please tell us your Account");
        System.out.println("The name of the Account: ");

        acc.setName(userInput());
        System.out.println("Your Password: ");
        acc.setPassword(userInput());
        if (acc.getName() == null || Objects.equals(acc.getName(), "")) {
            throw new NullPointerException("You can't logIn with no name");
        }
        if (verify.verifyAccount(acc)) {
            System.out.println("Welcome back, " + acc.getName());
        } else {
            System.out.println("This Account does not exist");
            System.out.println("Please choose an existing Account or make a new one");
        }
    }

    @Override
    public String userInput() {
        return LogIn.super.userInput();
    }


}
