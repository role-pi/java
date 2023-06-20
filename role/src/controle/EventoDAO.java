package controle;

import java.awt.Color;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import modelo.Evento;
import modelo.Usuario;

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

    public boolean update(Evento event) {
    	// Não implementado pois não houve necessidade
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
    	Usuario u1 = new Usuario("João Gabriel", "joao@role.com");
    	Usuario u2 = new Usuario("Ana Clara", "ana@role.com");
    	Usuario u3 = new Usuario("Maiara", "maiara@role.com");
    	
		Evento e1 = new Evento("✨", "Rolê na Fac", "Factory Antônio da Veiga", parseDateTime("2023-06-23 22:00"), parseDateTime("2023-06-24 05:00"));
		Evento e2 = new Evento("🌱", "Churrasco vegano", "Bela Vista Country Club", parseDateTime("2023-08-05 11:00"), parseDateTime("2023-08-05 15:00"));
		Evento e3 = new Evento("🎡", "Parque de diversões", "Beto Carrero", parseDateTime("2023-09-21 10:00"), null);
		
		e1.getUsuarios().add(u1);
		e1.getUsuarios().add(u2);
		e1.getUsuarios().add(u3);
		
		e2.getUsuarios().add(u1);
		e2.getUsuarios().add(u2);
		e2.getUsuarios().add(u3);
		
		e3.getUsuarios().add(u1);
		e3.getUsuarios().add(u2);
		e3.getUsuarios().add(u3);
		
		eventos.add(e1);
		eventos.add(e2);
		eventos.add(e3);
    }
    
    static LocalDateTime parseDateTime(String date) {
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    	return LocalDateTime.parse(date, formatter);
    }
    
    public static EventoDAO getInstance() {
        if (instance == null) {
            instance = new EventoDAO();
        }
        return instance;
    }
}
