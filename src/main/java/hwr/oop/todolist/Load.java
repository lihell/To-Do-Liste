package hwr.oop.todolist;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public interface Load {
    List<Task> loadFromFile(Account account) throws IOException;
}
