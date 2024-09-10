package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import excecoes.ExcecaoPersonalizada;
import model.Professor;
import model.Usuario;
import util.Conexao;
import util.Configurador;

public class ProfessorDAO {

	private static Configurador configurador = new Configurador();

	public static Professor inserir(String login, String senha, int tipo, int matriculaProfessor, String nome,
			String especialidade) {
		Professor professor = null;

		Usuario usuario = UsuarioDAO.inserir(login, senha, tipo);

		if (usuario != null) {

			Conexao conexao = new Conexao(configurador.getUrl(), configurador.getDriver(), configurador.getLogin(),
					configurador.getSenha());
			Connection con = conexao.obterConexao();

			String sql = "insert into Professor(id,matriculaProfessor,nome,especialidade) values (?,?,?,?)";

			String sql2 = "select * from MostrarProfessor where matriculaProfessor=?";
			try {

				PreparedStatement comando2 = con.prepareStatement(sql2);
				comando2.setInt(1, matriculaProfessor);

				ResultSet rs = comando2.executeQuery();

				if (rs.next()) {
					
					UsuarioDAO.excluir(usuario.getId());
					throw new ExcecaoPersonalizada("A matricula informada já existe");
				}

				PreparedStatement comando = con.prepareStatement(sql);
				comando.setInt(1, usuario.getId());
				comando.setInt(2, matriculaProfessor);
				comando.setString(3, nome);
				comando.setString(4, especialidade);

				if (comando.executeUpdate() > 0) {

					professor = new Professor(usuario.getId(), login, senha, tipo, matriculaProfessor, nome,
							especialidade);
				}

				comando.close();
				con.close();

			} catch (SQLException e) {
				System.out.println("Erro ao iserir no Banco de Dados.");
				System.out.println("Verifique sua instrução SQL.");
				System.out.println("Mensagem de erro: " + e.getMessage());
				e.printStackTrace();
			} catch (ExcecaoPersonalizada e) {
				System.out.println(e.getMessage());
			}
		}

		return professor;
	}

	public static List<Professor> buscarProfessores() {
		List<Professor> professor = new LinkedList<Professor>();

		Conexao conexao = new Conexao(configurador.getUrl(), configurador.getDriver(), configurador.getLogin(),
				configurador.getSenha());
		Connection con = conexao.obterConexao();

		String sql = "select * from MostrarProfessor";

		try {
			Statement comando = con.createStatement();

			ResultSet rs = comando.executeQuery(sql);

			while (rs.next()) {

				int id = rs.getInt("id");
				String nome = rs.getString("nome");
				int matriculaProfessor = rs.getInt("matriculaProfessor");
				String especialidade = rs.getString("especialidade");
				String login = rs.getString("login");
				int tipo = rs.getInt("tipo");

				String senha = "********";

				Professor prof = new Professor(id, login, senha, tipo, matriculaProfessor, nome, especialidade);

				professor.add(prof);
			}

			rs.close();
			comando.close();
			con.close();

		} catch (SQLException e) {
			System.out.println("Erro ao buscar no Banco de Dados.");
			System.out.println("Verifique sua instrução SQL.");
			System.out.println("Mensagem de erro: " + e.getMessage());
			e.printStackTrace();
		}

		return professor;
	}

	public static boolean excluir(int id) {
		boolean ok = false;

		Conexao conexao = new Conexao(configurador.getUrl(), configurador.getDriver(), configurador.getLogin(),
				configurador.getSenha());
		Connection con = conexao.obterConexao();

		String sql1 = "delete from Professor where id=?";
		String sql2 = "delete from Usuario where id=?";

		try {
			PreparedStatement comando1 = con.prepareStatement(sql1);
			comando1.setInt(1, id);

			if (comando1.executeUpdate() > 0) {
				PreparedStatement comando2 = con.prepareStatement(sql2);
				comando2.setInt(1, id);

				if (comando2.executeUpdate() > 0) {
					ok = true;
					comando2.close();
				} else {
					comando2.close();
				}
			}

			comando1.close();
			con.close();

		} catch (SQLException e) {
			System.out.println("Erro ao excluir no Banco de Dados.");
			System.out.println("Verifique sua instrução SQL.");
			System.out.println("Mensagem de erro: " + e.getMessage());
			e.printStackTrace();
		}
		return ok;
	}

