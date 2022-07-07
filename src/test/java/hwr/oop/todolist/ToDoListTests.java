package hwr.oop.todolist;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoListTests {

    @Nested
    class AccountTest {
        Account account;

        @BeforeEach
        void setUp() {
            account = new Account("Account", "Hallo123");
        }

        @Test
        void nameIsTheRightName() {
            Assertions.assertThat(account.getName()).isEqualTo("Account");
        }

        @Test
        void passwordIsRightPassword() {
            Assertions.assertThat(account.getPassword()).isEqualTo("Hallo123");
        }

        @Test
        void nameIsNotGiven() {
            try {
                Account noName = new Account();
                noName.setName("");
                noName.setPassword("Hallo123!");
            } catch (NullPointerException e) {
                System.out.println("You need to write a name");
            }
        }

        @Test
        void setName() {
            Account acc = new Account();
            acc.setName("Amelia");
            Assertions.assertThat(acc.getName()).isEqualTo("Amelia");
        }

        @Test
        void passwordIsNotGiven() {
            try {
                Account noPassword = new Account();
                noPassword.setPassword("");
                noPassword.setName("Amelia");
            } catch (NullPointerException e) {
                System.out.println("You need to write a password");
            }
        }

        @Test
        void setPassword() {
            Account acc = new Account();
            acc.setPassword("Haiho3021");
            Assertions.assertThat(acc.getPassword()).isEqualTo("Haiho3021");
        }

        @Test
        void testForSettingAccountName() {
            Account acc = new Account();
            acc.setName("Nila");
            Assertions.assertThat(acc.getName()).isEqualTo("Nila");
        }

        @Test
        void testForSettingAccountPassword() {
            Account acc = new Account();
            acc.setPassword("H311sKitchen");
            Assertions.assertThat(acc.getPassword()).isEqualTo("H311sKitchen");
        }
    }

    @Nested
    class TaskTest {
        Task task = new Task();


        @Test
        void taskTitleCanBeSet() {
            task.setTitle("Homework");
            Assertions.assertThat(task.getTitle()).isEqualTo("Homework");
        }

        @Test
        void ifTitleNotGivenThrowsException() {
            try {
                task.setTitle("");
                task.setStatus(Status.INCOMPLETE);
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }

        @Test
        void taskStatusCanBeSet() {
            task.setStatus(Status.DONE);
            Assertions.assertThat(String.valueOf(task.getStatus())).isEqualTo("DONE");
        }

        @Test
        void taskDateCanBeSet() throws ParseException {
            Date today = new SimpleDateFormat("yyyy-MM-dd").parse("2020-05-10");
            task.setDate(today);
            Assertions.assertThat(task.getDate()).isEqualTo("2020-05-10");
        }
    }
    @Nested
    class ToDoListTest {
        ToDoList todo;
        Task task = new Task();
        @BeforeEach
        void setUp() {
            todo = new ToDoList();
        }

        @Test
        void canAddNewTask() {
            task.setTitle("Homework");
            task.setStatus(Status.DONE);

            todo.addTask(task);
            int initialListSize = todo.getToDoList().size();

            Task task1 = new Task();
            task.setTitle("Dishwasher");
            task.setStatus(Status.INCOMPLETE);
            todo.addTask(task1);
            Assertions.assertThat(initialListSize+1).isEqualTo(todo.getToDoList().size());
        }

        @Test
        void addTaskWithNoName() throws ParseException{
            task.setStatus(Status.INCOMPLETE);
            Date today = new SimpleDateFormat("yyyy-MM-dd").parse("2020-05-10");
            task.setDate(today);
            try {
                todo.addTask(task);
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }

        @Test
        void taskTitleIsCorrectTitle() {
            task.setTitle("Feed the cat");
            task.setStatus(Status.INCOMPLETE);

            Assertions.assertThat(task.getTitle()).isEqualTo("Feed the cat");
        }


        @Test
        void canDeleteOneTask() {
            task.setTitle("Homework");
            task.setStatus(Status.INCOMPLETE);

            todo.addTask(task);
            int sizeAfterAddTask = todo.getToDoList().size();
            todo.deleteTask(0);
            Assertions.assertThat(sizeAfterAddTask-1).isEqualTo(todo.getToDoList().size());

        }

        @Test
        void getAnyTask() {
            task.setTitle("Homework");
            task.setStatus(Status.DONE);
            todo.addTask(task);
            final Task firstTask = todo.getTaskFromList(0);

            Task taskTwo = new Task();
            task.setTitle("Take the trash out");
            task.setStatus(Status.INCOMPLETE);
            todo.addTask(taskTwo);
            final Task secondTask = todo.getTaskFromList(1);
            Assertions.assertThat(firstTask).isNotEqualTo(secondTask);
        }

        @Test
        void listCanBeRetrieved() {
            List<Task> list = new ArrayList<>();

            todo.setToDoList(list);

            final List<Task> retrievedList = todo.getToDoList();

            assertEquals(retrievedList, list);
        }
    }
    @Nested
    class FileTest {
        @Test
        void canVerifyExistingAccount() {
            // have to create Folder for Account first, otherwise will return false
            Account acc = new Account("Nila", "Hallo123");
            FileVerify verify = new FileVerify();
            Assertions.assertThat(verify.verifyAccount(acc)).isEqualTo(true);
        }

        @Test
        void canGiveErrorForNonExistingAccount() {
            Account acc = new Account("Max", "M31N3_B4HN");
            FileVerify verify = new FileVerify();
            Assertions.assertThat(verify.verifyAccount(acc)).isEqualTo(false);
        }

        @Test
        void canCreateFolderForNewAccount() throws IOException {
            Account acc = new Account("Nila", "Hallo123");
            FileSaving save = new FileSaving();
            File directory = new File(acc.getName());
            save.createFolderForAccount(acc);
            Assertions.assertThat(directory.exists()).isEqualTo(true);
        }

        @Test
        void checkIfDirectoryIsCreated() throws IOException{
            Account acc = new Account("Nila", "Hallo123");
            FileSaving save = new FileSaving();
            File directory = new File(acc.getName());
            save.createFolderForAccount(acc);
            Assertions.assertThat(directory.exists()).isEqualTo(true);
        }


        @Test
        void canCreateFoldersForMoreThanOneAccount() throws  IOException {
            Account acc = new Account("Balu", "b00kJu");
            Account accOne = new Account("Mani", "myMoney340");
            Account accTwo = new Account("Sara", "headEmptyAlways11111");
            FileSaving save = new FileSaving();
            File directory1 = new File(acc.getName());
            File directory2 = new File(accOne.getName());
            File directory3 = new File(accTwo.getName());
            save.createFolderForAccount(acc);
            save.createFolderForAccount(accOne);
            save.createFolderForAccount(accTwo);
            Assertions.assertThat(directory1.exists()).isEqualTo(true);
            Assertions.assertThat(directory2.exists()).isEqualTo(true);
            Assertions.assertThat(directory3.exists()).isEqualTo(true);
        }

        @Test
        void usernameIsEmptyString() {
            Account acc = new Account();
            acc.setName("");
            acc.setPassword("3M9TY");
            FileSaving save = new FileSaving();
            try {
                save.createFolderForAccount(acc);
            } catch (NullPointerException | IOException e) {
                e.printStackTrace();
            }
        }

        @Test
        void usernameToSaveIsNull() {
            Account account = new Account();
            account.setPassword("IH4V3N0N4M3");
            FileSaving save = new FileSaving();
            try {
                save.createFolderForAccount(account);
            } catch (NullPointerException | IOException e) {
                e.printStackTrace();
            }
        }

        @Test
        void passwordToSaveIsEmptyString() {
            Account acc = new Account();
            acc.setName("Miranda");
            FileSaving save = new FileSaving();
            File directory = new File(acc.getName());
            try {
                save.createFolderForAccount(acc);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Assertions.assertThat(directory.exists()).isEqualTo(false);
        }



        @Test
        void passwordAndUsernameToSaveAreNull() {
            Account acc = new Account();
            FileSaving save = new FileSaving();
            try {
                save.createFolderForAccount(acc);
            } catch (NullPointerException | IOException e) {
                e.printStackTrace();
            }
        }

        @Test
        void passwordToSaveIsNull() {
            Account account = new Account();
            account.setName("Mira");
            FileSaving save = new FileSaving();
            File directory = new File(account.getName());
            try {
                save.createFolderForAccount(account);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Assertions.assertThat(directory.exists()).isEqualTo(false);
        }

        @Test
        void saveToDoListToAccountFolder() throws ParseException {
            Account account = new Account("Nila", "Hallo123");
            File directory = new File(account.getName());
            String filenameList = account.getName() + "'s ToDoList.txt";
            Date today = new SimpleDateFormat("yyyy-MM-dd").parse("2020-05-10");
            Task task = new Task();
            ToDoList todo = new ToDoList();
            FileSaving save = new FileSaving();
            task.setTitle("Homework");
            task.setDate(today);
            todo.addTask(task);
            File toDoList = new File(directory, filenameList);
            save.writeToDoListToFile(todo, account);
            Assertions.assertThat(!(toDoList.length() == 0)).isEqualTo(true);
        }

        @Test
        void readToDoListFromAccount() throws IOException {
            FileLoading load = new FileLoading();
            List<Task> list = load.loadFromFile(new Account("Nila", "Hallo123"));
            Assertions.assertThat(list.size()).isEqualTo(1);
        }

        @Test
        void tryToWriteToDoListToNotExistingAccount() throws ParseException {
            FileSaving save = new FileSaving();
            Date today = new SimpleDateFormat("yyyy-MM-dd").parse("2020-05-10");
            Task task = new Task();
            ToDoList todo = new ToDoList();
            task.setTitle("Get the Trash out");
            task.setDate(today);
            todo.addTask(task);
            try {
                save.writeToDoListToFile(todo, new Account("Bella", "n0t3x12t1ng"));
            } catch (NullPointerException e){
                e.printStackTrace();
            }
        }
    }
}
