package Services;

import java.util.List;

import model.Professor;
import model.DAO.ProfessorDAO;
import util.Teclado;

public class ProfessorService {
	public void cadastrarProfessor() {
		System.out.println("====================");
		System.out.println("=Cadastrar Professor=");
		System.out.println("====================");
		String nome = Teclado.lerTexto("Nome:");
		int matriculaProfessor = Teclado.lerInt("Matricula do professor:");
		String especialidade = Teclado.lerTexto("Especialidade:");
		String login = Teclado.lerTexto("Login:");
		String senha = Teclado.lerTexto("senha:");
		int tipo = Teclado.lerInt("Tipo:");

		Professor professor = ProfessorDAO.inserir(login, senha, tipo, matriculaProfessor, nome, especialidade);

		if (professor != null) {
			System.out.println("Professor inserido com sucesso.");
			System.out.println("Informações do professor inserido: ");
			System.out.println();
			professor.mostrar();
		} else {
			System.out.println("Erro ao inserir professor.");
		}
	}

	public void atualizarProfessor() {
		System.out.println("====================");
		System.out.println("=Atualizar Professor=");
		System.out.println("====================");
		System.out.println();
		int id = Teclado.lerInt("ID:");
		Professor professor = ProfessorDAO.buscarPorId(id);
		if (professor != null) {
			System.out.println("Nome atual: " + professor.getNome());
			String nome = Teclado.lerTexto("Informe o novo nome ou ENTER para manter o atual:");
			if (nome.equals("")) {
				nome = professor.getNome();
			}

			System.out.println("Especialidade atual: " + professor.getEspecialidade());
			String especialidade = Teclado.lerTexto("Informe a nova Especialidade ou ENTER para manter o atual:");
			if (especialidade.equals("")) {
				especialidade = professor.getEspecialidade();
			}

			System.out.println("Login atual: " + professor.getLogin());
			String login = Teclado.lerTexto("Informe o novo Login ou ENTER para manter o atual:");
			if (login.equals("")) {
				login = professor.getLogin();
			}

			System.out.println("Senha atual: " + professor.getSenha());
			String senha = Teclado.lerTexto("Informe a nova Senha ou ENTER para manter o atual:");
			if (senha.equals("")) {
				senha = professor.getSenha();
			}

			System.out.println("Tipo atual: " + professor.getTipo());
			String tipoString = Teclado.lerTexto("Informe o novo Tipo ou ENTER para manter o atual:");
			int tipo;
			if (tipoString.equals("")) {
				tipo = professor.getTipo();
			} else {
				tipo = Integer.parseInt(tipoString);
			}

			if (ProfessorDAO.atualizar(id, nome, especialidade, login, senha, tipo)) {
				System.out.println("Professor atualizado com sucesso.");
			} else {
				System.out.println("Erro ao atualizar professor.");
			}

		} else {
			System.out.println("Erro ao atualizar professor ou professor inexitente.");
		}
	}

	public void mostrarProfessores() {
		System.out.println("======================");
		System.out.println("=Visualizar Professor=");
		System.out.println("======================");
		List<Professor> professores = ProfessorDAO.buscarProfessores();
		if (!professores.isEmpty()) {
			System.out.println("Lista de Professores");
			System.out.println();
			for (Professor professor : professores ) {
				professor.mostrar();
				System.out.println();
			}
		} else {
			System.out.println("Nenhum registro encontrado.");
		}
	}

	public void buscarProfessorPorId() {
		System.out.println("========================");
		System.out.println("=Buscar Professor Por ID=");
		System.out.println("========================");
		int id = Teclado.lerInt("ID:");
		Professor professor = ProfessorDAO.buscarPorId(id);
		if (professor != null) {
			System.out.println("Informações do professor: ");
			System.out.println();
			professor.mostrar();
		} else {
			System.out.println("Erro ao buscar professor ou professor inexistente.");
		}
	}

	public void buscarProfessorPorMatricula() {
		System.out.println("========================");
		System.out.println("=Buscar Professor Por Matricula=");
		System.out.println("========================");
		int matricula = Teclado.lerInt("Matricula:");
		Professor professor = ProfessorDAO.buscarPorMatricula(matricula);
		if (professor != null) {
			System.out.println("Informações do professor: ");
			System.out.println();
			professor.mostrar();
		} else {
			System.out.println("Erro ao buscar professor ou professor inexistente.");
		}
	}

	public void apagarProfessor() {
		System.out.println("=================");
		System.out.println("=Apagar Professor=");
		System.out.println("=================");
		int id = Teclado.lerInt("ID:");
		Professor professor = ProfessorDAO.buscarPorId(id);
		if (professor != null) {
			System.out.println(professor);
			String conf = Teclado.lerTexto("Deseja realmente excluir o professor acima?(Sim ou Não)");
			if (conf.equalsIgnoreCase("sim")) {
				if (ProfessorDAO.excluir(id)) {
					System.out.println("Professor excluído com sucesso.");
				} else {
					System.out.println("Erro ao excluir professor.");
				}
			} else {
				System.out.println("Procedimento cancelado.");
			}
		} else {
			System.out.println("Erro ao excluir professor ou professor inexistente.");
		}

	}
}
