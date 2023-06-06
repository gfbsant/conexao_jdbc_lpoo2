import java.util.Calendar;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Test;

public class ContatoDaoTest {

    @Test
    public void testAdiciona() {

        ContatoDao dao = new ContatoDao();

        List<Contato> listaAntes = dao.lista();

        Calendar dataNascimento = Calendar.getInstance();
        dataNascimento.set(1991, 4, 15);
        Contato novoContato = new Contato("Gabriel Felipe", "exerciciodao@gmail.com", "Rua 1, 1234, Parana, Brasil",
                dataNascimento);
        dao.adiciona(novoContato);

        List<Contato> listaDepois = dao.lista();
        novoContato.setId(listaDepois.get(0).getId());

        assertEquals(listaAntes.size() + 1, listaDepois.size());
        assertTrue(listaDepois.contains(novoContato));
    }

    @Test
    public void testAltera() {

        ContatoDao dao = new ContatoDao();

        List<Contato> listaContatosAntes = dao.lista();
        Contato contatoExistente = listaContatosAntes.get(0);

        contatoExistente.setNome("Novo Nome");
        contatoExistente.setEmail("novoemail@example.com");
        contatoExistente.setEndereco("Novo Endereco");
        contatoExistente.setDataNascimento(Calendar.getInstance());
        dao.altera(contatoExistente);

        List<Contato> listaContatosDepois = dao.lista();
        Contato contatoAlterado = listaContatosDepois.get(0);

        assertEquals("Novo Nome", contatoAlterado.getNome());
        assertEquals("novoemail@example.com", contatoAlterado.getEmail());
        assertEquals("Novo Endereco", contatoAlterado.getEndereco());
    }

    @Test
    public void testLista() {

        ContatoDao dao = new ContatoDao();

        List<Contato> listaContatos = dao.lista();
        System.out.println(listaContatos);

        assertNotNull(listaContatos);
    }

    @Test
    public void testRemove() {

        ContatoDao dao = new ContatoDao();

        List<Contato> listaContatosAntes = dao.lista();

        if (!listaContatosAntes.isEmpty()) {
            Contato contatoRemover = listaContatosAntes.get(0);
            dao.remove(contatoRemover);

            List<Contato> listaContatosDepois = dao.lista();

            assertEquals(listaContatosAntes.size() - 1, listaContatosDepois.size());
        }
    }

}
