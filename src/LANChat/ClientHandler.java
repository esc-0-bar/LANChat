package LANChat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable{
    private final BufferedReader in;
    private final PrintWriter out;
    private final ArrayList<ClientHandler> clients;

    public ClientHandler(Socket client, ArrayList<ClientHandler> clients) throws IOException {
        this.clients = clients;
        in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        out = new PrintWriter(client.getOutputStream(),true);
    }

    @Override
    public void run() {
            try {
                //noinspection InfiniteLoopStatement
                while (true){
                    String request = in.readLine();
                    outToAll(request);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                out.close();
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

    }
    private void outToAll (String message){
        for(ClientHandler aClient : clients){
            aClient.out.println(message);
        }
    }
}
