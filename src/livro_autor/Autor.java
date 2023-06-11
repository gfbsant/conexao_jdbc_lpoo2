package livro_autor;

import java.util.ArrayList;
import java.util.List;

public class Autor {

    private int id;
    private String nome;
    private List<Livro> livros;

    public Autor() {
    }
    
    public Autor(String nome) {
        this.nome = nome;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public List<Livro> getLivros() {
        return livros;
    }

    public void setLivros(List<Livro> livros) {
        this.livros = livros;
    }
    
      public void adicionarLivro(Livro livro) {
        if (livros == null) {
            livros = new ArrayList<>();
        }
        livros.add(livro);
    }



}
