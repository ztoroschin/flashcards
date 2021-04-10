package flashcards.commands;

import flashcards.Card;
import flashcards.Database;
import flashcards.console.ApplicationCommand;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class HardestCardCommand implements ApplicationCommand {

    private final Database database;

    public HardestCardCommand(Database database) {
        this.database = database;
    }

    @Override
    public void execute() {
        Map<String, Card> cards = database.getCards();

        int mistakes = 0;
        Set<String> cardsWithMostMistakes = new HashSet<>();
        for (var card : cards.entrySet()) {
            if (card.getValue().getMistakes() > mistakes) {
                cardsWithMostMistakes.clear();
                mistakes = card.getValue().getMistakes();
                cardsWithMostMistakes.add(card.getKey());
            }
            if (card.getValue().getMistakes() == mistakes && mistakes > 0) {
                cardsWithMostMistakes.add(card.getKey());
            }
        }

        String message;
        if (cardsWithMostMistakes.isEmpty()) {
            database.print("There are no cards with errors.");
        } else if (cardsWithMostMistakes.size() == 1) {
            String card = cardsWithMostMistakes.iterator().next();
            database.print(String.format("The hardest card is \"%s\". You have %d errors answering it.", card, mistakes));
        } else {
            String cardsWithMistakes = String.join("\", \"", cardsWithMostMistakes);
            database.print(String.format("The hardest cards are \"%s\". You have %d errors answering it.", cardsWithMistakes, mistakes));
        }
    }
}