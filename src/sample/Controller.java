package sample;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.Label;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Controller implements ActionListener {

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
    public String message;



    Client client = new Client();

    public void closeButtonClicked() {
        Platform.exit();
    }

    public void loginButtonClicked() {
        client.startClient(userName.getText(),ip.getText(),Integer.parseInt(port.getText()));
    }
    public void sendButtonClicked() {
        client.sendMessage(messages.getText());
    }

    public void startServer() throws Exception {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Server Started");
        window.setMinWidth(350);
        window.setMaxWidth(350);
        window.setMinHeight(150);
        window.setMaxHeight(150);
        Label label = new Label("Server Started on this ip: "+FindLocalIP.ip());
        Label portLabel = new Label("Port is : 1337");

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
        ser.start();

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        messages.appendText(message);
    }
}