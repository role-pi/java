package controle;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.Evento;
import modelo.Usuario;

public class UsuarioDAO implements DAO<Usuario> {
	  private List<Usuario> usuarios = new ArrayList<Usuario>();

	    @Override
	    public List<Usuario> list() {
	    	Conexao c = Conexao.getInstancia();

	    	Connection con = c.conectar();

	    	String query = "SELECT * FROM usuários";

	    	usuarios.clear();
	    	try {
				PreparedStatement ps = con.prepareStatement(query);

				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					
					int email = rs.getInt("email");

					String Nome = rs.getString("nome");
					Usuario u = new Usuario();
					u.setEmail(email);
					u.setNome(nome);

					usuarios.add(u);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			c.fecharConexao();
			return usuarios;
		}
	    	
	    	public boolean inserir(Usuario u) {

	    		Conexao c = Conexao.getInstacia();
	    		Connection con = c.conectar();

	    		String query = "INSERT INTO usuarios " + "(email, nome) " + "VALUES (?, ?)";

	    		try {
	    			PreparedStatement ps = con.prepareStatement(query);

	    			ps.setInt(1, p.getEmail());
	    			ps.setString(2, p.getNome());

	    			ps.executeUpdate();

	    			c.fecharConexao();

	    			return true;
	    		} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	    		return false;
	    	
	    }
	private Usuario usuarioCadastrado;
	
	// Singleton
	
	public Usuario getUsuarioCadastrado() {
		return usuarioCadastrado;
	}

	public void setUsuarioCadastrado(Usuario usuarioCadastrado) {
		this.usuarioCadastrado = usuarioCadastrado;
	}

	private static UsuarioDAO instance = null;
	
	private UsuarioDAO() {
		this.usuarioCadastrado = new Usuario("João", "");
    }

    public static UsuarioDAO getInstance() {
        if (instance == null) {
            instance = new UsuarioDAO();
        }
        return instance;
    }
}