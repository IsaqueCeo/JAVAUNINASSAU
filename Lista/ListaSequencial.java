import java.util.Scanner;

public class ListaSequencial {

    static final int TAMANHO_MAX = 5;
    static int[] lista = new int[TAMANHO_MAX];
    static int topo = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\nMenu:");
            System.out.println("1. Cadastro");
            System.out.println("2. Remoção");
            System.out.println("3. Percurso");
            System.out.println("4. Busca");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    cadastro(scanner);
                    break;
                case 2:
                    remocao(scanner);
                    break;
                case 3:
                    percurso();
                    break;
                case 4:
                    busca(scanner);
                    break;
                case 5:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 5);

        scanner.close();
    }

    static void cadastro(Scanner scanner) {
        if (topo >= TAMANHO_MAX) {
            System.out.println("Lista cheia!");
            return;
        }

        System.out.print("Digite o valor a ser inserido: ");
        int valor = scanner.nextInt();
        lista[topo] = valor;
        topo++;
        System.out.println("Valor cadastrado com sucesso!");
    }

    static void remocao(Scanner scanner) {
        if (topo == 0) {
            System.out.println("Lista vazia!");
            return;
        }

        System.out.print("Digite a posição (1 a " + topo + ") para remover: ");
        int posicao = scanner.nextInt();

        if (posicao < 1 || posicao > topo) {
            System.out.println("Posição inválida!");
            return;
        }

        for (int i = posicao - 1; i < topo - 1; i++) {
            lista[i] = lista[i + 1];
        }
        topo--;
        System.out.println("Valor removido com sucesso!");
    }

    static void percurso() {
        if (topo == 0) {
            System.out.println("Lista vazia!");
            return;
        }

        System.out.println("Elementos da lista:");
        for (int i = 0; i < topo; i++) {
            System.out.print(lista[i] + " ");
        }
        System.out.println();
    }

    static void busca(Scanner scanner) {
        if (topo == 0) {
            System.out.println("Lista vazia!");
            return;
        }

        System.out.print("Digite o valor a ser buscado: ");
        int valor = scanner.nextInt();
        boolean encontrado = false;

        for (int i = 0; i < topo; i++) {
            if (lista[i] == valor) {
                System.out.println("Valor encontrado na posição: " + (i + 1));
                encontrado = true;
            }
        }

        if (!encontrado) {
            System.out.println("Valor não encontrado!");
        }
    }
}
