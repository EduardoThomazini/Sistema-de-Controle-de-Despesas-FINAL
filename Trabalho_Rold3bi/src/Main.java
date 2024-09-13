import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;
import java.security.NoSuchAlgorithmException;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchAlgorithmException {
        Scanner scanner = new Scanner(System.in);
        boolean executando = true;
        SistemaDespesas sistema = new SistemaDespesas();
        sistema.carregarDespesas();
        ArrayList<String> categorias = carregarCategorias(); // Carregar categorias de um arquivo

        while (executando) {
            System.out.println("Menu Principal");
            System.out.println("1. Entrar Despesa");
            System.out.println("2. Anotar Pagamento");
            System.out.println("3. Listar Despesas");
            System.out.println("4. Gerenciar Tipos de Despesa");
            System.out.println("5. Gerenciar Usuários");
            System.out.println("6. Sair");
            System.out.print("Escolha uma opção: ");

            int escolha = obterInteiro(scanner);
            switch (escolha) {
                case 1:
                    entrarDespesa(scanner, sistema, categorias);
                    break;
                case 2:
                    anotarPagamento(scanner, sistema);
                    break;
                case 3:
                    listarDespesas(scanner, sistema);
                    break;
                case 4:
                    gerenciarTiposDespesa(scanner, categorias);
                    salvarCategorias(categorias); // Salva as categorias após alteração
                    break;
                case 5:
                    gerenciarUsuarios(scanner);
                    break;
                case 6:
                    sistema.salvarDespesas();
                    executando = false;
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, escolha uma opção válida.");
            }
        }
        scanner.close();
    }

    // Método para obter um número inteiro, com validação
    private static int obterInteiro(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.println("Entrada inválida. Por favor, insira um número.");
            scanner.next();
        }
        int valor = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha após a entrada
        return valor;
    }

    // Método para obter um valor double, com validação
    private static double obterValor(Scanner scanner) {
        while (!scanner.hasNextDouble()) {
            System.out.println("Valor inválido. Por favor, insira um número.");
            scanner.next();
        }
        double valor = scanner.nextDouble();
        scanner.nextLine(); // Consumir a quebra de linha após o valor
        return valor;
    }

    // Método para entrar uma nova despesa
    private static void entrarDespesa(Scanner scanner, SistemaDespesas sistema, ArrayList<String> categorias) throws IOException {
        System.out.println("Entrar nova despesa:");
        
        // Solicita a descrição da despesa
        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();  // Solicita a descrição primeiro, de forma isolada

        // Solicita o valor da despesa em uma linha separada
        System.out.print("Valor: ");
        double valor = obterValor(scanner);  // Solicita o valor após a descrição, garantindo que seja feito em linhas separadas

        // Solicita a data de vencimento da despesa
        System.out.print("Data de Vencimento (dd/mm/aaaa): ");
        String dataVencimento = scanner.nextLine();

        // Solicita a seleção da categoria
        System.out.println("Selecione a Categoria:");
        for (int i = 0; i < categorias.size(); i++) {
            System.out.println((i + 1) + ". " + categorias.get(i));
        }
        
        int categoriaEscolha = obterInteiro(scanner);
        while (categoriaEscolha < 1 || categoriaEscolha > categorias.size()) {
            System.out.println("Opção inválida. Por favor, escolha uma categoria válida.");
            categoriaEscolha = obterInteiro(scanner);
        }

        String categoria = categorias.get(categoriaEscolha - 1);
        
        Despesa despesa = new DespesaGenerica(descricao, valor, dataVencimento, categoria);
        sistema.entrarDespesa(despesa);
        
        System.out.println("Despesa registrada com sucesso!");
    }

    private static void anotarPagamento(Scanner scanner, SistemaDespesas sistema) throws IOException, ClassNotFoundException {
        System.out.print("Digite a descrição da despesa a ser paga: ");
        String descricao = scanner.nextLine();
        sistema.anotarPagamento(descricao);
        System.out.println("Pagamento anotado com sucesso!");
    }

    private static void listarDespesas(Scanner scanner, SistemaDespesas sistema) throws IOException, ClassNotFoundException {
        System.out.println("1. Listar despesas em aberto");
        System.out.println("2. Listar despesas pagas");
        System.out.print("Escolha uma opção: ");
        int opcao = obterInteiro(scanner);

        if (opcao == 1) {
            sistema.listarDespesas(false);
        } else if (opcao == 2) {
            sistema.listarDespesas(true);
        } else {
            System.out.println("Opção inválida.");
        }
    }

    private static void gerenciarTiposDespesa(Scanner scanner, ArrayList<String> categorias) {
        boolean gerenciar = true;
        while (gerenciar) {
            System.out.println("Gerenciar Tipos de Despesa:");
            System.out.println("1. Listar Categorias");
            System.out.println("2. Adicionar Categoria");
            System.out.println("3. Remover Categoria");
            System.out.println("4. Voltar");
            System.out.print("Escolha uma opção: ");
            int escolha = obterInteiro(scanner);

            switch (escolha) {
                case 1:
                    System.out.println("Categorias atuais:");
                    for (int i = 0; i < categorias.size(); i++) {
                        System.out.println((i + 1) + ". " + categorias.get(i));
                    }
                    break;
                case 2:
                    System.out.print("Digite o nome da nova categoria: ");
                    String novaCategoria = scanner.nextLine();
                    categorias.add(novaCategoria);
                    System.out.println("Categoria adicionada com sucesso!");
                    break;
                case 3:
                    System.out.println("Selecione a categoria para remover:");
                    for (int i = 0; i < categorias.size(); i++) {
                        System.out.println((i + 1) + ". " + categorias.get(i));
                    }
                    int remover = obterInteiro(scanner);
                    categorias.remove(remover - 1);
                    System.out.println("Categoria removida com sucesso!");
                    break;
                case 4:
                    gerenciar = false;
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, escolha uma opção válida.");
            }
        }
    }

    private static void gerenciarUsuarios(Scanner scanner) throws IOException, NoSuchAlgorithmException {
        System.out.println("Gerenciar Usuários:");
        System.out.print("Login: ");
        String login = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        Usuario.salvarUsuario(login, senha);
        System.out.println("Usuário cadastrado com sucesso!");
    }

    // Persistência de categorias em arquivo
    private static ArrayList<String> carregarCategorias() throws IOException {
        File arquivo = new File("categorias.txt");
        ArrayList<String> categorias = new ArrayList<>();
        if (arquivo.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
                String linha;
                while ((linha = reader.readLine()) != null) {
                    categorias.add(linha);
                }
            }
        } else {
            // Se o arquivo não existir, inicializar com algumas categorias padrão
            categorias.add("Transporte");
            categorias.add("Alimentação");
        }
        return categorias;
    }

    private static void salvarCategorias(ArrayList<String> categorias) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("categorias.txt"))) {
            for (String categoria : categorias) {
                writer.write(categoria);
                writer.newLine();
            }
        }
    }
}
