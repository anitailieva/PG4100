package innlevering2;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * Innlevering 2 – PG4100
 * Created by Anita Ilieva
 * on 16/02/16
 */

public class Client {
    public static void main(String[] args) throws IOException {
        try (
                Scanner input = new Scanner(System.in);
                Socket socket = new Socket("localhost", 8080);
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                DataInputStream in = new DataInputStream(socket.getInputStream())) {

            out.flush();

            while (true) {
                System.out.print(in.readUTF());
                String answer = input.nextLine();
                out.writeUTF(answer);
                out.flush();
                if (answer.equals("nei") || answer.equals("n")) {
                    System.out.print(in.readUTF());
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();

        }
    }
}
