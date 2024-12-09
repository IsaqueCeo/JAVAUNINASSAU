import java.util.ArrayList;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

class Tarefa {
    int ordemServico;
    String descricao;
    String data;

    public Tarefa(int ordemServico, String descricao, String data) {
        this.ordemServico = ordemServico;
        this.descricao = descricao;
        this.data = data;
    }

    @Override
    public String toString() {
        return "(OS: " + ordemServico + ", \"" + descricao + "\", " + data + ")";
    }
}

class Acao {
    int codigo;
    String tipo;
    Tarefa tarefa;

    public Acao(int codigo, String tipo, Tarefa tarefa) {
        this.codigo = codigo;
        this.tipo = tipo;
        this.tarefa = tarefa;
    }

    @Override
    public String toString() {
        return "[" + codigo + ", '" + tipo + "', " + tarefa + "]";
    }
}

public class SistemaGerenciamentoTarefas {
    static List<Tarefa> listaTarefas = new ArrayList<>();
    static Stack<Acao> pilhaAcoes = new Stack<>();
    static int codigoAcao = 0;
    static int contadorOS = 1;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\nMenu:");
            System.out.println("1. Adicionar Tarefa");
            System.out.println("2. Remover Tarefa");
            System.out.println("3. Editar Tarefa");
            System.out.println("4. Exibir Lista de Tarefas");
            System.out.println("5. Exibir Pilha de Ações");
            System.out.println("6. Desfazer Última Ação");
            System.out.println("7. Sair");
            System.out.print("Escolha uma opção: ");
            try {
                opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {
                    case 1:
                        adicionarTarefa(scanner);
                        break;
                    case 2:
                        removerTarefa(scanner);
                        break;
                    case 3:
                        editarTarefa(scanner);
                        break;
                    case 4:
                        exibirLista();
                        break;
                    case 5:
                        exibirPilha();
                        break;
                    case 6:
                        desfazerAcao();
                        break;
                    case 7:
                        System.out.println("Encerrando o programa...");
                        break;
                    default:
                        System.out.println("Opção inválida! Tente novamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida! Por favor, insira um número.");
                scanner.nextLine(); // Limpar buffer
                opcao = 0; // Resetar para evitar saída do loop
            }
        } while (opcao != 7);

        scanner.close();
    }

    static void adicionarTarefa(Scanner scanner) {
        int os = contadorOS++;
        System.out.println("OS: " + os);
        System.out.print("Digite a descrição da tarefa: ");
        String descricao = scanner.nextLine();
        System.out.print("Digite a data (dd/mm/aaaa): ");
        String data = scanner.nextLine();

        Tarefa novaTarefa = new Tarefa(os, descricao, data);
        listaTarefas.add(novaTarefa);
        listaTarefas.sort(Comparator.comparingInt(t -> t.ordemServico)); // Ordenar após adição
        pilhaAcoes.push(new Acao(++codigoAcao, "Adicionado", novaTarefa));
        System.out.println("Tarefa adicionada com sucesso!");
        exibirLista();
    }

    static void removerTarefa(Scanner scanner) {
        if (listaTarefas.isEmpty()) {
            System.out.println("A lista de tarefas está vazia!");
            return;
        }

        System.out.print("Digite o número da OS da tarefa a ser removida: ");
        int os;
        try {
            os = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida! A OS deve ser um número.");
            scanner.nextLine();
            return;
        }

        Tarefa tarefaRemovida = listaTarefas.stream()
                .filter(t -> t.ordemServico == os)
                .findFirst()
                .orElse(null);

        if (tarefaRemovida != null) {
            listaTarefas.remove(tarefaRemovida);
            pilhaAcoes.push(new Acao(++codigoAcao, "Removido", tarefaRemovida));
            System.out.println("Tarefa removida com sucesso!");
            exibirLista();
        } else {
            System.out.println("Tarefa com OS " + os + " não encontrada!");
        }
    }

    static void editarTarefa(Scanner scanner) {
        if (listaTarefas.isEmpty()) {
            System.out.println("A lista de tarefas está vazia!");
            return;
        }

        System.out.print("Digite o número da OS da tarefa a ser editada: ");
        int os;
        try {
            os = scanner.nextInt();
            scanner.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida! A OS deve ser um número.");
            scanner.nextLine();
            return;
        }

        Tarefa tarefaEditada = listaTarefas.stream()
                .filter(t -> t.ordemServico == os)
                .findFirst()
                .orElse(null);

        if (tarefaEditada != null) {
            System.out.print("Digite a nova descrição da tarefa: ");
            String novaDescricao = scanner.nextLine();
            System.out.print("Digite a nova data (dd/mm/aaaa): ");
            String novaData = scanner.nextLine();

            tarefaEditada.descricao = novaDescricao;
            tarefaEditada.data = novaData;
            pilhaAcoes.push(new Acao(++codigoAcao, "Editado", tarefaEditada));
            System.out.println("Tarefa editada com sucesso!");
            exibirLista();
        } else {
            System.out.println("Tarefa com OS " + os + " não encontrada!");
        }
    }

    static void exibirLista() {
        if (listaTarefas.isEmpty()) {
            System.out.println("A lista de tarefas está vazia!");
        } else {
            System.out.println("Lista de Tarefas:");
            listaTarefas.forEach(System.out::println);
        }
    }

    static void exibirPilha() {
        if (pilhaAcoes.isEmpty()) {
            System.out.println("Nenhuma ação registrada na pilha.");
        } else {
            System.out.println("Histórico de Ações:");
            pilhaAcoes.forEach(System.out::println);
        }
    }

    static void desfazerAcao() {
        if (pilhaAcoes.isEmpty()) {
            System.out.println("Nenhuma ação para desfazer!");
            return;
        }

        Acao ultimaAcao = pilhaAcoes.pop();
        if (ultimaAcao.tipo.equals("Adicionado")) {
            listaTarefas.remove(ultimaAcao.tarefa);
            System.out.println("A última adição foi desfeita!");
        } else if (ultimaAcao.tipo.equals("Removido")) {
            listaTarefas.add(ultimaAcao.tarefa);
            listaTarefas.sort(Comparator.comparingInt(t -> t.ordemServico));
            System.out.println("A última remoção foi desfeita!");
        } else if (ultimaAcao.tipo.equals("Editado")) {
            System.out.println("A edição não pode ser desfeita diretamente.");
        }

        exibirLista();
    }
}