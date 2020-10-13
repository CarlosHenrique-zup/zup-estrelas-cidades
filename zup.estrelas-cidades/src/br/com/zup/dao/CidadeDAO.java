package br.com.zup.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import br.com.zup.dojo.Cidade;
import br.com.zup.factory.ConnectionFactory;

public class CidadeDAO {

	private Connection connection;
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	// new java.sql.Date(sdf.parse("22/04/1985").getTime()));

	public CidadeDAO() {
		this.connection = new ConnectionFactory().fabricaConexao();
	}

	public static void resultoDelete() {
		System.out.print("\n");
		System.out.println("=============================================");
		System.out.println("|       [CIDADE REMOVIDA COM SUCESSO!!!]    |");
		System.out.println("=============================================");
	}

	public static void resultadoBusca(List<Cidade> cidades) {
		System.out.print("\n");
		System.out.println("=============================================");
		System.out.println("|         [BUSCA FEITA COM SUCESSO!!!]      |");
		System.out.println("=============================================");
		System.out.print("\n");
	}

	/*
	 * Inserir cidades pelos dados informados
	 */
	public boolean insereCidade(Cidade cidade) throws SQLException {
		String sqlInsercao = "insert into cidade"
				+ "(cep,nome,nro_habitantes, capital, estado, renda_per_capita,data_fundacao)"
				+ "values(?,?,?,?,?,?,?);";

		PreparedStatement instrucao = connection.prepareStatement(sqlInsercao);
		instrucao.setInt(1, cidade.getId_cep());
		instrucao.setString(2, cidade.getNome());
		instrucao.setInt(3, cidade.getNumero_habitantes());
		instrucao.setBoolean(4, cidade.isCapital());
		instrucao.setString(5, cidade.getEstado());
		instrucao.setDouble(6, cidade.getRenda_per_capita());
		instrucao.setString(7, cidade.getData_fundacao().toString());

		instrucao.execute();
		instrucao.close();
		System.out.println("Conectado!");
		connection.close();

		return true;
	}

	/*
	 * 7 - Adicione ao DAO a funcionalidade de remover uma cidade passando como
	 * parâmetro seu número de cep.
	 */
	private static void whilePadraoCidade(ResultSet rs, List<Cidade> cidades) throws SQLException {

		while (rs.next()) {

			Cidade cidade = new Cidade();
			cidade.setId_cep(rs.getInt("cep"));
			cidade.setNome(rs.getString("nome"));
			cidade.setNumero_habitantes(rs.getInt("nro_habitantes"));
			cidade.setCapital(rs.getBoolean("capital"));
			cidade.setEstado(rs.getString("estado"));
			cidade.setRenda_per_capita(rs.getFloat("renda_per_capita"));
			cidade.setData_fundacao(rs.getString("data_fundacao"));

			cidades.add(cidade);
		}
	}

	/*
	 * 1 - Crie no seu CidadeDAO o método de listagem de cidades sem filtros.
	 */
	public List<Cidade> listaCidades() {
		List<Cidade> cidades = new ArrayList<Cidade>();

		String sqlConsulta = "select * from cidade";

		try {
			PreparedStatement imprimir = connection.prepareStatement(sqlConsulta);

			ResultSet rs = imprimir.executeQuery();

			/* 7- METODO IMPLEMENTADO */
			whilePadraoCidade(rs, cidades);

		} catch (SQLException e) {
			System.out.println("Erro na consulta!");
			e.printStackTrace();
		}

		return cidades;
	}

	/*
	 * 2 - Adicione ao DAO a funcionalidade de remover uma cidade passando como
	 * parâmetro seu número de cep.
	 */
	public boolean removeCidade(int cep) {

		String sqlRemove = "delete from cidade " + "where cep = ?";

		try {
			PreparedStatement imprime = connection.prepareStatement(sqlRemove);
			imprime.setInt(1, cep);

			imprime.execute();
			resultoDelete();
			imprime.close();

		} catch (SQLException e) {
			System.out.println("Erro na deleção!");
			System.out.println(e.getMessage());
			return false;
		}

		return true;
	}

