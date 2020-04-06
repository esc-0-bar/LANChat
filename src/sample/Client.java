package sample;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    Socket soc;
    String userName = "Unknown";
    public void startClient(String userName, String ip, int port){
        this.userName = userName;
        System.out.println("Client Started");
        try {
            soc = new Socket(ip,port);
            ServerConnection serverConnection = new ServerConnection(soc);
            new Thread(serverConnection).start();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void sendMessage(String message){
        try {
            BufferedReader keyboard = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(message.getBytes())));
            PrintWriter out = new PrintWriter(soc.getOutputStream(),true);

                System.out.println(">");
                String sendMessage = keyboard.readLine();
                out.println(userName+" : "+sendMessage);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
