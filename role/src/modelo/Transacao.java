package modelo;

public class Transacao {
	private double valor;
    private String data;
    private Usuario usuario;
    
	public Transacao(double valor, String data, Usuario usuario) {
		this.valor = valor;
		this.data = data;
		this.setUsuario(usuario);
	}

    public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
