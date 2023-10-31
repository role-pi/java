package teste;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import controle.EventoDAO;
import controle.UsuarioDAO;
import modelo.Evento;
import modelo.Usuario;

public class EventoDAOTest {
	private UsuarioDAO usuarioDao;
	private EventoDAO eventoDao;
	
	public EventoDAOTest() {
		usuarioDao = UsuarioDAO.getInstance();
		eventoDao = EventoDAO.getInstance();
	}
	
	@Test
	public void testListEventos() {
		Usuario usuario = new Usuario(0, "Nome", "teste@email.com");
		int usuarioId = usuarioDao.insert(usuario);
		usuario.setId(usuarioId);
		usuarioDao.setUsuarioSelecionado(usuario);
		
		Evento evento1 = new Evento();
		evento1.setNome("Evento 1");
		
		Evento evento2 = new Evento();
		evento2.setNome("Evento 2");
		
		eventoDao.insert(evento1);
		eventoDao.insert(evento2);
		
		ArrayList<Evento> eventos = eventoDao.list();
		assertEquals(eventos.size(), 2);
	}
	
	@Test
	public void testInsertEventoErroSemUsuario() {
		usuarioDao.setUsuarioSelecionado(null);
		Evento evento = new Evento();
		evento.setNome("Evento");
		
		int eventoId = eventoDao.insert(evento);
		assertTrue(eventoId == 0);
		evento.setId(eventoId);
	}

	@Test
	public void testInsertEvento() {
		Usuario usuario = new Usuario(0, "Nome", "teste@email.com");
		int usuarioId = usuarioDao.insert(usuario);
		assertTrue(usuarioId > 0);
		usuario.setId(usuarioId);
		
		usuarioDao.setUsuarioSelecionado(usuario);
		Evento evento = new Evento();
		evento.setNome("Evento");
		
		int eventoId = eventoDao.insert(evento);
		assertTrue(eventoId > 0);
		evento.setId(eventoId);
	}
	
	@Test
	public void testUpdateEvento() {
		Usuario usuario = new Usuario(0, "Nome", "teste@email.com");
		int usuarioId = usuarioDao.insert(usuario);
		usuario.setId(usuarioId);
		usuarioDao.setUsuarioSelecionado(usuario);
		
		Evento evento = new Evento();
		evento.setNome("Evento");
		
		int eventoId = eventoDao.insert(evento);
		assertTrue(eventoId > 0);
		evento.setId(eventoId);
		
		evento.setNome("Novo nome teste");
		evento.setEmoji("🥶");
		evento.setDataInicio(LocalDateTime.now());
		evento.setDataFim(LocalDateTime.now());
		assertTrue(eventoDao.update(evento));
		
		Evento eventoUpdate = eventoDao.list().stream()
	            .filter(e -> e.getId() == eventoId)
	            .findFirst()
	            .orElse(null);
	    assertEquals("Novo nome teste", eventoUpdate.getNome());
	    assertEquals("🥶", eventoUpdate.getEmoji());
	}
	
	@Test
	public void testDeleteEvento() {
		Usuario usuario = new Usuario(0, "Nome", "teste@email.com");
		int usuarioId = usuarioDao.insert(usuario);
		usuario.setId(usuarioId);
		usuarioDao.setUsuarioSelecionado(usuario);
		
		Evento evento = new Evento();
		evento.setNome("Evento");
		
		int eventoId = eventoDao.insert(evento);
		evento.setId(eventoId);
		
		assertTrue(eventoDao.delete(evento));
		
		Evento eventoUpdate = eventoDao.list().stream()
	            .filter(e -> e.getId() == eventoId)
	            .findFirst()
	            .orElse(null);
		assertNull(eventoUpdate);
	}
}
