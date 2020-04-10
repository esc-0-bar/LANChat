package LANChat;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    Socket soc;
    String userName;
    public void startClient(String userName, String ip, int port){
        this.userName = userName;
        System.out.println("Client Started");
        try {
            soc = new Socket(ip,port);
            ServerConnection serverConnection = new ServerConnection(soc);
            new Thread(serverConnection).start();
            Main.controller.bottomLabel.setText("Connected");
            try {
                BufferedReader keyboard = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(userName.getBytes())));
                PrintWriter out = new PrintWriter(soc.getOutputStream(),true);
                String sendMessage = keyboard.readLine();
                out.println("uSeRnAmE"+sendMessage);

            }catch (Exception e){
                e.printStackTrace();
            }


        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void sendMessage(String message){
        try {
            BufferedReader keyboard = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(message.getBytes())));
            PrintWriter out = new PrintWriter(soc.getOutputStream(),true);
                String sendMessage = keyboard.readLine();
                out.println(userName+" : "+sendMessage);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
