package javaPonto.conexao;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

// imports necess�rio para a conex�o

public class LoadDriver {

 Connection connection;
//inst�ncia do objeto de conex�o

 public LoadDriver() {
     try {

            // Class.forName("sun.jdbc.odbc.JdbcOdbcDriver"); // O sun.jdbc.odbc.JdbcOdbcDriver significa que ser� usado um banco access
             String url = "jdbc:ucanccess:CADASTRO";// esta string carrega o nome da fonte de dados
             connection = DriverManager.getConnection(url,"","");// aqui � efetuada conex�o passando�se a fonte de dados e o login e senha do banco, que neste caso n�o existem.
             

     }

     catch ( Exception ex ) {
    	 JOptionPane.showMessageDialog(null, ex.getMessage());
       }
         


 }//construtor


 public void shutDown()
 {// este m�todo cancela a conex�o
         try {
         connection.close();
         }
         catch ( Exception ex ) {
        	 JOptionPane.showMessageDialog(null, ex.getMessage());

         }
  }

 public Connection getConn(){// este m�todo retorna a conex�o
  return connection;
 }

}
