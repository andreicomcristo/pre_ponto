package javaPonto.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import javax.swing.JOptionPane;

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
    	//con = DriverManager.getConnection ("jdbc:ucanaccess://k:/NPD/Database_ponto/att2000.mdb");
    } catch (Exception e){
    	DaoPonto.escreverLog(e, "FALHA NA CONEXAO COM O ACCESS");
        
    }
    
    return con;
 
	
    }
    
	
	/*
	// Conexao com access
    public static Connection getConnection() throws SQLException {
    
      String dataBaseURL = "jdbc:ucanaccess://c:/zktime/att2000.mdb";
    	//String dataBaseURL = "jdbc:ucanaccess://k:/NPD/Database_ponto/att2000.mdb";
    	Connection connection = null;
    try {
    	connection  = DriverManager.getConnection(dataBaseURL);
       
       
	}catch (Exception e) {
		JOptionPane.showMessageDialog(null, e.getMessage());
	}
 
	return connection;
    }
    */
    
    
}
