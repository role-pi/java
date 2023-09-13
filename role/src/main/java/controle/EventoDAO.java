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
    public ArrayList<Evento> list() {
    	Conexao c = Conexao.getInstancia();

    	Connection con = c.conectar();

    	String query = "SELECT * FROM eventos INNER JOIN eventos_has_usuarios ON eventos.id_evento = eventos_has_usuarios.eventos_id_evento WHERE eventos_has_usuarios.usuarios_id_usuario = ?";

    	ArrayList<Evento> eventos = new ArrayList<Evento>();
    	
    	try {
    	    PreparedStatement ps = con.prepareStatement(query);
    	    ps.setInt(1, UsuarioDAO.getInstance().getUsuarioSelecionado().getId());
    	    
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

    			c.fecharConexao();
    			
                if (rs.next()) {
                    int insertId = rs.getInt(1);
        			return insertId;
                }
    		} catch (SQLException e) {
    			e.printStackTrace();
    		}
        }
        return 0;
    }

    public boolean update(Evento event) {
		if (event != null) {
			Conexao c = Conexao.getInstancia();
			Connection con = c.conectar();
	
			String query = "UPDATE eventos SET nome = ? WHERE id_evento = ?";
	
			try {
				PreparedStatement ps = con.prepareStatement(query);
				ps.setString(1, event.getNome());
				ps.setInt(2, event.getId());
	
				int rowsUpdated = ps.executeUpdate();
				
				ps.close();
				c.fecharConexao();
	
				return rowsUpdated > 0;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
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
