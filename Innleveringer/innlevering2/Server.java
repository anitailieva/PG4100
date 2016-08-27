package innlevering2;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;

/**
 * Innlevering 2 – PG4100
 * Created by Anita Ilieva
 * on 16/02/16
 */

public class Server {

    public static void main(String[] args) throws SQLException {

        try (ServerSocket serverSocket = new ServerSocket(8080)) {

            //server always running
            while(true) {
                Socket socket = serverSocket.accept();
                new Thread(new ClientThread(socket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
