import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;


/*2. (Tarefa 5) Crie uma classe que liste contatos inseridos na base dados, utilizando a
ContatoDao
a. Crie os métodos:
public void altera(Contato contato) -> altera o contato com base nos valores recebidos pelo id do contato
public void remove(Contato contato) -> remove o contato com base no id recebido do contato
public List<Contato> lista() -> retorna a lista de contatos
public void insere(Contato contato) -> insere um contato com base no id recebido do contato
b. Crie uma classe para testar os métodos.*/

public class ContatoDao {

    public void adiciona(Contato contato) {
        Connection connection = null;
        PreparedStatement stmt = null;
        ConnectionFactory connector = new ConnectionFactory();
        try {
            connection = connector.getConnection();
            stmt = connection.prepareStatement(
                    "INSERT INTO contatos (nome, email, endereco, data_nasc) VALUES (?, ?, ?, ?)");
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

    public void altera(Contato contato) {
        Connection connection = null;
        PreparedStatement stmt = null;
        ConnectionFactory connector = new ConnectionFactory();
        try {
            connection = connector.getConnection();
            stmt = connection.prepareStatement(
                    "UPDATE contatos SET nome = ?, email = ?, endereco = ?, data_nasc = ? WHERE id = ?");
            stmt.setString(1, contato.getNome());
            stmt.setString(2, contato.getEmail());
            stmt.setString(3, contato.getEndereco());
            stmt.setDate(4, new java.sql.Date(contato.getDataNascimento().getTimeInMillis()));
            stmt.setLong(5, contato.getId());
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (stmt != null) {
                    stmt.close();
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void remove(Contato contato) {
        Connection conn = null;
        PreparedStatement statement = null;
        ConnectionFactory connFactory = new ConnectionFactory();
        try {
            conn = connFactory.getConnection();
            statement = conn.prepareStatement("DELETE FROM contatos WHERE id = ?");
            statement.setLong(1, contato.getId());
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public List<Contato> lista() {
        Connection conn = null;
        PreparedStatement statement = null;
        ConnectionFactory connFactory = new ConnectionFactory();
        try {
            conn = connFactory.getConnection();
            statement = conn.prepareStatement("SELECT * FROM contatos");
            return new Contato().mapList(statement.executeQuery());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
                if (statement != null) {
                    statement.close();
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }

}


