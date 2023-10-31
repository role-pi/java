package controle;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import modelo.Evento;
import modelo.Usuario;

public class UsuarioDAO implements DAO<Usuario> {
	private Usuario usuarioSelecionado;

	private static UsuarioDAO instance = null;

    @Override
    public ArrayList<Usuario> list() {
		return list(null);
	}
    
    public ArrayList<Usuario> list(Evento evento) {
    	Conexao c = Conexao.getInstancia();

    	Connection con = c.conectar();
    	
    	String query;
    	if (evento != null) {
	    	query = "SELECT usuarios.* FROM usuarios "
	    			+ "INNER JOIN eventos_has_usuarios ON usuarios.id_usuario = eventos_has_usuarios.usuarios_id_usuario "
	    			+ "WHERE eventos_has_usuarios.eventos_id_evento = ?";
    	} else {
        	query = "SELECT * FROM usuarios";
    	}

    	ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
    	try {
			PreparedStatement ps = con.prepareStatement(query);
			
			if (evento != null) {
				ps.setInt(1, evento.getId());
			}

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id_usuario");
				String nome = rs.getString("nome");
				String email = rs.getString("email");
				
				Usuario u = new Usuario(id, nome, email);

				usuarios.add(u);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			c.fecharConexao();
		}
    	
		return usuarios;
	}
	    	
	public int insert(Usuario usuario) {
		Conexao c = Conexao.getInstancia();
		Connection con = c.conectar();

		String query = "INSERT INTO usuarios (email, nome) VALUES (?, ?)";

		try {
			PreparedStatement ps = con.prepareStatement(query,
	                Statement.RETURN_GENERATED_KEYS);

			ps.setString(1, usuario.getEmail());
			ps.setString(2, usuario.getNome());

			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			
            if (rs.next()) {
                int insertId = rs.getInt(1);
    			return insertId;
            }
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			c.fecharConexao();
		}
		return 0;
	}
	
	public boolean update(Usuario usuario) {
		if (usuario != null) {
			Conexao c = Conexao.getInstancia();
			Connection con = c.conectar();
	
			String query = "UPDATE usuarios SET nome = ?, email = ?";
	
			try {
				PreparedStatement ps = con.prepareStatement(query);
				ps.setString(1, usuario.getNome());
				ps.setString(2, usuario.getEmail());
	
				int rowsUpdated = ps.executeUpdate();
				
				ps.close();
	
				return rowsUpdated > 0;
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				c.fecharConexao();
			}
		}
		return false;
	}
	
	public boolean delete(Usuario usuario) {
		if (usuario != null) {
        	Conexao c = Conexao.getInstancia();

        	Connection con = c.conectar();

        	String query = "DELETE FROM usuarios "
        			+ "WHERE id_usuario = ?";
        	
        	try {
        	    PreparedStatement ps = con.prepareStatement(query);
        	    ps.setInt(1, usuario.getId());
    			ps.executeUpdate();
        	    ps.close();

                return true;
        	} catch (SQLException e) {
        	    e.printStackTrace();
        	} finally {
        	    c.fecharConexao();
        	}
        }
        return false;
	}
	
	// Singleton
	
	public Usuario getUsuarioSelecionado() {
		return usuarioSelecionado;
	}

	public void setUsuarioSelecionado(Usuario usuario) {
		this.usuarioSelecionado = usuario;
	}
	
	public UsuarioDAO() {
		this.usuarioSelecionado = new Usuario(1, "João", "");
    }

    public static UsuarioDAO getInstance() {
        if (instance == null) {
            instance = new UsuarioDAO();
        }
        return instance;
    }
}