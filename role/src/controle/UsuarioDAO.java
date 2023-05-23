package controle;

import java.util.ArrayList;
import java.util.List;

import modelo.Usuario;

public class UsuarioDAO implements DAO<Usuario> {
    private ArrayList<Usuario> usuarios = new ArrayList<Usuario>();

	@Override
	public List<Usuario> list() {
		return usuarios;
	}
	
	public boolean insert(Usuario usuario) {
		if (usuario != null) {
			usuarios.add(usuario);
			return true;
		}
		return false;
	}
	
	public boolean update(Usuario usuario, String[] params) {
		if (usuario != null) {
			usuarios.add(usuario);
			return true;
		}
		return false;
	}
	
	public boolean delete(Usuario t) {
		return true;
	}
	
	// Singleton
	
	private static UsuarioDAO instance = null;
	
	private UsuarioDAO() {
        this.instance = new UsuarioDAO();
    }

    public static UsuarioDAO getInstance() {
        if (instance == null) {
            instance = new UsuarioDAO();
        }
        return instance;
    }
}