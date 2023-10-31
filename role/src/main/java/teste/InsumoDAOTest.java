package teste;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import controle.EventoDAO;
import controle.InsumoDAO;
import controle.UsuarioDAO;
import modelo.Evento;
import modelo.Insumo;
import modelo.Transacao;
import modelo.Usuario;

public class InsumoDAOTest {
	private UsuarioDAO usuarioDao;
	private EventoDAO eventoDao;
	private InsumoDAO insumoDao;
	
	public InsumoDAOTest() {
		usuarioDao = UsuarioDAO.getInstance();
		eventoDao = EventoDAO.getInstance();
		insumoDao = InsumoDAO.getInstance();
	}
	
	@Test
	public void testListInsumos() {
		Usuario usuario = new Usuario(0, "Nome", "teste@email.com");
		int usuarioId = usuarioDao.insert(usuario);
		usuario.setId(usuarioId);
		usuarioDao.setUsuarioSelecionado(usuario);
		
		Evento evento = new Evento();
		evento.setNome("Evento");
		int insertId = eventoDao.insert(evento);
		evento.setId(insertId);
		
		Insumo insumo1 = new Insumo();
		insumo1.setNome("Ingresso");
		insumo1.setEvento(evento);
		
		Transacao transacao1 = new Transacao();
		transacao1.setValor(20);
		insumo1.setTransacao(transacao1);
		
		Insumo insumo2 = new Insumo();
		insumo2.setNome("Transporte");
		insumo2.setEvento(evento);
		
		Transacao transacao2 = new Transacao();
		transacao2.setValor(20);
		insumo2.setTransacao(transacao2);
		
		insumoDao.insert(insumo1);
		insumoDao.insert(insumo2);
		
		ArrayList<Insumo> insumos = insumoDao.list(evento);
		assertEquals(insumos.size(), 2);
	}
	
	@Test
	public void testInsertInsumo() {
		Usuario usuario = new Usuario(0, "Nome", "teste@email.com");
		int usuarioId = usuarioDao.insert(usuario);
		usuario.setId(usuarioId);
		usuarioDao.setUsuarioSelecionado(usuario);
		
		Evento evento = new Evento();
		evento.setNome("Evento");
		int eventoId = eventoDao.insert(evento);
		evento.setId(eventoId);
		
		Insumo insumo = new Insumo();
		insumo.setNome("Ingresso");
		insumo.setEvento(evento);
		
		Transacao transacao = new Transacao();
		transacao.setValor(20);
		insumo.setTransacao(transacao);
		
		int insumoId = insumoDao.insert(insumo);
		assertTrue(insumoId > 0);
	}
	
	@Test
	public void testUpdateInsumo() {
		Usuario usuario = new Usuario(0, "Nome", "teste@email.com");
		int usuarioId = usuarioDao.insert(usuario);
		usuario.setId(usuarioId);
		usuarioDao.setUsuarioSelecionado(usuario);
		
		Evento evento = new Evento();
		evento.setNome("Evento");
		int eventoId = eventoDao.insert(evento);
		evento.setId(eventoId);
		
		Insumo insumo = new Insumo();
		insumo.setNome("Ingresso");
		insumo.setEvento(evento);
		
		Transacao transacao = new Transacao();
		transacao.setValor(20);
		insumo.setTransacao(transacao);
		
		int insumoId = insumoDao.insert(insumo);
		insumo.setId(insumoId);
		
		insumo.setNome("Novo ingresso");
		insumo.setDescricao("Teste");
		insumo.setTipo(2);
		assertTrue(insumoDao.update(insumo));
		
		Insumo insumoUpdate = insumoDao.list().stream()
	            .filter(i -> i.getId() == insumoId)
	            .findFirst()
	            .orElse(null);
	    assertEquals("Novo ingresso", insumoUpdate.getNome());
	    assertEquals("Teste", insumoUpdate.getDescricao());
	    assertEquals(2, insumoUpdate.getTipo());
	}
	
	@Test
	public void testUpdateInsumoTransacao() {
		Usuario usuario = new Usuario(0, "Nome", "teste@email.com");
		int usuarioId = usuarioDao.insert(usuario);
		usuario.setId(usuarioId);
		usuarioDao.setUsuarioSelecionado(usuario);
		
		Evento evento = new Evento();
		evento.setNome("Evento");
		int eventoId = eventoDao.insert(evento);
		evento.setId(eventoId);
		
		Insumo insumo = new Insumo();
		insumo.setNome("Ingresso");
		insumo.setEvento(evento);
		
		Transacao transacao = new Transacao();
		transacao.setValor(20);
		insumo.setTransacao(transacao);
		
		int insumoId = insumoDao.insert(insumo);
		insumo.setId(insumoId);
		
		insumo.setNome("Novo ingresso");
		insumo.setDescricao("Teste");
		insumo.setTipo(2);
		
		transacao.setValor(100);
		
		assertTrue(insumoDao.update(insumo));
		
		Insumo insumoUpdate = insumoDao.list().stream()
	            .filter(i -> i.getId() == insumoId)
	            .findFirst()
	            .orElse(null);
	    assertEquals("Novo ingresso", insumoUpdate.getNome());
	    assertEquals("Teste", insumoUpdate.getDescricao());
	    assertEquals(2, insumoUpdate.getTipo());
	    
	    assertEquals(insumoUpdate.getTransacao().getValor(), 100, 0.1);
	}
	
	@Test
	public void testDeleteInsumo() {
		Usuario usuario = new Usuario(0, "Nome", "teste@email.com");
		int usuarioId = usuarioDao.insert(usuario);
		usuario.setId(usuarioId);
		usuarioDao.setUsuarioSelecionado(usuario);
		
		Evento evento = new Evento();
		evento.setNome("Evento");
		int eventoId = eventoDao.insert(evento);
		evento.setId(eventoId);
		
		Insumo insumo = new Insumo();
		insumo.setNome("Ingresso");
		insumo.setEvento(evento);
		
		Transacao transacao = new Transacao();
		transacao.setValor(20);
		insumo.setTransacao(transacao);
		
		int insumoId = insumoDao.insert(insumo);
		insumo.setId(insumoId);
		
		assertTrue(insumoDao.delete(insumo));
		
		Insumo insumoUpdate = insumoDao.list().stream()
	            .filter(i -> i.getId() == insumoId)
	            .findFirst()
	            .orElse(null);
		assertNull(insumoUpdate);
	}
}
