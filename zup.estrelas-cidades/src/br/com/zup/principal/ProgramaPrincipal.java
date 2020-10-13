package br.com.zup.principal;

import java.sql.SQLException;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import br.com.zup.dao.CidadeDAO;
import br.com.zup.dojo.Cidade;

public class ProgramaPrincipal {

	private static CidadeDAO cidadeDao = new CidadeDAO();

	public static void menuOpcoes() {
		System.out.println("=============================================");
		System.out.println("|||||||||||||||| [CIDADES] ||||||||||||||||||");
		System.out.println("=============================================");
		System.out.println("|                                           |");
		System.out.println("|    [1] - LISTAR CIDADES                   |");
		System.out.println("|    [2] - REMOVER CIDADE                   |");
		System.out.println("|    [3] - RETORNAR CIDADE (CEP)            |");
		System.out.println("|    [4] - PESQUISAR CIDADES (NOME)         |");
		System.out.println("|    [5] - LISTAR CIDADES (SIGLA)           |");
		System.out.println("|    [6] - QUANTIDADE DE CIDADES (ESTADO)   |");
		System.out.println("|    [7] - FILTRAR CIDADES (CAPITAL)        |");
		System.out.println("|    [8] - INSERIR CIDADES                  |");
		System.out.println("|    [9] - VOLTAR AO MENU                   |");
		System.out.println("|    [0] - SAIR DO PROGRAMA                 |");
		System.out.println("|                                           |");
		System.out.println("=============================================");

	}

	public static void listarCidades() {
		List<Cidade> cidadeBD = cidadeDao.listaCidades();

		System.out.print("\n");
		System.out.println("\t\t\t\t\t" + "=============================================");
		System.out.println("\t\t\t\t\t" + "|              [LISTA DE CIDADES]           |");
		System.out.println("\t\t\t\t\t" + "=============================================");
		System.out.println("\n");

		for (Cidade cidade : cidadeBD) {
			System.out.println(cidade);
		}
	}

	public static void removeCidade(Scanner sc) {
		System.out.println("=============================================");
		System.out.println("|       [REMOVA UMA CIDADE PELO CEP]        |");
		System.out.println("=============================================");
		System.out.print("\n");
		System.out.print("NÚMERO DO CEP: ");
		int numeroCep = sc.nextInt();

		cidadeDao.removeCidade(numeroCep);
	}

	public static void retornaCidadePorCep(Scanner sc) {

		System.out.println("=============================================");
		System.out.println("|        [RETORNE A CIDADE PELO CEP]        |");
		System.out.println("=============================================");
		System.out.print("\n");
		System.out.println("USUÁRIO, ");
		System.out.print("INFORME O SEU CEP: ");
		int numeroCep = sc.nextInt();

		cidadeDao.selecaoPorCep(numeroCep);
	}

	public static void pesquisaCidadePorNome(Scanner sc) {
		System.out.println("=============================================");
		System.out.println("|        [ESCOLHA UM NOME DE CIDADE]        |");
		System.out.println("=============================================");
		System.out.print("\n");
		System.out.println("USUÁRIO, ");
		System.out.print("INFORME O NOME DA CIDADE: ");
		String nomeCidade = sc.next();
		cidadeDao.listarCidadePorNome(nomeCidade);
	}

	public static void listaDeCidadesPelaSigla(Scanner sc) {
		System.out.println("=============================================");
		System.out.println("|       [LISTE UMA CIDADE PELA SIGLA]       |");
		System.out.println("=============================================");
		System.out.print("\n");
		System.out.println("USUÁRIO, ");
		System.out.print("INFORME A SIGLA DA CIDADE: ");
		String siglaCidade = sc.next();

		List<Cidade> cidadeBD = cidadeDao.listarCidadeSigla(siglaCidade);
		for (Cidade cidadeSigla : cidadeBD) {
			System.out.println(cidadeSigla);
		}
	}

	public static void quantidadeDeCidadesDoEstado(Scanner sc) {

		System.out.println("=============================================");
		System.out.println("|     [QUANTIDADE DE CIDADES DO ESTADO]     |");
		System.out.println("=============================================");
		System.out.print("\n");
		System.out.println("USUÁRIO, ");
		System.out.print("INFORME A SIGLA DA CIDADE: ");
		String siglaCidade = sc.next();

		cidadeDao.quantidadeCidadesEstado(siglaCidade);
	}

