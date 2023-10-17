package teste;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;

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
	public void testInsertEventoErroSemUsuario() {
		usuarioDAO.setUsuarioCadastrado(null);
		Evento evento = new Evento();
		evento.setNome("Evento");
		
		int eventoId = eventoDAO.insert(evento);
		assertTrue(eventoId == 0);
		evento.setId(eventoId);
	}

	@Test
	public void testInsertEvento() {
		Usuario usuario = new Usuario(0, "Nome", "teste@email.com");
		int usuarioId = usuarioDAO.insert(usuario);
		assertTrue(usuarioId > 0);
		usuario.setId(usuarioId);
		
		usuarioDAO.setUsuarioCadastrado(usuario);
		Evento evento = new Evento();
		evento.setNome("Evento");
		
		int eventoId = eventoDAO.insert(evento);
		assertTrue(eventoId > 0);
		evento.setId(eventoId);
	}
	
	@Test
	public void testUpdateEvento() {
		Usuario usuario = new Usuario(0, "Nome", "teste@email.com");
		int usuarioId = usuarioDAO.insert(usuario);
		usuario.setId(usuarioId);
		usuarioDAO.setUsuarioCadastrado(usuario);
		
		Evento evento = new Evento();
		evento.setNome("Evento");
		
		int eventoId = eventoDAO.insert(evento);
		assertTrue(eventoId > 0);
		evento.setId(eventoId);
		
		evento.setNome("Novo nome teste");
		evento.setEmoji("🥶");
		evento.setDataInicio(LocalDateTime.now());
		evento.setDataFim(LocalDateTime.now());
		assertTrue(eventoDAO.update(evento));
		
		Evento eventoUpdate = eventoDAO.list().stream()
	            .filter(e -> e.getId() == eventoId)
	            .findFirst()
	            .orElse(null);
	    assertEquals("Novo nome teste", eventoUpdate.getNome());
	    assertEquals("🥶", eventoUpdate.getEmoji());
	}
}
