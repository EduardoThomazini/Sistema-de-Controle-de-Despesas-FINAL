import java.io.*;
import java.util.ArrayList;

public class SistemaDespesas {
    private ArrayList<Despesa> despesas;

    public SistemaDespesas() {
        this.despesas = new ArrayList<>();
    }

    public void entrarDespesa(Despesa despesa) {
        despesas.add(despesa);
    }

    public void anotarPagamento(String descricao) {
        for (Despesa despesa : despesas) {
            if (despesa.getDescricao().equalsIgnoreCase(descricao)) {
                despesa.setPago(true);
                return;
            }
        }
        System.out.println("Despesa não encontrada.");
    }

    public void listarDespesas(boolean pagas) {
        for (Despesa despesa : despesas) {
            if (despesa.isPago() == pagas) {
                System.out.println(despesa);
            }
        }
    }

    // Método para carregar despesas de um arquivo
    public void carregarDespesas() throws IOException, ClassNotFoundException {
        File arquivo = new File("despesas.dat");
        if (arquivo.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
                despesas = (ArrayList<Despesa>) ois.readObject();
            }
        } else {
            despesas = new ArrayList<>(); // Inicializar a lista se o arquivo não existir
        }
    }

    // Método para salvar despesas em um arquivo
    public void salvarDespesas() throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("despesas.dat"))) {
            oos.writeObject(despesas);
        }
    }
}
