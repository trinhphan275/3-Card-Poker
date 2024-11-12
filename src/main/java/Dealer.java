import java.util.ArrayList;

public class Dealer {

    // Before each game starts, the Dealer class must check to see if there are more than 34 cards left in the deck
    // If not, theDeck must be reshuffled with a new set of 52 cards in random order
    // The data member dealersHand will hold the dealers hand in each game
    // The method dealHand() will return an ArrayList<Card> of three cards removed from theDeck

    private Deck theDeck;
    private ArrayList<Card> dealersHand;

    public Dealer() {
        this.theDeck = new Deck();
        this.dealersHand = new ArrayList<>();

        System.out.println("created dealer successfully");
    }

    public ArrayList<Card> dealHand() {
        if (theDeck.size() < 34) {
            theDeck.newDeck();
        }
        for (int i = 0; i < 9; i++) {
            // dealersHand.add(theDeck.remove(0));
            dealersHand.add(this.theDeck.get(0));
            this.theDeck.remove(0);
        }
        return this.dealersHand;
    }

    // For testing purposes
    @Override
    public String toString() {
        return "dealer{" + "theDeck=" + theDeck + ", dealersHand=" + dealersHand + '}';
    }

    public ArrayList<Card> getHand() {
        // reveal dealer's hand
        return dealersHand;
    }

    public void setHand(ArrayList<Card> dealHand) {
        this.dealersHand = dealHand;
    }

    public void clearDealer(){
        theDeck.clear();
        dealersHand.clear();
    }

    public void printHand() {
        System.out.println("Dealer's hand");
        for(int i = 0; i < 3; i++) {
            System.out.println(this.dealersHand.get(i).getValue() + " " + this.dealersHand.get(i).getSuit());
        }
        System.out.println();
    }
    
}
