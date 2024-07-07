package javaPonto.frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javaPonto.conexao.ConexaoAccess;
import javaPonto.conexao.DialetoSQLite;
import javaPonto.configuracao.Configuracao;
import javaPonto.dao.DaoPonto;
import javaPonto.service.ImportarService;
import javaPonto.service.ThreadImportarRegistros;
import javaPonto.service.ThreadImportarRegistrosAgora;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;

public class ImportacaoRegistrosPontoFrame extends JFrame {

	
	ImportarService importarService = new ImportarService();
	Configuracao configuracao = new Configuracao();
	DaoPonto daoPonto = new DaoPonto();
	ConexaoAccess conexaoAccess = new ConexaoAccess();
	boolean  dadosConfiguracao = importarService.acertarConfiguracoes( importarService.splitStringIntoThreeParts( importarService.lerArquivoConfiguracao()), configuracao, daoPonto, conexaoAccess); 
	
	
	
	
	boolean threadViva = false;
	
	
	
	private JPanel contentPane;
	
	public ImportacaoRegistrosPontoFrame frame1;
	private JTextField textField1;
	private JTextField textField2;
	private JTextField textField3;
	private JTextField textField4;
	private JTextField textField5;
	private JTextField textField6;
	private JTextField textField7;
	public void setFrame(ImportacaoRegistrosPontoFrame f) {
		frame1 = f;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					ImportacaoRegistrosPontoFrame frame = new ImportacaoRegistrosPontoFrame();
					
					frame.setFrame(frame);
					frame.setVisible(false);
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ImportacaoRegistrosPontoFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 344);
		contentPane = new JPanel();
		contentPane.setBounds(100, 100, 700, 344);
		contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
		setContentPane(contentPane);
		
		
		textField1 = new JTextField();
		textField1.setBounds(66, 46, 32, 20);
		contentPane.add(textField1);
		textField1.setColumns(10);
		
		textField2 = new JTextField();
		textField2.setBounds(108, 46, 32, 20);
		contentPane.add(textField2);
		textField2.setColumns(10);
		
		textField3 = new JTextField();
		textField3.setBounds(150, 46, 37, 20);
		contentPane.add(textField3);
		textField3.setColumns(10);
		
		textField4 = new JTextField();
		textField4.setBounds(265, 46, 32, 20);
		contentPane.add(textField4);
		textField4.setColumns(10);
		
		textField5 = new JTextField();
		textField5.setBounds(307, 46, 32, 20);
		contentPane.add(textField5);
		textField5.setColumns(10);
		
		textField6 = new JTextField();
		textField6.setBounds(349, 46, 37, 20);
		contentPane.add(textField6);
		textField6.setColumns(10);
		
		
		textField7 = new JTextField();
		textField7.setBounds(406, 46, 150, 20);
		contentPane.add(textField7);
		textField7.setColumns(10);
		
		
		
		JButton btnNewButton = new JButton("Thread Importar Registros de Ponto ["+configuracao.getDias()+" dia(s)] ou período (se tiver preenchido)");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				
				String dataInicial = "";
				String dataFinal = "";
				
				String diaInicial = textField1.getText();
				String mesInicial = textField2.getText();
				String anoInicial = textField3.getText();
				String diaFinal = textField4.getText();
				String mesFinal = textField5.getText();
				String anoFinal = textField6.getText();
				String cpf = textField7.getText().toUpperCase();
				if(cpf==null) {cpf = "";}
				String andCpf = "";
				if(cpf.length()>0) {
					andCpf = " and USERINFO.Name = '"+cpf+"' ";
				}
				
				if(diaInicial==null) {diaInicial="";}
				if(mesInicial==null) {mesInicial="";}
				if(anoInicial==null) {anoInicial="";}
				if(diaFinal==null) {diaFinal="";}
				if(mesFinal==null) {mesFinal="";}
				if(anoFinal==null) {anoFinal="";}
				
				
				if((diaInicial+mesInicial+anoInicial+diaFinal+mesFinal+anoFinal).contains(" ")) {
					diaInicial="";
					mesInicial="";
					anoInicial="";
					diaFinal="";
					mesFinal="";
					anoFinal="";
				}
				
