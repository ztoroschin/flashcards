package flashcards.commands;

import flashcards.Card;
import flashcards.Database;
import flashcards.console.ApplicationCommand;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Scanner;

public class ExportCommand implements ApplicationCommand {

    private final Database database;

    public ExportCommand(Database database) {
        this.database = database;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);

        database.print("File name:");
        String filename = scanner.nextLine().trim();
        database.appendLog(filename);

        exportCards(filename);
    }

    public void exportCards(String filename) {
        Map<String, Card> cards = database.getCards();

        File file = new File(filename);

        try (PrintWriter printWriter = new PrintWriter(file)) {
            for (var card : cards.entrySet()) {
                printWriter.printf("%s\t%s\t%s\n", card.getKey(), card.getValue().getDefinition(), card.getValue().getMistakes());
            }
        } catch (FileNotFoundException e) {
            database.print(String.format("File not found: %s", e.getMessage()));
        }

        database.print(String.format("%d cards have been saved.", cards.size()));
    }
}