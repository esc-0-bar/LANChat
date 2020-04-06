package sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable{
    private BufferedReader in;
    private PrintWriter out;
    private ArrayList<ClientHandler> clients;

    public ClientHandler(Socket client, ArrayList<ClientHandler> clients) throws IOException {
        this.clients = clients;
        in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        out = new PrintWriter(client.getOutputStream(),true);
    }

    @Override
    public void run() {
            try {
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
