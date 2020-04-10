package LANChat;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.SocketException;
import java.util.ArrayList;


public class Controller {

    @FXML
    public TextField port;
    @FXML
    public TextField ip;
    @FXML
    public TextField userName;
    @FXML
    public TextArea chatBox;
    @FXML
    public TextArea messages;
    @FXML
    public Label bottomLabel;
    @FXML
    public ListView userList;
    @FXML
    public MenuItem about;


    ArrayList<String> serverData = new ArrayList<>();
    Client client = new Client();

    public void closeButtonClicked() {
        ser.cancel();
        System.exit(0);
        Main.primaryStage.close();
    }

    public void loginButtonClicked() {
        client.startClient(userName.getText(),ip.getText(),Integer.parseInt(port.getText()));
    }
    public void sendButtonClicked() {
        client.sendMessage(messages.getText());
        messages.clear();

    }

    public void startServer() throws Exception {
        ArrayList<String> a = serverAlertBox();
        ip.setText(a.get(0));
        port.setText(a.get(1));

    }

    public ArrayList<String> serverAlertBox() throws SocketException {

        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Server Started");
        window.setMinWidth(350);
        window.setMaxWidth(350);
        window.setMinHeight(150);
        window.setMaxHeight(150);
        Label label = new Label("Server Started on this ip: "+FindLocalIP.ip());
        Label portLabel = new Label("Port is : 1337");


        serverData.add(FindLocalIP.ip());
        serverData.add("1337");

        Button okButton = new Button("Ok");
        okButton.setOnAction(e-> window.close());

        VBox layout = new VBox();
        layout.setPadding(new Insets(10,10,10,10));
        layout.setSpacing(10);
        layout.getChildren().addAll(label,portLabel,okButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
        ser.start();

        return serverData;
    }
    Service<Void> ser = new Service<>() {
        @Override
        protected Task createTask() {
            return new Task<Void>() {
                @Override
                protected Void call() {
                    Server server = new Server();
                    try {
                        server.startServer();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return null;
                }
            };
        }
    };
    public void aboutButtonClicked(){
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("About");
        window.setMinWidth(350);
        window.setMaxWidth(350);
        window.setMinHeight(150);
        window.setMaxHeight(150);
        Label label = new Label("Project made by Mohammad Shiblee Noman Ahad");
        Label portLabel = new Label("Github Link - https://github.com/i-am-ahad/LANChat");
        Button okButton = new Button("Ok");
        okButton.setOnAction(e-> window.close());
        VBox layout = new VBox();
        layout.setPadding(new Insets(10,10,10,10));
        layout.setSpacing(10);
        layout.getChildren().addAll(label,portLabel,okButton);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }
}