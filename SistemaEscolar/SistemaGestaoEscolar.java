import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

class Pessoa {
    private int idPessoa;
    private String nome;
    private String endereco;
    private String telefone;
    private String cpf;

    public Pessoa(int idPessoa, String nome, String endereco, String telefone, String cpf) {
        this.idPessoa = idPessoa;
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.cpf = cpf;
    }

    public int getIdPessoa() {
        return idPessoa;
    }

    public String getNome() {
        return nome;
    }
}


public class SistemaGestaoEscolar {
    static List<Curso> cursos = new ArrayList<>();
    static List<Turma> turmas = new ArrayList<>();
    static List<Aluno> alunos = new ArrayList<>();
    static List<Responsavel> responsaveis = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao = 0;

        do {
            System.out.println("\nMenu:");
            System.out.println("1. Cadastrar Curso");
            System.out.println("2. Cadastrar Turma");
            System.out.println("3. Cadastrar Aluno");
            System.out.println("4. Listar Cursos");
            System.out.println("5. Listar Turmas");
            System.out.println("6. Listar Alunos");
            System.out.println("7. Consultar Responsável");
            System.out.println("8. Listar Turmas por Curso");
            System.out.println("9. Sair");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida! Por favor, insira um número inteiro.");
                scanner.nextLine();
                continue;
            }

            switch (opcao) {
                case 1 -> cadastrarCurso(scanner);
                case 2 -> cadastrarTurma(scanner);
                case 3 -> cadastrarAluno(scanner);
                case 4 -> listarCursos();
                case 5 -> listarTurmas();
                case 6 -> listarAlunos();
                case 7 -> consultarResponsavel(scanner);
                case 8 -> listarTurmasPorCurso(scanner);
                case 9 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida! Escolha um número entre 1 e 9.");
            }
        } while (opcao != 9);

        scanner.close();
    }

    static void cadastrarCurso(Scanner scanner) {
        System.out.print("Digite o ano do concurso: ");
        String concurso = scanner.next();
        System.out.print("Digite o nome do curso: ");
        String nome = scanner.next();
        cursos.add(new Curso(concurso, nome));
        System.out.println("Curso cadastrado com sucesso!");
    }

    static void cadastrarTurma(Scanner scanner) {
        System.out.print("Digite o nome da turma: ");
        String nome = scanner.next();
        System.out.print("Digite a carga horária: ");
        int cargaHoraria = 0;

        try {
            cargaHoraria = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida! A carga horária deve ser um número inteiro.");
            scanner.nextLine();
            return;
        }

        System.out.print("Digite o nome do curso associado: ");
        String nomeCurso = scanner.next();

        for (Curso curso : cursos) {
            if (curso.getNome().equalsIgnoreCase(nomeCurso)) {
                turmas.add(new Turma(turmas.size() + 1, nome, cargaHoraria, curso));
                System.out.println("Turma cadastrada com sucesso!");
                return;
            }
        }
        System.out.println("Curso não encontrado!");
    }

    static void cadastrarAluno(Scanner scanner) {
        System.out.print("Digite o nome do aluno: ");
        String nome = scanner.next();
        System.out.print("Digite a matrícula: ");
        String matricula = scanner.next();
        System.out.print("Digite a turma do aluno: ");
        String nomeTurma = scanner.next();

        for (Turma turma : turmas) {
            if (turma.getNome().equalsIgnoreCase(nomeTurma)) {
                alunos.add(new Aluno(alunos.size() + 1, nome, "Endereço", "Telefone", "CPF", matricula, "Data", null,
                        turma.getCurso()));
                System.out.println("Aluno cadastrado com sucesso!");
                return;
            }
        }
        System.out.println("Turma não encontrada!");
    }

    static void listarCursos() {
        if (cursos.isEmpty()) {
            System.out.println("Nenhum curso cadastrado.");
        } else {
            cursos.forEach(curso -> System.out.println("Curso: " + curso.getNome()));
        }
    }

    static void listarTurmas() {
        if (turmas.isEmpty()) {
            System.out.println("Nenhuma turma cadastrada.");
        } else {
            turmas.forEach(turma -> System.out.println("Turma: " + turma.getNome()));
        }
    }

    static void listarAlunos() {
        if (alunos.isEmpty()) {
            System.out.println("Nenhum aluno cadastrado.");
        } else {
            alunos.forEach(aluno -> System.out.println("Aluno: " + aluno.getNome()));
        }
    }

    static void consultarResponsavel(Scanner scanner) {
        System.out.print("Digite o nome do aluno: ");
        String nomeAluno = scanner.next();

        for (Aluno aluno : alunos) {
            if (aluno.getNome().equalsIgnoreCase(nomeAluno) && aluno.getResponsavel() != null) {
                Responsavel responsavel = aluno.getResponsavel();
                System.out.println("Responsável: " + responsavel.getNome());
                return;
            }
        }
        System.out.println("Aluno ou responsável não encontrado.");
    }

    static void listarTurmasPorCurso(Scanner scanner) {
        System.out.print("Digite o nome do curso: ");
        String nomeCurso = scanner.next();

        for (Turma turma : turmas) {
            if (turma.getCurso().getNome().equalsIgnoreCase(nomeCurso)) {
                System.out.println("Turma: " + turma.getNome());
            }
        }
    }
}
