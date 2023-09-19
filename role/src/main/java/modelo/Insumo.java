package modelo;

public class Insumo {
    private int id;
    private int tipo;
    private String nome;
    private String descricao;
    
    private Transacao transacao;
    private Evento evento;
    
    public Insumo() {
    	
    }

    public Insumo(int tipo, String nome, String descricao, Transacao transacao, Evento evento) {
    	this.id = 0;
        this.tipo = tipo;
        this.nome = nome;
        this.descricao = descricao;
        this.setEvento(evento);
        this.setTransacao(transacao);
    }
    
    public int getId() {
		return id;
	}
    
	public void setId(int id) {
		this.id = id;
	}
	
    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

	public Transacao getTransacao() {
		return transacao;
	}

	public void setTransacao(Transacao transacao) {
		this.transacao = transacao;
	}
	
	public static String[] allTipos() {
		return new String[] { "Ingresso", "Comida", "Bebida", "Transporte" };
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}
}
