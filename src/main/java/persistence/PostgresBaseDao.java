package persistence;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.DriverManager;
import java.sql.Connection;


public class PostgresBaseDao {

    protected final Connection getConnection() {
        Connection result = null;

   /*     String url = "jdbc:postgresql://localhost:5432/lingo";
        String user = "postgres";
        String password = "admin";*/

        try {
            InitialContext ic = new InitialContext();
           DataSource ds = (DataSource) ic.lookup("java:comp/env/jdbc/PostgresDS");
            result = ds.getConnection();
            //result = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}

