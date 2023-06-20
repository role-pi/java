package controle;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import modelo.Evento;

public class EventoDAO implements DAO<Evento> {
    private List<Evento> eventos = new ArrayList<Evento>();

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
        return false;
    }

    public boolean delete(Evento event) {
        if (event != null) {
            eventos.remove(event);
            return true;
        }
        return false;
    }

    public List<Evento> listaEventos() {
        return eventos;
    }

    private static EventoDAO instance = null;

    public EventoDAO() {
		eventos.add(new Evento("✨", new Color(249, 236, 170), "Rolê na fac", "Factory Antônio da Veiga", null, null));
		eventos.add(new Evento("🌱", new Color(212, 229, 195), "Churrasco vegano", "Bela Vista Country Club", null, null));
		eventos.add(new Evento("🎡", new Color(216, 197, 243), "Parque de diversões", "Beto Carrero", null, null));
    }

    public static EventoDAO getInstance() {
        if (instance == null) {
            instance = new EventoDAO();
        }
        return instance;
    }
}
