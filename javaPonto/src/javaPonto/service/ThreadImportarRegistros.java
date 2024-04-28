package javaPonto.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import javaPonto.conexao.ConexaoAccess;
import javaPonto.conexao.ConnectionFactory;
import javaPonto.configuracao.Configuracao;
import javaPonto.dao.DaoPonto;
import javaPonto.frame.ImportacaoRegistrosPontoFrame;

public class ThreadImportarRegistros implements Runnable{

	ImportacaoRegistrosPontoFrame importacaoRegistrosPontoFrame;
	String dataInicial;
	String dataFinal;
	String andCpf;
	
	public ThreadImportarRegistros(ImportacaoRegistrosPontoFrame importacaoRegistrosPontoFrame, String dataInicial, String dataFinal,  String andCpf) {
		this.importacaoRegistrosPontoFrame = importacaoRegistrosPontoFrame;
		this.dataInicial = dataInicial;
		this.dataFinal = dataFinal;
		this.andCpf = andCpf;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {

		ImportarService importarService = new ImportarService();
		Configuracao configuracao = new Configuracao();
			
		while(true) {
			
			try {
				String resposta = "";
				boolean problemaConexao = false;
				Connection conexaoAcess = ConexaoAccess.getConnection();
				Connection conexaoFapeal = ConnectionFactory.getConnection();
				
				try {
					
				conexaoAcess = ConexaoAccess.getConnection();
				conexaoFapeal = ConnectionFactory.getConnection();
					if(conexaoAcess==null) {resposta = resposta+"Conexao ZkTime nula. ";}
					if(conexaoFapeal==null) {resposta = resposta+"Conexao Banco nula. ";}
					resposta = resposta.trim();
					
					if(resposta.length()>0) {
						problemaConexao=true;
						importacaoRegistrosPontoFrame.setTitle(resposta+" "+new Date());
						//JOptionPane.showMessageDialog(null, resposta+" "+new Date());
					}
					
					if(conexaoAcess!=null) {
						if(!conexaoAcess.isClosed()){
							try {
								conexaoAcess.close();
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
					if(conexaoFapeal!=null) {
						if(!conexaoFapeal.isClosed()){
							try {
								conexaoFapeal.close();
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					DaoPonto.escreverLog(e1, " TENTANDO CONECTAR");
				}
				
				
				if(problemaConexao==false) {
					
					
					if(true ) {
						
						System.out.println("Iniciando :"+new Date());
						importacaoRegistrosPontoFrame.setTitle("Coletando Registros");
						importarService.importarRegistrosPonto(importacaoRegistrosPontoFrame, dataInicial, dataFinal, andCpf);
						importacaoRegistrosPontoFrame.setTitle("");
						System.out.println("Findando  :"+new Date());
					}
					
				}
				
				
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				DaoPonto.escreverLog(e2, " EXECUTANDO A TAREFA");
			}
			
			try {
				Thread.sleep(1000*60*configuracao.getMinutosSleep());
				//Thread.sleep(1000*60*1);
			} catch (Exception e) {
				DaoPonto.escreverLog(e, "COLOCANDO A THREAD PARA DORMIR");
			}
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
}
