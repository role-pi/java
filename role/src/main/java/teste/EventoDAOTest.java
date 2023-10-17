package teste;

import static org.junit.Assert.assertNotEquals;

import org.junit.jupiter.api.Test;

import controle.EventoDAO;
import controle.UsuarioDAO;
import modelo.Evento;
import modelo.Usuario;

public class EventoDAOTest {
	private UsuarioDAO usuarioDAO;
	private EventoDAO eventoDAO;
	
	public EventoDAOTest() {
		usuarioDAO = UsuarioDAO.getInstance();
		eventoDAO = EventoDAO.getInstance();
	}

	@Test
	public void testMetodoInserirEvento() {
		Usuario usuario = new Usuario(0, "Nome", "teste@email.com");
		int id = usuarioDAO.insert(usuario);
		assertNotEquals(id, 0);
		usuario.setId(id);
		
		usuarioDAO.setUsuarioCadastrado(usuario);
		Evento evento = new Evento();
		evento.setNome("Evento");
		
		int id2 = EventoDAO.getInstance().insert(evento);
		assertNotEquals(id2, 0);
	}
	
}
