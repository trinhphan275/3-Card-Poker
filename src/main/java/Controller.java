import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class Controller {

    public AnchorPane GamePlayScreen;
    // Button controls
    @FXML private Button homeButton;
    @FXML private Button pauseButton;
    @FXML private Button playButton;

    // Dealer cards
    @FXML private ImageView dealerCard1;
    @FXML private ImageView dealerCard2;
    @FXML private ImageView dealerCard3;

    // Player 1 fields and cards
    @FXML private TextField player1AnteBetField;
    @FXML private TextField player1PlayBetField;
    @FXML private TextField player1PairPlusBetField;
    @FXML private TextField totalWinningsField1;
    @FXML private ImageView player1Card1;
    @FXML private ImageView player1Card2;
    @FXML private ImageView player1Card3;

    // Player 2 fields and cards
    @FXML private TextField player2AnteBetField;
    @FXML private TextField player2PlayBetField;
    @FXML private TextField player2PairPlusBetField;
    @FXML private TextField totalWinningsField2;
    @FXML private ImageView player2Card1;
    @FXML private ImageView player2Card2;
    @FXML private ImageView player2Card3;

    private Player player1;
    private Player player2;
    private Dealer dealer;
    private boolean isPaused = false;

    // Initialize the game with default values
    @FXML
    private void initialize() {
        player1 = new Player();
        player2 = new Player();
        dealer = new Dealer();

        resetBetFields();
        resetCardImages();
    }

    // Set all bet fields to zero and total winnings to zero
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

    // Set all card images to default card back
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

    // Handle play button - deal cards to players and display them
    @FXML
    private void handlePlayButton(ActionEvent event) {
        player1.clearHand();
        player2.clearHand();
        dealer.clearDealer();

        // Deal hands for players and dealer
        player1.setHand(dealer.dealHand());
        player2.setHand(dealer.dealHand());
        ArrayList<Card> dealerHand = dealer.dealHand();

        displayPlayerHand(player1.getHand(), player1Card1, player1Card2, player1Card3);
        displayPlayerHand(player2.getHand(), player2Card1, player2Card2, player2Card3);
        displayDealerHand(dealerHand);
    }

    // Display a player's hand in the specified card ImageView slots
    private void displayPlayerHand(ArrayList<Card> hand, ImageView card1, ImageView card2, ImageView card3) {
        card1.setImage(new Image("/image/deck/" + hand.get(0).getValue() + hand.get(0).getSuit() + ".png"));
        card2.setImage(new Image("/image/deck/" + hand.get(1).getValue() + hand.get(1).getSuit() + ".png"));
        card3.setImage(new Image("/image/deck/" + hand.get(2).getValue() + hand.get(2).getSuit() + ".png"));
    }

    // Display dealer's hand
    private void displayDealerHand(ArrayList<Card> dealerHand) {
        dealerCard1.setImage(new Image("/image/deck/" + dealerHand.get(0).getValue() + dealerHand.get(0).getSuit() + ".png"));
        dealerCard2.setImage(new Image("/image/deck/" + dealerHand.get(1).getValue() + dealerHand.get(1).getSuit() + ".png"));
        dealerCard3.setImage(new Image("/image/deck/" + dealerHand.get(2).getValue() + dealerHand.get(2).getSuit() + ".png"));
    }

    // Handle Start button - load the GamePlayScreen
    @FXML
    private void handleStartButton(ActionEvent event) {
        try {
            // Load the GamePlayScreen FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/GamePlayScreen.fxml"));
            AnchorPane gameplayScreen = loader.load();

            // Create a new scene with the GamePlayScreen layout
            Scene gameplayScene = new Scene(gameplayScreen);

            // Set the new scene on the current stage
            Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            primaryStage.setScene(gameplayScene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Could not load GamePlayScreen.fxml. Please check the file path.");
        }
    }

    // Handle Exit button - close the application
    @FXML
    private void handleExitButton(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }


    // Handle pause button - toggle pause state
    @FXML
    private void handlePauseButton(ActionEvent event) {
        isPaused = !isPaused;
        if (isPaused) {
            System.out.println("Game paused");
            // Display pause overlay or other UI effects here
        } else {
            System.out.println("Game resumed");
            // Hide pause overlay or reset UI effects here
        }
    }

    // Handle home button - redirect to home screen (implementation dependent)
    @FXML
    private void handleHomeButton(ActionEvent event) {
        System.out.println("Home button clicked - implement navigation logic here");
    }

    // Additional methods for handling bets, updates, etc., can be added as needed
}
