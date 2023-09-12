package controle;


import modelo.Usuario;

public class UsuarioDAO {
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