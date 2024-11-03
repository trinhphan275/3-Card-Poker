
public class Card {
    private char suit;
    private int value;

    // Constructor that sets the suit and value of the card
    public Card(char suit, int value) {
        if (isValidSuit(suit) && isValidValue(value)) {
            this.suit = suit;
            this.value = value;
        } else {
            throw new IllegalArgumentException("Invalid suit or value for card.");
        }
    }

    // Getter for suit
    public char getSuit() {
        return this.suit;
    }

    // Getter for value
    public int getValue() {
        return this.value;
    }

    // Helper method to validate suit
    private boolean isValidSuit(char suit) {
        return suit == 'C' || suit == 'D' || suit == 'S' || suit == 'H';
    }

    // Helper method to validate value
    private boolean isValidValue(int value) {
        return value >= 2 && value <= 14;
    }

    // Overriding toString() method for a readable representation of the card
    @Override
    public String toString() {
        return "Card{" + "suit=" + suit + ", value=" + value + '}';
    }
}
