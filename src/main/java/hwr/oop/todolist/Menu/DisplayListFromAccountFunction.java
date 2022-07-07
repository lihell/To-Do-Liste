package hwr.oop.todolist.Menu;

import hwr.oop.todolist.Task;

import java.io.IOException;
import java.util.List;

class DisplayListFromAccountFunction implements DisplayListFromAccount {

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
        } catch (IOException | NullPointerException e) {
            System.out.println("Your List could not be loaded");
            System.out.println("Your problem could be that you haven't signed in with an Account yet: ");
            System.out.println("In this case you should logIn first");

        }
    }
}
