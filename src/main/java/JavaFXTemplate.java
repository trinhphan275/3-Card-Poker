import java.util.ArrayList;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.binding.NumberBinding;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class JavaFXTemplate extends Application {
	public static int bet = 0;
	ArrayList<ImageView> playerOneCards = new ArrayList<ImageView>();
	ArrayList<ImageView> playerTwoCards = new ArrayList<ImageView>();
	ArrayList<ImageView> playerDealerCards = new ArrayList<ImageView>();

	Player playerOne;
	Dealer dealer;
	Player playerTwo;
	Text playerTitle = new Text();
	Player player = new Player();
	Text betText = new Text();
	Text playText = new Text();
	Text anteText = new Text();
	ThreeCardPokerGame game;
	Text totalText = new Text();
	Text pairplusText = new Text();
	Boolean playerTest = true;
	Boolean playerTurn = true; // true is 1, false is 0
	int roundCounter = 1;
	Boolean screenOn = true;
	Integer num;
	// Integer playerTest1 = 0;
	SimpleIntegerProperty playerTest1 = new SimpleIntegerProperty(0);
	SimpleIntegerProperty what = new SimpleIntegerProperty(1);
	NumberBinding sumWhat;
	Text text = new Text();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	public Boolean checkDealerCards(Dealer dealer) {
		int a = 0;
		// check special cases...
		// if there is special then return true
		if (ThreeCardLogic.evalHand(dealer.getHand()) != 0) {
			return true;
		}

		// This is for Queen high
		for (int i = 0; i < 3; i++) {
			a = dealer.getHand().get(i).getValue();

			if (a >= 12) // if queens higher
				return true;
			//System.out.println(dealer.getHand().get(i).getValue());
		}
		//System.out.println("No Q");
		return false;
	}

	public ArrayList<ImageView> defaultDealerCards(Dealer dealer) {
		ArrayList<ImageView> dealerCards = new ArrayList<ImageView>();
		for (int i = 0; i < 3; i++) {
			ImageView card = new ImageView(new Image("./image/backcard3.png"));
			card.setFitWidth(140);
			card.setFitHeight(180);
			card.setTranslateX(500 + i * 150);
			card.setTranslateY(80);
			dealerCards.add(card);
		}
		return dealerCards;
	}

	public ArrayList<ImageView> defaultPlayerCards(Player player, boolean isPlayer) {
		ArrayList<ImageView> playerCards = new ArrayList<ImageView>();
		for (int i = 0; i < 3; i++) {
			ImageView card = new ImageView(new Image("./image/backcard3.png"));
			// Display player1's card
			if (isPlayer) {
				card.setFitHeight(180);
				card.setFitWidth(140);
				card.setX(70 + (i * 150));
				card.setY(610);
			}
			// Display player2's card
			else {
				card.setFitHeight(180);
				card.setFitWidth(140);
				card.setX(930 + (i * 150));
				card.setY(610);
				// check PP?
			}

			playerCards.add(card);
		}
		return playerCards;
	}

	public ArrayList<ImageView> displayPlayerCards(Player player, boolean isPlayer) {
		ArrayList<ImageView> playerCards = new ArrayList<ImageView>();
		for (int i = 0; i < 3; i++) {
			ImageView card = new ImageView(
					new Image("./image/deck/" + player.getHand().get(i).getValue() + player.getHand().get(i).getSuit()
							+ ".png"));
			// Display player1's card
			if (isPlayer) {
				card.setFitHeight(180);
				card.setFitWidth(140);
				card.setX(70 + (i * 150));
				card.setY(610);
			}
			// Display player2's card
			else {
				card.setFitHeight(180);
				card.setFitWidth(140);
				card.setX(930 + (i * 150));
				card.setY(610);
				// check PP?
			}

			playerCards.add(card);
		}
		return playerCards;
	}

	public ArrayList<ImageView> displayDealerCards(Dealer dealer) {
		ArrayList<ImageView> dealerCards = new ArrayList<ImageView>();
		for (int i = 0; i < 3; i++) {
			ImageView card = new ImageView(
					new Image("./image/deck/" + dealer.getHand().get(i).getValue() + dealer.getHand().get(i).getSuit()
							+ ".png"));

			card.setFitWidth(140);
			card.setFitHeight(180);
			card.setTranslateX(500 + i * 150);
			card.setTranslateY(80);
			dealerCards.add(card);
		}
		return dealerCards;
	}

	// feel free to remove the starter code from this method
	@Override
	public void start(Stage primaryStage) throws Exception {

		// TODO Auto-generated method stub
		primaryStage.setTitle("Welcome to Project #2");

		game = new ThreeCardPokerGame();
		dealer = game.getDealer();
		playerOne = game.getPlayerOne();
		playerTwo = game.getPlayerTwo();

		BorderPane root = new BorderPane();

		// Set background color is dark green
		root.setStyle("-fx-background-color: #4f772d;");

		// ********** TABLE **********
		// Circle table = new Circle(720, 0, 880);
		Rectangle table = new Rectangle(0, 0, 1440, 830);
		table.setFill(Color.web("#31572c"));
		table.setStroke(Color.web("#582f0e"));
		table.setStrokeWidth(6);
		root.getChildren().add(table);

		// ********** PLAYER INFORMATION **********
		// Display text "Player 1"
		Text playerOneText = new Text();
		playerOneText.setText("Player 1");
		playerOneText.setStyle("-fx-font-weight: bold");
		playerOneText.setFill(Color.web("#ffc600"));
		playerOneText.setStyle("-fx-font-size: 40px");
		playerOneText.setTranslateX(100);
		playerOneText.setTranslateY(880);
		root.getChildren().add(playerOneText);
		// root.setTop(playerOneText);

		// Display text "Player 2"
		Text playerTwoText = new Text();
		playerTwoText.setText("Player 2");
		playerTwoText.setStyle("-fx-font-weight: bold");
		playerTwoText.setFill(Color.web("#ffc600"));
		playerTwoText.setStyle("-fx-font-size: 40px");
		playerTwoText.setTranslateX(1200);
		playerTwoText.setTranslateY(880);
		root.getChildren().add(playerTwoText);
		// root.setTop(playerTwoText);

		// ********** DEALER INFORMATION **********
		// Display text "Dealer"
		Text dealerText = new Text();
		dealerText.setText("Dealer");
		dealerText.setStyle("-fx-font-weight: bold");
		dealerText.setFill(Color.web("#ffc600"));
		dealerText.setStyle("-fx-font-size: 40px");
		dealerText.setTranslateX(700);
		dealerText.setTranslateY(50);
		root.getChildren().add(dealerText);

		// Display player's totalWinnings and text "Total Winnings" in rectangle
		// Player 1
		Text totalText1 = new Text();
		totalText1.setText("Total Winnings");
		totalText1.setStyle("-fx-font-weight: bold");
		totalText1.setFill(Color.web("#ffc600"));
		totalText1.setStyle("-fx-font-size: 30px");
		totalText1.setTranslateX(75);
		totalText1.setTranslateY(580);
		root.getChildren().add(totalText1);
		Rectangle playerOneTotalWinnings = new Rectangle(0, 0, 200, 45);
		playerOneTotalWinnings.setFill(Color.web("#ffffff"));
		playerOneTotalWinnings.setTranslateX(300);
		playerOneTotalWinnings.setTranslateY(550);
		root.getChildren().add(playerOneTotalWinnings);
		Text playerOneTotalText = new Text();
		playerOneTotalText.setText("$" + playerOne.getTotalWinnings());
		playerOneTotalText.setStyle("-fx-font-weight: bold");
		playerOneTotalText.setFill(Color.web("#000000"));
		playerOneTotalText.setStyle("-fx-font-size: 30px");
		playerOneTotalText.setTranslateX(380);
		playerOneTotalText.setTranslateY(582);
		root.getChildren().add(playerOneTotalText);

		// Player 2
		Text totalText2 = new Text();
		totalText2.setText("Total Winnings");
		totalText2.setStyle("-fx-font-weight: bold");
		totalText2.setFill(Color.web("#ffc600"));
		totalText2.setStyle("-fx-font-size: 30px");
		totalText2.setTranslateX(932);
		totalText2.setTranslateY(580);
		root.getChildren().add(totalText2);
		Rectangle playerTwoTotalWinnings = new Rectangle(0, 0, 200, 45);
		playerTwoTotalWinnings.setFill(Color.web("#ffffff"));
		playerTwoTotalWinnings.setTranslateX(1157);
		playerTwoTotalWinnings.setTranslateY(550);
		root.getChildren().add(playerTwoTotalWinnings);
		Text playerTwoTotalText = new Text();
		playerTwoTotalText.setText("$" + playerTwo.getTotalWinnings());
		playerTwoTotalText.setStyle("-fx-font-weight: bold");
		playerTwoTotalText.setFill(Color.web("#000000"));
		playerTwoTotalText.setStyle("-fx-font-size: 30px");
		playerTwoTotalText.setTranslateX(1240);
		playerTwoTotalText.setTranslateY(582);
		root.getChildren().add(playerTwoTotalText);

		// Display player's anteBet, playBet, and PairPlusBet with text in a row
		// Player 1
		// Ante Bet
		Text anteBetText = new Text();
		anteBetText.setText("Ante");
		anteBetText.setStyle("-fx-font-weight: bold");
		anteBetText.setFill(Color.web("#ffc600"));
		anteBetText.setStyle("-fx-font-size: 30px");
		anteBetText.setTranslateX(100);
		anteBetText.setTranslateY(440);
		root.getChildren().add(anteBetText);
		Circle playerOneAnteBet = new Circle(125, 490, 40);
		playerOneAnteBet.setFill(Color.web("#ffc600"));
		root.getChildren().add(playerOneAnteBet);
		Text playerOneAnteBetText = new Text();
		playerOneAnteBetText.setText("$" + playerOne.getAnteBet());
		playerOneAnteBetText.setStyle("-fx-font-weight: bold");
		playerOneAnteBetText.setFill(Color.web("#000000"));
		playerOneAnteBetText.setStyle("-fx-font-size: 30px");
		playerOneAnteBetText.setTranslateX(105);
		playerOneAnteBetText.setTranslateY(500);
		root.getChildren().add(playerOneAnteBetText);
		// Play Bet
		Text playBetText = new Text();
		playBetText.setText("Play Bet");
		playBetText.setStyle("-fx-font-weight: bold");
		playBetText.setFill(Color.web("#ffc600"));
		playBetText.setStyle("-fx-font-size: 30px");
		playBetText.setTranslateX(225);
		playBetText.setTranslateY(440);
		root.getChildren().add(playBetText);
		Circle playerOnePlayBet = new Circle(270, 490, 40);
		playerOnePlayBet.setFill(Color.web("#ffc600"));
		root.getChildren().add(playerOnePlayBet);
		Text playerOnePlayBetText = new Text();
		playerOnePlayBetText.setText("$" + playerOne.getPlayBet());
		playerOnePlayBetText.setStyle("-fx-font-weight: bold");
		playerOnePlayBetText.setFill(Color.web("#000000"));
		playerOnePlayBetText.setStyle("-fx-font-size: 30px");
		playerOnePlayBetText.setTranslateX(250);
		playerOnePlayBetText.setTranslateY(500);
		root.getChildren().add(playerOnePlayBetText);
		// Pair Plus Bet
		Text pairPlusBetText = new Text();
		pairPlusBetText.setText("Pair+");
		pairPlusBetText.setStyle("-fx-font-weight: bold");
		pairPlusBetText.setFill(Color.web("#ffc600"));
		pairPlusBetText.setStyle("-fx-font-size: 30px");
		pairPlusBetText.setTranslateX(400);
		pairPlusBetText.setTranslateY(440);
		root.getChildren().add(pairPlusBetText);
		Circle playerOnePairPlusBet = new Circle(425, 490, 40);
		playerOnePairPlusBet.setFill(Color.web("#ffc600"));
		root.getChildren().add(playerOnePairPlusBet);
		Text playerOnePairPlusBetText = new Text();
		playerOnePairPlusBetText.setText("$" + playerOne.getPairPlusBet());
		playerOnePairPlusBetText.setStyle("-fx-font-weight: bold");
		playerOnePairPlusBetText.setFill(Color.web("#000000"));
		playerOnePairPlusBetText.setStyle("-fx-font-size: 30px");
		playerOnePairPlusBetText.setTranslateX(405);
		playerOnePairPlusBetText.setTranslateY(500);
		root.getChildren().add(playerOnePairPlusBetText);

		// Player 2
		// Ante Bet
		Text anteBetText2 = new Text();
		anteBetText2.setText("Ante");
		anteBetText2.setStyle("-fx-font-weight: bold");
		anteBetText2.setFill(Color.web("#ffc600"));
		anteBetText2.setStyle("-fx-font-size: 30px");
		anteBetText2.setTranslateX(1000);
		anteBetText2.setTranslateY(440);
		root.getChildren().add(anteBetText2);
		Circle playerTwoAnteBet = new Circle(1025, 490, 40);
		playerTwoAnteBet.setFill(Color.web("#ffc600"));
		root.getChildren().add(playerTwoAnteBet);
		Text playerTwoAnteBetText = new Text();
		playerTwoAnteBetText.setText("$" + playerTwo.getAnteBet());
		playerTwoAnteBetText.setStyle("-fx-font-weight: bold");
		playerTwoAnteBetText.setFill(Color.web("#000000"));
		playerTwoAnteBetText.setStyle("-fx-font-size: 30px");
		playerTwoAnteBetText.setTranslateX(1005);
		playerTwoAnteBetText.setTranslateY(500);
		root.getChildren().add(playerTwoAnteBetText);
		// Play Bet
		Text playBetText2 = new Text();
		playBetText2.setText("Play Bet");
		playBetText2.setStyle("-fx-font-weight: bold");
		playBetText2.setFill(Color.web("#ffc600"));
		playBetText2.setStyle("-fx-font-size: 30px");
		playBetText2.setTranslateX(1125);
		playBetText2.setTranslateY(440);
		root.getChildren().add(playBetText2);
		Circle playerTwoPlayBet = new Circle(1175, 490, 40);
		playerTwoPlayBet.setFill(Color.web("#ffc600"));
		root.getChildren().add(playerTwoPlayBet);
		Text playerTwoPlayBetText = new Text();
		playerTwoPlayBetText.setText("$" + playerTwo.getPlayBet());
		playerTwoPlayBetText.setStyle("-fx-font-weight: bold");
		playerTwoPlayBetText.setFill(Color.web("#000000"));
		playerTwoPlayBetText.setStyle("-fx-font-size: 30px");
		playerTwoPlayBetText.setTranslateX(1155);
		playerTwoPlayBetText.setTranslateY(500);
		root.getChildren().add(playerTwoPlayBetText);
		// Pair Plus Bet
		Text pairPlusBetText2 = new Text();
		pairPlusBetText2.setText("Pair+");
		pairPlusBetText2.setStyle("-fx-font-weight: bold");
		pairPlusBetText2.setFill(Color.web("#ffc600"));
		pairPlusBetText2.setStyle("-fx-font-size: 30px");
		pairPlusBetText2.setTranslateX(1300);
		pairPlusBetText2.setTranslateY(440);
		root.getChildren().add(pairPlusBetText2);
		Circle playerTwoPairPlusBet = new Circle(1325, 490, 40);
		playerTwoPairPlusBet.setFill(Color.web("#ffc600"));
		root.getChildren().add(playerTwoPairPlusBet);
		Text playerTwoPairPlusBetText = new Text();
		playerTwoPairPlusBetText.setText("$" + playerTwo.getPairPlusBet());
		playerTwoPairPlusBetText.setStyle("-fx-font-weight: bold");
		playerTwoPairPlusBetText.setFill(Color.web("#000000"));
		playerTwoPairPlusBetText.setStyle("-fx-font-size: 30px");
		playerTwoPairPlusBetText.setTranslateX(1305);
		playerTwoPairPlusBetText.setTranslateY(500);
		root.getChildren().add(playerTwoPairPlusBetText);

		// ********** ROUND **********
		// Display round number
		Text roundText = new Text();
		roundText.setText("Round 1");
		roundText.setStyle("-fx-font-weight: bold");
		roundText.setFill(Color.BLACK);
		roundText.setStyle("-fx-font-size: 40px");
		roundText.setTranslateX(1250);
		roundText.setTranslateY(50);
		root.getChildren().add(roundText);

		// ********** PAUSE BUTTON **********
		// Create a button with pause icon
		Button pauseButton = new Button();
		pauseButton.setGraphic(new ImageView(new Image("./image/pause.png")));
		pauseButton.setMaxWidth(50);
		pauseButton.setMaxHeight(50);
		pauseButton.setTranslateX(78);
		pauseButton.setTranslateY(28);
		root.getChildren().add(pauseButton);
		// Pause button

		// Display pause screen when pause button is clicked
		pauseButton.setOnAction(e2 -> {
			// Create a pause screen
			Rectangle pauseScreen = new Rectangle(0, 0, 1440, 830);
			pauseScreen.setFill(Color.web("#000000", 0.5));
			pauseScreen.setStroke(Color.web("#582f0e"));
			pauseScreen.setStrokeWidth(6);
			root.getChildren().add(pauseScreen);

			// Create a pause text
			Text pauseText = new Text("PAUSE");
			pauseText.setStyle("-fx-font-size: 80px; -fx-text-fill: #ffffff;");
			pauseText.setTranslateX(600);
			pauseText.setTranslateY(350);
			root.getChildren().add(pauseText);
			// Create a text below pause text
			Text pauseTextBelow = new Text("Click the screen to resume");
			pauseTextBelow.setStyle("-fx-font-size: 40px; -fx-text-fill: #ffffff;");
			pauseTextBelow.setTranslateX(500);
			pauseTextBelow.setTranslateY(400);
			root.getChildren().add(pauseTextBelow);

			// double click screen to resume
			pauseScreen.setOnMouseClicked(e3 -> {
				root.getChildren().remove(pauseScreen);
				root.getChildren().remove(pauseText);
				root.getChildren().remove(pauseTextBelow);
			});
		});

		// ********** CARD IMAGES **********
		// set value for dealer and players cards
		dealer.setHand(dealer.dealHand());
		playerOne.setHand(dealer.dealHand());
		playerTwo.setHand(dealer.dealHand());
		// Take value of 3 card from dealer and display image
		for (int i = 0; i < 3; i++) {
			ImageView dealerCard = new ImageView(new Image("./image/backcard3.png"));
			dealerCard.setFitWidth(140);
			dealerCard.setFitHeight(180);
			dealerCard.setTranslateX(500 + i * 150);
			dealerCard.setTranslateY(80);
			root.getChildren().add(dealerCard);
		}

		// Take value of 3 card from player 1 and display image
		for (int i = 0; i < 3; i++) {
			ImageView playerOneCardx = new ImageView(new Image("./image/backcard3.png"));
			playerOneCardx.setFitWidth(140);
			playerOneCardx.setFitHeight(180);
			playerOneCardx.setTranslateX(70 + i * 150);
			playerOneCardx.setTranslateY(610);
			root.getChildren().add(playerOneCardx);
		}
		// Take value of 3 card from player 2 and display image
		for (int i = 0; i < 3; i++) {
			ImageView playerTwoCardx = new ImageView(new Image("./image/backcard3.png"));
			playerTwoCardx.setFitWidth(140);
			playerTwoCardx.setFitHeight(180);
			playerTwoCardx.setTranslateX(930 + i * 150);
			playerTwoCardx.setTranslateY(610);
			root.getChildren().add(playerTwoCardx);
		}

		// ********** START GAME ANIMATION **********
		// Display "Start Game" text when the game starts
		Rectangle blurScreen = new Rectangle(0, 0, 1440, 830);
		blurScreen.setFill(Color.web("#000000", 0.5));
		blurScreen.setStroke(Color.web("#582f0e"));
		blurScreen.setStrokeWidth(6);
		root.getChildren().add(blurScreen);

		// ********** PLAYER 1 **********
		// ********** BET FORM **********
		// ANTE
		VBox getBetVBox = new VBox();
		getBetVBox.setTranslateX(600);
		getBetVBox.setTranslateY(100);
		getBetVBox.setSpacing(20);
		getBetVBox.setStyle("-fx-background-color: #000000, opacity: 1.0;");
		// Create pane for bet
		Pane getBetPane = new Pane();
		getBetPane.setMinWidth(900);
		getBetPane.setMinHeight(300);
		getBetPane.setStyle("-fx-background-color: #000000");
		getBetPane.setTranslateX(-350);
		getBetPane.setTranslateY(-10);

		// Player title
		Text playerOneTitle = new Text();
		playerOneTitle.setText("Player 1\n");
		playerOneTitle.setStyle("-fx-font-weight: bold");
		playerOneTitle.setFill(Color.WHITE);
		playerOneTitle.setStyle("-fx-font-size: 35px");
		playerOneTitle.setTranslateX(400);
		playerOneTitle.setTranslateY(60);

		Text playerTwoTitle = new Text();
		playerTwoTitle.setText("Player 2\n");
		playerTwoTitle.setStyle("-fx-font-weight: bold");
		playerTwoTitle.setFill(Color.WHITE);
		playerTwoTitle.setStyle("-fx-font-size: 35px");
		playerTwoTitle.setTranslateX(400);
		playerTwoTitle.setTranslateY(60);

		// Asking player to enter bet
		Text anteBetAmount = new Text();
		anteBetAmount.setText("Enter your ante bet (5-25): \n"
				+ "(The ante bet is the amount of money you bet before the cards are dealt.)\n");
		anteBetAmount.setStyle("-fx-font-weight: bold");
		anteBetAmount.setFill(Color.WHITE);
		anteBetAmount.setStyle("-fx-font-size: 25px");
		anteBetAmount.setTranslateX(80);
		anteBetAmount.setTranslateY(130);
		anteBetAmount.setTextAlignment(TextAlignment.CENTER);

		Text playBetMessage = new Text();
		playBetMessage.setText("Do you want PLAY or FOLD?\n"
				+ "If you choose play, you must bet the play wage equal to the ante wage.\n"
				+ "If you choose fold, you will lose your ante wage.\n");
		playBetMessage.setStyle("-fx-font-weight: bold");
		playBetMessage.setFill(Color.WHITE);
		playBetMessage.setStyle("-fx-font-size: 25px");
		playBetMessage.setTranslateX(80);
		playBetMessage.setTranslateY(130);
		playBetMessage.setTextAlignment(TextAlignment.CENTER);

		Text pairPlusMessage = new Text();
		pairPlusMessage.setText("Do you want bet for Pair Plus?\n"
				+ "If you want, enter the amount you want from 5 to 25 and click enter.\n"
				+ "If you do not want, click no.\n");
		pairPlusMessage.setStyle("-fx-font-weight: bold");
		pairPlusMessage.setFill(Color.WHITE);
		pairPlusMessage.setStyle("-fx-font-size: 25px");
		pairPlusMessage.setTranslateX(120);
		pairPlusMessage.setTranslateY(120);
		pairPlusMessage.setTextAlignment(TextAlignment.CENTER);

		// Create a input text to get player one's ante bet
		TextField inputBox = new TextField();
		inputBox.setPromptText("Ante Bet");
		inputBox.setTranslateX(350);
		inputBox.setTranslateY(200);
		inputBox.setMinWidth(200);
		inputBox.setMinHeight(50);
		inputBox.setStyle("-fx-font-size: 20px; -fx-background-color: #ffffff");
		inputBox.setOnKeyReleased(e -> {
			if (inputBox.getText().length() > 0) {
				try {
					if (Integer.parseInt(inputBox.getText()) < 5 || Integer.parseInt(inputBox.getText()) > 25) {
						Alert alert = new Alert(AlertType.WARNING);
						alert.setTitle("Warning");
						alert.setHeaderText("Bet amount must be greater than 5 or less than 25");
						alert.setContentText("Please enter a valid bet amount");
						alert.showAndWait();
					}
				} catch (NumberFormatException ex) {
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("Warning");
					alert.setHeaderText("Ante Bet must be a number");
					alert.setContentText("Please enter a valid ante bet");
					alert.showAndWait();
				}
			}
		});

		VBox pairPlusBox = new VBox();
		pairPlusBox.setTranslateX(600);
		pairPlusBox.setTranslateY(100);
		pairPlusBox.setSpacing(20);
		pairPlusBox.setStyle("-fx-background-color: #000000, opacity: 1.0;");
		HBox optionsPairPlus = new HBox();

		// fold button
		Button foldButton = new Button("Fold");
		foldButton.setStyle("-fx-font: 22 arial;");
		foldButton.setMinWidth(300);
		foldButton.setMinHeight(50);
		foldButton.setTranslateX(-200);
		foldButton.setTranslateY(0);


		// *********************** GAME FLOW ***********************
		// while (playerTest1 < 1){
		Button enterButton = new Button("Enter");
		enterButton.setStyle("-fx-font: 22 arial;");
		enterButton.setMinWidth(300);
		enterButton.setMinHeight(50);

		Button PlayButton = new Button("Play");
		PlayButton.setStyle("-fx-font: 22 arial;");
		PlayButton.setMinWidth(300);
		PlayButton.setMinHeight(50);
		PlayButton.setTranslateX(600);
		PlayButton.setTranslateY(320);

		// add PlayButton to root and set it center top
		root.setTop(PlayButton);
		Button newGameButton = new Button("Fresh Start");
		newGameButton.setStyle("-fx-font: 22 arial;");
		newGameButton.setMinWidth(300);
		newGameButton.setMinHeight(50);

		// ********** HOME BUTTON **********
		// Create a button with home icon
		Button homeButton = new Button();
		homeButton.setGraphic(new ImageView(new Image("./image/home.png")));
		homeButton.setMaxWidth(50);
		homeButton.setMaxHeight(50);
		homeButton.setTranslateX(28);
		homeButton.setTranslateY(28);
		root.getChildren().add(homeButton);

		VBox homeVBox = new VBox();
		homeVBox.setTranslateX(600);
		homeVBox.setTranslateY(100);
		homeVBox.setSpacing(20);

		Rectangle homeScreen = new Rectangle(0, 0, 1440, 830);
		homeScreen.setFill(Color.web("#000000", 0.5));
		homeScreen.setStroke(Color.web("#582f0e"));
		homeScreen.setStrokeWidth(6);

		Button resumeButton = new Button("Resume");
		resumeButton.setStyle("-fx-font: 22 arial;");
		resumeButton.setMinWidth(300);
		resumeButton.setMinHeight(50);

		Button pairPlusInfoButton = new Button("Pair Plus Information");
		pairPlusInfoButton.setStyle("-fx-font: 22 arial;");
		pairPlusInfoButton.setMinWidth(300);
		pairPlusInfoButton.setMinHeight(50);

		Button playGuideButton = new Button("Play Guide");
		playGuideButton.setStyle("-fx-font: 22 arial;");
		playGuideButton.setMinWidth(300);
		playGuideButton.setMinHeight(50);

		Button changeThemeButton = new Button("New Look");
		changeThemeButton.setStyle("-fx-font: 22 arial;");
		changeThemeButton.setMinWidth(300);
		changeThemeButton.setMinHeight(50);

		Button exitButton = new Button("Exit");
		exitButton.setStyle("-fx-font: 22 arial;");
		exitButton.setMinWidth(300);
		exitButton.setMinHeight(50);
		homeVBox.getChildren().addAll(newGameButton, resumeButton, pairPlusInfoButton, playGuideButton,
				changeThemeButton, exitButton);
		root.getChildren().add(homeScreen);
		root.getChildren().add(homeVBox);
		homeScreen.setVisible(false);
		homeVBox.setVisible(false);
		screenOn = true;

		// init player one
		playerTitle = playerOneTitle;
		player = playerOne;
		anteText = playerOneAnteBetText;
		playText = playerOnePlayBetText;
		totalText = playerOneTotalText;
		pairplusText = playerOnePairPlusBetText;

		homeButton.setOnAction(e -> {
			homeScreen.toFront();
			homeVBox.toFront();
			homeScreen.setVisible(screenOn);
			homeVBox.setVisible(screenOn);
			
			// RESUME BUTTON
			resumeButton.setOnAction(e1 -> {
				homeScreen.setVisible(!screenOn);
				homeVBox.setVisible(!screenOn);
			});

			// PAIR PLUS INFO BUTTON
			pairPlusInfoButton.setOnAction(e1 -> {
				root.getChildren().remove(homeVBox);
				VBox pairPlusInfoVBox = new VBox();
				pairPlusInfoVBox.setTranslateX(600);
				pairPlusInfoVBox.setTranslateY(100);
				pairPlusInfoVBox.setSpacing(20);
				pairPlusInfoVBox.setStyle("-fx-background-color: #000000, opacity: 1.0;");

				Pane pairPlusInfoPane = new Pane();
				pairPlusInfoPane.setMinWidth(500);
				pairPlusInfoPane.setMinHeight(300);
				pairPlusInfoPane.setStyle("-fx-background-color: #000000");
				pairPlusInfoPane.setTranslateX(-100);
				pairPlusInfoPane.setTranslateY(-10);

				Text pairPlusInfoTitle = new Text();
				pairPlusInfoTitle.setText("Pair Plus Win\n");
				pairPlusInfoTitle.setStyle("-fx-font-weight: bold");
				pairPlusInfoTitle.setFill(Color.WHITE);
				pairPlusInfoTitle.setStyle("-fx-font-size: 35px");
				pairPlusInfoTitle.setTextAlignment(TextAlignment.CENTER);
				pairPlusInfoTitle.setTranslateX(170);
				pairPlusInfoTitle.setTranslateY(60);
				Text pairPlusInfoText = new Text();
				pairPlusInfoText.setText(
						"Straight Flush: 40 to 1\nThree of a Kind: 30 to 1\nStraight: 6 to 1\nFlush: 3 to 1\nPair: 1 to 1");
				pairPlusInfoText.setStyle("-fx-font-weight: bold");
				pairPlusInfoText.setFill(Color.WHITE);
				pairPlusInfoText.setStyle("-fx-font-size: 25px");
				pairPlusInfoText.setTranslateX(150);
				pairPlusInfoText.setTranslateY(130);
				pairPlusInfoText.setTextAlignment(TextAlignment.CENTER);

				pairPlusInfoPane.getChildren().addAll(pairPlusInfoTitle, pairPlusInfoText);
				pairPlusInfoVBox.getChildren().add(pairPlusInfoPane);

				Button backButton = new Button("Back");
				backButton.setStyle("-fx-font: 22 arial;");
				backButton.setMinWidth(300);
				backButton.setMinHeight(50);

				pairPlusInfoVBox.getChildren().addAll(backButton);
				root.getChildren().add(pairPlusInfoVBox);

				// BACK BUTTON
				backButton.setOnAction(e2 -> {
					root.getChildren().remove(pairPlusInfoVBox);
					root.getChildren().add(homeVBox);
				});

			});

			// PLAY GUIDE BUTTON
			playGuideButton.setOnAction(e1 -> {
				root.getChildren().remove(homeVBox);
				VBox playGuideVBox = new VBox();
				playGuideVBox.setTranslateX(600);
				playGuideVBox.setTranslateY(100);
				playGuideVBox.setSpacing(20);
				playGuideVBox.setStyle("-fx-background-color: #000000, opacity: 1.0;");

				Pane playGuidePane = new Pane();
				playGuidePane.setMinWidth(800);
				playGuidePane.setMinHeight(600);
				playGuidePane.setStyle("-fx-background-color: #000000");
				playGuidePane.setTranslateX(-300);
				playGuidePane.setTranslateY(-10);

				Text playGuideTitle = new Text();
				playGuideTitle.setText("Play Guide\n");
				playGuideTitle.setStyle("-fx-font-weight: bold");
				playGuideTitle.setFill(Color.WHITE);
				playGuideTitle.setStyle("-fx-font-size: 35px");
				playGuideTitle.setTextAlignment(TextAlignment.CENTER);
				playGuideTitle.setTranslateX(300);
				playGuideTitle.setTranslateY(60);

				Text playGuideText = new Text();
				playGuideText.setText("1. Start by place an Ante Wager and a Pair Plus Wager\n"
						+ "2. Decide to fold or play once you have your respective cards\n"
						+ "3. If fold, you will lose your Ante Wager and Pair Plus Wager.\nIf play, the dealer will show their cards\n"
						+ "4. There can be two cases once the dealer shows their card.\n"
						+ " - If the dealer does not have at least a Queen or higher,\nplay wager is returned and Ante Bet is pushed to the next round\n"
						+ " - If the dealer does have at least a Queen or higher,\nthen each player’s hand will be compared to the dealer’s hand\n"
						+ "5. You will lose both of your bets if the dealer wins\n OR get back 1 to 1 if they win based on their bet\n");
				playGuideText.setStyle("-fx-font-weight: bold");
				playGuideText.setFill(Color.WHITE);
				playGuideText.setStyle("-fx-font-size: 25px");
				playGuideText.setTranslateX(50);
				playGuideText.setTranslateY(130);
				playGuideText.setTextAlignment(TextAlignment.LEFT);

				playGuidePane.getChildren().addAll(playGuideTitle, playGuideText);
				playGuideVBox.getChildren().add(playGuidePane);

				Button backButton = new Button("Back");
				backButton.setStyle("-fx-font: 22 arial;");
				backButton.setMinWidth(300);
				backButton.setMinHeight(50);

				playGuideVBox.getChildren().addAll(backButton);
				root.getChildren().add(playGuideVBox);

				// BACK BUTTON
				backButton.setOnAction(e2 -> {
					root.getChildren().remove(playGuideVBox);
					root.getChildren().add(homeVBox);
				});
			});

			// CHANGE BACKGROUND BUTTON
			changeThemeButton.setOnAction(e1 -> {
				root.getChildren().remove(homeVBox);
				VBox changeThemeVBox = new VBox();
				changeThemeVBox.setTranslateX(700);
				changeThemeVBox.setTranslateY(100);
				changeThemeVBox.setSpacing(20);

				// Purple theme
				Button purpleButton = new Button("Purple");
				purpleButton.setStyle("-fx-font: 22 arial;");
				purpleButton.setMinWidth(150);
				purpleButton.setMinHeight(50);
				purpleButton.setOnAction(e2 -> {
					root.getChildren().remove(homeScreen);
					root.getChildren().remove(changeThemeVBox);

					root.setStyle("-fx-background-color: #b3b7ee;");

					table.setFill(Color.web("#9395d3"));
					table.setStroke(Color.web("#000807"));

				});

				// Red theme
				Button redButton = new Button("Red");
				redButton.setStyle("-fx-font: 22 arial;");
				redButton.setMinWidth(150);
				redButton.setMinHeight(50);
				redButton.setOnAction(e2 -> {
					root.getChildren().remove(homeScreen);
					root.getChildren().remove(changeThemeVBox);

					root.setStyle("-fx-background-color: #9d0208;");

					table.setFill(Color.web("#6a040f"));
					table.setStroke(Color.web("#03071e"));

				});

				// Blue theme
				Button blueButton = new Button("Blue");
				blueButton.setStyle("-fx-font: 22 arial;");
				blueButton.setMinWidth(150);
				blueButton.setMinHeight(50);
				blueButton.setOnAction(e2 -> {
					root.getChildren().remove(homeScreen);
					root.getChildren().remove(changeThemeVBox);

					root.setStyle("-fx-background-color: #a1c6ea;");

					table.setFill(Color.web("#507dbc"));
					table.setStroke(Color.web("#04080f"));

				});

				// Default
				Button defaultButton = new Button("Default");
				defaultButton.setStyle("-fx-font: 22 arial;");
				defaultButton.setMinWidth(150);
				defaultButton.setMinHeight(50);
				defaultButton.setOnAction(e2 -> {
					root.getChildren().remove(homeScreen);
					root.getChildren().remove(changeThemeVBox);

					root.setStyle("-fx-background-color: #4f772d;");

					table.setFill(Color.web("#31572c"));
					table.setStroke(Color.web("#582f0e"));

				});

				Button backButton = new Button("Back");
				backButton.setStyle("-fx-font: 22 arial;");
				backButton.setMinWidth(150);
				backButton.setMinHeight(50);
				backButton.setOnAction(e2 -> {
					root.getChildren().remove(changeThemeVBox);
					root.getChildren().add(homeVBox);
				});

				changeThemeVBox.getChildren().addAll(purpleButton, redButton, blueButton, defaultButton, backButton);
				root.getChildren().add(changeThemeVBox);
			});

			// Exit the game when exitButton is clicked
			exitButton.setOnAction(e1 -> {
				Platform.exit();
			});

		});


		// playTest1 is a variable to keep track of states
		// playTest1 = 0 means player 1
		// playTest1 = 1 means player 2
		// playTest1 = 3 means We are now the stage of repeatedly dealing cards until
		// dealer has Queen or higher
		// When you press play button, the cards will be dealt as you place the bets
		// it will run two times in total, as there are 2 players

		PlayButton.setOnAction(e9 -> {
			if (playerTest1.getValue() == 1) {
				root.getChildren().remove(PlayButton);
			}
			if (playerTurn == false) {
				playerTitle = playerTwoTitle;
				player = playerTwo;
				playerTest = false;
				anteText = playerTwoAnteBetText;
				playText = playerTwoPlayBetText;
				totalText = playerTwoTotalText;
				pairplusText = playerTwoPairPlusBetText;
			}
			else {
				playerTitle = playerOneTitle;
				player = playerOne;
				playerTest = true;
				anteText = playerOneAnteBetText;
				playText = playerOnePlayBetText;
				totalText = playerOneTotalText;
				pairplusText = playerOnePairPlusBetText;
			}

			getBetPane.getChildren().addAll(playerTitle, anteBetAmount, inputBox);
			getBetVBox.getChildren().addAll(getBetPane, enterButton);

			root.getChildren().add(getBetVBox);

			enterButton.setOnAction(e -> {
				if (playerTest1.getValue() < 2) {
					if (playerTurn == false) {
						root.getChildren().remove(blurScreen);
						root.getChildren().remove(getBetVBox);
						getBetPane.getChildren().removeAll(getBetPane.getChildren());
						getBetVBox.getChildren().removeAll(getBetVBox.getChildren());
					}

					player.setAnteBet(Integer.parseInt(inputBox.getText()));
					anteText.setText("$" + player.getAnteBet());

					inputBox.setPromptText("Pair Plus");
					getBetPane.getChildren().removeAll(getBetPane.getChildren());
					getBetVBox.getChildren().removeAll(getBetVBox.getChildren());
					root.getChildren().remove(blurScreen);
					root.getChildren().remove(getBetVBox);
					getBetPane.getChildren().addAll(playerTitle, pairPlusMessage, inputBox);
					getBetVBox.getChildren().addAll(getBetPane, enterButton);

					root.getChildren().add(getBetVBox);
					inputBox.clear();

					enterButton.setOnAction(e1 -> {
						player.setPairPlusBet(Integer.parseInt(inputBox.getText()));
						pairplusText.setText("$" + player.getPairPlusBet());
						// display the cards here
						if (playerTest){
							System.out.println();
							playerOneCards = displayPlayerCards(player, playerTest);
							for (int i = 0; i < playerOneCards.size(); i++) {
								root.getChildren().add(playerOneCards.get(i));
							}
						}
						else {
							playerTwoCards = displayPlayerCards(player, playerTest);
							for (int i = 0; i < playerTwoCards.size(); i++) {
								root.getChildren().add(playerTwoCards.get(i));
							}
						}

						root.getChildren().remove(blurScreen);
						root.getChildren().remove(getBetVBox);

						inputBox.setPromptText("Play Bet");
						getBetPane.getChildren().removeAll(getBetPane.getChildren());
						getBetVBox.getChildren().removeAll(getBetVBox.getChildren());
						getBetPane.getChildren().addAll(playerTitle, playBetMessage);
						enterButton.setText("Play");
						enterButton.setTranslateX(-130);
						enterButton.setTranslateY(0);
						optionsPairPlus.getChildren().addAll(foldButton, enterButton);
						getBetVBox.getChildren().addAll(getBetPane, optionsPairPlus);

						root.getChildren().add(getBetVBox);
						inputBox.clear();

						foldButton.setOnAction(e4 -> {
							// IF PLAYER FOLDS
							if (playerTest) {
								for (int i = 0; i < playerOneCards.size(); i++) {
									root.getChildren().remove(playerOneCards.get(i));
								}
								playerOneCards.clear();
							}
							else {
								for (int i = 0; i < playerTwoCards.size(); i++) {
									root.getChildren().remove(playerTwoCards.get(i));
								}
								playerTwoCards.clear();
							}
							

							player.setPlayBet(0);
							playText.setText("$" + player.getPlayBet());

							player.setTotalWinnings(player.getTotalWinnings());
							totalText.setText("$" + player.getTotalWinnings());
							getBetPane.getChildren().removeAll(getBetPane.getChildren());
							getBetVBox.getChildren().removeAll(getBetVBox.getChildren());
							optionsPairPlus.getChildren().removeAll(optionsPairPlus.getChildren());
							root.getChildren().remove(blurScreen);
							root.getChildren().remove(getBetVBox);
							playerTurn = !playerTurn;

							if (playerTest1.getValue() == 1) {
								playerDealerCards = displayDealerCards(dealer);
								for (int i = 0; i < playerDealerCards.size(); i++) {
									root.getChildren().add(playerDealerCards.get(i));
								}
							}
							enterButton.setText("Enter");
							enterButton.setTranslateX(0);
							//System.out.println("HELLO" + playerTest1.getValue());
							sumWhat = playerTest1.add(what);
							playerTest1.set(sumWhat.intValue());
						});

						enterButton.setOnAction(e3 -> {
							game.checkPPs(player);
							player.setPlayBet(player.getAnteBet());
							playText.setText("$" + player.getAnteBet());
							getBetPane.getChildren().removeAll(getBetPane.getChildren());
							getBetVBox.getChildren().removeAll(getBetVBox.getChildren());
							optionsPairPlus.getChildren().removeAll(optionsPairPlus.getChildren());
							root.getChildren().remove(blurScreen);
							root.getChildren().remove(getBetVBox);
							playerTurn = !playerTurn;
							if (playerTest1.getValue() == 1) {
								playerDealerCards = displayDealerCards(dealer);
								for (int i = 0; i < playerDealerCards.size(); i++) {
									root.getChildren().add(playerDealerCards.get(i));
								}
							}
							sumWhat = playerTest1.add(what);
							playerTest1.set(sumWhat.intValue());
							enterButton.setText("Enter");
							enterButton.setTranslateX(0);
							//System.out.println("HELLO" + playerTest1.getValue());
						});
					});
				}
			});
		});


		Button nextRoundButton = new Button("Next Round");
		nextRoundButton.setStyle("-fx-font: 22 arial;");
		nextRoundButton.setMinWidth(300);
		nextRoundButton.setMinHeight(50);

		Button endButton = new Button("End Round");
		endButton.setStyle("-fx-font: 22 arial;");
		endButton.setMinWidth(300);
		endButton.setMinHeight(50);

		HBox nextRoundHBox = new HBox();
		nextRoundHBox.setTranslateX(400);
		nextRoundHBox.setTranslateY(300);
		nextRoundHBox.setSpacing(20);
		nextRoundHBox.setStyle("-fx-background-color: #000000, opacity: 1.0;");
		nextRoundHBox.getChildren().addAll(endButton, nextRoundButton);

		playerTest1.addListener(e -> {
			//System.out.println("printing player test out: " + playerTest1.getValue());
			if (playerTest1.getValue() == 2) {
				root.getChildren().add(nextRoundHBox);
				if (checkDealerCards(dealer)) {
					// ok so dealer has Q, we reset
					game.checkWinings();
					num = game.winner();
					if (num == 1)
						text.setText("PLAYER 1 WIN!");
					else if (num == 2)
						text.setText("PLAYER 2 WIN!");
					else if (num == 3)
						text.setText("BOTH PLAYER WIN!");
					else if (num == 4)
						text.setText("DEALER WIN!");
					text.setStroke(Color.YELLOW);
					text.setFill(Color.RED);
					text.setStyle("-fx-font-size: 80px; -fx-font-weight: bold");

					root.setCenter(text);

					playerOneTotalText.setText("$ " + playerOne.getTotalWinnings());
					playerTwoTotalText.setText("$ " + playerTwo.getTotalWinnings());
					// nextRoundHBox.setVisible(false);
					nextRoundHBox.setDisable(true);
					playerTest1.set(0);
				}

				nextRoundButton.setOnAction(e1 -> {
					// remove old cards
					for (int i = 0; i < playerOneCards.size(); i++) {
						root.getChildren().remove(playerOneCards.get(i));
					}
					for (int i = 0; i < playerTwoCards.size(); i++) {
						root.getChildren().remove(playerTwoCards.get(i));
					}
					for (int i = 0; i < playerDealerCards.size(); i++) {
						root.getChildren().remove(playerDealerCards.get(i));
					}

					// getting new cards faced down
					dealer.setHand(dealer.dealHand());
					playerOne.setHand(dealer.dealHand());
					playerTwo.setHand(dealer.dealHand());

					// add new cards
					playerOneCards = displayPlayerCards(playerOne, true);
					for (int i = 0; i < playerOneCards.size(); i++) {
						root.getChildren().add(playerOneCards.get(i));
					}
					playerTwoCards = displayPlayerCards(playerTwo, false);
					for (int i = 0; i < playerTwoCards.size(); i++) {
						root.getChildren().add(playerTwoCards.get(i));
					}
					playerDealerCards = displayDealerCards(dealer);
					for (int i = 0; i < playerTwoCards.size(); i++) {
						root.getChildren().add(playerDealerCards.get(i));
					}

					roundCounter++;
					roundText.setText("Round " + Integer.toString(roundCounter));
					if (checkDealerCards(dealer)) {
						game.checkWinings();
						num = game.winner();
						if (num == 1)
							text.setText("PLAYER 1 WIN!");
						else if (num == 2)
							text.setText("PLAYER 2 WIN!");
						else if (num == 3)
							text.setText("BOTH PLAYER WIN!");
						else if (num == 4)
							text.setText("DEALER WIN!");
						text.setStroke(Color.YELLOW);
						text.setFill(Color.RED);
						text.setStyle("-fx-font-size: 80px; -fx-font-weight: bold");
						// to display
						root.setCenter(text);
						playerOneTotalText.setText("$ " + playerOne.getTotalWinnings());
						playerTwoTotalText.setText("$ " + playerTwo.getTotalWinnings());
						// nextRoundHBox.setVisible(false);
						nextRoundHBox.setDisable(true);
						// ok so dealer has Q, we reset
						playerTest1.set(0);
					}
				});



				endButton.setOnAction(e2 -> {
					Platform.exit();
				});
			}
		});

		// Once PlayButton Done Time to play the game coninuously
		// you don't need to place bet but just having the cards repeatedly dealt
		// New Game button
		// Reset the game when newGameButton is clicked
		newGameButton.setOnAction(e1 -> {
			
			for (int i = 0; i < playerOneCards.size(); i++) {
				root.getChildren().remove(playerOneCards.get(i));
			}
			for (int i = 0; i < playerTwoCards.size(); i++) {
				root.getChildren().remove(playerTwoCards.get(i));
			}
			for (int i = 0; i < playerTwoCards.size(); i++) {
				root.getChildren().remove(playerDealerCards.get(i));
			}
			
			nextRoundHBox.setVisible(true);
			nextRoundHBox.setDisable(false);
			root.getChildren().remove(nextRoundHBox);
			root.getChildren().remove(homeVBox);
			root.getChildren().remove(PlayButton);
			root.getChildren().remove(text);
			homeVBox.getChildren().removeAll(homeVBox.getChildren());
			homeVBox.getChildren().addAll(newGameButton, resumeButton, pairPlusInfoButton, playGuideButton, changeThemeButton, exitButton);
			homeScreen.setVisible(false);
			homeVBox.setVisible(false);
			
			root.getChildren().add(homeVBox);
			playerOne.clear();
			playerOne.clearHand();
			playerOne.setPairPlusBet(0);
			playerOne.setAnteBet(0);
			playerOne.setTotalWinnings(0);
			playerTwo.clear();
			playerTwo.clearHand();
			playerTwo.setPairPlusBet(0);
			playerTwo.setAnteBet(0);
			playerTwo.setTotalWinnings(0);
			dealer.clearDealer();

			playerOneAnteBetText.setText("$" + playerOne.getAnteBet());
			playerOnePairPlusBetText.setText("$" + playerOne.getPairPlusBet());
			playerOnePlayBetText.setText("$" + playerOne.getPlayBet());
			playerOneTotalText.setText("$" + playerOne.getTotalWinnings());
			playerTwoAnteBetText.setText("$" + playerTwo.getAnteBet());
			playerTwoPairPlusBetText.setText("$" + playerTwo.getPairPlusBet());
			playerTwoPlayBetText.setText("$" + playerTwo.getPlayBet());
			playerTwoTotalText.setText("$" + playerTwo.getTotalWinnings());
			root.getChildren().add(PlayButton);
			
			
			playerTest = true;
			playerTurn = true; // true is 1, false is 0
			roundCounter = 1;
			screenOn = true;
			playerTest1.set(0);
			what.set(1);
			playerTurn = true;
			playerTitle = playerOneTitle;
			player = playerOne;
			anteText = playerOneAnteBetText;
			playText = playerOnePlayBetText;
			totalText = playerOneTotalText;
			pairplusText = playerOnePairPlusBetText;
			game.resetGame();
			dealer = game.getDealer();
			playerOne = game.getPlayerOne();
			playerTwo = game.getPlayerTwo();
			dealer.setHand(dealer.dealHand());
			playerOne.setHand(dealer.dealHand());
			playerTwo.setHand(dealer.dealHand());
			System.out.println();
			playerOne.printHand();
			playerTwo.printHand();
			dealer.printHand();
		});

		Scene scene = new Scene(root, 1400, 900);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}