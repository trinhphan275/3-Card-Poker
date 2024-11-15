import java.util.ArrayList;
import java.util.Collections;

public class Deck extends ArrayList<Card> {

    /**
     * Constructor: Initializes a new deck of 52 cards and shuffles them into random order.
     */
    public Deck() {
        newDeck();
    }

    /**
     * Clears the deck, creates a new 52-card deck, and shuffles it into random order.
     */
    public void newDeck() {
        this.clear();
        createDeck();
        shuffle();
    }

    /**
     * Populates the deck with 52 cards (4 suits, values 2-14).
     */
    private void createDeck() {
        char[] suits = {'C', 'D', 'S', 'H'};
        for (char suit : suits) {
            for (int value = 2; value <= 14; value++) {
                this.add(new Card(suit, value));
            }
        }
    }

    /**
     * Shuffles the deck into random order.
     */
    public void shuffle() {
        Collections.shuffle(this);
    }

    /**
     * Draws (removes) the top card from the deck.
     * @return The top card, or null if the deck is empty.
     */
    public Card draw() {
        if (this.isEmpty()) {
            return null;  // No cards left to draw
        }
        return this.remove(0);  // Removes and returns the top card
    }

    /**
     * For testing purposes: Provides a string representation of the deck.
     * @return String representation of the deck.
     */
    @Override
    public String toString() {
        return "Deck{" + "cards=" + this + '}';
    }
}
