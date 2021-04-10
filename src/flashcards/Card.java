package flashcards;

public class Card {

    private final String term;
    private final String definition;
    private int mistakes;

    public Card(String term, String definition, int mistakes) {
        this.term = term;
        this.definition = definition;
        this.mistakes = mistakes;
    }

    public Card(String term, String definition) {
        this(term, definition, 0);
    }

    public String getTerm() {
        return term;
    }

    public String getDefinition() {
        return definition;
    }

    public int getMistakes() {
        return mistakes;
    }

    public void incrementMistakes() {
        mistakes++;
    }

    public void resetMistakes() {
        mistakes = 0;
    }
}