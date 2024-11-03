import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Deck extends ArrayList<Card> {

    // Constructor that creates a shuffled deck of 52 cards
    public Deck() {
        newDeck(); // Initializes the deck with 52 shuffled cards
    }

    // Method to clear the deck and create a new shuffled deck of 52 cards
    public void newDeck() {
        this.clear(); // Clear any existing cards in the deck

        // Loop through suits and values to create the 52 cards
        char[] suits = {'C', 'D', 'S', 'H'};
        for (char suit : suits) {
            for (int value = 2; value <= 14; value++) {
                this.add(new Card(suit, value));
            }
        }

        // Shuffle the deck
        Collections.shuffle(this, new Random());
    }

    @Override
    public String toString() {
        StringBuilder deckString = new StringBuilder();
        for (Card card : this) {
            deckString.append(card).append("\n");
        }
        return deckString.toString();
    }
}
