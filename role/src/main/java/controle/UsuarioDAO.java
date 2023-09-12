package controle;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modelo.Evento;
import modelo.Usuario;

public class UsuarioDAO implements DAO<Usuario> {
	private Usuario usuarioSelecionado;

	private static UsuarioDAO instance = null;

    @Override
    public List<Usuario> list() {
    	Conexao c = Conexao.getInstancia();

    	Connection con = c.conectar();

    	String query = "SELECT * FROM usuarios";

    	List<Usuario> usuarios = new ArrayList<Usuario>();
    	try {
			PreparedStatement ps = con.prepareStatement(query);

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
		}
    	
		c.fecharConexao();
		return usuarios;
	}
	    	
	public int insert(Usuario u) {
		Conexao c = Conexao.getInstancia();
		Connection con = c.conectar();

		String query = "INSERT INTO usuarios (email, nome) VALUES (?, ?)";

		try {
			PreparedStatement ps = con.prepareStatement(query,
	                Statement.RETURN_GENERATED_KEYS);

			ps.setString(1, u.getEmail());
			ps.setString(2, u.getNome());

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
		return 0;
	}
	
	public boolean update(Usuario u) {
		return true;
	}
	
	public boolean delete(Usuario usuario) {
		if (usuario != null) {
        	Conexao c = Conexao.getInstancia();

        	Connection con = c.conectar();

        	String query = "DELETE FROM usuarios WHERE id_usuario = ?";
        	
        	try {
        	    PreparedStatement ps = con.prepareStatement(query);
        	    ps.setInt(1, usuario.getId());
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
	
	// Singleton
	
	public Usuario getUsuarioSelecionado() {
		return usuarioSelecionado;
	}

	public void setUsuarioCadastrado(Usuario usuarioCadastrado) {
		this.usuarioSelecionado = usuarioCadastrado;
	}
	
	private UsuarioDAO() {
		this.usuarioSelecionado = new Usuario(1, "João", "");
    }

    public static UsuarioDAO getInstance() {
        if (instance == null) {
            instance = new UsuarioDAO();
        }
        return instance;
    }
}