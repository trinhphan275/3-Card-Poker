import java.util.ArrayList;
import java.util.Scanner;

import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;

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

    public void resetGame(){
        this.playerOne = new Player();
        this.playerTwo = new Player();
        this.theDealer = new Dealer();
    }

    // The playGame() method will be the main method that will run the game
    public void playGame() {

        // If dealer do not have a Queen or better, return pair plus wager for players
        // shuffle the deck and deal new cards to each player and the dealer
        // add the ante wager of each players to the totalWinnings variable
        for (int i = 0; i < 3; i++) {
            if (theDealer.getHand().get(i).getValue() > 12) {
                playAgain = false;
                break;
            }
        }

        if (playAgain) {
            // If the dealer do not have a Qeen or better
            // playerOne.setTotalMoney(playerOne.getTotalMoney() + playerOne.getPairPlusBet());
            // playerTwo.setTotalMoney(playerTwo.getTotalMoney() + playerTwo.getPairPlusBet());
            // playerOne.setTotalWinnings(playerOne.getAnteBet() + playerTwo.getAnteBet());
            // playerTwo.setTotalWinnings(playerOne.getAnteBet() + playerTwo.getAnteBet());
            deck.shuffle();
            playerOne.setHand(deck.dealHand());
            playerTwo.setHand(deck.dealHand());
            theDealer.setHand(deck.dealHand());
            this.countRound++;
            this.flag = true;
        }
    }

    public void checkPPs(Player player) {
        player.setTotalWinnings(player.getTotalWinnings() - player.getPairPlusBet());
        if (ThreeCardLogic.evalHand(player.getHand()) != 0) {
            player.setTotalWinnings(player.getTotalWinnings()
                    + ThreeCardLogic.evalPPWinnings(player.getHand(), player.getPairPlusBet()));
        } 
    }



    public void checkWinings() {
        // Player 1 win
        if (ThreeCardLogic.compareHands(theDealer.getHand(), playerOne.getHand(), "Player 1") == 2) {
            playerOne.setTotalWinnings(playerOne.getTotalWinnings() + 2*(playerOne.getAnteBet() + playerOne.getPlayBet()));
            playerOneWin = true;
        } else if (ThreeCardLogic.compareHands(theDealer.getHand(), playerOne.getHand(), "Dealer 1") == 1) {
            // Player 1 lose
            playerOne.setTotalWinnings(playerOne.getTotalWinnings() - playerOne.getAnteBet() - playerOne.getPlayBet());
        }

        // Player 2 win
        if (ThreeCardLogic.compareHands(theDealer.getHand(), playerTwo.getHand(), "Player 2") == 2) {
            playerTwoWin = true;
            playerTwo.setTotalWinnings(playerTwo.getTotalWinnings() + 2*(playerTwo.getAnteBet() + playerTwo.getPlayBet()));
        } else if (ThreeCardLogic.compareHands(theDealer.getHand(), playerTwo.getHand(), "Dealer 2") == 1) {
            // Player 2 lose
            playerTwo.setTotalWinnings(playerTwo.getTotalWinnings() - playerTwo.getAnteBet() - playerTwo.getPlayBet());
        }
    }

    public int winner() {
        if(playerOneWin == true && playerTwoWin == false)
            return 1;
        else if(playerOneWin == false && playerTwoWin == true)
            return 2;
        else if(playerOneWin == true && playerTwoWin == true)
            return 3;
        else return 4;
    }


    // Method to check if dealer has at least Queen high
    private boolean dealerHasQueenHigh() {
        // Assuming ThreeCardLogic.evalHand returns specific values,
        // you could add a custom check here for Queen-high or higher
        for (int i = 0; i < 3; i++) {
            if (theDealer.getHand().get(i).getValue() > 12) {
                return true;
            }
        }
        return false;
    }

    

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

    public void setCountRound(int countRound) {
        this.countRound = countRound;
    }
    
    public int getCountRound() {
        return this.countRound;
    }

    public boolean getFlag() {
        return this.flag;
    }
}
