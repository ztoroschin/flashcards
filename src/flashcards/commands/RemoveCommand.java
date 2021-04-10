package flashcards.commands;

import flashcards.Card;
import flashcards.Database;
import flashcards.console.ApplicationCommand;

import java.util.Map;
import java.util.Scanner;

public class RemoveCommand implements ApplicationCommand {

    private final Database database;

    public RemoveCommand(Database database) {
        this.database = database;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        Map<String, Card> cards = database.getCards();

        database.print("The card:");
        String term = scanner.nextLine().trim();
        database.appendLog(term);
        if (cards.containsKey(term)) {
            cards.remove(term);
            database.print("The card has been removed.");
        } else {
            database.print(String.format("Can't remove \"%s\": there is no such card.\n", term));
        }
    }
}