import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class Controller {

    @FXML public AnchorPane GamePlayScreen;
    @FXML public Button playButton;

    @FXML public Rectangle pauseScreen;
    @FXML public Text pauseText;
    @FXML public Text pauseTextBelow;
    @FXML public AnchorPane BetPlayer2;

    ThreeCardPokerGame game;

    // Player 1's FXML elements
    @FXML public AnchorPane BetPlayer1;
    @FXML public Text betTitle;
    @FXML public Button player1PlayButton;
    @FXML public Button player1FoldButton;
    @FXML private Button confirmButton;

    // Player 2's FXML elements

    @FXML private Button confirmButton2;


    // Navigation buttons
    @FXML private Button homeButton;
    @FXML private Button pauseButton;
    @FXML public Button player2PlayButton;
    @FXML public Button player2FoldButton;

    @FXML private Player player1;
    @FXML private Player player2;
    @FXML private Dealer dealer;

    private boolean isPaused = false;

    // Dealer cards
    @FXML private ImageView dealerCard1;
    @FXML private ImageView dealerCard2;
    @FXML private ImageView dealerCard3;

    // Player 1 fields
    @FXML private TextField player1AnteBetField;
    @FXML private TextField player1PlayBetField;
    @FXML private TextField player1PairPlusBetField;
    @FXML private TextField totalWinningsField1;
    @FXML private ImageView player1Card1;
    @FXML private ImageView player1Card2;
    @FXML private ImageView player1Card3;

    // Player 2 fields
    @FXML private TextField player2AnteBetField;
    @FXML private TextField player2PlayBetField;
    @FXML private TextField player2PairPlusBetField;
    @FXML private TextField totalWinningsField2;
    @FXML private ImageView player2Card1;
    @FXML private ImageView player2Card2;
    @FXML private ImageView player2Card3;

    // Player's bet confirmation

    @FXML private AnchorPane player2BettingSection;
    @FXML private AnchorPane cardsSection;
    @FXML private AnchorPane decisionSection;


    @FXML
    public HBox playButtonContainer; // Reference to the HBox containing the play button

    @FXML
    public Button playBtn; // Reference to the play button



    // Handle Start Button
    @FXML
    private void handleStartButton(ActionEvent event) {
        try {
            // Load GamePlayScreen.fxml when Start button is clicked
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/GamePlayScreen.fxml"));
            AnchorPane gameplayScreen = loader.load();

            // Set up and display the gameplay scene
            Scene gameplayScene = new Scene(gameplayScreen);
            Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            primaryStage.setScene(gameplayScene);
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Could not load GamePlayScreen.fxml. Please check the file path.");
        }
    }

    // Initialize method called when GamePlayScreen.fxml is loaded
    @FXML
    private void initialize() {
        if (dealerCard1 != null) { // Check if we are on the Gameplay screen
            resetBetFields();
            resetCardImages();
        }
        // Initialize player and dealer objects
        player1 = new Player();
        player2 = new Player();
        dealer = new Dealer();
        // Initialize the game object
        game = new ThreeCardPokerGame();  // Ensure the game object is initialized

    }

    // Method to handle the Home button click (navigates back to Welcome screen)
    @FXML
    private void handleHomeButton(ActionEvent event) {
        loadScene(event, "/FXML/WelcomeScreen.fxml");
    }

    // Method to handle the Pause button click
    @FXML
    private void handlePauseButton(ActionEvent event) {
        if (!isPaused) {
            // Pause the game: show the pause screen and messages
            isPaused = true;

            // Show pause overlay and texts
            pauseScreen.setVisible(true);
            pauseText.setVisible(true);
            pauseTextBelow.setVisible(true);
        } else {
            // Resume the game: hide the pause screen and texts
            isPaused = false;

            // Hide pause overlay and texts
            pauseScreen.setVisible(false);
            pauseText.setVisible(false);
            pauseTextBelow.setVisible(false);
        }
    }




    // Handle Play Button - Show the Betting Screen for Player 1
    @FXML
    private void handlePlayButton(ActionEvent event) {
        showBettingScreenForPlayer1(); // Show Player 1's betting screen
        // Hide the play button container when clicked
        playButtonContainer.setVisible(false);

    }

    // Method to show the betting screen for Player 1
    private void showBettingScreenForPlayer1() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/BetPlayer1.fxml"));
            AnchorPane player1BetScreen = loader.load();

            // Set up a new stage for the betting screen
            Stage betStage = new Stage();
            betStage.setTitle("Player 1: Place Your Bet");

            // Create a scene for the betting screen and show it
            Scene betScene = new Scene(player1BetScreen);
            betStage.setScene(betScene);
            betStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showError("Could not load BetPlayer1.fxml. Please check the file path.");
        }
    }


    // Method to handle Player 1's Ante Bet and Pair Plus Bet confirmation
    @FXML
    private void handleConfirmPlayer1Bet(ActionEvent event) {
        String anteBetText = player1AnteBetField.getText().trim();
        String pairPlusBetText = player1PairPlusBetField.getText().trim();

        // Validate Ante Bet for Player 1
        if (anteBetText.isEmpty()) {
            showError("Please enter a valid ante bet for Player 1.");
            return;
        }

        int anteBet = 0;
        try {
            anteBet = Integer.parseInt(anteBetText); // Convert to integer
            if (anteBet < 5 || anteBet > 25) {
                showError("Ante Bet for Player 1 must be between $5 and $25.");
                return;
            }
        } catch (NumberFormatException e) {
            showError("Invalid ante bet amount for Player 1. Please enter a valid number.");
            return;
        }

        // Set Player 1's Ante Bet
        player1.setAnteBet(anteBet);

        // Validate Pair Plus Bet for Player 1 (optional)
        int pairPlusBet = 0;
        if (!pairPlusBetText.isEmpty()) {
            try {
                pairPlusBet = Integer.parseInt(pairPlusBetText); // Convert to integer
                if (pairPlusBet < 5 || pairPlusBet > 25) {
                    showError("Pair Plus Bet for Player 1 must be between $5 and $25.");
                    return;
                }
            } catch (NumberFormatException e) {
                showError("Invalid Pair Plus bet amount for Player 1. Please enter a valid number.");
                return;
            }
        }

        // Set Player 1's Pair Plus Bet
        player1.setPairPlusBet(pairPlusBet);

        // Close Player 1's Ante Bet pop-up
        Stage anteBetStage = (Stage) player1AnteBetField.getScene().getWindow();
        anteBetStage.close();

        // Proceed to Player 2's bet screen
        showAnteBetPopUpForPlayer2();
    }

    // Method to handle Player 2's Ante Bet and Pair Plus Bet confirmation
    @FXML
    private void handleConfirmPlayer2Bet(ActionEvent event) {
        String anteBetText = player2AnteBetField.getText();
        String pairPlusBetText = player2PairPlusBetField.getText();

        // Validate Ante Bet for Player 2
        if (anteBetText.isEmpty()) {
            showError("Please enter a valid ante bet for Player 2.");
            return;
        }

        int anteBet = 0;
        try {
            anteBet = Integer.parseInt(anteBetText); // Convert to integer
            if (anteBet < 5 || anteBet > 25) {
                showError("Ante Bet for Player 2 must be between $5 and $25.");
                return;
            }
        } catch (NumberFormatException e) {
            showError("Invalid ante bet amount for Player 2. Please enter a valid number.");
            return;
        }

        // Set Player 2's Ante Bet
        player2.setAnteBet(anteBet);

        // Validate Pair Plus Bet for Player 2 (optional)
        int pairPlusBet = 0;
        if (!pairPlusBetText.isEmpty()) {
            try {
                pairPlusBet = Integer.parseInt(pairPlusBetText); // Convert to integer
                if (pairPlusBet < 5 || pairPlusBet > 25) {
                    showError("Pair Plus Bet for Player 2 must be between $5 and $25.");
                    return;
                }
            } catch (NumberFormatException e) {
                showError("Invalid Pair Plus bet amount for Player 2. Please enter a valid number.");
                return;
            }
        }

        // Set Player 2's Pair Plus Bet
        player2.setPairPlusBet(pairPlusBet);

        // Close Player 2's Ante Bet pop-up
        Stage anteBetStage = (Stage) player2AnteBetField.getScene().getWindow();
        anteBetStage.close();

        // Show the cards for both players and dealer
        showPlayerCards();
        showDealerCards();

        // After showing cards, switch to the gameplay screen
        goToGameplayScreen();
    }

    // Method to show Player 2's Ante Bet pop-up after Player 1's bet is confirmed
    private void showAnteBetPopUpForPlayer2() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/BetPlayer2.fxml"));
            AnchorPane anteBetPopUp = loader.load();

            // Set the stage for the pop-up
            Stage anteBetStage = new Stage();
            anteBetStage.setTitle("Player 2: Place Ante Bet");

            // Set the scene and show the pop-up
            Scene anteBetScene = new Scene(anteBetPopUp);
            anteBetStage.setScene(anteBetScene);
            anteBetStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showError("Could not load AnteBetPlayer2.fxml. Please check the file path.");
        }

    }
    // Show Player's Cards
    private void showPlayerCards() {
        // Deal hand for Player 1 and Player 2
        ArrayList<Card> player1Hand = dealer.dealHand();  // Get Player 1's hand
        ArrayList<Card> player2Hand = dealer.dealHand();  // Get Player 2's hand

        // Set the hands for each player
        player1.setHand(player1Hand);
        player2.setHand(player2Hand);

        // Display cards for Player 1 and Player 2
        displayCards(player1Hand, player1Card1, player1Card2, player1Card3);  // Display Player 1's cards
        displayCards(player2Hand, player2Card1, player2Card2, player2Card3);  // Display Player 2's cards
    }


    // Show dealer's cards
    private void showDealerCards() {
        ArrayList<Card> dealerHand = dealer.dealHand();
        dealer.setHand(dealerHand);  // Set the dealer's hand

        // Display the dealer's cards face up
        displayCards(dealerHand, dealerCard1, dealerCard2, dealerCard3);
    }

    // Display cards for a given hand
    private void displayCards(ArrayList<Card> hand, ImageView card1, ImageView card2, ImageView card3) {
        if (hand != null) {
            card1.setImage(new Image("/image/deck/" + hand.get(0).getValue() + hand.get(0).getSuit() + ".png"));
            card2.setImage(new Image("/image/deck/" + hand.get(1).getValue() + hand.get(1).getSuit() + ".png"));
            card3.setImage(new Image("/image/deck/" + hand.get(2).getValue() + hand.get(2).getSuit() + ".png"));
        } else {
            System.err.println("Invalid hand for player or dealer. Ensure hand has exactly 3 cards.");
        }
    }

    // Method to switch back to Gameplay Screen after cards are displayed
    private void goToGameplayScreen() {
        // Hide the current betting screen
        BetPlayer2.setVisible(false);

        // Show the gameplay screen with Play and Fold buttons
        GamePlayScreen.setVisible(true);

        // Update the TextFields for Player 1 and Player 2 to reflect the betting amounts
        player1AnteBetField.setText("$" + player1.getAnteBet());
        player1PlayBetField.setText("$" + player1.getPlayBet());
        player1PairPlusBetField.setText("$" + player1.getPairPlusBet());
        totalWinningsField1.setText("$" + player1.getTotalWinnings());

        player2AnteBetField.setText("$" + player2.getAnteBet());
        player2PlayBetField.setText("$" + player2.getPlayBet());
        player2PairPlusBetField.setText("$" + player2.getPairPlusBet());
        totalWinningsField2.setText("$" + player2.getTotalWinnings());
    }


    // Show error message
    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.showAndWait();
    }

    // Handle Player 1's Play Button
    @FXML
    private void handlePlayer1Play(ActionEvent event) {
        System.out.println("Player 1 chose to Play!");
        // Disable buttons after selection
        player1PlayButton.setDisable(true);
        player1FoldButton.setDisable(true);
    }

    // Handle Player 1's Fold Button
    @FXML
    private void handlePlayer1Fold(ActionEvent event) {
        System.out.println("Player 1 chose to Fold!");
        // Player forfeits, reset the hand and bets
        player1.setAnteBet(0);
        player1.setPairPlusBet(0);
        totalWinningsField1.setText("$0");

        // Disable buttons
        player1PlayButton.setDisable(true);
        player1FoldButton.setDisable(true);

        // Optionally, show message about folding
        showError("You folded. You lose your bets.");
    }

    // Handle Player 2's Play Button
    @FXML
    private void handlePlayer2Play(ActionEvent event) {
        System.out.println("Player 2 chose to Play!");
        // Disable buttons after selection
        player2PlayButton.setDisable(true);
        player2FoldButton.setDisable(true);
    }

    // Handle Player 2's Fold Button
    @FXML
    private void handlePlayer2Fold(ActionEvent event) {
        System.out.println("Player 2 chose to Fold!");
        // Player forfeits, reset the hand and bets
        player2.setAnteBet(0);
        player2.setPairPlusBet(0);
        totalWinningsField2.setText("$0");

        // Disable buttons
        player2PlayButton.setDisable(true);
        player2FoldButton.setDisable(true);

        // Optionally, show message about folding
        showError("You folded. You lose your bets.");
    }

    // Method to evaluate hands
    private void evaluateHands() {
        // Evaluate the hands after both players decide to play
        game.checkWinings();
        showWinner(game.winner());
    }

    // Show Winner in the UI
    private void showWinner(int winner) {
        switch(winner) {
            case 1:
                showError("Player 1 wins!");
                break;
            case 2:
                showError("Player 2 wins!");
                break;
            case 3:
                showError("Both players win!");
                break;
            case 4:
                showError("No winner, both players folded!");
                break;
        }
    }

    // Load a new scene
    private void loadScene(ActionEvent event, String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            loader.setController(this);  // Reuse the same controller
            AnchorPane pane = loader.load();

            Scene newScene = new Scene(pane);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(newScene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Could not load " + fxmlPath + ". Please check the file path.");
        }
    }



    // Method to reset bet fields to default values
    private void resetBetFields() {
        player1AnteBetField.setText("$0");
        player1PlayBetField.setText("$0");
        player1PairPlusBetField.setText("$0");
        totalWinningsField1.setText("$0");

        player2AnteBetField.setText("$0");
        player2PlayBetField.setText("$0");
        player2PairPlusBetField.setText("$0");
        totalWinningsField2.setText("$0");
    }

    // Method to reset card images to a back-of-card image
    private void resetCardImages() {
        Image backCardImage = new Image("/image/backcard3.png");

        dealerCard1.setImage(backCardImage);
        dealerCard2.setImage(backCardImage);
        dealerCard3.setImage(backCardImage);

        player1Card1.setImage(backCardImage);
        player1Card2.setImage(backCardImage);
        player1Card3.setImage(backCardImage);

        player2Card1.setImage(backCardImage);
        player2Card2.setImage(backCardImage);
        player2Card3.setImage(backCardImage);
    }
}
