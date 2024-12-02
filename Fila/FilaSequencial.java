import java.util.Scanner;

public class FilaSequencial {

    static final int TAMANHO_MAX = 5;
    static int[] fila = new int[TAMANHO_MAX];
    static int inicio = 0;
    static int fim = 0;

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
        if (fim >= TAMANHO_MAX) {
            System.out.println("Fila cheia!");
            return;
        }

        System.out.print("Digite o valor a ser inserido: ");
        fila[fim] = scanner.nextInt();
        fim++;
        System.out.println("Valor inserido com sucesso!");
    }

    static void remocao() {
        if (inicio == fim) {
            System.out.println("Fila vazia!");
            return;
        }

        System.out.println("Removendo o elemento: " + fila[inicio]);
        for (int i = inicio; i < fim - 1; i++) {
            fila[i] = fila[i + 1];
        }
        fim--;
    }

    static void percurso() {
        if (inicio == fim) {
            System.out.println("Fila vazia!");
            return;
        }

        System.out.println("Elementos na fila:");
        for (int i = inicio; i < fim; i++) {
            System.out.print(fila[i] + " ");
        }
        System.out.println();
    }

    static void busca(Scanner scanner) {
        if (inicio == fim) {
            System.out.println("Fila vazia!");
            return;
        }

        System.out.print("Digite o valor a ser buscado: ");
        int valor = scanner.nextInt();
        boolean encontrado = false;

        for (int i = inicio; i < fim; i++) {
            if (fila[i] == valor) {
                System.out.println("Valor encontrado na posição: " + (i + 1));
                encontrado = true;
            }
        }

        if (!encontrado) {
            System.out.println("Valor não encontrado!");
        }
    }
}
