package livro_autor;

import java.sql.Connection;
import java.sql.PreparedStatement;

import connection.ConnectionFactory;

//INSERIR, CONSULTAR, LISTAR, EXCLUIR

/*public class AutorDao {


    private final String stmtInserir = "INSERT INTO autor(nome) VALUES(?)";
    private final String stmtConsultar = "SELECT * FROM autor WHERE id = ?";
    private final String stmtListar = "SELECT * FROM autor";
    private final String stmtExcluir = "DELETE FORM SELECT * FROM auAUTOR WHERE ID = ?";

    public void inserirAutor(Autor autor) {
        try (Connection con = ConnectionFactory.getConnection();
                PreparedStatement stmt = con.prepareStatement(stmtInserir, Statement.RETURN_GENERATED_KEYS);) {
            stmt.setString(1, autor.getNome());
            stmt.executeUpdate();
            autor.setId(lerIdAutor(stmt));
            // Inserir os ids dos livros e autores para fazer o realcionamento
            // this.gravarLivros(con,autor);
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao inserir um autor no banco de dados. Origem=" + ex.getMessage());
        }
    }

    private int lerIdAutor(PreparedStatement stmt) throws SQLException {
        ResultSet rs = stmt.getGeneratedKeys();
        rs.next();
        return rs.getInt(1);
    }

    public Autor consultarAutor(int id) {
        Autor autorLido;
        try (Connection con = ConnectionFactory.getConnection();
                PreparedStatement stmt = con.prepareStatement(stmtConsultar);) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            rs = stmt.executeQuery();
            if (rs.next()) {
                autorLido = new Autor(rs.getString("nome"));
                autorLido.setId(rs.getInt("id"));
                return autorLido;
            } else {
                throw new RuntimeException("Não existe autor com este id. Id=" + id);
            }

        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao consultar um autor no banco de dados. Origem=" + ex.getMessage());
        }

    }

    public List<Autor> listarAutores() throws Exception {
        List<Autor> lista = new ArrayList();
        try (Connection con = ConnectionFactory.getConnection();
                PreparedStatement stmt = con.prepareStatement(stmtListar);) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Autor autor = new Autor(rs.getString("nome"));
                autor.setId(rs.getInt("id"));
                lista.add(autor);
            }
            return lista;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao consultar uma lista de autores. Origem=" + ex.getMessage());
        }

    }

    public void excluirAutor(long id) {

        try (Connection con = ConnectionFactory.getConnection();
                PreparedStatement stmt = con.prepareStatement(stmtExcluir);) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao inserir um autor no banco de dados. Origem=" + ex.getMessage());
        }
    }

}*/
