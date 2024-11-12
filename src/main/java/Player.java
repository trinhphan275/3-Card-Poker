import java.util.ArrayList;

public class Player {
    // This class represents a player in the game
    // It keeps track of each games current hand and current bets as well as the total winnings for  that player across multiple games
    // If the player has lost more than he/she has won,that number can be negative

    private ArrayList<Card> hand;
    private int anteBet;
    private int playBet;
    private int pairPlusBet;
    private int totalWinnings;
    private int totalMoney;

    // Provide a no argument constructor for this class
    public Player() {
        this.hand = new ArrayList<>();
        this.anteBet = 0;
        this.playBet = 0;
        this.pairPlusBet = 0;
        this.totalWinnings = 0;
        this.totalMoney = 500;
        System.out.println("created player successfully");
    }

    public void setHand(ArrayList<Card> hand) {
        //this.hand = hand;
        for (int i = 0; i < 3; i++) {
            // dealersHand.add(theDeck.remove(0));
            this.hand.add(hand.get(0));
            hand.remove(0);
        }
    }

    public void setAnteBet(int anteBet) {
        this.anteBet = anteBet;
    }

    public void setPlayBet(int playBet) {
        this.playBet = playBet;
    }

    public void setPairPlusBet(int pairPlusBet) {
        this.pairPlusBet = pairPlusBet;
    }

    public void setTotalWinnings(int totalWinnings) {
        this.totalWinnings = totalWinnings;
    }

    public void setTotalMoney(int totalMoney) {
        this.totalMoney = totalMoney;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }
    public void printHand() {
        for (int i = 0; i < 3; i++) {
            System.out.println(this.hand.get(i).getValue() + " " + this.hand.get(i).getSuit());
        }
        System.out.println();
    }

    public int getAnteBet() {
        return anteBet;
    }

    public int getPlayBet() {
        return playBet;
    }

    public int getPairPlusBet() {
        return pairPlusBet;
    }

    public int getTotalWinnings() {
        return totalWinnings;
    }

    public int getTotalMoney() {
        return totalMoney;
    }

    // Provide a method to add a card to the players hand
    public void addCard(Card card) {
        hand.add(card);
    }

    // Provide a method to remove a card from the players hand
    public void removeCard(Card card) {
        hand.remove(card);
    }

    // Provide a method to clear the players hand
    public void clearHand() {
        hand.clear();
    }

    // Provide a method to add the players bets
    public void addBets(int anteBet, int playBet, int pairPlusBet) {
        this.anteBet = anteBet;
        this.playBet = playBet;
        this.pairPlusBet = pairPlusBet;
    }

    // Provide a method to clear the players bets
    public void clear() {
        hand.clear();
        anteBet = 0;
        playBet = 0;
        pairPlusBet = 0;
        totalWinnings = 0;
        totalMoney = 500;
    }

    // Provide a method to add winnings to the players total winnings
    public void addWinnings(int winnings) {
        totalWinnings += winnings;
    }

    // For testing purposes
    @Override
    public String toString() {
        return "Player{" + "hand=" + hand + ", anteBet=" + anteBet + ", playBet=" + playBet + ", pairPlusBet=" + pairPlusBet + ", totalWinnings=" + totalWinnings + '}';
    }


}