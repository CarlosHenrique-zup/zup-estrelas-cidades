package br.com.zup.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	public Connection fabricaConexao() {

		try {
			return DriverManager.getConnection("jdbc:mysql://localhost:3306/BANCO_CIDADE?user=root&password=root"
					+"&useTimezone=true&serverTimezone=UTC");
		} catch (SQLException e) {
			return null;
		}
	}

}
