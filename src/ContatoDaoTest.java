import java.util.Calendar;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ContatoDaoTest {
@Test
    public void testAdiciona() {
        ContatoDao dao = new ContatoDao();
        List<Contato> listaAntes = dao.lista();
        long idMaisAlto = 0;
        for (Contato contato : listaAntes) {
            if (contato.getId() > idMaisAlto) {
                idMaisAlto = contato.getId();
            }
        }
        Calendar dataNascimento = Calendar.getInstance();
        dataNascimento.set(1991, 4, 15);
        Contato novoContato = new Contato("Gabriel Felipe", "exercicioDao@gmail.com", "Rua 1, 1234, Parana, Brasil", dataNascimento);
        dao.adiciona(novoContato);
        List<Contato> listaDepois = dao.lista();
        novoContato.setId(idMaisAlto + 1);
        assertTrue(listaDepois.contains(novoContato));
        assertEquals(listaAntes.size() + 1, listaDepois.size());
    }

    @Test
    public void testAltera() {
        ContatoDao dao = new ContatoDao();
        Contato contatoExistente = dao.lista().get(0);
        contatoExistente.setNome("Novo Nome");
        contatoExistente.setEmail("novoemail@example.com");
        contatoExistente.setEndereco("Novo Endereoo");
        contatoExistente.setDataNascimento(Calendar.getInstance());
        dao.altera(contatoExistente);
        Contato contatoAlterado = dao.lista().get(0);
        assertEquals("Novo Nome", contatoAlterado.getNome());
        assertEquals("novoemail@example.com", contatoAlterado.getEmail());
        assertEquals("Novo Endereco", contatoAlterado.getNome());
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
        List<Contato> listaContatosAntes = dao.lista();
        Contato contatoRemover = listaContatosAntes.get(0);
        dao.remove(contatoRemover);
        List<Contato> listaContatosDepois = dao.lista();
        assertFalse(listaContatosDepois.contains(contatoRemover));
        assertEquals(listaContatosAntes.size() - 1, listaContatosDepois.size());
    }

}
