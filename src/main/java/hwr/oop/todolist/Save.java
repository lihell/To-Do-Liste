package hwr.oop.todolist;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public interface Save  {

    void createFolderForAccount(@NotNull Account account) throws IOException;

    void writeToDoListToFile(ToDoList todo, @NotNull Account account) throws IOException;
}
