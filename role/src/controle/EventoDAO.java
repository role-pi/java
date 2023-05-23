package controle;

import java.util.ArrayList;
import java.util.List;

import modelo.Evento;

public class EventoDAO implements DAO<Evento> {
	private ArrayList<Evento> eventos = new ArrayList<Evento>();

	@Override
	public List<Evento> list() {
		return eventos;
	}
	
	public boolean insert(Evento event) {
		if (event != null) {
			eventos.add(event);
			return true;
		}
		return false;
	}
	
	public boolean update(Evento event, String[] params) {
		if (event != null) {
			eventos.add(event);
			return true;
		}
		return false;
	}
	
	public boolean delete(Evento t) {
		return true;
	}

	// Singleton
	
	private static EventoDAO instance = null;
	
	private EventoDAO() {
        this.instance = new EventoDAO();
    }

    public static EventoDAO getInstance() {
        if (instance == null) {
            instance = new EventoDAO();
        }
        return instance;
    }
}
