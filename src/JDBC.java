import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JDBC {
    public static void main(String[] args) {
        try {
            Properties prop = new Properties();
            InputStream inputStream = JDBC.class.getResourceAsStream("/bancoDeDados.properties");
            prop.load(inputStream);

            String dbDriver = prop.getProperty("db.driver");
            String dbUrl = prop.getProperty("db.url");
            String dbUser = prop.getProperty("db.user");
            String dbPwd = prop.getProperty("db.pwd");

            Class.forName(dbDriver);
            Connection conexao = DriverManager.getConnection(dbUrl, dbUser, dbPwd);
            System.out.println("Conectado!");

            conexao.close();
        } catch (IOException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
