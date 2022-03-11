package sudoku;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.application.Application;
import java.io.IOException;
import org.apache.log4j.Logger;

public class ViewApp extends Application {
    private static final Logger log = Logger.getLogger(ViewApp.class.getName());

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/choiceWindow.fxml"));
        Scene scene = new Scene(loader.load(), 700, 500);

        stage.setTitle("Gra w Sudoku - Igor Milczarski i Jacek Nowak");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        log.info("The Game Has BEGUN!!!");
        launch();
    }
}