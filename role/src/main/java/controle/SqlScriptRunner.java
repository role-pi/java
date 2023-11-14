package controle;

import java.sql.Connection;

import org.apache.ibatis.jdbc.ScriptRunner;

public class SqlScriptRunner {
	public static void runScript(
      String path,
      Connection connection
    ) throws Exception {
      ScriptRunner scriptRunner = new ScriptRunner(connection);
      scriptRunner.setSendFullScript(false);
      scriptRunner.setStopOnError(true);
      scriptRunner.runScript(new java.io.FileReader(path));
    }
}