package hwr.oop.todolist.Menu;

import hwr.oop.todolist.Task;

import java.io.IOException;
import java.util.List;

class DeleteTaskFunction implements DeleteTask {

    @Override
    public void deleteFunction() {
        System.out.println("Which Task Would You Like To Delete? \n");
        try {
            todo.setToDoList(loading.loadFromFile(acc));
            List<Task> listOfToDos = todo.getToDoList();
            for (Task list : listOfToDos) {
                System.out.print(listOfToDos.indexOf(list) + 1 + ". ");
                System.out.println(String.format("%1$-25s", list.getTitle()));
            }
        } catch (NullPointerException | IOException e) {
            System.out.println("There is no Account");
            System.out.println("You can't get a To Do List when you aren't logged into an Account");
        }
        System.out.println("Write down the number of which task you want to DELETE (0 -> return to Menu Selection)");
        boolean check = true;

        while (check) {
            try {
                int removeByTaskNumber = Integer.parseInt(userInput());

                if (removeByTaskNumber != 0) {
                    todo.deleteTask(removeByTaskNumber - 1);
                    check = false;
                } else {
                    menu.returnMenu();
                }
            } catch (Exception e) {
                System.out.println("Task with selected index does not exist.");
                check = false;
            }
        }
        try {
            saving.writeToDoListToFile(todo, acc);
        } catch (NullPointerException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String userInput() {
        return DeleteTask.super.userInput();
    }

}
