package flashcards.commands;

import flashcards.Database;
import flashcards.console.ApplicationCommand;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class LogCommand implements ApplicationCommand {

    private final Database database;

    public LogCommand(Database database) {
        this.database = database;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);

        database.print("File name:");
        String filename = scanner.nextLine().trim();
        database.appendLog(filename);

        try {
            Files.write(Paths.get(filename), database.getLog().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        database.print("The log has been saved.");
    }
}