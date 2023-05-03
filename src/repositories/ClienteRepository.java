package repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entities.Cliente;
import factories.ConnectionFactory;

public class ClienteRepository {

	public void create(Cliente cliente) throws Exception {

		Connection connection = ConnectionFactory.getConnection();

		PreparedStatement statement = connection
				.prepareStatement("INSERT INTO TB_CLIENTE(nome, email, cpf, telefone) VALUES(?, ?, ?, ?)");

		statement.setString(1, cliente.getNome());
		statement.setString(2, cliente.getEmail());
		statement.setString(3, cliente.getCpf());
		statement.setString(4, cliente.getTelefone());
		statement.execute();

		// fechando a conexão:
		connection.close();
	}

	public void update(Cliente cliente) throws Exception {

		Connection connection = ConnectionFactory.getConnection();

		PreparedStatement statement = connection
				.prepareStatement("UPDATE tb_cliente SET nome=?, email=?, cpf=?, telefone=? WHERE idcliente=?");

		statement.setString(1, cliente.getNome());
		statement.setString(2, cliente.getEmail());
		statement.setString(3, cliente.getCpf());
		statement.setString(4, cliente.getTelefone());
		statement.setInt(5, cliente.getIdCliente());
		statement.execute();

		// fechando a conexão:
		connection.close();
	}

	public void delete(Cliente cliente) throws Exception {

		Connection connection = ConnectionFactory.getConnection();

		PreparedStatement statement = connection.prepareStatement("DELETE FROM tb_cliente WHERE IDCLIENTE =?");

		statement.setInt(1, cliente.getIdCliente());
		statement.execute();

		// fechando a conexão:
		connection.close();
	}

	public List<Cliente> findAll() throws Exception {

		Connection connection = ConnectionFactory.getConnection();

		PreparedStatement statement = connection.prepareStatement
		("SELECT * FROM TB_CLIENTE ORDER BY IDCLIENTE");

		ResultSet resultSet = statement.executeQuery();

		List<Cliente> lista = new ArrayList<Cliente>();

		Cliente cliente;

		while (resultSet.next()) {

			cliente = new Cliente();

			cliente.setIdCliente(resultSet.getInt("idcliente"));
			cliente.setNome(resultSet.getString("nome"));
			cliente.setEmail(resultSet.getString("email"));
			cliente.setTelefone(resultSet.getString("telefone"));
			cliente.setCpf(resultSet.getString("cpf"));

			lista.add(cliente);
		}

		// fechando a conexão:
		connection.close();

		return lista;
	}

	public Cliente findById(Integer idCliente) throws Exception {

		// abrindo conexão com o banco de dados
		Connection connection = ConnectionFactory.getConnection();

		// escrevendo uma instrução SQL para consultar 1 cliente através do id
		PreparedStatement statement = connection.prepareStatement
		("select * from tb_cliente where idcliente=?");
		
		statement.setInt(1, idCliente);
		ResultSet resultSet = statement.executeQuery();

		// criando um objeto Cliente ainda vazio..
		Cliente cliente = null;

		// verificando se algum registro foi encontrado
		if (resultSet.next()) {

			// instanciar o objeto Cliente
			cliente = new Cliente();

			cliente.setIdCliente(resultSet.getInt("idcliente"));
			cliente.setNome(resultSet.getString("nome"));
			cliente.setEmail(resultSet.getString("email"));
			cliente.setTelefone(resultSet.getString("telefone"));
			cliente.setCpf(resultSet.getString("cpf"));
		}

		connection.close(); // fechando a conexão com o banco de dados
		return cliente;
	}

}