	public static void filtraCidadesPorCapital(Scanner sc) {
		System.out.println("=============================================");
		System.out.println("|        [FILTRA CIDADES POR CAPITAL]       |");
		System.out.println("=============================================");
		System.out.print("\n");
		System.out.println("USUÁRIO, ");
		System.out.print("INFORME A CIDADE POR CAPITAL: ");
		boolean cidadeCapital = sc.nextBoolean();

//		cidadeDao.filtraCidades(cidadeCapital);

		List<Cidade> cidadeBD = cidadeDao.filtraCidades(cidadeCapital);
		for (Cidade cidadeSigla : cidadeBD) {
			System.out.println(cidadeSigla);
		}
	}

	public static void inserirCidade(Scanner sc) throws SQLException {
		System.out.println("=============================================");
		System.out.println("|           [ADICIONE SUA CIDADE]           |");
		System.out.println("=============================================");
		System.out.print("\n");
		System.out.print("\nQUANTIDADE DE CIDADES: ");
		int qtdCidade = sc.nextInt();

		for (int i = 0; i < qtdCidade; i++) {
			System.out.println("=============================================");
			System.out.println("|               |DADOS CIDADE|              |");
			System.out.println("=============================================");

			System.out.print("CEP: ");
			int cep = sc.nextInt();
			System.out.print("CIDADE: ");
			String cidade = sc.next();
			System.out.print("NÚMERO DE HABITANTES: ");
			Integer nro_habitantes = sc.nextInt();
			System.out.print("CAPITAL: ");
			boolean capital = sc.nextBoolean();
			System.out.print("ESTADO: ");
			String estado = sc.next();
			System.out.print("RENDA PER CAPITA: ");
			float renda_per_capita = sc.nextFloat();
			System.out.print("DATA DE FUNDAÇÃO: ");
			String dataDeFundacao = sc.next();

			cidadeDao.insereCidade(
					new Cidade(cep, cidade, nro_habitantes, capital, estado, renda_per_capita, dataDeFundacao));
		}
	}

	public static void fimDoPrograma() {
		System.out.print("\n");
		System.out.println("=============================================");
		System.out.println("|              [FIM DO PROGRAMA]            |");
		System.out.println("=============================================");
	}

	public static void opcaoInvalida() {
		System.out.print("\n");
		System.out.println("=============================================");
		System.out.println("|              [OPÇÃO INVÁLIDA]             |");
		System.out.println("=============================================");
	}

	public static void main(String[] args) throws SQLException {

		Cidade cidade = new Cidade();
		Scanner sc = new Scanner(System.in);
		int opcaoUsuario;
		int opcaoPessoa;
		final String DETALHAMENTO = "=============================================";
		final String OPCAOINVALIDA = "OPÇÃO INVALIDA!";

		menuOpcoes();

		do {
			System.out.print("\n");
			System.out.println(DETALHAMENTO);
			System.out.print("DIGITE UMA OPÇÃO: ");
			opcaoUsuario = sc.nextInt();
			System.out.println(DETALHAMENTO);

			switch (opcaoUsuario) {
			case 1:
				try {
					listarCidades();
				} catch (InputMismatchException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 2:
				try {
					removeCidade(sc);
				} catch (InputMismatchException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 3:
				try {
					retornaCidadePorCep(sc);
				} catch (InputMismatchException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 4:
				try {
					pesquisaCidadePorNome(sc);
				} catch (InputMismatchException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 5:
				try {
					listaDeCidadesPelaSigla(sc);
				} catch (InputMismatchException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 6:
				try {
					quantidadeDeCidadesDoEstado(sc);
				} catch (InputMismatchException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 7:
				try {
					filtraCidadesPorCapital(sc);
				} catch (InputMismatchException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 8:
				try {
					inserirCidade(sc);
				} catch (InputMismatchException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 9:
				menuOpcoes();
				break;
			case 0:
				fimDoPrograma();
				break;
			default:
				opcaoInvalida();
			}

		} while (opcaoUsuario != 0);

		sc.close();
	}

}
