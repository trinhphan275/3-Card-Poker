import java.util.ArrayList;

public class Dealer {
    private Deck theDeck;
    private ArrayList<Card> dealersHand;

    public Dealer() {
        this.theDeck = new Deck();
        this.dealersHand = new ArrayList<>();
        System.out.println("Dealer created successfully.");
    }

    /**
     * This method deals a hand of 3 cards to the dealer.
     * If there are fewer than 34 cards left in the deck, a new deck is created and shuffled.
     *
     * @return ArrayList<Card> containing the dealer's current hand of 3 cards.
     */
    public ArrayList<Card> dealHand() {

        if (theDeck.size() < 34) {
            theDeck.newDeck();
            System.out.println("Deck reshuffled due to low card count.");
        }

        // Clear the dealer's hand for a new round
        dealersHand.clear();

        // Deal 3 cards to the dealer
        for (int i = 0; i < 3; i++) {
            dealersHand.add(theDeck.remove(0));
        }

        return new ArrayList<>(dealersHand);  // Return a copy of the hand to avoid direct modification
    }

    /**
     * Gets the size of the deck.
     * @return the number of cards remaining in the deck.
     */
    public int getDeckSize() {
        return theDeck.size();
    }

    @Override
    public String toString() {
        return "Dealer{" + "Deck Size=" + theDeck.size() + ", Dealers Hand=" + dealersHand + '}';
    }

    /**
     * Gets the dealer's current hand.
     * @return ArrayList<Card> representing the dealer's hand.
     */
    public ArrayList<Card> getHand() {
        return new ArrayList<>(dealersHand);
    }

    /**
     * Clears the dealer's deck and hand, typically for resetting the game.
     */
    public void clearDealer() {
        theDeck.clear();
        dealersHand.clear();
    }

    /**
     * Prints the dealer's hand to the console.
     */
    public void printHand() {
        System.out.println("Dealer's hand:");
        for (Card card : dealersHand) {
            System.out.println(card.getValue() + " " + card.getSuit());
        }
        System.out.println();
    }

    /**
     * Sets the dealer's hand directly.
     * @param dealerHand the new hand to set for the dealer.
     */
    public void setHand(ArrayList<Card> dealerHand) {
        // Ensure the dealer's hand is replaced with the provided hand
        if (dealerHand.size() != 3) {
            throw new IllegalArgumentException("Dealer hand must contain exactly 3 cards.");
        }

        dealersHand.clear();  // Clear current hand before setting new one
        dealersHand.addAll(dealerHand);  // Add all cards from the new hand
    }
}