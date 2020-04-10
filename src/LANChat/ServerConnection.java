package LANChat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ServerConnection implements Runnable {
    String serverResponse;
    private final BufferedReader in;
    public ServerConnection(Socket server) throws IOException {
        in = new BufferedReader(new InputStreamReader(server.getInputStream()));
    }

    @Override
    public void run() {

            try {
                while (true){
                    serverResponse = in.readLine();
                    if(serverResponse == null) break;
                    if(serverResponse.contains("uSeRnAmE")){
                        String username = serverResponse.replace("uSeRnAmE","");
                        Main.controller.userList.getItems().add(username);
                    }else {
                        System.out.println("Server Says: " + serverResponse);
                        Main.controller.chatBox.appendText(serverResponse + "\n");
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

    }
}
