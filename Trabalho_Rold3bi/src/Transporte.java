public class Transporte extends Despesa {
    public Transporte(String descricao, double valor, String dataVencimento) {
        super(descricao, valor, dataVencimento, "Transporte");
    }

    @Override
    public double calcularMulta() {
        // Exemplo de cálculo de multa para Transporte
        return valor * 0.05; // 5% de multa
    }
}
