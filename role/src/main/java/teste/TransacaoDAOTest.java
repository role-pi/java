package teste;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;

import org.junit.Test;

import controle.EventoDAO;
import controle.InsumoDAO;
import controle.UsuarioDAO;
import modelo.Evento;
import modelo.Insumo;
import modelo.Transacao;
import modelo.Usuario;

public class TransacaoDAOTest {
	private UsuarioDAO usuarioDao;
	private EventoDAO eventoDao;
	private InsumoDAO insumoDao;
	
	public TransacaoDAOTest() {
		usuarioDao = UsuarioDAO.getInstance();
		eventoDao = EventoDAO.getInstance();
		insumoDao = InsumoDAO.getInstance();
	}
	
	@Test
	public void testListTransacao () {
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

		Insumo insumoGet1 = insumoDao.list().stream()
	            .filter(i -> i.getId() == insumo2.getId())
	            .findFirst()
	            .orElse(null);
		assertNotNull(insumoGet1.getTransacao());
	}
	
	@Test
	public void testInsertTransacao () {
		
	}
	
	@Test
	public void testUpdateTrasacao () {
		
	}
	
	@Test
	public void testDeleteTrasacao () {
		
	}
}
