package hwr.oop.todolist;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public class DeleteAllFunction implements DeleteAll {

    @Override
    public void deleteAllFunction() {
        System.out.println("Do you want to delete all Tasks? \n");
        try {
            todo.setToDoList(loading.loadFromFile(acc));
        } catch (IOException | ParseException | NullPointerException e) {
            e.printStackTrace();
        }
        List<Task> listOfToDos= todo.getToDoList();
        for (Task list : listOfToDos) {
            System.out.print(listOfToDos.indexOf(list) + 1 + ". ");
            System.out.println(String.format("%1$-25s", list.getTitle()));
        }
        System.out.println("If you want to delete all Tasks press any number but 0 (0 -> return to Menu)");
        try {
            int removeAllTasks = Integer.parseInt(userInput());

            if (removeAllTasks != 0) {
                todo.deleteAll();
            } else {
                menu.displayOptionsList();
            }
        } catch (Exception e) {
            System.out.println("Please put in a number");
        }
    }

    @Override
    public String userInput() {
        return DeleteAll.super.userInput();
    }
}
