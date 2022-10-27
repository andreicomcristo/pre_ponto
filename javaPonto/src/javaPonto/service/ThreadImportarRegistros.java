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

public class ThreadImportarRegistros implements Runnable{

	ImportacaoRegistrosPontoFrame importacaoRegistrosPontoFrame;
	String dataInicial;
	String dataFinal;
	
	public ThreadImportarRegistros(ImportacaoRegistrosPontoFrame importacaoRegistrosPontoFrame, String dataInicial, String dataFinal) {
		this.importacaoRegistrosPontoFrame = importacaoRegistrosPontoFrame;
		this.dataInicial = dataInicial;
		this.dataFinal = dataFinal;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {

		ImportarService importarService = new ImportarService();
		while(true) {
			
			String resposta = "";
			boolean problemaConexao = false;
			
			try {
				
			Connection conexaoAcess = ConexaoAccess.getConnection();
			Connection conexaoFapeal = ConnectionFactory.getConnection();
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
				System.out.println("Iniciando :"+new Date());
				importacaoRegistrosPontoFrame.setTitle("");
				importarService.importarRegistrosPonto(importacaoRegistrosPontoFrame, dataInicial, dataFinal);
				importacaoRegistrosPontoFrame.setTitle("");
				System.out.println("Findando  :"+new Date());
			}
			
			try {
				Thread.sleep(1000*60*10);
			} catch (Exception e) {
				DaoPonto.escreverLog(e, "COLOCANDO A THREAD PARA DORMIR");
			}
		}
	}
	
}
