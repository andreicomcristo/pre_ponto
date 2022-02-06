package javaPonto.conexao;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

// imports necessário para a conexão

public class LoadDriver {

 Connection connection;
//instância do objeto de conexão

 public LoadDriver() {
     try {

            // Class.forName("sun.jdbc.odbc.JdbcOdbcDriver"); // O sun.jdbc.odbc.JdbcOdbcDriver significa que será usado um banco access
             String url = "jdbc:ucanccess:CADASTRO";// esta string carrega o nome da fonte de dados
             connection = DriverManager.getConnection(url,"","");// aqui é efetuada conexão passando–se a fonte de dados e o login e senha do banco, que neste caso não existem.
             

     }

     catch ( Exception ex ) {
    	 JOptionPane.showMessageDialog(null, ex.getMessage());
       }
         


 }//construtor


 public void shutDown()
 {// este método cancela a conexão
         try {
         connection.close();
         }
         catch ( Exception ex ) {
        	 JOptionPane.showMessageDialog(null, ex.getMessage());

         }
  }

 public Connection getConn(){// este método retorna a conexão
  return connection;
 }

}
