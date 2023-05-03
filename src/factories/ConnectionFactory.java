package factories;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {

	// atributos
	private static final String url = "jdbc:postgresql://localhost:5432/bd_aula04";
	private static final String user = "postgres";
	private static final String password = "coti";
	private static final String driver = "org.postgresql.Driver";
	
	// método para abrir e retornar uma conexão com o PostGreSQL 	
	public static Connection getConnection() throws Exception { 
		Class.forName(driver); 
		return DriverManager.getConnection(url, user, password);
	}
}
