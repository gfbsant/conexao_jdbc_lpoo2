import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ContatoDAO {

    public void adiciona(Contato contato) {
        Connection connection = null;
        PreparedStatement stmt = null;
        ConnectionFactory connector = new ConnectionFactory();
        try {
            connection = connector.getConnection();
            stmt = connection.prepareStatement("INSERT INTO contatos (nome, email, endereco, dataNascimento) VALUES (?, ?, ?, ?)");
            stmt.setString(1, contato.getNome());
            stmt.setString(2, contato.getEmail());
            stmt.setString(3, contato.getEndereco());
            stmt.setDate(4, new java.sql.Date(contato.getDataNascimento().getTimeInMillis()));
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
