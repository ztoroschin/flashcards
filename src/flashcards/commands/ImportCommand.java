package flashcards.commands;

import flashcards.Card;
import flashcards.Database;
import flashcards.console.ApplicationCommand;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Scanner;

public class ImportCommand implements ApplicationCommand {

    private final Database database;

    public ImportCommand(Database database) {
        this.database = database;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);

        database.print("File name:");
        String filename = scanner.nextLine().trim();
        database.appendLog(filename);

        importCards(filename);
    }

    public void importCards(String filename) {
        Map<String, Card> cards = database.getCards();

        try {
            String cardFile = new String(Files.readAllBytes(Paths.get(filename)));
            String[] cardLine = cardFile.split("\n");
            for (var card : cardLine) {
                String[] cardArr = card.split("\t");
                if (cardArr.length != 3) {
                    throw new IllegalArgumentException("Wrong file format");
                }
                cards.put(cardArr[0], new Card(cardArr[0], cardArr[1], Integer.parseInt(cardArr[2])));
            }
            database.print(String.format("%d cards have been loaded.", cardLine.length));
        } catch (IOException e) {
            database.print("File not found.");
        }
    }
}