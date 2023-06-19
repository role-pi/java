package controle;


import modelo.Usuario;

public class UsuarioManager {
	private Usuario usuarioCadastrado;
	
	// Singleton
	
	public Usuario getUsuarioCadastrado() {
		return usuarioCadastrado;
	}

	public void setUsuarioCadastrado(Usuario usuarioCadastrado) {
		this.usuarioCadastrado = usuarioCadastrado;
	}

	private static UsuarioManager instance = null;
	
	private UsuarioManager() {
		this.usuarioCadastrado = new Usuario("João", "");
    }

    public static UsuarioManager getInstance() {
        if (instance == null) {
            instance = new UsuarioManager();
        }
        return instance;
    }
}