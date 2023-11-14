package controle;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {	
	private static Connection conexao;
	private static Conexao instancia;
	
	private static final String DATABASE = "rolejava";
	private static final String USER     = "root";
	private static final String PSW      = "root";
	
	public Conexao() {}
	
	public static Conexao getInstancia() {
		if (instancia == null) { 
			instancia = new Conexao(); 
		}
		return instancia;	
	}
	
	public Connection conectar() {
		try {
			conexao = DriverManager.getConnection("jdbc:mysql://localhost/"+ DATABASE + "?serverTimezone=UTC", USER, PSW);
			runScript();
		} catch (Exception e) { 
			e.printStackTrace(); 
		}
		return conexao;		
	}
	
	public boolean fecharConexao() { 
		try { 
			conexao.close(); 
		} 
		catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}
	
	public static void runScript() {
        try {
        	String path = new File(ClassLoader.getSystemClassLoader().getResource("scripts_role.sql").getFile()).toPath().toString();
            SqlScriptRunner.runScript(path, conexao);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}