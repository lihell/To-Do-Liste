package hwr.oop.todolist;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.assertj.core.api.Assertions;

public class ToDoListTests {

    @Nested
    class TaskTest {
        Task task = new Task();
        Date date;
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");


        @Test
        void taskTitleCanBeSet() {
            task.setTitle("Homework");
            Assertions.assertThat(task.getTitle()).isEqualTo("Homework");
        }

        @Test
        void ifTitleNotGivenOrNullThrowsException() {
            try {
                task.setTitle("");
                task.setStatus("COMPLETE");
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }

        @Test
        void ifStatusNotGivenOrNullThrowsException() {
            try {
                task.setTitle("Task Title");
                task.setStatus("");
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }

        @Test
        void taskStatusCanBeSet() {
            task.setStatus("DONE");
            Assertions.assertThat(task.getStatus()).isEqualTo("DONE");
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
        Date date;
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        @BeforeEach
        void setUp() {
            todo = new ToDoList();
        }
        @Test
        void canAddNewTask() {
            task.setTitle("Homework");
            task.setStatus("NOT COMPLETE");

            int initialListSize = todo.getToDoList().size();
            todo.addTask(task);
            Assertions.assertThat(initialListSize+1).isEqualTo(todo.getToDoList().size());
        }

        @Test
        void taskTitleIsCorrectTitle() {
            task.setTitle("Feed the cat");
            task.setStatus("NOT COMPLETE");

            Assertions.assertThat(task.getTitle()).isEqualTo("Feed the cat");
        }


        @Test
            // gibt richtiges Ergebnis, aber wird als falsch markiert
        void noStatusGivenGivesBackException() {
            task.setTitle("I am a Title");
            task.setStatus("");
            Assertions.assertThatThrownBy(() -> task.getStatus()).isInstanceOf(NullPointerException.class);
        }

        @Test
        void canDeleteOneTask() {
            task.setTitle("Homework");
            task.setStatus("NOT COMPLETE");

            todo.addTask(task);
            int sizeAfterAddTask = todo.getToDoList().size();
            todo.deleteTask(0);
            Assertions.assertThat(sizeAfterAddTask-1).isEqualTo(todo.getToDoList().size());

        }

        @Test
        void getAnyTask() {
            task.setTitle("Homework");
            task.setStatus("NOT COMPLETE");
            todo.addTask(task);
            final Task firstTask = todo.getTaskFromList(0);

            Task tasktwo = new Task();
            task.setTitle("Take the trash out");
            task.setStatus("COMPLETE");
            todo.addTask(tasktwo);
            final Task secondTask = todo.getTaskFromList(1);
            Assertions.assertThat(firstTask).isNotEqualTo(secondTask);
        }

        @Test
        void deleteAllTasksFromList() {
            task.setTitle("Homework");
            task.setStatus("NOT COMPLETE");
            todo.addTask(task);

            Task tasktwo = new Task();
            task.setTitle("Take the trash out");
            task.setStatus("COMPLETE");
            todo.addTask(tasktwo);
            List<Task> toDoListWithTasks = todo.getToDoList();
            todo.deleteAll();
            List<Task> toDoListDeleted = todo.getToDoList();

            Assertions.assertThat(toDoListWithTasks).isEqualTo(toDoListDeleted);
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

        }
    }
}

