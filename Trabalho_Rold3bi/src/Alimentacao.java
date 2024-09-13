public class Alimentacao extends Despesa {
    public Alimentacao(String descricao, double valor, String dataVencimento) {
        super(descricao, valor, dataVencimento, "Alimentação");
    }

    @Override
    public double calcularMulta() {
        return valor * 0.02;
    }
}