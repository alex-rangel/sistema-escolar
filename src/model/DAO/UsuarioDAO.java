package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import excecoes.ExcecaoPersonalizada;
import model.Usuario;
import util.Conexao;
import util.Configurador;

public class UsuarioDAO {

	private static Configurador configurador = new Configurador();

	public static Usuario inserir(String login, String senha, int tipo) {
		Usuario usuario = null;

		Conexao conexao = new Conexao(configurador.getUrl(), configurador.getDriver(), configurador.getLogin(),
				configurador.getSenha());
		Connection con = conexao.obterConexao();

		String sql = "insert into Usuario(login,senha,tipo) values (?,?,?)";
		String sql2 = "select * from Usuario where login=?";

		try {

			PreparedStatement comando2 = con.prepareStatement(sql2);
			comando2.setString(1, login);

			ResultSet rs2 = comando2.executeQuery();

			if (rs2.next()) {

				throw new ExcecaoPersonalizada("O login informado já existe");
			}

			PreparedStatement comando = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			comando.setString(1, login);
			comando.setString(2, senha);
			comando.setInt(3, tipo);

			if (comando.executeUpdate() > 0) {
				ResultSet rs = comando.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);

					usuario = new Usuario(id, login, senha, tipo) {

						@Override
						public void mostrar() {
							// TODO Auto-generated method stub

						}

					};
				}
				rs.close();
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

		return usuario;

	}

	public static boolean excluir(int id) {
		boolean ok = false;

		Conexao conexao = new Conexao(configurador.getUrl(), configurador.getDriver(), configurador.getLogin(),
				configurador.getSenha());
		Connection con = conexao.obterConexao();

		String sql = "delete from Usuario where id=?";

		try {
			PreparedStatement comando = con.prepareStatement(sql);
			comando.setInt(1, id);

			ok = comando.executeUpdate() > 0;

			comando.close();
			con.close();

		} catch (SQLException e) {
			System.out.println("Erro ao excluir no Banco de Dados.");
			System.out.println("Verifique sua instrução SQL.");
			System.out.println("Mensagem de erro: " + e.getMessage());
			e.printStackTrace();
		}
		return ok;
	}

}
