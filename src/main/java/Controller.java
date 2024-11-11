
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class Controller {

    @FXML
    private Text gameInfoText;
    @FXML
    private TextField betAmount;
    @FXML
    private Button betButton, startButton, resetButton;
    @FXML
    private ImageView dealerCard1, dealerCard2, dealerCard3;
    @FXML
    private ImageView playerOneCard1, playerOneCard2, playerOneCard3;
    @FXML
    private ImageView playerTwoCard1, playerTwoCard2, playerTwoCard3;
    @FXML
    private ArrayList<ImageView> dealerCards;
    @FXML
    private ArrayList<ImageView> playerOneCards;
    @FXML
    private ArrayList<ImageView> playerTwoCards;

//    public void initialize() {
//        // Initialize card lists
//        dealerCards = new ArrayList<>();
//        dealerCards.add(dealerCard1);
//        dealerCards.add(dealerCard2);
//        dealerCards.add(dealerCard3);
//
//        playerOneCards = new ArrayList<>();
//        playerOneCards.add(playerOneCard1);
//        playerOneCards.add(playerOneCard2);
//        playerOneCards.add(playerOneCard3);
//
//        playerTwoCards = new ArrayList<>();
//        playerTwoCards.add(playerTwoCard1);
//        playerTwoCards.add(playerTwoCard2);
//        playerTwoCards.add(playerTwoCard3);
//
//        startButton.setOnAction(event -> startGame());
//        betButton.setOnAction(event -> placeBet());
//        resetButton.setOnAction(event -> resetGame());
//    }


//    public void onStartButtonPressed(ActionEvent event) {
//        try {
//            // Load the GamePlay scene
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/GamePlayScreen.fxml"));
//            BorderPane gamePlayRoot = loader.load();
//
//            // Set the scene to the new GamePlay scene
//            Scene gamePlayScene = new Scene(gamePlayRoot, 1440, 800);
//            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
//            stage.setScene(gamePlayScene);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }


//
//    private void startGame() {
//        // Set the back side of the dealer's cards as default
//        for (ImageView card : dealerCards) {
//            card.setImage(new Image("/image/backcard3.png"));
//        }
//        gameInfoText.setText("Game started! Place your bets.");
//    }
//
//    private void placeBet() {
//        // Process the bet input
//        String betText = betAmount.getText();
//        try {
//            int bet = Integer.parseInt(betText);
//            gameInfoText.setText("Bet of $" + bet + " placed.");
//        } catch (NumberFormatException e) {
//            gameInfoText.setText("Invalid bet amount. Please enter a number.");
//        }
//    }
//
//    private void resetGame() {
//        // Reset game status
//        gameInfoText.setText("Game reset. Place your bets to start again.");
//        betAmount.clear();
//        for (ImageView card : dealerCards) {
//            card.setImage(new Image("/image/backcard3.png"));
//        }
//    }
}