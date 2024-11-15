import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class Controller {

    // Welcome Screen
    @FXML private Button playButton;
    @FXML private Button exitButton;

    // Game Play Screen
    @FXML private AnchorPane gamePlayScreen;
    @FXML private Text gameInfoText;

    // Player 1 UI Elements
    @FXML private TextField player1AnteBetField;
    @FXML private TextField player1PlayBetField;
    @FXML private TextField player1PairPlusBetField;
    @FXML private TextField player1TotalWinningsField;
    @FXML private ImageView player1Card1;
    @FXML private ImageView player1Card2;
    @FXML private ImageView player1Card3;
    @FXML private Button player1PlayButton;
    @FXML private Button player1FoldButton;

    // Player 2 UI Elements
    @FXML private TextField player2AnteBetField;
    @FXML private TextField player2PlayBetField;
    @FXML private TextField player2PairPlusBetField;
    @FXML private TextField player2TotalWinningsField;
    @FXML private ImageView player2Card1;
    @FXML private ImageView player2Card2;
    @FXML private ImageView player2Card3;
    @FXML private Button player2PlayButton;
    @FXML private Button player2FoldButton;

    // Dealer UI Elements
    @FXML private ImageView dealerCard1;
    @FXML private ImageView dealerCard2;
    @FXML private ImageView dealerCard3;

    // Pause Screen
    @FXML private AnchorPane pauseScreen;
    @FXML private Text pauseText;
    @FXML private Text pauseTextBelow;

    // Menu Bar
    @FXML private Button exitButton2;
    @FXML private Button freshStartButton;
    @FXML private Button newLookButton;

    private ThreeCardPokerGame game;
    private Player player1;
    private Player player2;
    private Dealer dealer;

    private boolean isPaused = false;

    @FXML
    private void initialize() {
        // Initialize the game and players
        game = new ThreeCardPokerGame();
        player1 = game.getPlayerOne();
        player2 = game.getPlayerTwo();
        dealer = game.getDealer();

        // Shuffle the deck
        game.getDeck().shuffle();

        // Deal hands to the players and dealer
        player1.setHand(dealer.dealHand());
        player2.setHand(dealer.dealHand());
        dealer.setHand(dealer.dealHand());
    }

    // Handle Start Button click
    @FXML
    private void handleStartButton(ActionEvent event) {
        // Load the game play screen
        loadGamePlayScreen();
    }

    // Handle Exit Button click
    @FXML
    private void handleExitButton(ActionEvent event) {
        // Exit the application
        System.exit(0);
    }

    // Handle Pause Button click
    @FXML
    private void handlePauseButton(ActionEvent event) {
        if (!isPaused) {
            // Pause the game: show the pause screen and messages
            isPaused = true;
            pauseScreen.setVisible(true);
            pauseText.setVisible(true);
            pauseTextBelow.setVisible(true);
        } else {
            // Resume the game: hide the pause screen and texts
            isPaused = false;
            pauseScreen.setVisible(false);
            pauseText.setVisible(false);
            pauseTextBelow.setVisible(false);
        }
    }

    // Handle Fresh Start Button click
    @FXML
    private void handleFreshStartButton(ActionEvent event) {
        // Reset player winnings to 0
        player1.setTotalWinnings(0);
        player2.setTotalWinnings(0);

        // Reset bet fields
        resetBetFields();

        // Shuffle the deck and deal new hands
        game.getDeck().shuffle();
        player1.setHand(dealer.dealHand());
        player2.setHand(dealer.dealHand());
        dealer.setHand(dealer.dealHand());

        // Update the UI with the new hands
        displayCards(player1.getHand(), player1Card1, player1Card2, player1Card3);
        displayCards(player2.getHand(), player2Card1, player2Card2, player2Card3);
        displayCards(dealer.getHand(), dealerCard1, dealerCard2, dealerCard3);
    }

    // Handle New Look Button click
    @FXML
    private void handleNewLookButton(ActionEvent event) {
        // Load a new CSS file to change the look of the UI
        Scene scene = gamePlayScreen.getScene();
        scene.getStylesheets().clear();
        scene.getStylesheets().add("/css/newlook.css");
    }

    // Handle Player 1 Play Button click
    @FXML
    private void handlePlayer1Play(ActionEvent event) {
        // Disable buttons after selection
        player1PlayButton.setDisable(true);
        player1FoldButton.setDisable(true);

        // Evaluate the hands and show the winner
        evaluateHands();
    }

    // Handle Player 1 Fold Button click
    @FXML
    private void handlePlayer1Fold(ActionEvent event) {
        // Player forfeits, reset the hand and bets
        player1.setAnteBet(0);
        player1.setPairPlusBet(0);
        player1.setTotalWinnings(0);

        // Disable buttons
        player1PlayButton.setDisable(true);
        player1FoldButton.setDisable(true);

        // Show message about folding
        showError("You folded. You lose your bets.");
    }

    // Handle Player 2 Play Button click
    @FXML
    private void handlePlayer2Play(ActionEvent event) {
        // Disable buttons after selection
        player2PlayButton.setDisable(true);
        player2FoldButton.setDisable(true);

        // Evaluate the hands and show the winner
        evaluateHands();
    }

    // Handle Player 2 Fold Button click
    @FXML
    private void handlePlayer2Fold(ActionEvent event) {
        // Player forfeits, reset the hand and bets
        player2.setAnteBet(0);
        player2.setPairPlusBet(0);
        player2.setTotalWinnings(0);

        // Disable buttons
        player2PlayButton.setDisable(true);
        player2FoldButton.setDisable(true);

        // Show message about folding
        showError("You folded. You lose your bets.");
    }

    // Handle Exit Button click from Pause Screen
    @FXML
    private void handleExitButton2(ActionEvent event) {
        // Show confirmation dialog
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to exit the game?");
        alert.setTitle("Exit Game");
        alert.setHeaderText(null);
        alert.showAndWait().ifPresent(response -> {
            if (response.getButtonData().isDefaultButton()) {
                // Exit the application
                System.exit(0);
            }
        });
    }

    // Evaluate the hands and show the winner
    private void evaluateHands() {
        // Evaluate the hands after both players decide to play
        game.checkWinnings();
        showWinner(game.winner());
    }

    // Show the winner in the UI
    private void showWinner(int winner) {
        switch (winner) {
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

    // Load the game play screen
    private void loadGamePlayScreen() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/GamePlayScreen.fxml"));
            loader.setController(this);
            AnchorPane gamePlayScreen = loader.load();

            Scene gameScene = new Scene(gamePlayScreen);
            Stage stage = (Stage) playButton.getScene().getWindow();
            stage.setScene(gameScene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showError("Could not load game play screen.");
        }
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

    // Reset the bet fields to default values
    private void resetBetFields() {
        player1AnteBetField.setText("$0");
        player1PlayBetField.setText("$0");
        player1PairPlusBetField.setText("$0");
        player1TotalWinningsField.setText("$0");

        player2AnteBetField.setText("$0");
        player2PlayBetField.setText("$0");
        player2PairPlusBetField.setText("$0");
        player2TotalWinningsField.setText("$0");
    }

    // Show an error message
    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.showAndWait();
    }
}