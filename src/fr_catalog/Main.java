package fr_catalog;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private static Stage primaryStage;
    private final DBHelper db = DBHelper.getInstance();

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        final Controller controller = new Controller();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/mainWindow.fxml"));
        loader.setController(controller);
        Parent root = loader.load();

        primaryStage.setTitle("Catalog");
        primaryStage.setScene(new Scene(root));
        primaryStage.setWidth(1280);
        primaryStage.setHeight(720);
        primaryStage.setMinWidth(500);
        primaryStage.setMinHeight(300);
        primaryStage.show();

        db.connectToDB();
        db.createTables();

        controller.setMain(this);
        controller.setPrimaryStage(this.primaryStage);
        controller.setButtons();
    }


    public static void main(String[] args) {
        launch(args);
    }
    public Stage getPrimaryStage(){
        return primaryStage;
    }
}