package University.Server.Socket;

import java.net.ServerSocket;
import java.net.Socket;


public class Server {

    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(8081);

        while (true) {
            Socket socket = serverSocket.accept();

            SocketThread socketThread = new SocketThread(socket);
            socketThread.start();
        }
    }
}