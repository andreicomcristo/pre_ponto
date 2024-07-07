package javaPonto.conexao;

public class DialetoSQLite {
    public static String traduzirParaSQLite(String sql) {
    	// Substitui tipos de dados comuns pelo equivalente no SQLite
        sql = sql.replaceAll("(?i)INT\\b", "INTEGER");
        sql = sql.replaceAll("(?i)VARCHAR\\(\\d+\\)", "TEXT");
        sql = sql.replaceAll("(?i)CHAR\\(\\d+\\)", "TEXT");
        sql = sql.replaceAll("(?i)BIGINT\\b", "INTEGER");
        sql = sql.replaceAll("(?i)FLOAT\\b", "REAL");
        sql = sql.replaceAll("(?i)DOUBLE\\b", "REAL");
        sql = sql.replaceAll("(?i)DECIMAL\\(\\d+,\\d+\\)", "REAL");
        sql = sql.replaceAll("(?i)BOOLEAN\\b", "INTEGER"); // SQLite doesn't have a BOOLEAN type

        // Remove SQL syntax elements not supported by SQLite
        sql = sql.replaceAll("(?i)AUTO_INCREMENT", "AUTOINCREMENT");
        sql = sql.replaceAll("(?i)ENGINE=\\w+", "");
        sql = sql.replaceAll("(?i)DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP", "DEFAULT CURRENT_TIMESTAMP");

        // Remove ON UPDATE NO ACTION clause completely
        sql = sql.replaceAll("(?i)ON UPDATE NO ACTION", "");

        // Substitui restrições de chave estrangeira e referências
        sql = sql.replaceAll("(?i)ADD CONSTRAINT (\\w+) FOREIGN KEY", "FOREIGN KEY");
        sql = sql.replaceAll("(?i)REFERENCES (\\w+) \\((\\w+)\\)", "REFERENCES $1($2)");




        return sql;
    }
}