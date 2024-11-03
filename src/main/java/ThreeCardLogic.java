import java.util.ArrayList;
import java.util.Collections;

public class ThreeCardLogic {

    // Evaluates the hand and returns an integer representing its rank
    public static int evalHand(ArrayList<Card> hand) {
        if (isStraightFlush(hand)) return 1;
        if (isThreeOfAKind(hand)) return 2;
        if (isStraight(hand)) return 3;
        if (isFlush(hand)) return 4;
        if (isPair(hand)) return 5;
        return 0; // High card
    }

    // Calculates winnings for PairPlus bet
    public static int evalPPWinnings(ArrayList<Card> hand, int bet) {
        int handRank = evalHand(hand);
        switch (handRank) {
            case 1: return bet * 40; // Straight flush
            case 2: return bet * 30; // Three of a kind
            case 3: return bet * 6;  // Straight
            case 4: return bet * 3;  // Flush
            case 5: return bet * 1;  // Pair
            default: return 0;       // High card, no win
        }
    }

    // Compares two hands: dealer and player
    public static int compareHands(ArrayList<Card> dealer, ArrayList<Card> player) {
        int dealerRank = evalHand(dealer);
        int playerRank = evalHand(player);

        // Compare based on hand rank
        if (dealerRank > playerRank) return 1; // Dealer wins
        if (playerRank > dealerRank) return 2; // Player wins

        // If ranks are equal, compare highest card values for a tie-breaker
        int dealerHighCard = getHighestCardValue(dealer);
        int playerHighCard = getHighestCardValue(player);

        if (dealerHighCard > playerHighCard) return 1;
        if (playerHighCard > dealerHighCard) return 2;

        return 0; // Tie
    }

    // Helper to check for a straight flush
    private static boolean isStraightFlush(ArrayList<Card> hand) {
        return isFlush(hand) && isStraight(hand);
    }

    // Helper to check for three of a kind
    private static boolean isThreeOfAKind(ArrayList<Card> hand) {
        return hand.get(0).getValue() == hand.get(1).getValue() &&
                hand.get(1).getValue() == hand.get(2).getValue();
    }

    // Helper to check for a straight
    private static boolean isStraight(ArrayList<Card> hand) {
        ArrayList<Integer> values = new ArrayList<>();
        for (Card card : hand) {
            values.add(card.getValue());
        }
        Collections.sort(values);
        return (values.get(2) - values.get(1) == 1) && (values.get(1) - values.get(0) == 1);
    }

    // Helper to check for a flush
    private static boolean isFlush(ArrayList<Card> hand) {
        char suit = hand.get(0).getSuit();
        return hand.get(1).getSuit() == suit && hand.get(2).getSuit() == suit;
    }

    // Helper to check for a pair
    private static boolean isPair(ArrayList<Card> hand) {
        return hand.get(0).getValue() == hand.get(1).getValue() ||
                hand.get(1).getValue() == hand.get(2).getValue() ||
                hand.get(0).getValue() == hand.get(2).getValue();
    }

    // Helper to get the highest card value in a hand
    private static int getHighestCardValue(ArrayList<Card> hand) {
        int highest = hand.get(0).getValue();
        for (Card card : hand) {
            if (card.getValue() > highest) {
                highest = card.getValue();
            }
        }
        return highest;
    }
}
