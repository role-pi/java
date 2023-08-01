package modelo;

import java.util.UUID;

public class Insumo {
    private String tipo;
    private String nome;
    private String descricao;
    private Transacao transacao;

    public Insumo(String tipo, String nome, String descricao, Transacao transacao) {
        this.tipo = tipo;
        this.nome = nome;
        this.descricao = descricao;
        this.setTransacao(transacao);
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
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
}
