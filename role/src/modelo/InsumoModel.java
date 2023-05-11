package modelo;

public class InsumoModel {
    private String tipo;
    private String nome;
    private String descricao;
    private double valor;
    private Evento eventos;

    public InsumoModel(String tipo, String nome, String descricao, double valor, Evento eventos) {
        this.tipo = tipo;
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
        this.eventos = eventos;
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

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Evento getEventos() {
        return eventos;
    }

    public void setEventos(Evento eventos) {
        this.eventos = eventos;
    }
}
