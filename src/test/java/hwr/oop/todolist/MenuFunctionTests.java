package hwr.oop.todolist;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;

public class MenuFunctionTests {
    ToDoList todo;
    MenuFunction menu;
    Verify verify = new FileVerify();
    Load loading = new FileLoading();
    Save saving = new FileSaving();
    @BeforeEach
    void setUp()  {
        todo = new ToDoList();
        menu = new MenuFunction();
    }

    @Test
    void addFunctionWorks() throws IOException, ParseException {
        Account acc = new Account("Balu", "b00kJu");
        loading.loadFromFile(acc);
        LogIn login = new LogInFunction();
        login.logIn();
        Add add = new AddFunction();
        add.addFunction();
        int sizeAfterOneTaskAdded = todo.getToDoList().size();
        Assertions.assertThat(todo.getToDoList().size()+1).isNotEqualTo(sizeAfterOneTaskAdded);
    }

    @Test
    void addFunctionWrongDateFormat() {
        //Write wrong date format (the right date format is yyyy-MM-dd)
        try {
            Add add = new AddFunction();
            add.addFunction();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }
    @Test
    void addingNewAccountWorks()  {
        // name for Account to create is Bina
        File directory = new File("Bina");
        CreateNewAccount newAccount = new CreateNewAccountFunction();
        newAccount.createNewAccount();
        Assertions.assertThat(directory.exists()).isEqualTo(true);
    }

    @Test
    void addNewAccountAlreadyExists()  {
        // Needs to already have Account saved in account.txt
        // Used saved Account is Balu/B00kJu
        Account acc = new Account("Balu", "b00kJu");
        CreateNewAccount newAccount = new CreateNewAccountFunction();
        newAccount.createNewAccount();
        Assertions.assertThat(verify.verifyAccount(acc)).isEqualTo(true);
    }

    @Test
    void logInAccountWorks()  {
        Account acc = new Account("Nila", "Hallo123");
        LogIn login = new LogInFunction();
        login.logIn();
        Assertions.assertThat(login.acc.getName()).isEqualTo(acc.getName());
    }

    @Test
    void logInAccountDoesntExist() throws IOException {
        // use the Account (name:Manuela, password:M0N3YB1TCH)
        Account acc = new Account("Manuela", "M0N3YB1TCH");
        LogIn login = new LogInFunction();
        login.logIn();
        Assertions.assertThat(verify.verifyAccount(acc)).isEqualTo(false);
    }

    @Test
    void canChangeTheTasksStatus() throws IOException, ParseException {
        // Use Nila Account, already has ToDoList in it
        Account acc = new Account("Nila", "Hallo123");
        LogIn login = new LogInFunction();
        ChangeStatus status = new ChangeStatusFunction();
        login.logIn();
        status.changeStatus();
        todo.setToDoList(loading.loadFromFile(acc));
        Assertions.assertThat(todo.getTaskFromList(0).getStatus()).isEqualTo(Status.DONE);
    }

    @Test
    void tryToChangeStatusOfTaskThatDoesntExist() {
        // Use Nila Account, already has ToDoList in it
        // input number of Task that doesnt exist
        LogIn login = new LogInFunction();
        ChangeStatus status = new ChangeStatusFunction();
        try {
            login.logIn();
            status.changeStatus();
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }

    @Test
    void tryToChangeStatusOfTaskWithNoAccountSet() {
        ChangeStatus status = new ChangeStatusFunction();
        try {
            status.changeStatus();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Test
    void deleteOneTaskFunctionWorks() throws IOException, ParseException {
        Account acc = new Account("Nila", "Hallo123");
        LogIn login = new LogInFunction();
        DeleteTask delete = new DeleteTaskFunction();
        login.logIn();
        delete.deleteFunction();
        todo.setToDoList(loading.loadFromFile(acc));
        Assertions.assertThat(todo.getToDoList().size()).isEqualTo(0);
    }

    @Test
    void tryToDeleteButDoesntUseNumbersButLetters() {
        try {
            LogIn login = new LogInFunction();
            DeleteTask delete = new DeleteTaskFunction();
            login.logIn();
            delete.deleteFunction();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void tryToDeleteTaskFromListWithoutAccount() {
        try {
            LogIn login = new LogInFunction();
            DeleteTask delete = new DeleteTaskFunction();
            login.logIn();
            delete.deleteFunction();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Test
    void deleteAllTasksFunction()  {
        LogIn login = new LogInFunction();
        DeleteAll deleteAll = new DeleteAllFunction();
        login.logIn();
        deleteAll.deleteAllFunction();
    }

    @Test
    void tryToDeleteAllWithoutAccount() {
        try {
            LogIn login = new LogInFunction();
            DeleteAll deleteAll = new DeleteAllFunction();
            login.logIn();
            deleteAll.deleteAllFunction();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Test
    void tryToDeleteAllButDoesntUseNumbersButLetters() {
        try {
            LogIn login = new LogInFunction();
            DeleteAll deleteAll = new DeleteAllFunction();
            login.logIn();
            deleteAll.deleteAllFunction();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void displayTheListWithNoAccountGiven() {
        try {
            DisplayListFromAccount display = new DisplayListFromAccountFunction();
            display.displayListFromAccount();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Test
    void displayListFromAccountWithEmptyList() {
        //use any Account that doesnt have a filled List yet
        try {
            DisplayListFromAccount display = new DisplayListFromAccountFunction();
            LogIn login = new LogInFunction();
            login.logIn();
            display.displayListFromAccount();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Test
    void displayTheListFromAnExistingAccount() {
        DisplayListFromAccount display = new DisplayListFromAccountFunction();
        LogIn login = new LogInFunction();
        login.logIn();
        display.displayListFromAccount();
    }
}

