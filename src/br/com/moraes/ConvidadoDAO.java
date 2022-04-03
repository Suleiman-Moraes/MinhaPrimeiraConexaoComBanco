package br.com.moraes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class ConvidadoDAO {

	public List<Convidado> listar() throws Exception {
		Statement statement = MySqlAccess.getConnection().createStatement();
		ResultSet resultSet = statement.executeQuery("SELECT id, nome, quantidade_acompanhantes AS qtd FROM convidado");
		List<Convidado> convidados = new LinkedList<>();
		while (resultSet.next()) {
			Convidado convidado = new Convidado();
			convidado.setId(resultSet.getLong("id"));
			convidado.setNome(resultSet.getString("nome"));
			convidado.setQuantidadeAcompanhantes(resultSet.getInt("qtd"));
			convidados.add(convidado);
		}
		return convidados;
	}

	public void atualizar(Convidado convidado) throws Exception {
		PreparedStatement preparedStatement = MySqlAccess.getConnection()
				.prepareStatement("UPDATE convidado SET nome = ?, quantidade_acompanhantes = ? WHERE id = ?");
		preparedStatement.setString(1, convidado.getNome());
		preparedStatement.setInt(2, convidado.getQuantidadeAcompanhantes());
		preparedStatement.setLong(3, convidado.getId());
		preparedStatement.executeUpdate();
	}

	public Convidado inserir(Convidado convidado) throws Exception {
		PreparedStatement preparedStatement = MySqlAccess.getConnection().prepareStatement(
				"INSERT INTO convidado (nome, quantidade_acompanhantes) VALUES (?, ?)",
				Statement.RETURN_GENERATED_KEYS);
		preparedStatement.setString(1, convidado.getNome());
		preparedStatement.setInt(2, convidado.getQuantidadeAcompanhantes());
		final int affectedRows = preparedStatement.executeUpdate();
		if (affectedRows == 0) {
			throw new SQLException("Falha ao criar usuário.");
		}
		try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
			if (generatedKeys.next()) {
				convidado.setId(generatedKeys.getLong(1));
			} else {
				throw new SQLException("Falha ao criar usuário.");
			}
		}
		return convidado;
	}

	public void apagar(Long id) throws Exception {
		PreparedStatement preparedStatement = MySqlAccess.getConnection()
				.prepareStatement("DELETE FROM convidado WHERE id = ?");
		preparedStatement.setLong(1, id);
		preparedStatement.executeUpdate();
	}
}
