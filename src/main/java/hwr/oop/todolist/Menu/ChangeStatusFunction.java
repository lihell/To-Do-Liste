package hwr.oop.todolist.Menu;

import hwr.oop.todolist.Status;
import hwr.oop.todolist.Task;

import java.io.IOException;
import java.util.List;

class ChangeStatusFunction implements ChangeStatus {

    @Override
    public void changeStatus() throws IOException {
        System.out.println("What task do you want to mark as done? \n ");
        try {
            todo.setToDoList(loading.loadFromFile(acc));
            List<Task> listOfToDos = todo.getToDoList();
            for (Task list : listOfToDos) {
                System.out.print((listOfToDos.indexOf(list) + 1) + ". ");
                System.out.println(String.format("%1$-25s", list.getTitle()));
                System.out.println(String.format("%1$-25s", list.getStatus()));
            }
        } catch (IOException | NullPointerException e) {
            System.out.println("The Account isn't set");
            System.out.println("Cant read ToDoList from nonexistent Account");
            menu.displayOptionsList();
        }
        System.out.println("\n Enter the number in front of the task that you want to mark DONE (0 -> return to Menu)");
        Task searched = new Task();
        boolean check = true;

        while (check) {
            try {
                int getTaskAtIndex = Integer.parseInt(userInput());

                if (getTaskAtIndex != 0) {
                    searched = todo.getTaskFromList(getTaskAtIndex - 1);
                    searched.setStatus(Status.DONE);
                    System.out.println("Task is now set to DONE");
                    check = false;
                } else {
                    menu.displayOptionsList();
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Task with selected index does not exist.");
                e.printStackTrace();
                check = false;
            }
        }
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
