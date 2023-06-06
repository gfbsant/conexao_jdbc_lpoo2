import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

public class Contato {
    private Long id;
    private String nome;
    private String email;
    private String endereco;

    public Contato(String nome, String email, String endereco, Calendar dataNascimento) {
        this.nome = nome;
        this.email = email;
        this.endereco = endereco;
        this.dataNascimento = dataNascimento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    private Calendar dataNascimento;

    public Contato() {
    }

    public Contato getById(Long id) {
        Contato contato = new Contato();
        contato.setId(id);
        return contato;
    }


    public Calendar getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Calendar dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Contato> mapList(ResultSet rs) {
        List<Contato> contatos = new ArrayList<Contato>();
        try {
            while (rs.next()) {
                Contato contato = new Contato();
                contato.setId(rs.getLong("id"));
                contato.setNome(rs.getString("nome"));
                contato.setEmail(rs.getString("email"));
                contato.setEndereco(rs.getString("endereco"));
                Calendar data = Calendar.getInstance();
                data.setTime(rs.getDate("data_nasc"));
                contato.setDataNascimento(data);
                contatos.add(contato);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return contatos;
    }


    @Override
    public boolean equals(Object o) {
        Contato contato = (Contato) o;
        if (this == o)
            return true;
        if (!(o instanceof Contato))
            return false;
        return Objects.equals(nome, contato.nome) &&
                Objects.equals(email, contato.email) &&
                Objects.equals(endereco, contato.endereco);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public boolean isContatoExistente(List<Contato> listaContatos, Contato contatoReferencia) {
        for (Contato contato : listaContatos) {
            if (contato.equals(contatoReferencia)) {
                return true;
            }
        }
        return false;
    }



}
