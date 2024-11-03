import java.util.ArrayList;

public class Dealer {
    private final Deck theDeck;
    private final ArrayList<Card> dealersHand;

    // No-argument constructor that initializes the deck and the dealer's hand
    public Dealer() {
        theDeck = new Deck(); // Creates a new shuffled deck of 52 cards
        dealersHand = new ArrayList<>(); // Initializes the dealer's hand
    }

    // Method to deal a hand of three cards to the dealer
    public ArrayList<Card> dealHand() {
        // Check if the deck has more than 34 cards left; if not, reshuffle
        if (theDeck.size() <= 34) {
            theDeck.newDeck();
        }

        // Clear the dealer's hand and deal three cards
        dealersHand.clear();
        for (int i = 0; i < 3; i++) {
            dealersHand.add(theDeck.remove(0)); // Remove the top card from the deck and add to hand
        }

        return new ArrayList<>(dealersHand); // Return a copy of the hand
    }

    @Override
    public String toString() {
        StringBuilder handString = new StringBuilder("Dealer's Hand:\n");
        for (Card card : dealersHand) {
            handString.append(card).append("\n");
        }
        return handString.toString();
    }
}
