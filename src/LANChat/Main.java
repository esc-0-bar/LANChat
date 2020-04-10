package LANChat;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    static Controller controller;
    static Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception{

        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent root = loader.load();
        controller = loader.getController();
        Scene scene = new Scene(root, 600, 400);
        primaryStage.setScene(scene);

        primaryStage.setMaxHeight(445);
        primaryStage.setMinHeight(445);

        primaryStage.setMaxWidth(600);
        primaryStage.setMinWidth(600);

        primaryStage.setTitle("LAN Chat");
        Main.primaryStage = primaryStage;
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
