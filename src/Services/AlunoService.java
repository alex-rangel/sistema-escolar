package Services;

import java.util.List;

import model.Aluno;
import model.DAO.AlunoDAO;
import util.Teclado;

public class AlunoService {
	public void cadastrarAluno() {
		System.out.println("====================");
		System.out.println("=Cadastrar Aluno=");
		System.out.println("====================");
		String nome = Teclado.lerTexto("Nome:");
		int matriculaAluno = Teclado.lerInt("Matricula do aluno:");
		String turma = Teclado.lerTexto("Turma:");
		String turno = Teclado.lerTexto("Turno:");
		String login = Teclado.lerTexto("Login:");
		String senha = Teclado.lerTexto("senha:");
		int tipo = Teclado.lerInt("Tipo:");

		Aluno aluno = AlunoDAO.inserir(login, senha, tipo, matriculaAluno, nome, turno, turma);

		if (aluno != null) {
			System.out.println("Aluno inserido com sucesso.");
			System.out.println("Informações do aluno inserido: ");
			System.out.println();
			aluno.mostrar();
		} else {
			System.out.println("Erro ao inserir aluno.");
		}
	}

	public void atualizarAluno() {
		System.out.println("====================");
		System.out.println("=Atualizar Aluno=");
		System.out.println("====================");
		System.out.println();
		int id = Teclado.lerInt("ID:");
		Aluno aluno = AlunoDAO.buscarPorId(id);
		if (aluno != null) {
			System.out.println("Nome atual: " + aluno.getNome());
			String nome = Teclado.lerTexto("Informe o novo nome ou ENTER para manter o atual:");
			if (nome.equals("")) {
				nome = aluno.getNome();
			}

			System.out.println("Turma atual: " + aluno.getTurma());
			String turma = Teclado.lerTexto("Informe a nova Turma ou ENTER para manter o atual:");
			if (turma.equals("")) {
				turma = aluno.getTurma();
			}

			System.out.println("Turno atual: " + aluno.getTurno());
			String turno = Teclado.lerTexto("Informe o novo Turno ou ENTER para manter o atual:");
			if (turno.equals("")) {
				turno = aluno.getTurno();
			}

			System.out.println("Login atual: " + aluno.getLogin());
			String login = Teclado.lerTexto("Informe o novo Login ou ENTER para manter o atual:");
			if (login.equals("")) {
				login = aluno.getLogin();
			}

			System.out.println("Senha atual: " + aluno.getSenha());
			String senha = Teclado.lerTexto("Informe a nova Senha ou ENTER para manter o atual:");
			if (senha.equals("")) {
				senha = aluno.getSenha();
			}

			System.out.println("Tipo atual: " + aluno.getTipo());
			String tipoString = Teclado.lerTexto("Informe o novo Tipo ou ENTER para manter o atual:");
			int tipo;
			if (tipoString.equals("")) {
				tipo = aluno.getTipo();
			} else {
				tipo = Integer.parseInt(tipoString);
			}

			if (AlunoDAO.atualizar(id, nome, turma, turno, login, senha, tipo)) {
				System.out.println("Aluno atualizado com sucesso.");
			} else {
				System.out.println("Erro ao atualizar aluno.");
			}

		} else {
			System.out.println("Erro ao atualizar aluno ou aluno inexitente.");
		}
	}

	public void mostrarAlunos() {
		System.out.println("======================");
		System.out.println("=Visualizar Alunos=");
		System.out.println("======================");
		List<Aluno> alunos = AlunoDAO.buscarAlunos();
		if (!alunos.isEmpty()) {
			System.out.println("Lista de Alunos");
			System.out.println();
			for (Aluno aluno : alunos) {
				aluno.mostrar();
				System.out.println();
			}
		} else {
			System.out.println("Nenhum registro encontrado.");
		}
	}

	public void buscarAlunoPorId() {
		System.out.println("========================");
		System.out.println("=Buscar Aluno Por ID=");
		System.out.println("========================");
		int id = Teclado.lerInt("ID:");
		Aluno aluno = AlunoDAO.buscarPorId(id);
		if (aluno != null) {
			System.out.println("Informações do aluno: ");
			System.out.println();
			aluno.mostrar();
		} else {
			System.out.println("Erro ao buscar aluno ou aluno inexistente.");
		}
	}

	public void buscarAlunoPorMatricula() {
		System.out.println("========================");
		System.out.println("=Buscar Aluno Por Matricula=");
		System.out.println("========================");
		int matricula = Teclado.lerInt("Matricula:");
		Aluno aluno = AlunoDAO.buscarPorMatricula(matricula);
		if (aluno != null) {
			System.out.println("Informações do aluno: ");
			System.out.println();
			aluno.mostrar();
		} else {
			System.out.println("Erro ao buscar contato ou contato inexistente.");
		}
	}

	public void apagarAluno() {
		System.out.println("=================");
		System.out.println("=Apagar Aluno=");
		System.out.println("=================");
		int id = Teclado.lerInt("ID:");
		Aluno aluno = AlunoDAO.buscarPorId(id);
		if (aluno != null) {
			System.out.println(aluno);
			String conf = Teclado.lerTexto("Deseja realmente excluir o aluno acima?(Sim ou Não)");
			if (conf.equalsIgnoreCase("sim")) {
				if (AlunoDAO.excluir(id)) {
					System.out.println("Aluno excluído com sucesso.");
				} else {
					System.out.println("Erro ao excluir aluno.");
				}
			} else {
				System.out.println("Procedimento cancelado.");
			}
		} else {
			System.out.println("Erro ao excluir aluno ou aluno inexistente.");
		}

	}
}
