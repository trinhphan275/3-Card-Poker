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
     * Deals a hand of 3 cards to the dealer.
     * If fewer than 34 cards remain, reshuffle the deck.
     *
     * @return ArrayList<Card> containing the dealer's current hand of 3 cards.
     */
    public ArrayList<Card> dealHand() {
        if (theDeck.size() < 34) {
            theDeck.newDeck();
            System.out.println("Deck reshuffled due to low card count.");
        }

        dealersHand.clear();  // Clear hand for a new round

        for (int i = 0; i < 3; i++) {
            dealersHand.add(theDeck.remove(0));
        }

        return new ArrayList<>(dealersHand);  // Return a copy to protect encapsulation
    }

    public ArrayList<Card> getHand() {
        return new ArrayList<>(dealersHand);  // Protect encapsulation
    }

    public int getDeckSize() {
        return theDeck.size();
    }

    public void resetDeck() {
        theDeck.newDeck();
        dealersHand.clear();
    }

    @Override
    public String toString() {
        return "Dealer{" +
                "Deck Size=" + theDeck.size() +
                ", Dealers Hand=" + dealersHand +
                '}';
    }

    public void clearDealer() {
        theDeck.clear();
        dealersHand.clear();
    }
}
