package javaPonto.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javaPonto.configuracao.Configuracao;
import javaPonto.dao.DaoPonto;
import org.sqlite.JDBC;	


public class ConexaoAccess {

	Configuracao configuracao;
	
	public void setConfiguracao(Configuracao configuracao) {
		this.configuracao = configuracao;
	}
	
	// Conexao com access
    public  Connection getConnection()  {
    	Connection con = null;
    	
    try {
    	
    	Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
    	con =  DriverManager.getConnection (configuracao.getCaminhoBanco());
    	
    } catch (Exception e){
    	DaoPonto.escreverLog(e, "FALHA NA CONEXAO COM ZKTIME");
        
    }
    
    return con;
 
	
    }
    
    
    
    public  Connection getConnectionSqlServer() {
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
	
	
   
    
    public  Connection getConnectionSqlite() {
        Connection con = null;
        try {
        	
            // Carrega a classe do driver SQLite
            Class.forName("org.sqlite.JDBC");
            
            // URL de conexão para o banco de dados SQLite
            //String url = "jdbc:sqlite:C:\\sqlite\\acesso.sqlite";
            String url = configuracao.getCaminhoBanco();
            
            // Estabelece a conexão
            con = DriverManager.getConnection(url);
        } catch (ClassNotFoundException e) {
            DaoPonto.escreverLog(e, "FALHA AO CARREGAR O DRIVER SQLITE");
        } catch (SQLException e) {
            DaoPonto.escreverLog(e, "FALHA NA CONEXÃO COM O SQLITE");
        }
        return con;
    }
    
    
    
}
