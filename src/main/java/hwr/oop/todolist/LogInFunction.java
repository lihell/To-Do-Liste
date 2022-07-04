package hwr.oop.todolist;

public class LogInFunction implements LogIn {

    @Override
    public void logIn() {
        System.out.println("Please tell us your Account");
        System.out.println("The name of the Account: ");

        acc.setName(userInput());
        System.out.println("Your Password: ");
        acc.setPassword(userInput());
        if (verify.verifyAccount(acc)) {
            System.out.println("Welcome back, " + acc.getName());
        } else {
            System.out.println("This Account does not exist");
            System.out.println("Please choose an existing Account or make a new one");
            //chooseLogIn();
        }
    }

    @Override
    public String userInput() {
        return LogIn.super.userInput();
    }


}
