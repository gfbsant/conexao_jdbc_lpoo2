package livro_autor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainLivroAutor {

    private AutorDao autorDao;
    private LivroDao livroDao;

    public MainLivroAutor() throws Exception {
        autorDao = new AutorDao();
        livroDao = new LivroDao();
    }

    public static void main(String args[]) throws Exception {
        MainLivroAutor main = new MainLivroAutor();
        String opcao = "";
        while (true) {
            try {
                System.out.println("Escolha uma das opções e tecle <ENTER>: ");
                System.out.println("  1 - Incluir Autor");
                System.out.println("  2 - Incluir Livro");
                System.out.println("  3 - Listar Autores");
                System.out.println("  4 - Listar Livro com autores");
                System.out.println("  5 - Listar Autores de um livro");
                System.out.println("  6 - Listar Livros de um autor");
                System.out.println("  7 - Sair");
                opcao = System.console().readLine();
                switch (opcao) {
                    case "1":
                        main.incluirAutor();
                        break;
                    case "2":
                        main.incluirLivro();
                        break;
                    case "3":
                        main.listarAutores();
                        break;
                    case "4":
                        main.listarLivroComAutores();
                        break;
                    case "5":
                        main.listarAutoresDeUmLivro();
                        break;
                    case "6":
                        System.out.println("Opção não implementada.");
                        break;
                    default:
                        System.out.println("Opção inválida.");
                        break;
                }
                if (opcao.equals("7")) {
                    break;
                }
            } catch (Exception ex) {
                System.out.println("Falha na operação. Mensagem=" + ex.getMessage());
                // ex.printStackTrace();
            }
        }
    }

    public void incluirAutor() throws Exception {
        System.out.print("Digite o nome do autor:");
        String nome = System.console().readLine();
        Autor autor = new Autor(nome);

        System.out.print("Digite os IDs dos livros escritos pelo autor (separados por vírgula):");
        String livros = System.console().readLine();
        String[] livroIds = livros.split(",");

        for (String livroId : livroIds) {
            livroId = livroId.trim();
            if (!livroId.isEmpty()) {
                try {
                    int id = Integer.parseInt(livroId);
                    Livro livro = new Livro(id, "");
                    autor.adicionarLivro(livro);
                } catch (NumberFormatException e) {
                    System.out.println("ID inválido: " + livroId);
                }
            }
        }
        autorDao.inserirAutor(autor);
    }

    public void incluirLivro() {
        System.out.print("Digite o título do livro:");
        String titulo = System.console().readLine();
        int numAutores = 1;
        List<Autor> listaAutores = new ArrayList<>();
        int idAutor = 0;
        do {
            try {
                System.out.print("ID Autor " + numAutores + ":");
                idAutor = Integer.parseInt(System.console().readLine());
                if (idAutor == -1)
                    break;
                Autor autor = autorDao.consultarAutor(idAutor);
                if (autor != null) {
                    listaAutores.add(autor);
                    numAutores++;
                } else {
                    System.out.println("Autor não existe!");
                }
            } catch (Exception ex) {
                System.out.println("ID autor não é inteiro ou inválido!");
            }
        } while (true);

        Livro livro = new Livro(titulo, listaAutores);
        livroDao.inserirLivro(livro);
    }

    public void listarAutores() throws Exception {
        List<Autor> listaAutores = autorDao.listarAutores();
        Collections.sort(listaAutores, new Comparator<Autor>() {
            @Override
            public int compare(Autor arg0, Autor arg1) {
                String nomeAutor1 = arg0.getNome();
                String nomeAutor2 = arg1.getNome();
                int retorno = nomeAutor1.compareToIgnoreCase(nomeAutor2);
                return retorno;
                /*
                 * int idAutor1 = arg0.getId();
                 * int idAutor2 = arg1.getId();
                 * if(idAutor1 > idAutor2)
                 * return 1;
                 * else if(idAutor1 == idAutor2)
                 * return 0;
                 * else
                 * return -1;
                 */
            }
        });
        System.out.println("ID\tNOME");
        for (Autor autor : listaAutores) {
            System.out.println(autor.getId() + " \t" + autor.getNome());
        }
    }

    public void listarLivroComAutores() throws Exception {
        List<Livro> listaLivros = livroDao.listarLivroComAutores();
        Collections.sort(listaLivros, new Comparator<Livro>() {
            public int compare(Livro arg0, Livro arg1) {
                String titulo = arg0.getTitulo();
                int i = titulo.compareToIgnoreCase(arg1.getTitulo());
                return i;
            }
        });
        System.out.println("ID\tTitulo do Livro\tAutores");
        for (Livro livro : listaLivros) {
            System.out.print(livro.getId() + "\t" + livro.getTitulo() + "\t");
            for (Autor autor : livro.getAutores()) {
                System.out.print(autor.getNome() + ";");
            }
            System.out.print("\n");
        }

    }

    public void listarAutoresDeUmLivro() {
        System.out.print("Digite o ID do livro:");
        int idLivro = Integer.parseInt(System.console().readLine());
        Livro livro = livroDao.consultarLivro(idLivro);
        if (livro != null) {
            System.out.println("ID\tNOME");
            for (Autor autor : livro.getAutores()) {
                System.out.println(autor.getId() + " \t" + autor.getNome());
            }
        } else {
            System.out.println("Livro não existe!");
        }
    }

    public void listarLivrosDeUmAutor() {
        System.out.println("Digite o id do autor: ");
        int idAutor = Integer.parseInt(System.console().readLine());
        Autor autor = autorDao.consultarAutor(idAutor);
        if (autor != null) {
            System.out.println("ID\tTitulo ");
            for (Livro livro : autor.getLivros()) {
                System.out.println(livro.getId() + " \t" + livro.getTitulo());
            }
        } else {
            System.out.println("Autor não existe!");
        }
    }
}
