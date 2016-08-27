package innlevering2;

import java.sql.*;

/**
 * Innlevering 2 – PG4100
 * Created by Anita Ilieva
 * on 16/02/16
 */
public class ConnectToDB implements AutoCloseable{

    Connection con;

    public ConnectToDB (String server, String database,
                        String user, String password)
            throws SQLException {

        con = DriverManager.getConnection("jdbc:mysql://" + server + "/" + database, user, password);
    }
    public void close() throws SQLException {
        con.close();
    }
    public Connection getConnection() {
        return con;
    }

}


