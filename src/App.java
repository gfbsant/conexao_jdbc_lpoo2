import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;

public class App {
    public static void main(String[] args) throws Exception {
        Connection conexao = new ConnectionFactory().getConnection();
        Statement stmt = conexao.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM contato");
        rs.next();
        int quantidadeContatos = rs.getInt(1);
        int userId = quantidadeContatos + 1;

        stmt.executeUpdate("INSERT INTO contato" +
                " (id, nome, email, endereco, data_nasc)" +
                " VALUES" +
                " (" + userId + ", 'Maria', 'maria@gmail.com', 'Rua 1', '1990-01-01')");
         
        System.out.println("Conectado!");
        stmt.executeUpdate("DELETE FROM contato WHERE id = " + userId);
        conexao.close();
    }
}
