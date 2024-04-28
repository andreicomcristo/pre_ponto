package javaPonto.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import javaPonto.conexao.ConexaoAccess;
import javaPonto.conexao.ConnectionFactory;
import javaPonto.dao.DaoPonto;
import javaPonto.frame.ImportacaoRegistrosPontoFrame;

public class ThreadImportarRegistrosAgora implements Runnable{

	ImportacaoRegistrosPontoFrame importacaoRegistrosPontoFrame;
	String dataInicial;
	String dataFinal;
	String andCpf;
	
	public ThreadImportarRegistrosAgora(ImportacaoRegistrosPontoFrame importacaoRegistrosPontoFrame, String dataInicial, String dataFinal,  String andCpf) {
		this.importacaoRegistrosPontoFrame = importacaoRegistrosPontoFrame;
		this.dataInicial = dataInicial;
		this.dataFinal = dataFinal;
		this.andCpf = andCpf;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {

		ImportarService importarService = new ImportarService();
		
			
		
			
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
						conexaoAcess.close();
					}
				}
				if(conexaoFapeal!=null) {
					if(!conexaoFapeal.isClosed()){
						conexaoFapeal.close();
					}
				}
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			if(problemaConexao==false) {
				
				DaoPonto daoPonto = new DaoPonto();
				Integer horaAgora = daoPonto.buscarHoraAtual(ConnectionFactory.getConnection());
				horaAgora = horaAgora - 3;
				
				if(/*horaAgora>=21 || horaAgora<4*/true ) {
					
					System.out.println("Iniciando :"+new Date());
					importacaoRegistrosPontoFrame.setTitle("Coletando Registros");
					importarService.importarRegistrosPonto(importacaoRegistrosPontoFrame, dataInicial, dataFinal, andCpf);
					importacaoRegistrosPontoFrame.setTitle("");
					System.out.println("Findando  :"+new Date());
				}
				
			}
			
			
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
}
