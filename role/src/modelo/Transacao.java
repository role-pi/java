package modelo;

import java.util.Date;

public class Transacao {
	private double valor;
    private Date data;
    private Usuario usuario;
    
	public Transacao(double valor, Date data, Usuario usuario) {
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
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
