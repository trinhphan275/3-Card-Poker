import java.util.ArrayList;

public class Player {
    // Represents a player in the game, tracking the hand, bets, total winnings, and total money available.

    private ArrayList<Card> hand;
    private int anteBet;
    private int playBet;
    private int pairPlusBet;
    private int totalWinnings;
    private int totalMoney;

    // Constructor initializes player with default values, including starting money.
    public Player() {
        this.hand = new ArrayList<>();
        this.anteBet = 0;
        this.playBet = 0;
        this.pairPlusBet = 0;
        this.totalWinnings = 0;
        this.totalMoney = 500;
        System.out.println("Player created successfully.");
    }

    // Sets the player's hand with a given set of cards.
    public void setHand(ArrayList<Card> hand) {
        this.hand.clear();
        for (int i = 0; i < 3 && !hand.isEmpty(); i++) {
            this.hand.add(hand.remove(0));
        }
    }

    // Sets the ante bet amount.
    public void setAnteBet(int anteBet) {
        this.anteBet = anteBet;
    }

    // Sets the play bet amount.
    public void setPlayBet(int playBet) {
        this.playBet = playBet;
    }

    // Sets the pair plus bet amount.
    public void setPairPlusBet(int pairPlusBet) {
        this.pairPlusBet = pairPlusBet;
    }

    // Updates total winnings by adding a specified amount.
    public void addWinnings(int winnings) {
        totalWinnings += winnings;
        totalMoney += winnings;  // Increase total money with winnings
    }

    // Sets total winnings directly (useful for resets or specific adjustments).
    public void setTotalWinnings(int totalWinnings) {
        this.totalWinnings = totalWinnings;
    }

    // Adjusts the player's total money, which can be positive or negative based on outcomes.
    public void setTotalMoney(int totalMoney) {
        this.totalMoney = totalMoney;
    }

    // Retrieves the current hand of the player.
    public ArrayList<Card> getHand() {
        return new ArrayList<>(hand);
    }

    // Displays the player's hand in a readable format.
    public void printHand() {
        System.out.println("Player's hand:");
        for (Card card : hand) {
            System.out.println(card.getValue() + " " + card.getSuit());
        }
        System.out.println();
    }

    // Retrieves current ante bet.
    public int getAnteBet() {
        return anteBet;
    }

    // Retrieves current play bet.
    public int getPlayBet() {
        return playBet;
    }

    // Retrieves current pair plus bet.
    public int getPairPlusBet() {
        return pairPlusBet;
    }

    // Retrieves total winnings, which can be negative if losses exceed wins.
    public int getTotalWinnings() {
        return totalWinnings;
    }

    // Retrieves the player's total money.
    public int getTotalMoney() {
        return totalMoney;
    }

    // Adds a card to the player's hand.
    public void addCard(Card card) {
        if (hand.size() < 3) {
            hand.add(card);
        }
    }

    // Removes a specific card from the player's hand.
    public void removeCard(Card card) {
        hand.remove(card);
    }

    // Clears the player's hand for a new game.
    public void clearHand() {
        hand.clear();
    }

    // Sets bets for the player at once, useful for initializing a game.
    public void addBets(int anteBet, int playBet, int pairPlusBet) {
        this.anteBet = anteBet;
        this.playBet = playBet;
        this.pairPlusBet = pairPlusBet;
    }

    // Clears the player's hand, bets, and winnings, and resets total money.
    public void clear() {
        clearHand();
        anteBet = 0;
        playBet = 0;
        pairPlusBet = 0;
        totalWinnings = 0;
        totalMoney = 500;
    }

    // Returns a string representation of the player's current status for testing and debugging.
    @Override
    public String toString() {
        return "Player{" +
                "hand=" + hand +
                ", anteBet=" + anteBet +
                ", playBet=" + playBet +
                ", pairPlusBet=" + pairPlusBet +
                ", totalWinnings=" + totalWinnings +
                ", totalMoney=" + totalMoney +
                '}';
    }
}
