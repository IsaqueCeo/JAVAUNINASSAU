import java.util.Scanner;

public class PilhaSequencial {

    static final int TAMANHO_MAX = 5;
    static int[] pilha = new int[TAMANHO_MAX];
    static int topo = -1;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\nMenu:");
            System.out.println("1. Cadastro (Push)");
            System.out.println("2. Remoção (Pop)");
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
                    remocao();
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
        if (topo == TAMANHO_MAX - 1) {
            System.out.println("Pilha cheia!");
            return;
        }

        System.out.print("Digite o valor a ser inserido: ");
        int valor = scanner.nextInt();
        topo++;
        pilha[topo] = valor;
        System.out.println("Valor inserido com sucesso!");
    }

    static void remocao() {
        if (topo == -1) {
            System.out.println("Pilha vazia!");
            return;
        }

        System.out.println("Removendo o elemento: " + pilha[topo]);
        topo--;
    }

    static void percurso() {
        if (topo == -1) {
            System.out.println("Pilha vazia!");
            return;
        }

        System.out.println("Elementos na pilha:");
        for (int i = topo; i >= 0; i--) {
            System.out.println(pilha[i]);
        }
    }

    static void busca(Scanner scanner) {
        if (topo == -1) {
            System.out.println("Pilha vazia!");
            return;
        }

        System.out.print("Digite o valor a ser buscado: ");
        int valor = scanner.nextInt();
        boolean encontrado = false;

        for (int i = topo; i >= 0; i--) {
            if (pilha[i] == valor) {
                System.out.println("Valor encontrado na posição (do topo): " + (topo - i + 1));
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            System.out.println("Valor não encontrado!");
        }
    }
}
