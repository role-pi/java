package controle;

import java.util.ArrayList;

import modelo.Evento;

public class EventoDAO {
	private ArrayList<Evento> eventos = new ArrayList();
	
	public boolean insert(Evento event) {
		if (event != null) {
			eventos.add(event);
			return true;
		}
		return false;
	}
	
	public boolean update(Evento event, Integer eventoID) {
		if (event != null) {
			eventos.add(event);
			return true;
		}
		return false;
	}
	
	public boolean update(Integer eventoID) {
		return true;
	}
	
	public boolean delete(Integer eventoID) {
		return true;
	}
	
	public ArrayList<Evento> listaEventos() {
		return eventos;
	}
}