	/*
	 * 3 - Crie no seu DAO um método que é capaz de retornar uma cidade com base no
	 * seu número de cep.
	 */
	public List<Cidade> selecaoPorCep(int cep) {

		List<Cidade> cidades = new ArrayList<>();

		String sqlSelecaoCep = "select * from cidade c " + "where c.cep = ?";

		try {
			PreparedStatement imprimir = connection.prepareStatement(sqlSelecaoCep);

			imprimir.setInt(1, cep);

			ResultSet rs = imprimir.executeQuery();

			/* 7- METODO IMPLEMENTADO */
			whilePadraoCidade(rs, cidades);

			resultadoBusca(cidades);
			System.out.println(cidades);
			imprimir.close();

		} catch (SQLException e) {
			System.out.println("Erro na seleção do CEP!");
			System.out.println(e.getMessage());
		}
		return cidades;
	}

	/*
	 * 4 - Crie um método que é capaz de pesquisar cidades cujos nomes se iniciam
	 * por um texto passado como parâmetro.
	 */
	public List<Cidade> listarCidadePorNome(String nome) {

		List<Cidade> cidades = new ArrayList<>();

		String sqlSelectNome = "select c.* from cidade c where c.nome like ?";

		try {
			PreparedStatement selecionar = connection.prepareStatement(sqlSelectNome);
			selecionar.setString(1, nome + "%");

			ResultSet rs = selecionar.executeQuery();

			/* METODO IMPLEMENTADO */
			whilePadraoCidade(rs, cidades);

			resultadoBusca(cidades);
			System.out.println(cidades);
			selecionar.close();
		} catch (SQLException e) {
			System.out.println("Erro ao listar o nome!");
			System.out.println(e.getMessage());
		}

		return cidades;
	}

	/*
	 * 5 - Crie um método que é capaz de listar cidades filtradas pela sigla de
	 * estado.
	 */
	public List<Cidade> listarCidadeSigla(String sigla) {

		List<Cidade> cidades = new ArrayList<>();

		String sqlSelectSigla = "select * from cidade c,estado e where e.sigla = c.estado AND e.sigla = ?";

		try {
			PreparedStatement selecionar = connection.prepareStatement(sqlSelectSigla);
			selecionar.setString(1, sigla);

			ResultSet rs = selecionar.executeQuery();

			/* 7- METODO IMPLEMENTADO */
			whilePadraoCidade(rs, cidades);

			resultadoBusca(cidades);

			System.out.println(cidades);

			selecionar.close();
		} catch (SQLException e) {
			System.out.println("Erro ao listar cidades!");
			System.out.println(e.getMessage());
		}

		return cidades;
	}

	/*
	 * 6 - Crie um método que recebe a sigla de um estado e retorna a quantidade de
	 * cidades daquele estado.
	 */
	public List<Cidade> quantidadeCidadesEstado(String sigla) {
		int contadorCidades = 0;
		List<Cidade> cidades = new ArrayList<>();

		String sqlQuantidade = "select count(*) from cidade c where c.estado = ?";

		try {
			PreparedStatement imprimir = connection.prepareStatement(sqlQuantidade);

			imprimir.setString(1, sigla);

			ResultSet rs = imprimir.executeQuery();

			while (rs.next()) {
				contadorCidades = rs.getInt(1);
			}

			resultadoBusca(cidades);
			System.out.println("QUANTIDADES DE CIDADES = " + contadorCidades);

			imprimir.close();

		} catch (SQLException e) {
			System.out.println("Erro na seleção de cidades!");
			System.out.println(e.getMessage());
		}

		return cidades;
	}

	/*
	 * 8 - Crie um método que filtra cidades pela coluna capital, onde o valor do
	 * filtro é passado como parâmetro.
	 */
	public List<Cidade> filtraCidades(boolean capital) {

		List<Cidade> cidades = new ArrayList<>();

		String sqlFiltra = "select * from cidade c where c.capital = ?";

		try {
			PreparedStatement imprimir = connection.prepareStatement(sqlFiltra);

			imprimir.setBoolean(1, capital);

			ResultSet rs = imprimir.executeQuery();

			/* 7 - METODO IMPLEMENTADO */
			whilePadraoCidade(rs, cidades);

			resultadoBusca(cidades);
			System.out.println(cidades);

			imprimir.close();
		} catch (SQLException e) {
			System.out.println("Erro");
			System.out.println(e.getMessage());
		}

		return cidades;
	}
}