package hwr.oop.todolist;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public class DisplayListFromAccountFunction implements DisplayListFromAccount {
    private List<Task> listOfToDos;

    @Override
    public void displayListFromAccount() {
        try {
            if (!(acc == null)) {
                todo.setToDoList(loading.loadFromFile(acc));
                List<Task> listOfToDos = todo.getToDoList();
                for (Task task : listOfToDos) {
                    System.out.println(listOfToDos.indexOf(task) + 1 + ". ");
                    System.out.println(String.format("%1$-25s", task.getTitle()));
                    System.out.println(String.format("%1$-25s", task.getStatus()));
                    System.out.println(String.format("%1$-25s", task.getDate()));
                }
            }
        } catch (IOException | ParseException | NullPointerException e) {
            System.out.println("Your List could not be loaded");
            System.out.println("Your problem could be that you haven't signed in with an Account yet: ");
            System.out.println("In this case you should logIn first");
            System.out.println("Or your List is empty: ");
            System.out.println("In this case you should start by creating some Tasks");
            //menu.displayOptionsList();
        }
    }
}
