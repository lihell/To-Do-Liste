package hwr.oop.todolist;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public class ChangeStatusFunction implements ChangeStatus {

    @Override
    public void changeStatus() {
        System.out.println("What task do you want to mark as done? \n ");
        try {
            todo.setToDoList(loading.loadFromFile(acc));
        } catch (IOException | ParseException | NullPointerException e) {
            e.printStackTrace();
        }
        List<Task> listOfToDos = todo.getToDoList();
        for (Task list : listOfToDos) {
            System.out.print((listOfToDos.indexOf(list) + 1) + ". ");
            System.out.println(String.format("%1$-25s", list.getTitle()));
            System.out.println(String.format("%1$-25s", list.getStatus()));
        }
        System.out.println("\n Enter the number in front of the task that you want to mark DONE (0 -> return to Menu)");
        Task searched = new Task();
        boolean check = true;

        while (check) {
            try {
                int getTaskAtIndex = Integer.parseInt(userInput());

                if (getTaskAtIndex != 0) {
                    searched = todo.getTaskFromList(getTaskAtIndex - 1);
                    check = false;
                } else {
                    menu.displayOptionsList();
                }
            } catch (IndexOutOfBoundsException | ParseException e) {
                System.out.println("Task with selected index does not exist.");
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        searched.setStatus(Status.DONE);
        System.out.println("Task is now set to DONE");
        try {
            saving.writeToDoListToFile(todo, acc);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String userInput() {
        return ChangeStatus.super.userInput();
    }

}
