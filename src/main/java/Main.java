//นายณัฐภัทร ชาญธีระเดช 5810404995

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("moneyBook.fxml"));
        primaryStage.setTitle("Money Book");
        primaryStage.setScene(new Scene(root, 390, 260));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

