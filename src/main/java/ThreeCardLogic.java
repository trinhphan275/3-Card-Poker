import java.util.ArrayList;
import java.util.Comparator;


public class ThreeCardLogic {

    // This class will contain the logic for the game
    // It will be used by the ThreeCardPoker class to determine the outcome of each game

    private static boolean isPair(ArrayList<Card> hand) {
        // This method will return true if the hand has a pair
        for (int i = 0; i < 3; i++) {
            for (int j = i + 1; j < 3; j++) {
                if (hand.get(i).getValue() == hand.get(j).getValue()) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean isFlush(ArrayList<Card> hand) {
        // This method will return true if the hand has a flush
        boolean isFlush = false;
        for (int i = 0; i < hand.size() - 1; i++) {
            // for (int j = i + 1; j < hand.size(); j++) {
            if (hand.get(i).getSuit() == hand.get(i + 1).getSuit()) {
                isFlush = true;
            } else {
                isFlush = false;
                break;
            }
        }
        //}
        return isFlush;
    }

    private static boolean isStraight(ArrayList<Card> hand) {
        // This method will return true if the hand has a straight
        // boolean isStraight = false;
        ArrayList<Integer> list = new ArrayList<>();
        list.add(hand.get(0).getValue());
        list.add(hand.get(1).getValue());
        list.add(hand.get(2).getValue());
        list.sort(Comparator.naturalOrder());
        if ((list.get(2) - list.get(1)) == 1)
            if (list.get(1) - list.get(0) == 1)
                return true;
                // Special case
            else if ((list.get(0) == 2) && (list.get(1) == 3) &&
                    (list.get(2) == 14))
                return true;
        return false;
    }

    private static boolean isThreeOfAKind(ArrayList<Card> hand) {
        // This method will return true if the hand has three of a kind
        if (hand.get(0).getValue() == hand.get(1).getValue() && hand.get(1).getValue() == hand.get(2).getValue()) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean isStraightFlush(ArrayList<Card> hand) {
        // This method will return true if the hand has a straight flush
        if (isStraight(hand) && isFlush(hand)) {
            return true;
        } else {
            return false;
        }
    }

    public static int evalPPWinnings(ArrayList<Card> hand, int bet) {
        // return the amount won for the PairPlus bet
        //  It will evaluate the hand and then evaluate the winnings and return the amount won
        //  If the player lost the Pair Plus bet, it will just return 0
        int winnings = 0;
        if (evalHand(hand) == 1) {
            winnings = bet * 40 + bet;
        } else if (evalHand(hand) == 2) {
            winnings = bet * 30 + bet;
        } else if (evalHand(hand) == 3) {
            winnings = bet * 6 + bet;
        } else if (evalHand(hand) == 4) {
            winnings = bet * 3 + bet;
        } else if (evalHand(hand) == 5) {
            winnings = bet * 1 + bet;
        }
        return winnings;
    }


    // The method evaluateHand() will be used to determine the outcome of the game
    // return 0 if the hand just has a high card
    // return 1 for a straight flush
    // return 2 for three of a kind
    // return 3 for a straight
    // return 4 for a flush
    // return 5 for a pair
    public static int evalHand(ArrayList<Card> hand) {
        int handValue = 0;
        if (isStraightFlush(hand)) {
            handValue = 1;
        } else if (isThreeOfAKind(hand)) {
            handValue = 2;
        } else if (isStraight(hand)) {
            handValue = 3;
        } else if (isFlush(hand)) {
            handValue = 4;
        } else if (isPair(hand)) {
            handValue = 5;
        }
        return handValue;
    }

    private static int getPair(ArrayList<Integer> list) {
        int num = 0;
        if (list.get(0) == list.get(1))
            num = list.get(0);
        else if (list.get(1) == list.get(2))
            num = list.get(2);
        else
            num = list.get(0);
        return num;
    }

    private static int getPairSingle(ArrayList<Integer> list) {
        int num = 0;
        if (list.get(0) == list.get(1))
            num = list.get(2);
        else if (list.get(1) == list.get(2))
            num = list.get(0);
        else
            num = list.get(1);
        return num;
    }

    private static int comparePairs(ArrayList<Integer> listDealer, ArrayList<Integer> listPlayer) {
        int playerPair = ThreeCardLogic.getPair(listPlayer);
        int dealerPair = ThreeCardLogic.getPair(listDealer);
        // System.out.println("playerPair " + playerPair + "!\n");
        // System.out.println("dealerrPair " + dealerPair + "!\n");
        int dealerSingle = 0;
        int playerSingle = 0;

        if (playerPair > dealerPair)
            return 2;
        else if (playerPair < dealerPair)
            return 1;
        else {
            playerSingle = getPairSingle(listDealer);
            dealerSingle = getPairSingle(listPlayer);
            if (playerSingle > dealerSingle)
                return 2;
            else if (playerSingle < dealerSingle)
                return 1;
            else
                return 0;
        }
    }

    private static int compareFlush(ArrayList<Integer> dealer, ArrayList<Integer> player) {
        int dealerHighest = 0;
        int playerHighest = 0;

        for(int i = 0; i < 3; i++){
            if(dealer.get(i) > dealerHighest) {
                dealerHighest = dealer.get(i);
            }
            if(player.get(i) > playerHighest) {
                playerHighest = player.get(i);
            }
        }

        if (playerHighest > dealerHighest)
            return 2;
        else if (playerHighest < dealerHighest)
            return 1;
        else
            return 0;
    }

    private static int compareStraight(ArrayList<Integer> dealer, ArrayList<Integer> player) {
        if (dealer.get(0) > player.get(0))
            return 1;
        else if (dealer.get(0) < player.get(0))
            return 2;
        else
            return 0;
    }

    private static int compareThreeOfAKind(ArrayList<Integer> listDealer, ArrayList<Integer> listPlayer) {
        // In Three of a Kind, all three cards have the same rank, so we compare the rank of the three cards.
        // Assuming that listDealer and listPlayer are sorted and contain only the three identical cards' values.

        int dealerRank = listDealer.get(0); // All three cards in listDealer are the same, so we can just take the first.
        int playerRank = listPlayer.get(0); // Similarly, for listPlayer.

        // Compare the ranks
        if (dealerRank > playerRank) {
            return 1; // Dealer wins
        } else if (dealerRank < playerRank) {
            return 2; // Player wins
        } else {
            return 0; // Tie (both hands have the same Three of a Kind rank)
        }
    }


    public static int compareHands(ArrayList<Card> dealer, ArrayList<Card> player, String whichPlayer) {
        int valDealer = evalHand(dealer);
        int valPlayer = evalHand(player);

        ArrayList<Integer> listDealer = new ArrayList<>();
        ArrayList<Integer> listPlayer = new ArrayList<>();

        // Populate card values
        for (int i = 0; i < 3; i++) {
            listDealer.add(dealer.get(i).getValue());
            listPlayer.add(player.get(i).getValue());
        }

        // Sort the hands in descending order by card value
        listDealer.sort(Comparator.reverseOrder());
        listPlayer.sort(Comparator.reverseOrder());

        System.out.println(valDealer);
        System.out.println(whichPlayer + " " + valPlayer);

        // Compare hand rankings
        if (valDealer == valPlayer) {
            // If hands are of the same type, compare individual cards
            return compareSameRankHands(valDealer, valPlayer, listDealer, listPlayer);
        }

        // Compare hands based on hand ranking (0 = no hand, 1 = pair, etc.)
        if (valDealer < valPlayer) {
            return 1; // Dealer wins
        } else {
            return 2; // Player wins
        }
    }

    // Additional method to handle tie-breakers for same-ranked hands
    private static int compareSameRankHands(int valDealer, int valPlayer, ArrayList<Integer> listDealer, ArrayList<Integer> listPlayer) {
        if (valDealer == 5) { // Flush
            return compareFlush(listDealer, listPlayer);
        } else if (valDealer == 4) { // Straight
            return compareStraight(listDealer, listPlayer);
        } else if (valDealer == 3) { // Three of a kind (if implemented)
            return compareThreeOfAKind(listDealer, listPlayer);
        } else if (valDealer == 2) { // Pair
            return comparePairs(listDealer, listPlayer);
        } else {
            return 0; // Both hands are equally weak (e.g., no valid hand)
        }
    }
}