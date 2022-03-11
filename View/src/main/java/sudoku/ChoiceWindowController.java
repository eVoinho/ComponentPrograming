package sudoku;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;


public class ChoiceWindowController {
    public Pane choiceWindowPane;
    public Label gameNameChoiceWindow;
    public Label authors;
    public Button englishButton;
    public Button polishButton;
    public ComboBox<String> difficultyComboBox;
    public Button startGameButton;

    private static Difficulty selectedDifficulty;
    private final ResourceBundle bundle = ResourceBundle.getBundle("languages");

    @FXML
    public void initialize() {
        gameNameChoiceWindow.setText(bundle.getString("gameName"));
        authors.setText(bundle.getString("authors"));
        englishButton.setText(bundle.getString("languageEnglish"));
        polishButton.setText(bundle.getString("languagePolish"));
        difficultyComboBox.setValue(bundle.getString("selectDifficulty"));
        difficultyComboBox.getItems().addAll(bundle.getString("difficultyEasy"), bundle.getString("difficultyMedium"), bundle.getString("difficultyHard"));
        startGameButton.setText(bundle.getString("startGame"));

        selectedDifficulty = Difficulty.MEDIUM;

        difficultyComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.matches(bundle.getString("difficultyEasy"))) {
                selectedDifficulty = Difficulty.EASY;
            } else if (newValue.matches(bundle.getString("difficultyMedium"))) {
                selectedDifficulty = Difficulty.MEDIUM;
            } else if (newValue.matches(bundle.getString("difficultyHard"))) {
                selectedDifficulty = Difficulty.HARD;
            }

            startGameButton.setDisable(false);
        });
    }

    @FXML
    private void setEnglishLanguage() {
        Locale.setDefault(new Locale("en"));
        refreshLanguage();
    }

    @FXML
    private void setPolishLanguage() {
        Locale.setDefault(new Locale("pl"));
        refreshLanguage();
    }

    private void refreshLanguage() {
        Stage stage = (Stage) choiceWindowPane.getScene().getWindow();
        Parent root;

        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/choiceWindow.fxml")));
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Difficulty getDifficulty() {
        return selectedDifficulty;
    }

    @FXML
    private void startGame() {
        Stage stage = (Stage) choiceWindowPane.getScene().getWindow();
        Parent root;

        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/boardWindow.fxml")));
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
