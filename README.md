# Sistema de Gerenciamento de Despesas
   O projeto foi desenvolvido para facilitar o controle e gerenciamento de despesas de uma maneira simples e funcional. A ideia principal é que o usuário registre diferentes tipos de despesas, acompanhe quais foram pagas e que consiga salvar e carregar essas informações. Além disso, o sistema tem uma camada de segurança ao gerenciar usuários, com criptografia de senhas. 

Estrutura e principais componentes.

# Estrutura do Sistema

# 1. Classes de Despesas
   O sistema gira em torno da classe Despesa, que está ali para representar qualquer tipo de gasto. Essa classe é complementada por subclasses como Transporte e Alimentação, que permite estender o comportamento padrão com coisas proprias de cada tipo de despesa. Essa separação ajuda a manter o código organizado e fácil de expandir no futuro.

# Despesa.java: Classe abstrata que define a estrutura de uma despesa, incluindo descrição, valor e data de vencimento.
Atributos:
descricao (String): descrição da despesa.
valor (double): valor da despesa.
dataVencimento (String): data de vencimento da despesa.
categoria (String): categoria da despesa.
pago (boolean): indica se a despesa foi paga ou não.
Métodos:
getDescricao(): Retorna a descrição da despesa.
getValor(): Retorna o valor da despesa.
getDataVencimento(): Retorna a data de vencimento da despesa.
isPago(): Retorna se a despesa está paga.
setPago(boolean pago): Define o status de pagamento.
calcularMulta(): Método abstrato para calcular multas, a ser implementado nas subclasses.

# Transporte.java: Extende a classe Despesa e cria suas regras próprias, como o cálculo de multas para transporte.
Métodos:
calcularMulta(): Calcula a multa com base em 5% do valor da despesa de transporte.

# Alimentacao.java: Classe semelhante, que poderá ser expandida para incluir suas próprias regras no futuro.
Métodos:
calcularMulta(): Método futuro, pode ser ajustado conforme as necessidades específicas da categoria de alimentação.

# 2. Sistema de Gerenciamento de Despesas
   A classe SistemaDespesas é o coração do sistema. Ela gerencia o registro de novas despesas, mostra a lista de despesas pagas ou não, e oferece métodos para salvar e carregar as despesas de um arquivo para o salvamento dos dados. Permitindo que o usuário não perca suas anotações quando o sistema for finalizado.

SistemaDespesas.java
Atributos:

despesas (ArrayList<Despesa>): lista de todas as despesas registradas.
Métodos:

entrarDespesa(Despesa despesa): Adiciona uma nova despesa à lista.
anotarPagamento(String descricao): Marca a despesa com a descrição fornecida como paga.
listarDespesas(boolean pagas): Lista as despesas com base no status de pagamento.
carregarDespesas(): Carrega as despesas salvas de um arquivo despesas.dat.
salvarDespesas(): Salva as despesas em um arquivo despesas.dat para persistência de dados.

# Salvar e Carregar Despesas: Ao salvar, todas as despesas são gravadas em um arquivo de texto chamado despesas.dat. Isso permite que o sistema seja reiniciado sem perda de dados.

# 3. Usuários e Segurança
   O sistema também inclui a classe Usuario, que adiciona uma camada de segurança. Aqui as senhas dos usuários são criptografadas usando o algoritmo SHA-256, garantindo que, mesmo que o arquivo de usuários seja acessado, as senhas estarão escondidas.
   Verificação de Senha: Toda vez que um usuário tenta fazer login, a senha fornecida é criptografada e comparada com a senha armazenada de forma segura.
   Armazenamento de Usuários: Os usuários são salvos em um arquivo de texto (usuarios.txt) em um formato simples e facil de entender.

Usuario.java
Atributos:

login (String): nome de usuário.
senhaCriptografada (String): senha do usuário, criptografada com SHA-256.
Métodos:

Usuario(String login, String senha): Construtor que cria um usuário e criptografa sua senha.
criptografarSenha(String senha): Método privado que criptografa a senha usando SHA-256.
verificarSenha(String senha): Verifica se a senha fornecida corresponde à senha armazenada.
salvarUsuario(String login, String senha): Salva o login e a senha criptografada em um arquivo usuarios.txt.

# 4. Arquivos de Texto
   Os arquivos de texto despesas.txt, tipos_despesas.txt e usuarios.txt servem como fontes para armazenar e acessar dados do sistema, oferecendo uma interface de facil entendimento.
   despesas.txt: Lista de despesas que pode ser visualizada ou manipulada diretamente.
   tipos_despesas.txt: Serve como catálogo para os diferentes tipos de despesas que podem ser cadastradas.
   usuarios.txt: Contém o login e a senha criptografada dos usuários cadastrados no sistema.

# Como Usar o Sistema
   Registrar uma Despesa: A qualquer momento, uma nova despesa pode ser registrada, informando a descrição, valor e data de vencimento. O sistema também permite colocar as despesas em suas devidas categorias, como transporte ou alimentação.
   Anotar Pagamento: Quando uma despesa for paga, o sistema pode marcá-la como "paga", permitindo um controle fácil sobre os gastos pagos e não pagos.
   Listar Despesas: É possível listar tanto as despesas pagas quanto as não pagas. Isso dá ao usuário uma visão bem abrangente da situação financeira.
   Gerenciamento de Usuários: O sistema permite criar novos usuários e realizar o login de forma segura, com a criptografia de senhas.

# Conclusão
   Esse sistema foi feito com o intuito de ser simples de ser usado, e mesmo assim seguro e eficiente. Ele pode ser facilmente alterado para incluir novas funcionalidades. A divisão entre as classes e o uso de salvamento de dados garantem que o usuário tenha uma experiência confiável e boa.
