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


    Connection connection = null;
    PreparedStatement stmt = null;
    ConnectionFactory connector = new ConnectionFactory();

    public void adiciona(Contato contato) {

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

        try {
            connection = connector.getConnection();
            stmt = connection.prepareStatement("DELETE FROM contatos WHERE id = ?");
            stmt.setLong(1, contato.getId());
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

    public List<Contato> lista() {

        try {
            connection = connector.getConnection();
            stmt = connection.prepareStatement("SELECT * FROM contatos");
            List<Contato> contatos = new Contato().mapList(stmt.executeQuery());
            for (Contato contato : contatos) {
                System.out.println("Id: " + contato.getId());
                System.out.println("Nome: " + contato.getNome());
                System.out.println("Email: " + contato.getEmail());
                System.out.println("Endereço: " + contato.getEndereco());
                System.out.println("Data de Nascimento: " + contato.getDataNascimento().getTime() + "\n");
            }

            return new Contato().mapList(stmt.executeQuery());
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

}


