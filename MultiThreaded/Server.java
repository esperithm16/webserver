import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Consumer;

public class Server {

    public Consumer<Socket> getConsumer() {
        return (clientSocket) -> {
            try {
                PrintWriter toClient =
                        new PrintWriter(clientSocket.getOutputStream(), true);

                toClient.println("Hello from the server");

                toClient.close();
                clientSocket.close();

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        };
    }

    public static void main(String[] args) {
        int port = 8010;
        Server server = new Server();

        try (ServerSocket serverSocket = new ServerSocket(port)) {

            System.out.println("Server listening on port " + port);

            while (true) {
                Socket acceptedSocket = serverSocket.accept();

                // ✅ Correct thread usage
                Thread thread =
                        new Thread(() -> server.getConsumer().accept(acceptedSocket));

                thread.start();
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
