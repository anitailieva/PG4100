package innlevering2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by anitailieva on 30/03/16.
 */
public class ClientThread implements Runnable {
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private ArrayList<String> books = new ArrayList<>();
    private DBHandler db = new DBHandler();


    public ClientThread(Socket socket) throws SQLException {
        this.socket = socket;
    }

    public void run() {
        try {
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            out.writeUTF("Vil du delta i forfatter-QUIZ? (ja/nei) ");
            out.flush();

            if (continuePlaying()) {
                playQuiz();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean askMoreQuestions() {
        return !books.isEmpty();
    }

    public boolean continuePlaying() {
        try {
            while (!socket.isClosed() ) {
                String answer = in.readUTF();

                if (answer.toLowerCase().equals("ja") || answer.toLowerCase().equals("j")) {
                    return true;
                } else if (answer.toLowerCase().equals("nei") || answer.toLowerCase().equals("n")) {
                    out.writeUTF("Takk for at du deltok!");
                    out.flush();
                    out.close();
                    in.close();
                    socket.close();
                }else{
                    out.writeUTF("Ugyldig svar. Ja eller Nei?: ");
                }
            }if (socket.isClosed()) {
                System.out.println("The connection was closed by user.");
            }
        } catch (IOException e) {
            System.err.println("I/O exception caused by user closing the client");
            return false;
        }
        return false;
    }
    public void playQuiz() {
        try {
            while (!socket.isClosed()) {
                books.addAll(db.getTitle());
                for (String book : books) {
                    out.writeUTF("Hvem har skrevet " + book + "? ");
                    out.flush();
                    String answer = in.readUTF();
                    String writer = db.getWriter(book);
                    if (db.compareAnswer(answer, writer)) {
                        out.writeUTF("Riktig!");
                        out.flush();
                    } else {
                        out.writeUTF("Feil - svaret er " + writer);
                        out.flush();
                    }

                    if (askMoreQuestions()) {
                        System.out.println(in.readUTF());
                        out.writeUTF("Vil du fortsette? j/n ");
                        out.flush();
                        continuePlaying();
                    }

                }
            }
        } catch (IOException | NullPointerException e) {
            System.out.println("Client was closed");
        } catch (SQLException e) {
            e.printStackTrace();

        }

    }
}