				boolean diaInicialValido = importarService.diaValido(diaInicial);
				boolean mesInicialValido = importarService.mesValido(mesInicial);
				boolean anoInicialValido = importarService.anoValido(anoInicial);
				boolean diaFinalValido = importarService.diaValido(diaFinal);
				boolean mesFinalValido = importarService.mesValido(mesFinal);
				boolean anoFinalValido = importarService.anoValido(anoFinal);
				
				if(diaInicialValido==true) {
					if(diaInicial.length()==1) {diaInicial="0"+diaInicial;}
				}
				if(mesInicialValido==true) {
					if(mesInicial.length()==1) {mesInicial="0"+mesInicial;}
				}
				if(diaFinalValido==true) {
					if(diaFinal.length()==1) {diaFinal="0"+diaFinal;}
				}
				if(mesFinalValido==true) {
					if(mesFinal.length()==1) {mesFinal="0"+mesFinal;}
				}
				
				
				if( diaInicialValido==true && 
					mesInicialValido==true &&
					anoInicialValido==true &&
					diaFinalValido==true &&
					mesFinalValido==true &&
					anoFinalValido==true 
						) {
					dataInicial = anoInicial+"-"+mesInicial+"-"+diaInicial;
					dataFinal = anoFinal+"-"+mesFinal+"-"+diaFinal;
				}
				
				
				if(threadViva==false) {
				ThreadImportarRegistros threadImportarRegistros = new ThreadImportarRegistros(frame1, dataInicial, dataFinal, andCpf, configuracao,daoPonto, importarService, conexaoAccess);
				Thread t1 = new Thread(threadImportarRegistros);
				t1.start();
				threadViva = true;
				}
				
				
			}
		});
		btnNewButton.setBounds(108, 86, 482, 23);
		if(configuracao.isExibirImportarRegistros()==true) {
			contentPane.add(btnNewButton);
		}
		contentPane.setLayout(null);
		
		
		

		
		JButton btnNewButton1 = new JButton("Thread Importar Registros AGORA ");
		btnNewButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
							
				
				String dataInicial = "";
				String dataFinal = "";
				
				String diaInicial = textField1.getText();
				String mesInicial = textField2.getText();
				String anoInicial = textField3.getText();
				String diaFinal = textField4.getText();
				String mesFinal = textField5.getText();
				String anoFinal = textField6.getText();
				String cpf = textField7.getText().toUpperCase();
				if(cpf==null) {cpf = "";}
				String andCpf = "";
				if(cpf.length()>0) {
					andCpf = " and USERINFO.Name = '"+cpf+"' ";
				}
				
				if(diaInicial==null) {diaInicial="";}
				if(mesInicial==null) {mesInicial="";}
				if(anoInicial==null) {anoInicial="";}
				if(diaFinal==null) {diaFinal="";}
				if(mesFinal==null) {mesFinal="";}
				if(anoFinal==null) {anoFinal="";}
				
				
				if((diaInicial+mesInicial+anoInicial+diaFinal+mesFinal+anoFinal).contains(" ")) {
					diaInicial="";
					mesInicial="";
					anoInicial="";
					diaFinal="";
					mesFinal="";
					anoFinal="";
				}
				
				boolean diaInicialValido = importarService.diaValido(diaInicial);
				boolean mesInicialValido = importarService.mesValido(mesInicial);
				boolean anoInicialValido = importarService.anoValido(anoInicial);
				boolean diaFinalValido = importarService.diaValido(diaFinal);
				boolean mesFinalValido = importarService.mesValido(mesFinal);
				boolean anoFinalValido = importarService.anoValido(anoFinal);
				
				if(diaInicialValido==true) {
					if(diaInicial.length()==1) {diaInicial="0"+diaInicial;}
				}
				if(mesInicialValido==true) {
					if(mesInicial.length()==1) {mesInicial="0"+mesInicial;}
				}
				if(diaFinalValido==true) {
					if(diaFinal.length()==1) {diaFinal="0"+diaFinal;}
				}
				if(mesFinalValido==true) {
					if(mesFinal.length()==1) {mesFinal="0"+mesFinal;}
				}
				
				
				if( diaInicialValido==true && 
					mesInicialValido==true &&
					anoInicialValido==true &&
					diaFinalValido==true &&
					mesFinalValido==true &&
					anoFinalValido==true 
						) {
					dataInicial = anoInicial+"-"+mesInicial+"-"+diaInicial;
					dataFinal = anoFinal+"-"+mesFinal+"-"+diaFinal;
				}
				
				
				if(true) {
				ThreadImportarRegistrosAgora threadImportarRegistrosAgora = new ThreadImportarRegistrosAgora(frame1, dataInicial, dataFinal,  andCpf, configuracao,daoPonto, importarService, conexaoAccess);
				Thread t2 = new Thread(threadImportarRegistrosAgora);
				t2.start();
				//threadViva = true;
				}
				
				
				
			}
		});
		btnNewButton1.setBounds(108, 120, 482, 23);
		if(configuracao.isExibirImportarRegistros()==true) {
			contentPane.add(btnNewButton1);
		}
		contentPane.setLayout(null);
		
		
		
		JLabel lblNewLabel = new JLabel(daoPonto.selectUnidade(configuracao.getIdUnidade()).getNome()+"-"+ daoPonto.selectUnidade(configuracao.getIdUnidade()).getSigla());
		lblNewLabel.setBounds(108, 11, 482, 14);
		lblNewLabel.setForeground(Color.red);
		contentPane.add(lblNewLabel);
		

		
		JLabel lblNewLabel_1 = new JLabel("Dia, mes, ano (inicio)");
		lblNewLabel_1.setFont(new Font("SansSerif", Font.BOLD, 11));
		lblNewLabel_1.setBounds(66, 23, 121, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Dia, mes, ano (fim)");
		lblNewLabel_1_1.setFont(new Font("SansSerif", Font.BOLD, 11));
		lblNewLabel_1_1.setBounds(265, 24, 121, 14);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("CPF");
		lblNewLabel_1_1_1.setFont(new Font("SansSerif", Font.BOLD, 11));
		lblNewLabel_1_1_1.setBounds(405, 24, 121, 14);
		contentPane.add(lblNewLabel_1_1_1);
		
		JButton btnNewButton_1 = new JButton("Converter Nome em Cpf (Função Desabilitada)");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				ImportarService importarService = new ImportarService();
				importarService.trocarNomePorCpf();
				
				
				
				
			}
		});
		
		btnNewButton_1.setBounds(108, 148, 482, 23);
		if(configuracao.isExibirConverteCpf()==true) {
			contentPane.add(btnNewButton_1);
		}
		
		
		
		JButton btnNewButton_2 = new JButton("Limpar Registros Antigos (2 meses)");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				setTitle("Executando...");
				java.util.Date date = new java.util.Date();
				java.sql.Date sqlDate = new java.sql.Date( date.getTime() );
				
				Long momentoInicial = sqlDate.getTime();
				momentoInicial = momentoInicial - (65*24*60*60*10*10*10);//menos 65 dias
				java.sql.Date momentoFinal = new java.sql.Date(momentoInicial);   
				
				daoPonto.apagarTabelaCheckInOutAccess(momentoFinal);
				
				setTitle("Concluído");
			}
		});
		btnNewButton_2.setBounds(108, 200, 482, 23);
		if(configuracao.isExibirApagarRegistros()==true) {
			contentPane.add(btnNewButton_2);
		}
		
	}
}
