package flashcards;

import java.util.HashMap;
import java.util.Map;

public class Database {

    private final Map<String, Card> cards;
    private final StringBuilder log;

    public Database() {
        cards = new HashMap<>();
        log = new StringBuilder();
    }

    public Map<String, Card> getCards() {
        return cards;
    }

    public void appendLog(String line) {
        log.append(line).append("\n");
    }

    public void print(String line) {
        System.out.println(line);
        log.append(line).append("\n");
    }

    public String getLog() {
        return log.toString();
    }
}