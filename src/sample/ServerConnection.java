package sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ServerConnection implements Runnable {
    private BufferedReader in;
    public ServerConnection(Socket server) throws IOException {
        in = new BufferedReader(new InputStreamReader(server.getInputStream()));
    }

    @Override
    public void run() {
            String serverResponse;
            try {
                while (true){
                    serverResponse = in.readLine();
                    if(serverResponse == null) break;
                    System.out.println("Server Says: "+serverResponse);
                    Controller controller = new Controller();
                    controller.message = serverResponse;

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
