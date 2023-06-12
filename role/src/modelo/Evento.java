package modelo;

import java.awt.Color;
import java.awt.List;

public class Evento {
	private Integer id;
	private String emoji;
	private Color color;
	private String nome;
	private String dataInicio;
	private String dataFim;
	
	public Evento(String emoji, Color color, String nome, String dataInicio, String dataFim) {
		super();
		this.id = 0;
		this.emoji = emoji;
		this.color = color;
		this.nome = nome;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
	}

	public String getEmoji() {
		return emoji;
	}
	public void setEmoji(String emoji) {
		this.emoji = emoji;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(String dataInicio) {
		this.dataInicio = dataInicio;
	}
	public String getDataFim() {
		return dataFim;
	}
	public void setDataFim(String dataFim) {
		this.dataFim = dataFim;
	}

}
