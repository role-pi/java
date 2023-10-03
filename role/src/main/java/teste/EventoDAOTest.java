package teste;

import static org.junit.Assert.assertNotEquals;

import org.junit.jupiter.api.Test;

import controle.EventoDAO;
import modelo.Evento;

public class EventoDAOTest {

	@Test
	public void testMetodoInserirEvento() {
		Evento evento = new Evento();
		evento.setNome("Evento");
		
		int id = EventoDAO.getInstance().insert(evento);
		assertNotEquals(id, 0);
	}
	
}
