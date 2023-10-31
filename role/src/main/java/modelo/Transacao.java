package modelo;

import java.time.LocalDateTime;

public class Transacao {
	private int id;
	private double valor = 0;
    private LocalDateTime data = LocalDateTime.now();
    private Usuario usuario = new Usuario();
    
    public Transacao() {
    	
    }
    
	public Transacao(double valor, LocalDateTime data, Usuario usuario) {
		this.id = 0;
		this.valor = valor;
		this.data = data;
		this.setUsuario(usuario);
	}

	public int getId() {
		return id;
	}
    
	public void setId(int id) {
		this.id = id;
	}
	
    public double getValor() {
		return valor;
	}
    
	public void setValor(double valor) {
		this.valor = valor;
	}
	
	public LocalDateTime getData() {
		return data;
	}
	
	public void setData(LocalDateTime data) {
		this.data = data;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
