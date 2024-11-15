public class ThreeCardPokerGame {

    Player playerOne;
    Player playerTwo;
    Dealer theDealer;
    int countRound;

    Deck deck = new Deck();
    boolean playAgain;
    boolean flag = false;

    boolean playerOneWin = false;
    boolean playerTwoWin = false;

    public ThreeCardPokerGame() {
        this.playerOne = new Player();
        this.playerTwo = new Player();
        this.theDealer = new Dealer();
    }

    // Reset the game by creating new Player and Dealer objects
    public void resetGame() {
        this.playerOne = new Player();
        this.playerTwo = new Player();
        this.theDealer = new Dealer();
        this.deck.newDeck();  // Reset the deck
    }

    // Main method that runs the game
    public void playGame() {
        playAgain = dealerHasQueenHigh();

        if (playAgain) {
            deck.shuffle();  // Shuffle the deck before dealing new hands

            // Deal hands to players and dealer
            playerOne.setHand(theDealer.dealHand());
            playerTwo.setHand(theDealer.dealHand());
            theDealer.dealHand();  // Dealer deals hand to themselves

            this.countRound++;
            this.flag = true;
        }
    }

    // Method to check Pair Plus Winnings
    public void checkPPs(Player player) {
        player.setTotalWinnings(player.getTotalWinnings() - player.getPairPlusBet());
        if (ThreeCardLogic.evalHand(player.getHand()) != 0) {
            player.setTotalWinnings(player.getTotalWinnings() +
                    ThreeCardLogic.evalPPWinnings(player.getHand(), player.getPairPlusBet()));
        }
    }



    // Determines the winner of the game
    public int winner() {
        if (playerOneWin && !playerTwoWin) {
            return 1;
        } else if (!playerOneWin && playerTwoWin) {
            return 2;
        } else if (playerOneWin && playerTwoWin) {
            return 3;  // Tie between both players
        } else {
            return 4;  // No winner yet
        }
    }

    // Method to check if the dealer has at least a Queen-high hand
    private boolean dealerHasQueenHigh() {
        // Checking if any card in the dealer's hand is higher than a Queen (value > 12)
        for (Card card : theDealer.getHand()) {
            if (card.getValue() > 12) {
                return true;  // Dealer has a Queen-high or better
            }
        }
        return false;  // Dealer does not have a Queen-high or better
    }

    // Getter methods
    public Dealer getDealer() {
        return this.theDealer;
    }

    public Player getPlayerOne() {
        return this.playerOne;
    }

    public Player getPlayerTwo() {
        return this.playerTwo;
    }

    public Deck getDeck() {
        return this.deck;
    }

    public int getCountRound() {
        return this.countRound;
    }

    public boolean getFlag() {
        return this.flag;
    }

    // Method to check for winnings after the game round
    public void checkWinnings() {
        // Player 1 win
        if (ThreeCardLogic.compareHands(theDealer.getHand(), playerOne.getHand(), "Player 1") == 2) {
            playerOne.setTotalWinnings(playerOne.getTotalWinnings() + 2 * (playerOne.getAnteBet() + playerOne.getPlayBet()));
            playerOneWin = true;
        } else if (ThreeCardLogic.compareHands(theDealer.getHand(), playerOne.getHand(), "Dealer 1") == 1) {
            // Player 1 loses
            playerOne.setTotalWinnings(playerOne.getTotalWinnings() - playerOne.getAnteBet() - playerOne.getPlayBet());
        }

        // Player 2 win
        if (ThreeCardLogic.compareHands(theDealer.getHand(), playerTwo.getHand(), "Player 2") == 2) {
            playerTwoWin = true;
            playerTwo.setTotalWinnings(playerTwo.getTotalWinnings() + 2 * (playerTwo.getAnteBet() + playerTwo.getPlayBet()));
        } else if (ThreeCardLogic.compareHands(theDealer.getHand(), playerTwo.getHand(), "Dealer 2") == 1) {
            // Player 2 loses
            playerTwo.setTotalWinnings(playerTwo.getTotalWinnings() - playerTwo.getAnteBet() - playerTwo.getPlayBet());
        }
    }

}
