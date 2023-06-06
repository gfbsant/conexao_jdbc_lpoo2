import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
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
        if (listaAntes.size() > 0) {
            for (Contato contato : listaAntes) {
                if (contato.getId() > idMaisAlto) {
                    idMaisAlto = contato.getId();
                }
            }
        }
        Calendar dataNascimento = Calendar.getInstance();
        dataNascimento.set(1991, 4, 15);
        Contato novoContato = new Contato("Gabriel Felipe", "exerciciodao@gmail.com", "Rua 1, 1234, Parana, Brasil",
                dataNascimento);
        dao.adiciona(novoContato);
        novoContato.setId((idMaisAlto > 0) ? idMaisAlto + 1 : 1);
        List<Contato> listaDepois = dao.lista();
        assertEquals(listaAntes.size() + 1, listaDepois.size());
        assertTrue(listaDepois.contains(novoContato));
    }

    @Test
    public void testAltera() {
        ContatoDao dao = new ContatoDao();

        List<Contato> listaContatosAntes = dao.lista();
        Collections.sort(listaContatosAntes, Comparator.comparingLong(Contato::getId).reversed());
        Contato contatoExistente = listaContatosAntes.get(0);

        contatoExistente.setNome("Novo Nome");
        contatoExistente.setEmail("novoemail@example.com");
        contatoExistente.setEndereco("Novo Endereco");
        contatoExistente.setDataNascimento(Calendar.getInstance());
        dao.altera(contatoExistente);

        List<Contato> listaContatosDepois = dao.lista();
        Collections.sort(listaContatosDepois, Comparator.comparingLong(Contato::getId).reversed());
        Contato contatoAlterado = listaContatosDepois.get(0);

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
        List<Contato> listaContatosAntes = dao.lista();
        listaContatosAntes.sort(Comparator.comparingLong(Contato::getId).reversed());
        if (listaContatosAntes.size() > 0) {
            Contato contatoRemover = listaContatosAntes.get(0);
            dao.remove(contatoRemover);
            List<Contato> listaContatosDepois = dao.lista();
            assertEquals(listaContatosAntes.size() - 1, listaContatosDepois.size());
        } else {
            assertTrue(listaContatosAntes.isEmpty());
        }
    }

}