	public static Professor buscarPorId(int id) {
		Professor professor = null;

		Conexao conexao = new Conexao(configurador.getUrl(), configurador.getDriver(), configurador.getLogin(),
				configurador.getSenha());
		Connection con = conexao.obterConexao();

		String sql = "select * from MostrarProfessor where id=?";

		try {
			PreparedStatement comando = con.prepareStatement(sql);
			comando.setInt(1, id);

			ResultSet rs = comando.executeQuery();

			if (rs.next()) {

				int Id = rs.getInt("id");
				String nome = rs.getString("nome");
				int matriculaProfessor = rs.getInt("matriculaProfessor");
				String especialidade = rs.getString("especialidade");
				String login = rs.getString("login");
				int tipo = rs.getInt("tipo");

				String senha = "********";

				professor = new Professor(Id, login, senha, tipo, matriculaProfessor, nome, especialidade);

				rs.close();
				comando.close();
				con.close();

			}
		} catch (SQLException e) {
			System.out.println("Erro ao buscar por ID no Banco de Dados.");
			System.out.println("Verifique sua instrução SQL.");
			System.out.println("Mensagem de erro: " + e.getMessage());
			e.printStackTrace();
		}
		return professor;
	}

	public static Professor buscarPorMatricula(int matricula) {
		Professor professor = null;

		Conexao conexao = new Conexao(configurador.getUrl(), configurador.getDriver(), configurador.getLogin(),
				configurador.getSenha());
		Connection con = conexao.obterConexao();

		String sql = "select * from MostrarProfessor where matriculaProfessor=?";

		try {
			PreparedStatement comando = con.prepareStatement(sql);
			comando.setInt(1, matricula);

			ResultSet rs = comando.executeQuery();

			if (rs.next()) {

				int id = rs.getInt("id");
				String nome = rs.getString("nome");
				int matriculaProfessor = rs.getInt("matriculaProfessor");
				String especialidade = rs.getString("especialidade");
				String login = rs.getString("login");
				int tipo = rs.getInt("tipo");

				String senha = "********";

				professor = new Professor(id, login, senha, tipo, matriculaProfessor, nome, especialidade);

				rs.close();
				comando.close();
				con.close();

			}
		} catch (SQLException e) {
			System.out.println("Erro ao buscar por ID no Banco de Dados.");
			System.out.println("Verifique sua instrução SQL.");
			System.out.println("Mensagem de erro: " + e.getMessage());
			e.printStackTrace();
		}
		return professor;
	}

	public static boolean atualizar(int id, String nome, String especialidade, String login, String senha, int tipo) {
		boolean ok = false;

		Conexao conexao = new Conexao(configurador.getUrl(), configurador.getDriver(), configurador.getLogin(),
				configurador.getSenha());
		Connection con = conexao.obterConexao();

		String sql1 = "update Usuario set login=?, senha=?, tipo=? where id=?";

		String sql2 = "update Professor set nome=?, especialidade=? where id=?";

		try {
			PreparedStatement comando1 = con.prepareStatement(sql1);

			comando1.setString(1, login);
			comando1.setString(2, senha);
			comando1.setInt(3, tipo);
			comando1.setInt(4, id);

			if (comando1.executeUpdate() > 0) {

				PreparedStatement comando2 = con.prepareStatement(sql2);
				comando2.setString(1, nome);
				comando2.setString(2, especialidade);
				comando2.setInt(3, id);

				if (comando2.executeUpdate() > 0) {
					ok = true;
					comando2.close();
				} else {
					comando2.close();
				}

			}
			comando1.close();
			con.close();

		} catch (SQLException e) {
			System.out.println("Erro ao atualizar no Banco de Dados.");
			System.out.println("Verifique sua instrução SQL.");
			System.out.println("Mensagem de erro: " + e.getMessage());
			e.printStackTrace();
		}
		return ok;

	}

}
