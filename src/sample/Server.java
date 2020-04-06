package sample;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private static ArrayList<ClientHandler> clients = new ArrayList<>();
    private static ExecutorService pool = Executors.newFixedThreadPool(4);

    public void startServer() {
        System.out.println("Server signing On");
        try {
            ServerSocket ss = new ServerSocket(9081);
            while (true){
                Socket soc = ss.accept();
                System.out.println("Server Connected");
                ClientHandler clientThread = new ClientHandler(soc, clients);
                clients.add(clientThread);
                pool.execute(clientThread);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}