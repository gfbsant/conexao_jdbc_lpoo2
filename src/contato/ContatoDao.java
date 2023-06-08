package contato;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;


public class ContatoDao {

    ConnectionFactory connector = new ConnectionFactory();
    Connection connection = null;
    PreparedStatement stmt;

    public void adiciona(Contato contato) {

        try {
            connection = ConnectionFactory.getConnection();
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
            connection = ConnectionFactory.getConnection();
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
            connection = ConnectionFactory.getConnection();
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
            ResultSet rs = stmt.executeQuery();
            List<Contato> contatos = new ArrayList<Contato>();
            while (rs.next()) {
                Contato contato = new Contato();
                contato.setId(rs.getLong("id"));
                contato.setNome(rs.getString("nome"));
                contato.setEmail(rs.getString("email"));
                contato.setEndereco(rs.getString("endereco"));
                java.sql.Date data = rs.getDate("data_nasc");
                if (data != null) {
                    Calendar dataNascimento = Calendar.getInstance();
                    dataNascimento.setTime(data);
                    contato.setDataNascimento(dataNascimento);
                }
                contatos.add(contato);

            }
            contatos.sort(Comparator.comparingLong(Contato::getId).reversed());
            return contatos;

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
