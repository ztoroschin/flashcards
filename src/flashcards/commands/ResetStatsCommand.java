package flashcards.commands;

import flashcards.Card;
import flashcards.Database;
import flashcards.console.ApplicationCommand;

import java.util.Map;

public class ResetStatsCommand implements ApplicationCommand {

    private final Database database;

    public ResetStatsCommand(Database database) {
        this.database = database;
    }

    @Override
    public void execute() {
        Map<String, Card> cards = database.getCards();
        for (var card : cards.entrySet()) {
            card.getValue().resetMistakes();
        }
        database.print("Card statistics has been reset.");
    }
}