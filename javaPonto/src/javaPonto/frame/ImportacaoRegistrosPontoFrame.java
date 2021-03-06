package javaPonto.frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javaPonto.dao.DaoPonto;
import javaPonto.service.ImportarService;
import javaPonto.service.ThreadImportarRegistros;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class ImportacaoRegistrosPontoFrame extends JFrame {

	DaoPonto daoPonto = new DaoPonto();
	
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ImportacaoRegistrosPontoFrame frame = new ImportacaoRegistrosPontoFrame();
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
		setBounds(100, 100, 700, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Thread Importar Registros de Ponto");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ThreadImportarRegistros threadImportarRegistros = new ThreadImportarRegistros();
				Thread t1 = new Thread(threadImportarRegistros);
				t1.start();
				
				
				
				
				
			}
		});
		btnNewButton.setBounds(108, 62, 482, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel(daoPonto.selectUnidade().getNome()+"-"+ daoPonto.selectUnidade().getSigla());
		lblNewLabel.setBounds(108, 11, 482, 14);
		lblNewLabel.setForeground(Color.red);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton_1 = new JButton("Converter Nome em Cpf (Fun??o Desabilitada)");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				/*
				ImportarService importarService = new ImportarService();
				importarService.trocarNomePorCpf();
				*/
				
				
				
			}
		});
		btnNewButton_1.setBounds(108, 148, 482, 23);
		contentPane.add(btnNewButton_1);
	}
}
