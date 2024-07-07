package javaPonto.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import javaPonto.conexao.ConexaoAccess;
import javaPonto.configuracao.Configuracao;
import javaPonto.dao.DaoPonto;
import javaPonto.domain.RegistroPonto;
import javaPonto.frame.ImportacaoRegistrosPontoFrame;

	
public class ImportarService {
	
	DaoPonto daoPonto;
	Configuracao configuracao;
	
	public void setDaoPonto(DaoPonto daoPonto) {
		this.daoPonto = daoPonto;
	}
	
	public void setConfiguracao(Configuracao configuracao) {
		this.configuracao = configuracao;
	}
	
	public void importarRegistrosPonto(ImportacaoRegistrosPontoFrame importacaoRegistrosPontoFrame, String dataInicial, String dataFinal, String andCpf) {
		
		DaoPonto daoPonto =  new DaoPonto();
		daoPonto.setConfiguracao(configuracao);
		ConexaoAccess conexaoAccess= new ConexaoAccess();
		conexaoAccess.setConfiguracao(configuracao);
		daoPonto.setConexaoAccess(conexaoAccess);
		
		List<java.sql.Date> listaDataAtual = daoPonto.selectMaximaDataPostgres(configuracao);
		
		
		if(dataInicial.length()==0|| dataFinal.length()==0) {
			if(!listaDataAtual.isEmpty()) {
				
				//daoPonto.inserirRegistrosNoPostgres(daoPonto.selectListaNomesAccess(daoPonto.selectMaximaDataAccess().get(0), andCpf), importacaoRegistrosPontoFrame);
				if(!configuracao.getCaminhoBanco().toUpperCase().contains("SQLITE")) {
					if(!configuracao.getTipoRegistro().toUpperCase().contains("ACESSO")) {
						daoPonto.inserirRegistrosNoPostgres(daoPonto.selectListaNomesAccess(listaDataAtual.get(0), andCpf), importacaoRegistrosPontoFrame);
					}else {
						daoPonto.inserirRegistrosNoPostgresAcesso(daoPonto.selectListaNomesAccess(listaDataAtual.get(0), andCpf), importacaoRegistrosPontoFrame);
					}
				}else {
					if(!configuracao.getTipoRegistro().toUpperCase().contains("ACESSO")) {
						daoPonto.inserirRegistrosNoPostgres(daoPonto.selectListaNomesSqlite(listaDataAtual.get(0), andCpf), importacaoRegistrosPontoFrame);
					}else {
						daoPonto.inserirRegistrosNoPostgresAcesso(daoPonto.selectListaNomesSqlite(listaDataAtual.get(0), andCpf), importacaoRegistrosPontoFrame);
					}
				}
			}else {
				DaoPonto.escreverLog(null, "Data atual nao obtida do postgres.");
			}
		}else {
			if(!listaDataAtual.isEmpty()) {
				if(!configuracao.getCaminhoBanco().toUpperCase().contains("SQLITE")) {
					if(!configuracao.getTipoRegistro().toUpperCase().contains("ACESSO")) {
						daoPonto.inserirRegistrosNoPostgres(daoPonto.selectListaNomesAccessComDatas(dataInicial, dataFinal, andCpf), importacaoRegistrosPontoFrame);
					}else {
						daoPonto.inserirRegistrosNoPostgresAcesso(daoPonto.selectListaNomesAccessComDatas(dataInicial, dataFinal, andCpf), importacaoRegistrosPontoFrame);
					}
				}else {
					if(!configuracao.getTipoRegistro().toUpperCase().contains("ACESSO")) {
						daoPonto.inserirRegistrosNoPostgres(daoPonto.selectListaNomesSqliteComDatas(dataInicial, dataFinal, andCpf), importacaoRegistrosPontoFrame);
					}else {
						daoPonto.inserirRegistrosNoPostgresAcesso(daoPonto.selectListaNomesSqliteComDatas(dataInicial, dataFinal, andCpf), importacaoRegistrosPontoFrame);
					}
				}
			}else {
				DaoPonto.escreverLog(null, "Data atual nao obtida do postgres.");
			}
		}
		
		//daoPonto.inserirRegistrosNoPostgres(daoPonto.selectListaNomesAccess( new java.sql.Date(2021-1900, 11, 1) ));
	}
	

	
	public  boolean acertarConfiguracoes(ArrayList<String> lista, Configuracao configuracao, DaoPonto daoPonto, ConexaoAccess conexaoAccess) {
		boolean resposta = false;
		configuracao.setIdUnidade( Long.parseLong( lista.get(0) ));
		configuracao.setTipoRegistro(lista.get(1));
		configuracao.setCaminhoBanco(lista.get(2));
		configuracao.setConexaoDestino( lista.get(3) );
		setConfiguracao(configuracao);
		setDaoPonto(daoPonto);
		conexaoAccess.setConfiguracao(configuracao);
		daoPonto.setConexaoAccess(conexaoAccess); 
		daoPonto.setConfiguracao(configuracao);
		
		
		
		
		resposta = true;
		return resposta;
	}
	
