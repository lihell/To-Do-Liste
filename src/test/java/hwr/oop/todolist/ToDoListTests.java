package hwr.oop.todolist;


import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.assertj.core.api.Assertions;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoListTests {

    @Nested
    class TaskTest {
        Task task = new Task();


        @Test
        void taskTitleCanBeSet() {
            task.setTitle("Homework");
            Assertions.assertThat(task.getTitle()).isEqualTo("Homework");
        }

        @Test
        void ifTitleNotGivenOrNullThrowsException() {
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
        void deleteAllTasksFromList() {
            task.setTitle("Homework");
            task.setStatus(Status.INCOMPLETE);
            todo.addTask(task);

            Task taskTwo = new Task();
            task.setTitle("Take the trash out");
            task.setStatus(Status.DONE);
            todo.addTask(taskTwo);
            List<Task> toDoListWithTasks = todo.getToDoList();
            todo.deleteAll();
            List<Task> toDoListDeleted = todo.getToDoList();

            Assertions.assertThat(toDoListWithTasks).isEqualTo(toDoListDeleted);
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
    class AccountTest {
        Account account;

        @BeforeEach
        void setUp() {
            account = new Account("Account", "Hallo123");
        }


        @Test
        void nameIsTheRightName() {
            Assertions.assertThat(account.getNewName()).isEqualTo("Account");
        }

        @Test
        void passwordIsRightPassword() {
            Assertions.assertThat(account.getNewPassword()).isEqualTo("Hallo123");
        }

        @Test
        void nameIsNotGiven() {
            try {
                Account noName = new Account("", "Hallo1223");
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }

        @Test
        void passwordIsNotGiven() {
            try {
                Account noPassword = new Account("MyName", "");
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }

        @Test
        void testForSettingAccountName() {
            Account acc = new Account();
            acc.setNewName("Nila");
            Assertions.assertThat(acc.getNewName()).isEqualTo("Nila");
        }

        @Test
        void testForSettingAccountPassword() {
            Account acc = new Account();
            acc.setNewPassword("H311sKitchen");
            Assertions.assertThat(acc.getNewPassword()).isEqualTo("H311sKitchen");
        }
    }

        @Nested
        class FileTest {

            @Test
            void canWriteAccountToFile() throws IOException {
                Account acc = new Account("Nila", "Hallo123");
                FileSaving save = new FileSaving();
                save.writeAccountToFile(acc);
            }

            @Test
            void canWriteMoreThanOneAccountToFile() throws IOException {
                Account acc = new Account("Nila", "Hallo123");
                Account accone = new Account("Mani", "myMoney340");
                Account acctwo = new Account("Sara", "headEmptyAlways11111");
                FileSaving save = new FileSaving();
                save.writeAccountToFile(acc);
                save.writeAccountToFile(accone);
                save.writeAccountToFile(acctwo);
            }

            @Test
            void canVerifyExistingAccount() {
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
                save.createFolderForAccount(acc);
            }
        }
    @Nested
    class MenuFunctionTest {
        ToDoList todo;
        @BeforeEach
        void setUp() {
            todo = new ToDoList();
        }

        @Test
        void addFunctionWorks() throws ParseException {
            MenuFunction menu = new MenuFunction();
            menu.addFunction();
            int sizeAfterOneTaskAdded = todo.getToDoList().size();
            Assertions.assertThat(todo.getToDoList().size()+1).isNotEqualTo(sizeAfterOneTaskAdded);
        }

        @Test
        void addFunctionWrongDateFormat() throws ParseException {
            MenuFunction menu = new MenuFunction();
            try {
                menu.addFunction();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }
}

