package flashcards.commands;

import flashcards.Card;
import flashcards.Database;
import flashcards.console.ApplicationCommand;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class AskCommand implements ApplicationCommand {

    private final Database database;

    public AskCommand(Database database) {
        this.database = database;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        Map<String, Card> cards = database.getCards();

        List<String> cardArray = new ArrayList<>(cards.keySet());
        Random random = new Random();

        database.print("How many times to ask?");
        int questions = Integer.parseInt(scanner.nextLine());
        database.appendLog(String.valueOf(questions));

        for (int i = 0; i < questions; i++) {
            int randomIndex = random.nextInt(cardArray.size());
            String term = cardArray.get(randomIndex);

            database.print(String.format("Print definition of \"%s\":", term));
            String answer = scanner.nextLine().trim();
            database.appendLog(answer + "\n");
            if (cards.get(term).getDefinition().equals(answer)) {
                database.print("Correct answer.");
            } else {
                cards.get(term).incrementMistakes();
                String originalTerm = "";
                for (var card : cards.entrySet()) {
                    if (card.getValue().getDefinition().equals(answer)) {
                        originalTerm = card.getKey();
                    }
                }

                if (originalTerm.equals("")) {
                    database.print(String.format("Wrong answer. The correct one is \"%s\".", cards.get(term).getDefinition()));
                } else {
                    database.print(String.format("Wrong answer. The correct one is \"%s\", you've just written the definition of \"%s\".", cards.get(term).getDefinition(), originalTerm));
                }
            }
        }
    }
}