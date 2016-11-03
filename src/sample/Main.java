package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {



    @Override
    public void start(Stage primaryStage) throws Exception {
        Controller controller = new Controller();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("mainWindow.fxml"));
        Parent root = loader.load();
        loader.setController(controller);

        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root));
        primaryStage.setWidth(1084);
        primaryStage.setHeight(600);
        primaryStage.setMinWidth(500);
        primaryStage.setMinHeight(300);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}