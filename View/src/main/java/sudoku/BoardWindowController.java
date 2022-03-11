package sudoku;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.apache.commons.lang3.math.NumberUtils;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;
import org.apache.log4j.Logger;
import sudoku.exception.DaoException;
import sudoku.exception.FileOperationException;


public class BoardWindowController {
    public Pane boardWindowPane;
    public GridPane boardGrid;
    public Label gameNameBoardWindow;
    public Button loadButton;
    public Button loadDbButton;
    public Button saveButton;
    public Button saveDbButton;
    public Button checkButton;

    SudokuBoard board;
    SudokuBoard startBoard;
    private final ResourceBundle bundle = ResourceBundle.getBundle("languages");
    private static final Logger log = Logger.getLogger(BoardWindowController.class.getName());

    @FXML
    public void initialize() throws CloneNotSupportedException {
        gameNameBoardWindow.setText(bundle.getString("gameName"));
        loadButton.setText(bundle.getString("load"));
        loadDbButton.setText(bundle.getString("loadDb"));
        saveButton.setText(bundle.getString("save"));
        saveDbButton.setText(bundle.getString("saveDb"));
        checkButton.setText(bundle.getString("check"));

        board = new SudokuBoard();
        board.solveGame();

        setDifficulty();
        startBoard = board.clone();
        fillWithTextFields();
    }

    private void setDifficulty() {
        Difficulty difficulty = ChoiceWindowController.getDifficulty();

        if (difficulty == Difficulty.EASY) {
            DifficultyLevel.makeEasy(board);
        } else if (difficulty == Difficulty.MEDIUM) {
            DifficultyLevel.makeMedium(board);
        } else if (difficulty == Difficulty.HARD) {
            DifficultyLevel.makeHard(board);
        }
    }

    private void updateBoard() {
        ObservableList<Node> gridList = boardGrid.getChildren();

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (gridList.get(i + 9 * j) instanceof TextField) {
                    TextField textField = (TextField) gridList.get(i + 9 * j);

                    if (NumberUtils.isParsable(textField.getText())) {
                        int valueToSet = Integer.parseInt(textField.getText());
                        board.setValue(j, i, valueToSet);
                    } else {
                        board.setValue(j, i, 0);
                    }
                }
            }
        }
    }

    private void fillWithTextFields() {
        if (boardGrid.getChildren() != null) {
            boardGrid.getChildren().clear();
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                TextField textField = new TextField();

                textField.setMinSize(30, 30);
                textField.setAlignment(Pos.CENTER);
                textField.setFont(Font.font(20));
                textField.setEditable(startBoard.getValue(i, j) == 0);



                if (board.getValue(i, j) != 0) {
                    textField.setText(String.valueOf(board.getValue(i, j)));
                    if (startBoard.getValue(i, j) != 0) {
                        textField.setStyle("-fx-text-inner-color: #2c5cd7;");
                    }
                }

                textField.textProperty().addListener((observable, oldValue, newValue) -> {
                    if (textField.getText().matches("[1-9]") || textField.getText().matches("")) {
                        updateBoard();
                    } else {
                        Platform.runLater(textField::clear);
                    }
                });

                boardGrid.add(textField, j, i);
            }
        }
    }

    @FXML
    private void saveGame() throws DaoException {
        SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
        Dao<SudokuBoard> file;

        file = factory.getFileDao("savedSudokuGame");
        updateBoard();

        try {
            file.write(board);
        } catch (RuntimeException e) {
            log.error("save error");
        }
    }

    @FXML
    private void loadGame() throws DaoException {
        try {
            SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
            Dao<SudokuBoard> file;

            file = factory.getFileDao("savedSudokuGame");
            board = file.read();

            fillWithTextFields();
        } catch (RuntimeException e) {
           log.error("load error");
        }
    }

    @FXML
    private void saveToDb() {
        TextInputDialog td = new TextInputDialog();
        td.setTitle(bundle.getString("saveDb"));
        td.setHeaderText(bundle.getString("enterDbBoard"));
        td.setContentText(bundle.getString("enterDbName"));
        Optional<String> result = td.showAndWait();

        result.ifPresent(name -> {
            SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();

            try {
                factory.getDatabaseDao(name, "sudokuproject", "sudokuboards").write(board);
                factory.getDatabaseDao(name, "sudokuproject", "startboards").write(startBoard);
            } catch (DaoException e) {
                log.error("Saving to database failed.");
                e.printStackTrace();
            }
        });
    }

    @FXML
    private void loadFromDb() {
        TextInputDialog td = new TextInputDialog();
        td.setTitle(bundle.getString("loadDb"));
        td.setHeaderText(bundle.getString("enterDbBoard"));
        td.setContentText(bundle.getString("enterDbName"));
        Optional<String> result = td.showAndWait();

        result.ifPresent(name -> {
            SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();

            try {
                board = factory.getDatabaseDao(name, "sudokuproject", "sudokuboards").read();
                startBoard = factory.getDatabaseDao(name, "sudokuproject", "startboards").read();
                fillWithTextFields();
            } catch (DaoException e) {
                log.error("Loading from database failed.");
                e.printStackTrace();
            }
        });
    }

    @FXML
    private void checkGame() {
        boolean isSolved = true;

        for (int i = 0; i < 9; i++) {
            if (!board.getColumn(i).verify() || !board.getRow(i).verify()) {
                isSolved = false;
                break;
            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++){
                if (!board.getBox(3*i, 3*j).verify()) {
                    isSolved = false;
                    break;
                }
            }
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(bundle.getString("results"));
        alert.setHeaderText(null);

        if (isSolved) {
            alert.setContentText(bundle.getString("winGame"));
        } else {
            alert.setContentText(bundle.getString("lostGame"));
        }

        alert.showAndWait();
        backToChoiceWindow();
    }


    private void backToChoiceWindow() {
        Stage stage = (Stage) boardWindowPane.getScene().getWindow();
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
}
