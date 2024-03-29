package modelo;

import java.awt.Color;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

public class Evento {
	private int id;
	
	private String emoji = "";
	private Color color = Color.black;
	private String nome = "";
	private String local = "";
	private LocalDateTime dataInicio = LocalDateTime.now();
	private LocalDateTime dataFim = LocalDateTime.now();
	
	private ArrayList<Insumo> insumos = new ArrayList<>();
	private ArrayList<Usuario> usuarios = new ArrayList<>();

	public Evento() {
		
	}
	
	public Evento(String emoji, String nome, String local, LocalDateTime dataInicio, LocalDateTime dataFim) {
		super();
		this.id = 0;
		this.emoji = emoji;
		this.color = Evento.corEmoji(emoji);
		this.nome = nome;
		this.local = local;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getEmoji() {
		return emoji;
	}
	
	public void setEmoji(String emoji) {
		this.emoji = emoji;
		if(this.emoji == null) {
			this.emoji = "";
		}
		this.color = Evento.corEmoji(emoji);
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
	
	public LocalDateTime getDataInicio() {
		return dataInicio;
	}
	
	public void setDataInicio(LocalDateTime dataInicio) {
		this.dataInicio = dataInicio;
	}
	
	public LocalDateTime getDataFim() {
		return dataFim;
	}
	
	public void setDataFim(LocalDateTime dataFim) {
		this.dataFim = dataFim;
	}
	
	public ArrayList<Insumo> getInsumos() {
		return insumos;
	}
	
	public void setInsumos(ArrayList<Insumo> insumos) {
		this.insumos = insumos;
	}
	
	public ArrayList<Usuario> getUsuarios() {
		return usuarios;
	}
	
	public void setUsuarios(ArrayList<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	public String getLocal() {
		return local;
	}
	
	public void setLocal(String local) {
		this.local = local;
	}
	
	public String dataCompleta() {
		if (dataInicio != null) {
			String data = "";
			DateTimeFormatter df = DateTimeFormatter.ofPattern("dd 'de' MMM 'de' yyyy 'às' HH:mm", new Locale("pt", "BR"));
			data = df.format(dataInicio);
			
			if (dataFim != null) {
				data+= " até ";
				data+= df.format(dataFim);
			}
			return data;
		} else {
			return "Sem data definida";
		}
	}
	
	public String descricaoSimples() {
		String data = null;
		
		if (dataInicio != null) {
			DateTimeFormatter df = DateTimeFormatter.ofPattern("dd 'de' MMM 'de' yyyy 'às' HH:mm", new Locale("pt", "BR"));
			data = df.format(dataInicio);
		} else {
			data = "Sem data definida";
		}
		
		if (usuarios.size() > 0) {
			return data + " • " + String.valueOf(usuarios.size()) + " participantes";
		}
		return data;
	}
	
	public static Color corEmoji(String emoji) {
		if(emoji != null) {
			if (emoji.contentEquals("✨")) {
				return new Color(249, 236, 170);
			} else if  (emoji.contentEquals("🌱")) {
				return new Color(212, 229, 195);
			} else if (emoji.contentEquals("🎡")) {
				return new Color(216, 197, 243);
			} else if (emoji.contentEquals("🥶")) {
				return new Color(156, 179, 255);
		}
		}
		return new Color(200, 200, 200);
	}
}
