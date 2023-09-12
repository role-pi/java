package controle;

import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import modelo.Evento;
import modelo.Insumo;
import modelo.Usuario;

public class EventoDAO implements DAO<Evento> {
    @Override
    public List<Evento> list() {
    	Conexao c = Conexao.getInstancia();

    	Connection con = c.conectar();

    	String query = "SELECT * FROM eventos";

    	List<Evento> eventos = new ArrayList<Evento>();
    	
    	try {
    	    PreparedStatement ps = con.prepareStatement(query);
    	    ResultSet rs = ps.executeQuery();

    	    while (rs.next()) {
    	        int id = rs.getInt("id_evento");
    	        String nome = rs.getString("nome");

    	        Evento event = new Evento();
    	        event.setId(id);
    	        event.setNome(nome);

    	        eventos.add(event);
    	    }

    	    rs.close();
    	    ps.close();

    	    c.fecharConexao();
    	} catch (SQLException e) {
    	    e.printStackTrace();
    	}

	    return eventos;
    }

    public int insert(Evento event) {
        if (event != null) {
    		Conexao c = Conexao.getInstancia();
    		
    		Connection con = c.conectar();
    		
    		String query = "INSERT INTO eventos "
    				+ "(nome) "
    				+ "VALUES (?)";
    		
    		try {
    			PreparedStatement ps = con.prepareStatement(query);
    			
    			ps.setString(1, event.getNome());
    			ps.executeUpdate();
    			ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    int insertId = rs.getInt(1);
        			return insertId;
                }
                
    			c.fecharConexao();
    		} catch (SQLException e) {
    			e.printStackTrace();
    		}
        }
        return 0;
    }

    public boolean update(Evento event) {
        return false;
    }

    public boolean delete(Evento event) {
        if (event != null) {
        	Conexao c = Conexao.getInstancia();

        	Connection con = c.conectar();

        	String query = "DELETE FROM eventos WHERE id_evento = ?";
        	
        	try {
        	    PreparedStatement ps = con.prepareStatement(query);
        	    ps.setInt(1, event.getId());
    			ps.executeUpdate();
    			ResultSet rs = ps.getGeneratedKeys();
    			
        	    rs.close();
        	    ps.close();

        	    c.fecharConexao();

                return true;
        	} catch (SQLException e) {
        	    e.printStackTrace();
        	}
        }
        return false;
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
		
//		eventos.add(e1);
//		eventos.add(e2);
//		eventos.add(e3);
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
