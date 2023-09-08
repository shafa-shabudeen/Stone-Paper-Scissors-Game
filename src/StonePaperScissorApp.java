import java.util.Random;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StonePaperScissorApp extends Application {

    private Label resultLabel,chooseLabel;
    private Button stoneButton, paperButton, scissorsButton,exitButton, resetButton;
    Random random = new Random();
    int userPoint =0, computerPoint =0;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Stone Paper Scissors Game");

        VBox layout = new VBox(10);
        HBox design = new HBox(15);
        HBox elayer = new HBox(10);
        layout.setStyle("-fx-background-color: #E6E6FA; -fx-padding: 20px;");

        Label instructions = new Label("Choose Stone, Paper, or Scissors:");
        stoneButton = new Button("✊");
        paperButton = new Button("🖐");
        scissorsButton = new Button("✌");
        exitButton = new Button("Exit");
        resetButton = new Button("Reset");
        resultLabel = new Label();
        chooseLabel = new Label();
        
        stoneButton.setStyle("-fx-font-size: 24px; -fx-background-color: #C0C0C0; -fx-background-radius: 60px");
        paperButton.setStyle("-fx-font-size: 24px; -fx-background-color: #98FB98; -fx-background-radius: 60px;");
        scissorsButton.setStyle("-fx-font-size: 24px; -fx-background-color: #9400D3; -fx-background-radius: 60px;");
        exitButton.setStyle("-fx-font-size: 24px; -fx-background-color: #FFD700; -fx-background-radius: 30px;");
        resetButton.setStyle("-fx-font-size: 24px; -fx-background-color: #FFFFF0; -fx-background-radius: 30px;");


        // Event handling
        stoneButton.setOnAction(e -> handleUserChoice("Stone"));
        paperButton.setOnAction(e -> handleUserChoice("Paper"));
        scissorsButton.setOnAction(e -> handleUserChoice("Scissors"));
        exitButton.setOnAction(e -> primaryStage.close());
        resetButton.setOnAction(e -> handleRestart());

        design.getChildren().addAll(stoneButton,paperButton,scissorsButton);
        elayer.getChildren().addAll(exitButton, resetButton);
        layout.getChildren().addAll(instructions,design, chooseLabel, resultLabel, elayer);
        Scene scene = new Scene(layout, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private int round = 1; // Initialize round number

    private void handleUserChoice(String userChoice) {
    // Generate computer's choice randomly (Stone, Paper, Scissors)
    String[] choices = {"Stone", "Paper", "Scissors"};
    int randomIndex = random.nextInt(3);
    String computerChoice = choices[randomIndex];

    // Determine the winner for the current round
    String result = determineWinner(userChoice, computerChoice, round);
    resultLabel.setText(result);

    round++; // Increment round number
    
    if (round > 5) {
            // Disable the buttons after 5 rounds
            stoneButton.setDisable(true);
            paperButton.setDisable(true);
            scissorsButton.setDisable(true);
        }
}


    private String determineWinner(String userChoice, String computerChoice, int round) {
    if (userChoice.equals(computerChoice)) {
        chooseLabel.setText("Round " + round + ": Draw\nYour choice: " + userChoice + "\nComputer choice: " + computerChoice);
    } else if ((userChoice.equals("Stone") && computerChoice.equals("Scissors")) ||
               (userChoice.equals("Paper") && computerChoice.equals("Stone")) ||
               (userChoice.equals("Scissors") && computerChoice.equals("Paper"))) {
        userPoint += 1;
        chooseLabel.setText("Round " + round + ": You win\nYour choice: " + userChoice + "\nComputer choice: " + computerChoice);
    } else {
        computerPoint += 1;
        chooseLabel.setText("Round " + round + ": Computer wins\nYour choice: " + userChoice + "\nComputer choice: " + computerChoice);
    }

    if (round == 5) {
        String matchResult = determineMatchWinner();
        return matchResult + "\nYour Points: " + userPoint + "\nComputer Points: " + computerPoint;
    } else {
        return "Round " + round + "\nYour Points: " + userPoint + "\nComputer Points: " + computerPoint;
    }
}

private String determineMatchWinner() {
    if (userPoint > computerPoint) {
        return "You win the match!";
    } else if (userPoint < computerPoint) {
        return "Computer wins the match!";
    } else {
        return "It's a tie!";
    }
}

private void handleRestart() {
    userPoint = 0;
    computerPoint = 0;
    round = 1;
    chooseLabel.setText("");
    resultLabel.setText("");
    stoneButton.setDisable(false);
    paperButton.setDisable(false);
    scissorsButton.setDisable(false);
}

}