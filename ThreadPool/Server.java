package ThreadPool;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private final ExecutorService threadPool;
    public Server(int pooolSize){
        this.threadPool= Executors.newFixedThreadPool(pooolSize);
    }
    public void handleClient(Socket clientSocket){
        try {PrintWriter toSocket=new PrintWriter(clientSocket.getOutputStream(),true)
            {toSocket.println("hello from the server"+clientSocket.getInetAddress());}
            
        } catch (IOException ex) {
         ex.printStackTrace();   // TODO: handle exception
        }
    }
   public static void main(String[] args) {
    int port=8010;
    int pooolSize=10;
    Server server = new Server(pooolSize);
    try {
        ServerSocket serverSocket=new ServerSocket(port);
        serverSocket.setSoTimeout(70000);
        System.out.println("Server is listening on port"+port);
        while (true) {
          Socket clientSocket  = serverSocket.accept();
          server.ThreadPool.execute(()->server.handleClient(clientSocket));
        }
    } catch (IOException ex) {
      ex.printStackTrace();  // TODO: handle exception
    } finally{
        server.ThreadPool.shutdown();
    }
   } 
}
