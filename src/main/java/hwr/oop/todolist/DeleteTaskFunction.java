package hwr.oop.todolist;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public class DeleteTaskFunction implements DeleteTask {

    @Override
    public void deleteFunction() {
        System.out.println("Which Task Would You Like To Delete? \n");
        try {
            todo.setToDoList(loading.loadFromFile(acc));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        List<Task> listOfToDos = todo.getToDoList();
        for (Task list : listOfToDos) {
            System.out.print(listOfToDos.indexOf(list) + 1 + ". ");
            System.out.println(String.format("%1$-25s", list.getTitle()));
        }
        System.out.println("Write down the number of which task you want to DELETE (0 -> return to Menu");
        boolean check = true;

        while (check) {
            try {
                int removeByTaskNumber = Integer.parseInt(userInput());

                if (removeByTaskNumber != 0) {
                    todo.deleteTask(removeByTaskNumber - 1);
                    check = false;
                } else {
                    menu.displayOptionsList();
                }
            } catch (Exception e) {
                System.out.println("Task with selected index does not exist.");
            }
        }
        try {
            saving.writeToDoListToFile(todo, acc);
        } catch (NullPointerException | IOException e) {
            e.printStackTrace();
        }
        System.out.println("Task is removed");
    }

    @Override
    public String userInput() {
        return DeleteTask.super.userInput();
    }

}
