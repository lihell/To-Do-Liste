package hwr.oop.todolist;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.CREATE;

public class FileSaving implements Serializable{
    // Muss noch weiter bearbeitet werden, noch nicht getestet und nur basic erstellen von accounts erstellt
    // noch keine implementierung von

    private String filename = "accounts.txt";
    Account account;
    private static Scanner x;

    void writeAccountToFile() throws IOException {
        Path path = Paths.get(filename);
        BufferedOutputStream output = new BufferedOutputStream(Files.newOutputStream(path, CREATE));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(output));
        try {
            writer.write(this.account.getNewName() + "\0" + this.account.getNewPassword());
            writer.newLine();
            System.out.println("Account has been successfully saved");
        } catch (IOException ex) {
            System.out.println("Error: Couldn't create file");
        } finally {
            output.close();
            writer.close();
        }
    }
}
