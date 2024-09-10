package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import excecoes.ExcecaoPersonalizada;
import model.Aluno;
import model.Usuario;
import util.Conexao;
import util.Configurador;

public class AlunoDAO {

	private static Configurador configurador = new Configurador();

	public static Aluno inserir(String login, String senha, int tipo, int matriculaAluno, String nome, String turno,
			String turma) {
		Aluno aluno = null;

		Usuario usuario = UsuarioDAO.inserir(login, senha, tipo);

		if (usuario != null) {

			Conexao conexao = new Conexao(configurador.getUrl(), configurador.getDriver(), configurador.getLogin(),
					configurador.getSenha());
			Connection con = conexao.obterConexao();

			String sql = "insert into Aluno(id,matriculaAluno,nome,turno,turma) values (?,?,?,?,?)";
			String sql2 = "select * from MostrarAlunos where matriculaAluno=?";

			try {

				PreparedStatement comando2 = con.prepareStatement(sql2);
				comando2.setInt(1, matriculaAluno);

				ResultSet rs2 = comando2.executeQuery();

				if (rs2.next()) {
					
					UsuarioDAO.excluir(usuario.getId());
					throw new ExcecaoPersonalizada("A matricula informada já existe");
				}

				PreparedStatement comando = con.prepareStatement(sql);
				comando.setInt(1, usuario.getId());
				comando.setInt(2, matriculaAluno);
				comando.setString(3, nome);
				comando.setString(4, turno);
				comando.setString(5, turma);

				if (comando.executeUpdate() > 0) {

					aluno = new Aluno(usuario.getId(), login, senha, tipo, matriculaAluno, nome, turno, turma);
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

		return aluno;
	}

	public static List<Aluno> buscarAlunos() {
		List<Aluno> alunos = new LinkedList<Aluno>();

		Conexao conexao = new Conexao(configurador.getUrl(), configurador.getDriver(), configurador.getLogin(),
				configurador.getSenha());
		Connection con = conexao.obterConexao();

		String sql = "select * from MostrarAlunos";

		try {
			Statement comando = con.createStatement();

			ResultSet rs = comando.executeQuery(sql);

			while (rs.next()) {

				int id = rs.getInt("id");
				String nome = rs.getString("nome");
				int matriculaAluno = rs.getInt("matriculaAluno");
				String turma = rs.getString("turma");
				String turno = rs.getString("turno");
				String login = rs.getString("login");
				int tipo = rs.getInt("tipo");

				String senha = "********";

				Aluno aluno = new Aluno(id, login, senha, tipo, matriculaAluno, nome, turno, turma);

				alunos.add(aluno);
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

		return alunos;
	}

	public static boolean excluir(int id) {
		boolean ok = false;

		Conexao conexao = new Conexao(configurador.getUrl(), configurador.getDriver(), configurador.getLogin(),
				configurador.getSenha());
		Connection con = conexao.obterConexao();

		String sql1 = "delete from Aluno where id=?";
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

	public static Aluno buscarPorId(int id) {
		Aluno aluno = null;

		Conexao conexao = new Conexao(configurador.getUrl(), configurador.getDriver(), configurador.getLogin(),
				configurador.getSenha());
		Connection con = conexao.obterConexao();

		String sql = "select * from MostrarAlunos where id=?";

		try {
			PreparedStatement comando = con.prepareStatement(sql);
			comando.setInt(1, id);

			ResultSet rs = comando.executeQuery();

			if (rs.next()) {

				int Id = rs.getInt("id");
				String nome = rs.getString("nome");
				int matriculaAluno = rs.getInt("matriculaAluno");
				String turma = rs.getString("turma");
				String turno = rs.getString("turno");
				String login = rs.getString("login");
				int tipo = rs.getInt("tipo");

				String senha = "********";

				aluno = new Aluno(Id, login, senha, tipo, matriculaAluno, nome, turno, turma);

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
		return aluno;
	}

	public static Aluno buscarPorMatricula(int matricula) {
		Aluno aluno = null;

		Conexao conexao = new Conexao(configurador.getUrl(), configurador.getDriver(), configurador.getLogin(),
				configurador.getSenha());
		Connection con = conexao.obterConexao();

		String sql = "select * from MostrarAlunos where matriculaAluno=?";

		try {
			PreparedStatement comando = con.prepareStatement(sql);
			comando.setInt(1, matricula);

			ResultSet rs = comando.executeQuery();

			if (rs.next()) {

				int id = rs.getInt("id");
				String nome = rs.getString("nome");
				int matriculaAluno = rs.getInt("matriculaAluno");
				String turma = rs.getString("turma");
				String turno = rs.getString("turno");
				String login = rs.getString("login");
				int tipo = rs.getInt("tipo");

				String senha = "********";

				aluno = new Aluno(id, login, senha, tipo, matriculaAluno, nome, turno, turma);

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
		return aluno;
	}

	public static boolean atualizar(int id, String nome, String turma, String turno, String login, String senha,
			int tipo) {
		boolean ok = false;

		Conexao conexao = new Conexao(configurador.getUrl(), configurador.getDriver(), configurador.getLogin(),
				configurador.getSenha());
		Connection con = conexao.obterConexao();

		String sql1 = "update Usuario set login=?, senha=?, tipo=? where id=?";

		String sql2 = "update Aluno set nome=?, turma=?, turno=? where id=?";

		try {
			PreparedStatement comando1 = con.prepareStatement(sql1);

			comando1.setString(1, login);
			comando1.setString(2, senha);
			comando1.setInt(3, tipo);
			comando1.setInt(4, id);

			if (comando1.executeUpdate() > 0) {

				PreparedStatement comando2 = con.prepareStatement(sql2);
				comando2.setString(1, nome);
				comando2.setString(2, turma);
				comando2.setString(3, turno);
				comando2.setInt(4, id);

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
