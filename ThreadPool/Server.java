package ThreadPool;

import java.net.ServerSocket;
import java.net.Socket;

public class Server {
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
        // TODO: handle exception
    }
   } 
}
