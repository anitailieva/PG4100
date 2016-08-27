package innlevering2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by anitailieva on 30/03/16.
 */
public class DBHandler implements AutoCloseable{
    private ConnectToDB db;
    private PreparedStatement prepGetWriter, prepGetTitle;
    private Connection con;

    public DBHandler() throws SQLException {
        db = new ConnectToDB("localhost", "pg4100innlevering2", "root", "");
        con = db.getConnection();
        String table = "bokliste";
        prepGetWriter = con.prepareStatement("SELECT forfatter FROM " + table + " WHERE tittel = ?");
        prepGetTitle = con.prepareStatement("SELECT tittel FROM " + table);
    }

    public void close() throws SQLException {
        prepGetTitle.close();
        prepGetWriter.close();
        con.close();
        db.close();
    }

    //retrieving data from the table "bokliste"
    public String getWriter(String title) throws SQLException {
        String theCorrectAnswer = "";
        prepGetWriter.setString(1, title);
        ResultSet res = prepGetWriter.executeQuery();
        while (res.next()) {
            theCorrectAnswer = res.getString("forfatter");
        }
        return theCorrectAnswer;
    }

    public ArrayList<String> getTitle() throws SQLException{
        ArrayList<String> list = new ArrayList<>();
        ResultSet rs = prepGetTitle.executeQuery();
        while (rs.next()) {
            list.add(rs.getString(1));
        }
        rs.close();
        return list;
    }
    //comparing input from client to the author of a book
    public boolean compareAnswer(String answer, String writerFromDbString) {
        if (answer.equals(writerFromDbString.toLowerCase())) {
            return true;
        }
        String[] split = writerFromDbString.split(",");
        String type1 = split[1].toLowerCase().trim() + " " + split[0].toLowerCase();
        if (answer.equals(type1)) {
            return true;
        }
        String type2 = split[0].toLowerCase().trim() + " " + split[1].toLowerCase().trim();
        if (answer.equals(type2)) {
            return true;
        }
        return false;
    }
}
