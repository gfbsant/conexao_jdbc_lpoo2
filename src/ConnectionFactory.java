import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

    public Connection getConnection() {
        try {
            Properties prop = new Properties();
            InputStream inputStream = ConnectionFactory.class.getResourceAsStream("/bancoDeDados.properties");
            prop.load(inputStream);

            String dbDriver = prop.getProperty("db.driver");
            String dbUrl = prop.getProperty("db.url");
            String dbUser = prop.getProperty("db.user");
            String dbPwd = prop.getProperty("db.pwd");

            Class.forName(dbDriver);
            return DriverManager.getConnection(dbUrl, dbUser, dbPwd);
        }  catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}