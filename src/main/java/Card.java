public class Card {
    // This class represents a card in a deck of 52 playing cards. 
    // The data member suit will be a capitalized character representing the suit of the card(clubs, diamonds, spades, or hearts) ‘C’, ‘D’, ’S’, ‘H’
    // The data member value will be an integer value between 2 - 14, with the value of an ace being 14,king 13,queen 12,jack 11,ten 10…..and so on.
    // You will provide a two argument constructor that takes in and sets the values for suit and value.

    private char suit;
    private int value;

    public Card(char suit, int value) {
        this.suit = suit;
        this.value = value;
    }

    public char getSuit() {
        return suit;
    }

    public void setSuit(char suit) {
        this.suit = suit;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    // For testing purposes
    @Override
    public String toString() {
        return "Card{" + "suit=" + suit + ", value=" + value + '}';
    }

}
