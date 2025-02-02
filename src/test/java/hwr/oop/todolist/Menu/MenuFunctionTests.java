package hwr.oop.todolist.Menu;

import hwr.oop.todolist.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public class MenuFunctionTests {
    // Tests for classes with user input

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
    void addFunctionWorks() throws IOException {
        // input correct
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
            LogIn login = new LogInFunction();
            Add add = new AddFunction();
            login.logIn();
            add.addFunction();
        } catch (NullPointerException | IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void addFunctionWithNoDate() {
        //give no date / press enter without writing date
        try {
            LogIn login = new LogInFunction();
            Add add = new AddFunction();
            login.logIn();
            add.addFunction();
        } catch (NullPointerException | IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void addFunctionWithNoTitle() {
        //give no title / press enter without writing title
        try {
            LogIn login = new LogInFunction();
            Add add = new AddFunction();
            login.logIn();
            add.addFunction();
        } catch (NullPointerException | IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void addFunctionWithoutAnAccount() {
        // leads to Menu Selection
        try {
            Add add = new AddFunction();
            add.addFunction();
        } catch (NullPointerException | IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void addingNewAccountWorks() throws IOException {
        // name for Account to create is Bina
        //password: HolyMolyFolyKoly
        File directory = new File("Bina");
        CreateNewAccount newAccount = new CreateNewAccountFunction();
        newAccount.createNewAccount();
        Assertions.assertThat(directory.exists()).isEqualTo(true);
    }

    @Test
    void addingNewAccountWithNameIsNull() {
        // dont write Account Name / press enter with no input
        CreateNewAccount newAccount = new CreateNewAccountFunction();
        try {
            newAccount.createNewAccount();
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Test
    void addingNewAccountWithPasswordIsNull() throws IOException{
        // dont write Account Password / press enter with no input
        Account acc = new Account();
        acc.setName("Manja");
        File directory = new File(acc.getName());
        CreateNewAccount newAccount = new CreateNewAccountFunction();
        newAccount.createNewAccount();
        Assertions.assertThat(directory.exists()).isEqualTo(false);
    }

    @Test
    void addNewAccountAlreadyExists() throws IOException {
        // Needs to already have Account saved in account.txt
        // Used saved Account is Balu/B00kJu
        // leads to Menu Selection
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
    void logInAccountDoesntExist() {
        // use the Account (name:Manuela, password:M0N3YB1TCH)
        Account acc = new Account("Manuela", "M0N3YB1TCH");
        LogIn login = new LogInFunction();
        login.logIn();
        Assertions.assertThat(verify.verifyAccount(acc)).isEqualTo(false);
    }

    @Test
    void logInWithNoName() {
        // dont put in a name
        LogIn login = new LogInFunction();
        try {
            login.logIn();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Test
    void logInWithNoPassword() {
        // dont put in a password
        Account acc = new Account();
        acc.setName("Madame");
        LogIn login = new LogInFunction();
        login.logIn();
        Assertions.assertThat(verify.verifyAccount(acc)).isEqualTo(false);
    }

    @Test
    void canChangeTheTasksStatus() throws IOException {
        // Use Nila Account, already has ToDoList in it
        //Set first Task to DONE
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
        // input number of Task that doesn't exist
        LogIn login = new LogInFunction();
        ChangeStatus status = new ChangeStatusFunction();
        try {
            login.logIn();
            status.changeStatus();
        } catch (IndexOutOfBoundsException | IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void tryToChangeStatusOfTaskWithNoAccountSet() throws IOException {
        //try to change the first Task
        ChangeStatus status = new ChangeStatusFunction();
        status.changeStatus();
        Assertions.assertThat(todo.getTaskFromList(1)).isEqualTo(Status.INCOMPLETE);
    }

    @Test
    void deleteOneTaskFunctionWorks() throws IOException {
        ToDoList notDeletedToDo = new ToDoList();
        Account acc = new Account("Nila", "Hallo123");
        notDeletedToDo.setToDoList(loading.loadFromFile(acc));
        LogIn login = new LogInFunction();
        DeleteTask delete = new DeleteTaskFunction();
        login.logIn();
        delete.deleteFunction();
        todo.setToDoList(loading.loadFromFile(acc));
        Assertions.assertThat(todo.getToDoList()).isNotEqualTo(notDeletedToDo.getToDoList());
    }

    @Test
    void goToMenuWhen0FromDeleteFunction() throws IOException {
        // press 0 when asked to select Tasks
        Account acc = new Account("Nila", "Hallo123");
        LogIn login = new LogInFunction();
         List<Task> beforeDelete = loading.loadFromFile(acc);
        DeleteTask delete = new DeleteTaskFunction();
        login.logIn();
        delete.deleteFunction();
        Assertions.assertThat(beforeDelete).isEqualTo(todo.getToDoList());
    }

    @Test
    void tryToDeleteButDoesntUseNumbersButLetters() {
        // when asked to select, dont use a number, use letter
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
            DeleteTask delete = new DeleteTaskFunction();
            delete.deleteFunction();
        } catch (NullPointerException e) {
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
    void displayTheListFromAnExistingAccount() {
        DisplayListFromAccount display = new DisplayListFromAccountFunction();
        LogIn login = new LogInFunction();
        login.logIn();
        display.displayListFromAccount();
    }

    @Test
    void editTaskWorks() throws IOException {
        Account acc = new Account("Nila", "Hallo123");
        Task notChangedTask = loading.loadFromFile(acc).get(0);
        EditTask edit = new EditTaskFunction();
        LogIn login = new LogInFunction();
        login.logIn();
        edit.editTask();
        Task changedTask = loading.loadFromFile(acc).get(0);
        Assertions.assertThat(notChangedTask.getTitle()).isNotEqualTo(changedTask.getTitle());
    }

    @Test
    void editTaskDoesntWorkWithWrongDate() throws IOException {
        Account acc = new Account("Nila", "Hallo123");
        Task notChangedTask = loading.loadFromFile(acc).get(0);
        EditTask edit = new EditTaskFunction();
        LogIn login = new LogInFunction();
        login.logIn();
        edit.editTask();
        Task changedTask = loading.loadFromFile(acc).get(0);
        Assertions.assertThat(notChangedTask.getTitle()).isEqualTo(changedTask.getTitle());
    }

    @Test
    void editTaskWithoutAccount() throws IOException {
        // leads to return Menu
        EditTask edit = new EditTaskFunction();
        edit.editTask();
    }
}

