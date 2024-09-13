import java.io.Serializable;

public class Despesa implements Serializable {
    private String descricao;
    private double valor;
    private String dataVencimento;
    private boolean pago;
    private String categoria;

    public Despesa(String descricao, double valor, String dataVencimento, String categoria) {
        this.descricao = descricao;
        this.valor = valor;
        this.dataVencimento = dataVencimento;
        this.categoria = categoria;
        this.pago = false;
    }

    public String getDescricao() {
        return descricao;
    }

    public boolean isPago() {
        return pago;
    }

    public void setPago(boolean pago) {
        this.pago = pago;
    }

    @Override
    public String toString() {
        return "Descrição: " + descricao + ", Valor: " + valor + ", Data de Vencimento: " + dataVencimento + ", Pago: " + (pago ? "Sim" : "Não") + ", Categoria: " + categoria;
    }
}
