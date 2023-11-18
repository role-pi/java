package controle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import modelo.Evento;
import modelo.Usuario;

public class EventoDAO implements DAO<Evento> {
    @Override
    public ArrayList<Evento> list() {
    	Conexao c = Conexao.getInstancia();

    	Connection con = c.conectar();

    	String query = "SELECT * FROM eventos "
    			+ "INNER JOIN eventos_has_usuarios ON eventos.id_evento = eventos_has_usuarios.eventos_id_evento "
    			+ "WHERE eventos_has_usuarios.usuarios_id_usuario = ? "
    			+ "GROUP BY eventos.id_evento";

    	ArrayList<Evento> eventos = new ArrayList<Evento>();
    	
    	try {
    	    PreparedStatement ps = con.prepareStatement(query);
    	    ps.setInt(1, UsuarioDAO.getInstance().getUsuarioSelecionado().getId());
    	    
    	    ResultSet rs = ps.executeQuery();

    	    while (rs.next()) {
    	        int id = rs.getInt("id_evento");
    	        String nome = rs.getString("nome");
    	        String emoji = rs.getString("emoji");
    	        Timestamp dataInicio = rs.getTimestamp("data_inicio");
    	        Timestamp dataFim = rs.getTimestamp("data_fim");

    	        Evento event = new Evento();
    	        event.setId(id);
    	        event.setNome(nome);
    	        event.setEmoji(emoji);
    	        
    	        if (dataInicio != null) {
    	        	event.setDataInicio(dataInicio.toLocalDateTime());
    	        }
    	        
    	        if (dataFim != null) {
    	        	event.setDataFim(dataFim.toLocalDateTime());
    	        }

    	        eventos.add(event);
    	    }

    	    rs.close();
    	    ps.close();
    	} catch (SQLException e) {
    	    e.printStackTrace();
    	} finally {
			c.desconectar();
		}

	    return eventos;
    }

    public int insert(Evento event) {
    	Usuario usuario = UsuarioDAO.getInstance().getUsuarioSelecionado();
        if (event != null && usuario != null) {
    		Conexao c = Conexao.getInstancia();
    		
    		Connection con = c.conectar();
    		
    		String query = "INSERT INTO eventos "
    				+ "(nome) "
    				+ "VALUES (?)";
    		
    		try {
    			PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
    			
    			ps.setString(1, event.getNome());
    			ps.executeUpdate();
    			ResultSet rs = ps.getGeneratedKeys();
    			
                if (rs.next()) {
                    int insertId = rs.getInt(1);
                    
                    String query2 = "INSERT INTO eventos_has_usuarios "
            				+ "(eventos_id_evento, usuarios_id_usuario) "
            				+ "VALUES (?, ?)";
                    
        			PreparedStatement ps2 = con.prepareStatement(query2);
        			ps2.setInt(1,insertId);
        			ps2.setInt(2, usuario.getId());
        			ps2.executeUpdate();
                    
        			return insertId;
                }
    		} catch (SQLException e) {
    			e.printStackTrace();
    		} finally {
    			c.desconectar();
    		}
        }
        return 0;
    }
    
    public int insert(Evento event, Usuario usuario) {
        if (event != null) {
    		Conexao c = Conexao.getInstancia();
    		
    		Connection con = c.conectar();
    		
    		String query = "INSERT INTO eventos_has_usuarios "
    				+ "(eventos_id_evento, usuarios_id_usuario) "
    				+ "VALUES (?, ?)";
    		
    		try {
    			PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
    			
    			ps.setInt(1, event.getId());
    			ps.setInt(2, usuario.getId());
    			ps.executeUpdate();
    			ResultSet rs = ps.getGeneratedKeys();
    			
                if (rs.next()) {
                    int insertId = rs.getInt(1);
        			c.desconectar();
            		return insertId;
                }
    		} catch (SQLException e) {
    			e.printStackTrace();
    		} finally {
    			c.desconectar();
    		}
        }
        return 0;
    }

    public boolean update(Evento event) {
		if (event != null) {
			Conexao c = Conexao.getInstancia();
			Connection con = c.conectar();
	
			String query = "UPDATE eventos SET nome = ?, data_inicio = ?, data_fim = ?, local = ?, emoji = ? WHERE id_evento = ?";
	
			try {
				PreparedStatement ps = con.prepareStatement(query);
				ps.setString(1, event.getNome());
				ps.setTimestamp(2, Timestamp.valueOf(event.getDataInicio()));
				ps.setTimestamp(3, Timestamp.valueOf(event.getDataFim()));
				ps.setString(4, event.getLocal());
				ps.setString(5, event.getEmoji());
				ps.setInt(6, event.getId());
	
				int rowsUpdated = ps.executeUpdate();
				
				ps.close();
	
				return rowsUpdated > 0;
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
    			c.desconectar();
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
        	    ps.close();

                return true;
        	} catch (SQLException e) {
        	    e.printStackTrace();
        	} finally {
    			c.desconectar();
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
