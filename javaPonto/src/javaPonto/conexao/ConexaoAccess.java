package javaPonto.conexao;

import java.sql.Connection;
import java.sql.DriverManager;

import javaPonto.configuracao.Configuracao;
import javaPonto.dao.DaoPonto;


public class ConexaoAccess {

	
	
	// Conexao com access
    public static Connection getConnection()  {
    	Connection con = null;
    	
    try {
    	Configuracao configuracao = new Configuracao();
    	
    	Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
    	con =  DriverManager.getConnection (configuracao.getCaminhoBanco());
    	
    } catch (Exception e){
    	DaoPonto.escreverLog(e, "FALHA NA CONEXAO COM ZKTIME");
        
    }
    
    return con;
 
	
    }
    
    
    
    public static Connection getConnectionSqlServer() {
        Connection con = null;

        try {
            // Informações de conexão
            String url = "jdbc:sqlserver://sua_url_do_servidor:porta;databaseNome=seu_banco_de_dados";
            String usuario = "seu_usuario";
            String senha = "sua_senha";

            // Carrega o driver JDBC do SQL Server
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            // Cria a conexão
            con = DriverManager.getConnection(url, usuario, senha);
        } catch (Exception e) {
        	DaoPonto.escreverLog(e, "FALHA NA CONEXAO COM ZKTIME");
            e.printStackTrace();
        } 

        return con;
    }
	
	
    
    
}
