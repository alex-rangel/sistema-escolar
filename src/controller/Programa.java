package controller;

import Services.AlunoService;
import Services.ProfessorService;
import util.Teclado;

public class Programa {

	private static final int CADASTRAR_ALUNO = 1;
	private static final int ATUALIZAR_ALUNO = 2;
	private static final int MOSTRAR_ALUNOS = 3;
	private static final int MOSTRAR_ALUNO_POR_ID = 4;
	private static final int MOSTRAR_ALUNO_POR_MATRICULA = 5;
	private static final int APAGAR_ALUNO = 6;
	private static final int CADASTRAR_PROFESSOR = 7;
	private static final int ATUALIZAR_PROFESSOR = 8;
	private static final int MOSTRAR_PROFESSORES = 9;
	private static final int MOSTRAR_PROFESSOR_POR_ID = 10;
	private static final int MOSTRAR_PROFESSOR_POR_MATRICULA = 11;
	private static final int APAGAR_PROFESSOR = 12;
	private static final int SAIR = 13;

	private void mostrarMenu() {
		System.out.println("=========================");
		System.out.println("==Sistema Escolar==");
		System.out.println("=========================");
		System.out.println("1 - Cadastrar aluno.");
		System.out.println("2 - Atualizar aluno.");
		System.out.println("3 - Mostrar alunos.");
		System.out.println("4 - Mostrar aluno por ID.");
		System.out.println("5 - Mostrar aluno por matricula.");
		System.out.println("6 - Apagar aluno.");
		System.out.println("7 - Cadastrar professor.");
		System.out.println("8 - Atualizar professor.");
		System.out.println("9 - Mostrar professor.");
		System.out.println("10 - Mostrar professor por ID.");
		System.out.println("11- Mostrar professor por matricula.");
		System.out.println("12- Apagar professor.");
		System.out.println("13- Sair");

	}

	public static void main(String[] args) {
		Programa programa = new Programa();
		AlunoService aluno = new AlunoService();
		ProfessorService professor = new ProfessorService();

		int opcao = SAIR;
		do {
			programa.mostrarMenu();
			opcao = Teclado.lerInt("Opção: ");
			switch (opcao) {
			case CADASTRAR_ALUNO:
				aluno.cadastrarAluno();
				break;

			case ATUALIZAR_ALUNO:
				aluno.atualizarAluno();
				break;

			case MOSTRAR_ALUNOS:
				aluno.mostrarAlunos();
				break;

			case MOSTRAR_ALUNO_POR_ID:
				aluno.buscarAlunoPorId();
				break;

			case MOSTRAR_ALUNO_POR_MATRICULA:
				aluno.buscarAlunoPorMatricula();
				break;

			case APAGAR_ALUNO:
				aluno.apagarAluno();
				break;

			case CADASTRAR_PROFESSOR:
				professor.cadastrarProfessor();
				break;

			case ATUALIZAR_PROFESSOR:
				professor.atualizarProfessor();
				break;

			case MOSTRAR_PROFESSORES:
				professor.mostrarProfessores();
				break;

			case MOSTRAR_PROFESSOR_POR_ID:
				professor.buscarProfessorPorId();
				break;

			case MOSTRAR_PROFESSOR_POR_MATRICULA:
				professor.buscarProfessorPorMatricula();
				break;

			case APAGAR_PROFESSOR:
				professor.apagarProfessor();
				break;

			case SAIR:
				System.out.println("Saindo do sistema...");
				break;
			default:
				System.out.println("Opção inválida.");
				break;
			}
			Teclado.lerTexto("\nPressione ENTER para continuar...\n");
		} while (opcao != SAIR);
		System.out.println("Sistema encerrado.");
	}

}
