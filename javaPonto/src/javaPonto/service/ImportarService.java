package javaPonto.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import javaPonto.dao.DaoPonto;
import javaPonto.domain.RegistroPonto;
import javaPonto.frame.ImportacaoRegistrosPontoFrame;

	
public class ImportarService {
	
	DaoPonto daoPonto = new DaoPonto();
	
	
	public void importarRegistrosPonto(ImportacaoRegistrosPontoFrame importacaoRegistrosPontoFrame) {
		if(!daoPonto.selectMaximaDataAccess().isEmpty()) {
			daoPonto.inserirRegistrosNoPostgres(daoPonto.selectListaNomesAccess(daoPonto.selectMaximaDataAccess().get(0)), importacaoRegistrosPontoFrame);
		}
		//daoPonto.inserirRegistrosNoPostgres(daoPonto.selectListaNomesAccess( new java.sql.Date(2021-1900, 11, 1) ));
	}
	
	public void trocarNomePorCpf() {
		

		
		List<String> listaSql = new ArrayList<String>();
        
        try {
        	FileReader arq = new FileReader( "./conversao.txt"  );
            //FileReader arq = new FileReader( "c:/zktime/conversao.txt"  );
            BufferedReader lerArq = new BufferedReader(arq);
            String str = lerArq.readLine();
                
            while (str != null) {
                
                
              String[] textoSeparado = str.split(":"); 
              String linhaSql = new String();
              
              linhaSql = textoSeparado[0];
              
              listaSql.add(linhaSql);
            
            str = lerArq.readLine();
            
            }
            
            
            arq.close();
        } catch (Exception f) {
            JOptionPane.showMessageDialog(null, f.getMessage());
        }

        
        if(!listaSql.isEmpty()){
            //Vendo como estava antes
        	DaoPonto.escreverLista(daoPonto.selectListaPontoNomeAccess());
        	//Executando
            daoPonto.trocarNomePorCpfNoAccess(listaSql);
            //Vendo como ficou
            DaoPonto.escreverLista(daoPonto.selectListaPontoNomeAccess());
            
        }else{JOptionPane.showMessageDialog(null, "Não há o que importar.");}
        
        
    

		
	}
	
	
}
