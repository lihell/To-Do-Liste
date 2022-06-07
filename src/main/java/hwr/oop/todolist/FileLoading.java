package hwr.oop.todolist;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

class FileLoading implements Serializable {

    FileSaving save;

    public List<Task> loadFromFile() throws IOException, ClassNotFoundException {
        InputStream file = new FileInputStream("account.txt");
        InputStream buffer = new BufferedInputStream(file);
        ObjectInput input = new ObjectInputStream(buffer);
            try {
                List<Task> loadedList = (List<Task>)input.readObject();
                return loadedList;
            } catch (IOException e) {
                System.out.println("File is empty. First thing you need to do is create a task!");
            } finally {
                file.close();
                buffer.close();
                input.close();
            }
            return null;
    }
}
