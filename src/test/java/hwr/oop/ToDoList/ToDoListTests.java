package hwr.oop.ToDoList;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.assertj.core.api.Assertions;

public class ToDoListTests {

    @Nested
    class TaskTest {
        hwr.oop.Task task;
        @BeforeEach
        void setUp() {
            task = new hwr.oop.Task();
        }

        @Test
        void taskTitleCanBeSet() {
            task.setTitle("Homework");
            Assertions.assertThat(task.getTitle()).isEqualTo("Homework");
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

    }
}
