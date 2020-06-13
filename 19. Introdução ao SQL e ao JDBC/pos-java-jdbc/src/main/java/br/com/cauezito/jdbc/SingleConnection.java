package br.com.cauezito.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingleConnection {
	
	private static String url = "jdbc:postgresql://localhost:5432/posjava";
	private static String password = "134679ca";
	private static String user = "postgres";
	private static Connection connection = null;

	//primeira estrutura a ser executada no carregamento da classe.
	static {
		connect();
	}
	
	public SingleConnection() {
		connect();
	}
	
	private static void connect() {
		try {
			/*A conexão deve ser feita apenas uma vez e mantida.
			 * O que é aberto e fechado são as sessões no banco de dados.
			 * Portanto, o bloco testa se a conexão é nula, se for é
			 * criada uma nova, senão é retornada a existente.*/
			if(connection == null) {
				Class.forName("org.postgresql.Driver");
				connection = DriverManager.getConnection(url, user, password);
				//Não salva automaticamente
				connection.setAutoCommit(false);
				System.out.println("Ok!");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		return connection;
	}

}