	 public  ArrayList<String> splitStringIntoThreeParts(String input) {
	        String[] parts = input.split(";", 4);
	        ArrayList<String> result = new ArrayList<>();

	        for (String part : parts) {
	            result.add(part);
	        }

	        return result;
	    }
	
	public String lerArquivoConfiguracao() {
		
		String str = "";
        try {
        	FileReader arq = new FileReader( "./conf.txt"  );
            //FileReader arq = new FileReader( "c:/zktime/conf.txt"  );
            BufferedReader lerArq = new BufferedReader(arq);
            str = lerArq.readLine();
                
            while (str != null) {
                
            	if(str.length()>0) {
            		return str; 
            		
            	}
                
              
            DaoPonto.escreverLog(null, " TENTANDO INSERIR :"+str);
            }
            
            
            arq.close();
        } catch (Exception f) {
            JOptionPane.showMessageDialog(null, f.getMessage());
        }

       
        
        
        return str;
		
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
	
	
	
	
	public boolean diaValido(String texto) {
		boolean resposta = true;
		
		if(texto==null) {return false;}
		if(texto.length()<1) {return false;}
		
		for(int i=0;i<texto.length();i++) {
			String subtexto = texto.substring(i, i+1);
			if(
				(!subtexto.equalsIgnoreCase("0")) && 
				(!subtexto.equalsIgnoreCase("1")) &&
				(!subtexto.equalsIgnoreCase("2")) &&
				(!subtexto.equalsIgnoreCase("3")) &&
				(!subtexto.equalsIgnoreCase("4")) &&
				(!subtexto.equalsIgnoreCase("5")) &&
				(!subtexto.equalsIgnoreCase("6")) &&
				(!subtexto.equalsIgnoreCase("7")) &&
				(!subtexto.equalsIgnoreCase("8")) &&
				(!subtexto.equalsIgnoreCase("9")) 
				
					) {return false;}
		}
		
		if( (Integer.parseInt(texto)>31) || (Integer.parseInt(texto)<1) ) {return false;}
		
		return resposta;
	}
	
	
	
	
	
	
	public boolean mesValido(String texto) {
		boolean resposta = true;
		
		if(texto==null) {return false;}
		if(texto.length()<1) {return false;}
		
		for(int i=0;i<texto.length();i++) {
			String subtexto = texto.substring(i, i+1);
			if(
				(!subtexto.equalsIgnoreCase("0")) && 
				(!subtexto.equalsIgnoreCase("1")) &&
				(!subtexto.equalsIgnoreCase("2")) &&
				(!subtexto.equalsIgnoreCase("3")) &&
				(!subtexto.equalsIgnoreCase("4")) &&
				(!subtexto.equalsIgnoreCase("5")) &&
				(!subtexto.equalsIgnoreCase("6")) &&
				(!subtexto.equalsIgnoreCase("7")) &&
				(!subtexto.equalsIgnoreCase("8")) &&
				(!subtexto.equalsIgnoreCase("9")) 
				
					) {return false;}
		}
		
		if( (Integer.parseInt(texto)>12) || (Integer.parseInt(texto)<1) ) {return false;}
		
		return resposta;
	}
	
	
	
	
	
	public boolean anoValido(String texto) {
		boolean resposta = true;
		
		if(texto==null) {return false;}
		if(texto.length()<1) {return false;}
		
		for(int i=0;i<texto.length();i++) {
			String subtexto = texto.substring(i, i+1);
			if(
				(!subtexto.equalsIgnoreCase("0")) && 
				(!subtexto.equalsIgnoreCase("1")) &&
				(!subtexto.equalsIgnoreCase("2")) &&
				(!subtexto.equalsIgnoreCase("3")) &&
				(!subtexto.equalsIgnoreCase("4")) &&
				(!subtexto.equalsIgnoreCase("5")) &&
				(!subtexto.equalsIgnoreCase("6")) &&
				(!subtexto.equalsIgnoreCase("7")) &&
				(!subtexto.equalsIgnoreCase("8")) &&
				(!subtexto.equalsIgnoreCase("9")) 
				
					) {return false;}
		}
		
		if( (Integer.parseInt(texto)>3000) || (Integer.parseInt(texto)<2020) ) {return false;}
		
		return resposta;
	}
	
	
	
}
