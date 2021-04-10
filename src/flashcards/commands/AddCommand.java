package flashcards.commands;

import flashcards.Card;
import flashcards.Database;
import flashcards.console.ApplicationCommand;

import java.util.Map;
import java.util.Scanner;

public class AddCommand implements ApplicationCommand {

    private final Database database;

    public AddCommand(Database database) {
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
            database.print(String.format("The card \"%s\" already exists.", term));
            return;
        }

        database.print("The definition of the card:");
        String definition = scanner.nextLine().trim();
        database.appendLog(definition);
        for (var card : cards.entrySet()) {
            if (card.getValue().getDefinition().equals(definition)) {
                database.print(String.format("The definition \"%s\" already exists.", definition));
                return;
            }
        }

        cards.put(term, new Card(term, definition));
        database.print(String.format("The pair (\"%s\":\"%s\") has been added.", term, definition));
    }
}