public class ThreeCardPokerGame {

    private Player playerOne;
    private Player playerTwo;
    private Dealer theDealer;
    private int countRound;
    private boolean flag;
    private boolean playAgain;

    private boolean playerOneWin = false;
    private boolean playerTwoWin = false;

    public ThreeCardPokerGame() {
        this.playerOne = new Player();
        this.playerTwo = new Player();
        this.theDealer = new Dealer();
        this.countRound = 0;
        this.flag = false;
        this.playAgain = false;
    }

    public void resetGame() {
        this.playerOne = new Player();
        this.playerTwo = new Player();
        this.theDealer = new Dealer();
        this.countRound = 0;
        this.flag = false;
        this.playAgain = false;
    }

    public void playGame() {
        playAgain = dealerHasQueenHigh();

        if (playAgain) {
            theDealer.resetDeck();  // Reset and shuffle the deck
            flag = true;

            // Deal hands
            playerOne.setHand(theDealer.dealHand());
            playerTwo.setHand(theDealer.dealHand());
            theDealer.dealHand();  // Dealer gets their own hand

            countRound++;
        }
    }

    private boolean dealerHasQueenHigh() {
        for (Card card : theDealer.getHand()) {
            if (card.getValue() >= 12) {  // 12 represents Queen or higher
                return true;
            }
        }
        return false;
    }

    public void checkPPs(Player player) {
        int pairPlusWinnings = ThreeCardLogic.evalPPWinnings(player.getHand(), player.getPairPlusBet());
        player.setTotalWinnings(player.getTotalWinnings() - player.getPairPlusBet() + pairPlusWinnings);
    }

    public int winner() {
        if (playerOneWin && !playerTwoWin) return 1;
        if (!playerOneWin && playerTwoWin) return 2;
        if (playerOneWin && playerTwoWin) return 3;
        return 4;
    }

    public void checkWinnings() {
        int playerOneResult = ThreeCardLogic.compareHands(theDealer.getHand(), playerOne.getHand(), "Player 1");
        int playerTwoResult = ThreeCardLogic.compareHands(theDealer.getHand(), playerTwo.getHand(), "Player 2");

        // Player 1
        if (playerOneResult == 2) {
            playerOneWin = true;
            playerOne.setTotalWinnings(playerOne.getTotalWinnings() + 2 * (playerOne.getAnteBet() + playerOne.getPlayBet()));
        } else if (playerOneResult == 1) {
            playerOne.setTotalWinnings(playerOne.getTotalWinnings() - playerOne.getAnteBet() - playerOne.getPlayBet());
        }

        // Player 2
        if (playerTwoResult == 2) {
            playerTwoWin = true;
            playerTwo.setTotalWinnings(playerTwo.getTotalWinnings() + 2 * (playerTwo.getAnteBet() + playerTwo.getPlayBet()));
        } else if (playerTwoResult == 1) {
            playerTwo.setTotalWinnings(playerTwo.getTotalWinnings() - playerTwo.getAnteBet() - playerTwo.getPlayBet());
        }
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

    public int getCountRound() {
        return this.countRound;
    }

    public boolean getFlag() {
        return this.flag;
    }
}
