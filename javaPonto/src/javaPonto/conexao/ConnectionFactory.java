package javaPonto.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import javaPonto.dao.DaoPonto;


public class ConnectionFactory {

	// Conexao com postgres
	
	//Fapeal
/*  
	public static Connection getConnection()  {
	    Connection con = null;
	    try {
	        Class.forName ("org.postgresql.Driver");    
	        
	        Properties props = new Properties();
	        
	        props.setProperty("user", "postgres");
	        props.setProperty("password", "RyRhvsqJzUMfKhge");
	        props.setProperty("currentSchema", "public");
	        
	                                                                // caminho / nome do banco / senha
	         con =  DriverManager.getConnection ("jdbc:postgresql://200.133.132.101:5432/folha", props);
	            } catch (Exception e){DaoPonto.escreverLog(e, "FALHA NA CONEXAO COM O POSTGRES");
	                
	            }
	    return con;
    }    
*/ 
	
	
	//Itec
    public static Connection getConnection()  {
	    Connection con = null;
	    try {
	        Class.forName ("org.postgresql.Driver");    
	        
	        Properties props = new Properties();
	        
	        props.setProperty("user", "postgres");
	        props.setProperty("password", "465dGbZK");
	        props.setProperty("currentSchema", "public");
	        
	                                                                // caminho / nome do banco / senha
	         con =  DriverManager.getConnection ("jdbc:postgresql://186.249.51.220:5432/folha", props);
	            } catch (Exception e){DaoPonto.escreverLog(e, "FALHA NA CONEXAO COM O POSTGRES");
	                
	            }
	    return con;
    }    

    
    /*
  //Contabo
    public static Connection getConnection()  {
	    Connection con = null;
	    try {
	        Class.forName ("org.postgresql.Driver");    
	        
	        Properties props = new Properties();
	        
	        props.setProperty("user", "postgres");
	        props.setProperty("password", "c41p9huvx6");
	        props.setProperty("currentSchema", "public");
	        
	                                                                // caminho / nome do banco / senha
	         con =  DriverManager.getConnection ("jdbc:postgresql://154.38.164.151:5432/folha_hom", props);
	            } catch (Exception e){DaoPonto.escreverLog(e, "FALHA NA CONEXAO COM O POSTGRES");
	                
	            }
	    return con;
    }    
*/
    
    
}
