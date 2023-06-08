package contato;

import java.util.Calendar;
import java.util.Objects;

public class Contato {
    private Long id;
    private String nome;
    private String email;
    private String endereco;

    public Contato(){}

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

    @Override
    public boolean equals(Object o) {
        Contato contato = (Contato) o;
        if (this == o)
            return true;
        if (!(o instanceof Contato))
            return false;
        return Objects.equals(id, contato.id) && Objects.equals(nome, contato.nome)
                && Objects.equals(email, contato.email) && Objects.equals(endereco, contato.endereco);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


}
