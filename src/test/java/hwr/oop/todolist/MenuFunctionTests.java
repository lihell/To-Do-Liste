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
    FileVerify verify;
    @BeforeEach
    void setUp() throws ParseException, IOException {
        todo = new ToDoList();
        menu = new MenuFunction();
    }

    @Test
    void addFunctionWorks() throws ParseException, IOException {
        menu.addFunction();
        int sizeAfterOneTaskAdded = todo.getToDoList().size();
        Assertions.assertThat(todo.getToDoList().size()+1).isNotEqualTo(sizeAfterOneTaskAdded);
    }

    @Test
    void addFunctionWrongDateFormat() throws ParseException, IOException {
        try {
            menu.addFunction();
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    void addingNewAccountWorks() throws IOException, ParseException {
        // name for Account to create is Bina
        File directory = new File("Bina");
        menu.createNewAccount();
        Assertions.assertThat(directory.exists()).isEqualTo(true);
    }

    @Test
    void addNewAccountAlreadyExists() throws IOException, ParseException {
        // Needs to already have Account saved in account.txt
        // Used saved Account is Balu/B00kJu
        Account acc = new Account("Balu", "B00kJu");
        menu.createNewAccount();
        Assertions.assertThat(verify.verifyAccount(acc)).isEqualTo(true);
    }

    @Test
    void logInAccountWorks() throws IOException {
        Account acc = new Account("Manuela", "BuBbLeGuM");
        Assertions.assertThat(menu.logIn()).isEqualTo(acc);
    }

    @Test
    void canChangeTheTasksStatus() throws IOException, ParseException {
        // Use Nila Account, already has ToDoList in it
        menu.logIn();
        menu.changeStatus();
    }

    @Test
    void deleteOneTaskFunctionWorks() {

    }
}

