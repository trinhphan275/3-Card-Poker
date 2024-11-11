import java.util.ArrayList;

public class Deck extends ArrayList<Card>{


    // This class represents a 52 card, standard deck, of playing cards. 
    // The constructor will create a new deck of 52 cards that have been sorted in random order. 
    // The newDeck() method will clear all the cards and create a brand new deck of 52 cards sorted in random order. 

    // The constructor will create a new deck of 52 cards in random order
    public Deck() {
        this.createDeck();
        this.shuffle();
    }

    // clear all the cards and create a brand new deck of 52 cards sorted in random order
    public void newDeck() {
        // this.clear();
        this.createDeck();
        this.shuffle();
    }

    // create a deck of 52 cards
    public void createDeck() {
        for (int i = 2; i <= 14; i++) {
            this.add(new Card('C', i));
            this.add(new Card('D', i));
            this.add(new Card('S', i));
            this.add(new Card('H', i));
        }
    }

    // create a deck of 52 cards sorted in random order
    public void shuffle() {
        for (int i = 0; i < 52; i++) {
            int randomIndex = (int) (Math.random() * 52);
            Card temp = this.get(i);
            this.set(i, this.get(randomIndex));
            this.set(randomIndex, temp);
        }
    }

    // Rearrange the card in descending order
    public ArrayList<Card> rearrange(ArrayList<Card> hand) {
        for (int i = 0; i < hand.size(); i++) {
            for (int j = i + 1; j < hand.size(); j++) {
                if (hand.get(i).getValue() < hand.get(j).getValue()) {
                    Card temp = hand.get(i);
                    hand.set(i, hand.get(j));
                    hand.set(j, temp);
                }
            }
        }
        return hand;
    }

    public ArrayList<Card> dealHand() {
        ArrayList<Card> hand = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            hand.add(this.get(0));
            this.remove(0);
        }
        return rearrange(hand) ;
    }

    // For testing purposes
    @Override
    public String toString() {
        return "Deck{" + "cards=" + this + '}';
    }

}
