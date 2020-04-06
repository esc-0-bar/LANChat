package sample;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class NewClient {
    public static void main(String[] args) {
        System.out.println("Client Started");
        try {
            Socket soc = new Socket("192.168.1.2",9081);
            ServerConnection serverConnection = new ServerConnection(soc);

            BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter out = new PrintWriter(soc.getOutputStream(),true);

            new Thread(serverConnection).start();

            while (true){
               System.out.println(">");
                String message = keyboard.readLine();
                out.println("Demo : "+message);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
