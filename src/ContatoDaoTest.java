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

        List<Contato> listaAntes = dao.lista();
        Contato contatoExistente = listaAntes.get(0);

        contatoExistente.setNome("Novo Nome");
        contatoExistente.setEmail("novoemail@example.com");
        contatoExistente.setEndereco("Novo Endereco");
        contatoExistente.setDataNascimento(Calendar.getInstance());

        dao.altera(contatoExistente);

        List<Contato> listaDepois = dao.lista();
        Contato contatoAlterado = listaDepois.get(0);

        assertEquals("Novo Nome", contatoAlterado.getNome());
        assertEquals("novoemail@example.com", contatoAlterado.getEmail());
        assertEquals("Novo Endereco", contatoAlterado.getEndereco());
    }

    @Test
    public void testLista() {

        ContatoDao dao = new ContatoDao();

        List<Contato> listaContatos = dao.lista();
        assertNotNull(listaContatos);
    }

    @Test
    public void testRemove() {

        ContatoDao dao = new ContatoDao();

        List<Contato> listaAntes = dao.lista();

        if (!listaAntes.isEmpty()) {
            Contato contatoRemover = listaAntes.get(0);

            dao.remove(contatoRemover);

            List<Contato> listaDepois = dao.lista();
            assertEquals(listaAntes.size() - 1, listaDepois.size());
            assertFalse(listaDepois.contains(contatoRemover));
        }
    }

}
