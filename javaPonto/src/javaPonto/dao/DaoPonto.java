package javaPonto.dao;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import javaPonto.conexao.ConexaoAccess;
import javaPonto.conexao.ConnectionFactory;
import javaPonto.configuracao.Configuracao;
import javaPonto.domain.RegistroPonto;
import javaPonto.domain.Unidade;
import javaPonto.frame.ImportacaoRegistrosPontoFrame;

public class DaoPonto {

	Configuracao configuracao = new Configuracao();
	
	Connection con = null;
	
	Connection conPostgres = null;

	public static void escreverLog(Exception e, String complemento) {

		Date data = new Date();
		String mes = String.valueOf(data.getMonth()+1);
		while(mes.length()<2) {mes = "0"+mes;}
		String dia = String.valueOf(data.getDate());
		while(dia.length()<2) {dia = "0"+dia;}
		String hora = String.valueOf(data.getHours());
		while(hora.length()<2) {hora = "0"+hora;}
		String minuto = String.valueOf(data.getMinutes());
		while(minuto.length()<2) {minuto = "0"+minuto;}
		String segundo = String.valueOf(data.getSeconds());
		while(segundo.length()<2) {segundo = "0"+segundo;}
		String nome = (data.getYear()+1900)+"-"+mes+"-"+dia+"@"+hora+"h"+minuto+"m"+segundo+"s.txt";
		String caminho = ".\\";
		try {
			FileWriter arq = new FileWriter(caminho+nome);
			PrintWriter gravarArq = new PrintWriter(arq);
			gravarArq.printf(data+"-"+e.getMessage()+" ["+complemento+"]");
			arq.close();

		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	public static void escreverLista(List<String>lista ) {

		Date data = new Date();
		String mes = String.valueOf(data.getMonth()+1);
		while(mes.length()<2) {mes = "0"+mes;}
		String dia = String.valueOf(data.getDate());
		while(dia.length()<2) {dia = "0"+dia;}
		String hora = String.valueOf(data.getHours());
		while(hora.length()<2) {hora = "0"+hora;}
		String minuto = String.valueOf(data.getMinutes());
		while(minuto.length()<2) {minuto = "0"+minuto;}
		String segundo = String.valueOf(data.getSeconds());
		while(segundo.length()<2) {segundo = "0"+segundo;}
		String nome = (data.getYear()+1900)+"-"+mes+"-"+dia+"@"+hora+"h"+minuto+"m"+segundo+"s.txt";
		String caminho = ".\\";
		try {
			
			FileWriter arq = new FileWriter(caminho+nome);
			PrintWriter gravarArq = new PrintWriter(arq);
			
			for(int i=0;i<lista.size();i++) {
				gravarArq.printf(lista.get(i)+"%n");
			}
			arq.close();

		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	
	public Unidade selectUnidade() {

		Configuracao configuracao = new Configuracao();
		Long idUnidade = configuracao.getIdUnidade();
		Unidade unidadeResposta = new Unidade();

		try {
			con = ConnectionFactory.getConnection();

			try {

				PreparedStatement stmt = con.prepareStatement("select nome_fantasia, sigla, id from unidades where id = ? ");

				stmt.setLong(1,  idUnidade);



				ResultSet rs = stmt.executeQuery();

				while(rs.next()) {
					Long id = rs.getLong("id");
					String nome = rs.getString("nome_fantasia");
					String sigla = rs.getString("sigla");


					unidadeResposta = (new Unidade(id, nome, sigla));
					break;

				}


			} finally {
				try {
					if(con!=null){
						if(!con.isClosed()){
							con.close();
						}}
				} catch (Exception e) { escreverLog(e, "SELECIONANDO DADOS DA UNIDADE NO POSTGRES");
				e.printStackTrace();
				}
			}
		} catch (Exception e) { escreverLog(e, "SELECIONANDO DADOS DA UNIDADE NO POSTGRES");
		e.printStackTrace();
		}

		return unidadeResposta;

	} 

	
	public boolean eUmCpf(String cpf) {
		boolean resposta = true;
		
		if(cpf.length()!=11) {resposta = false;}
		if(resposta==true) {
			for(int i=0;i<cpf.length();i++) {
				if(     (!cpf.substring(i, (i+1)).equalsIgnoreCase("0"))  && 
						(!cpf.substring(i, (i+1)).equalsIgnoreCase("1"))  &&	
						(!cpf.substring(i, (i+1)).equalsIgnoreCase("2"))  &&
						(!cpf.substring(i, (i+1)).equalsIgnoreCase("3"))  &&
						(!cpf.substring(i, (i+1)).equalsIgnoreCase("4"))  &&
						(!cpf.substring(i, (i+1)).equalsIgnoreCase("5"))  &&
						(!cpf.substring(i, (i+1)).equalsIgnoreCase("6"))  &&
						(!cpf.substring(i, (i+1)).equalsIgnoreCase("7"))  &&
						(!cpf.substring(i, (i+1)).equalsIgnoreCase("8"))  &&
						(!cpf.substring(i, (i+1)).equalsIgnoreCase("9"))  
						) { resposta = false; break; }
			}
		}
		
		if(resposta==true) {
			resposta = validaCpfCompleto(cpf);
		}
		
		return resposta;
	} 

	public static boolean validaCpfCompleto (String cpf) {
		boolean resposta = true;
		
		if(cpf.length()!=11) {resposta = false;}else {
			
			String d1=cpf.substring(0, 1);
			String d2=cpf.substring(1, 2);
			String d3=cpf.substring(2, 3);
			String d4=cpf.substring(3, 4);
			String d5=cpf.substring(4, 5);
			String d6=cpf.substring(5, 6);
			String d7=cpf.substring(6, 7);
			String d8=cpf.substring(7, 8);
			String d9=cpf.substring(8, 9);
			String d10=cpf.substring(9, 10);
			String d11=cpf.substring(10, 11);
			resposta = validaCpf(d1, d2, d3, d4, d5, d6, d7, d8, d9, d10, d11);
		}
		
		return resposta;
	}
 
	public static boolean validaCpf(String d1, String d2, String d3, String d4, String d5, String d6, String d7, String d8,
			String d9, String d10, String d11) {

		boolean resposta = false;

		int D1 = Integer.parseInt(d1);
		int D2 = Integer.parseInt(d2);
		int D3 = Integer.parseInt(d3);
		int D4 = Integer.parseInt(d4);
		int D5 = Integer.parseInt(d5);
		int D6 = Integer.parseInt(d6);
		int D7 = Integer.parseInt(d7);
		int D8 = Integer.parseInt(d8);
		int D9 = Integer.parseInt(d9);
		int D10 = Integer.parseInt(d10);
		int D11 = Integer.parseInt(d11);
		int DV1 = 0;
		int DV2 = 0;
		int soma1 = (D1 * 10 + D2 * 9 + D3 * 8 + D4 * 7 + D5 * 6 + D6 * 5 + D7 * 4 + D8 * 3 + D9 * 2);
		int resto1 = soma1 % 11;

		if (resto1 < 2) {
			DV1 = 0;
		} else {
			DV1 = 11 - resto1;
		}

		int soma2 = (D1 * 11 + D2 * 10 + D3 * 9 + D4 * 8 + D5 * 7 + D6 * 6 + D7 * 5 + D8 * 4 + D9 * 3 + DV1 * 2);

		int resto2 = soma2 % 11;

		if (resto2 < 2) {
			DV2 = 0;
		} else {
			DV2 = 11 - resto2;
		}

		if (D10 == DV1 && D11 == DV2) {
			resposta = true;
		}

		return resposta;
	}

	

	public boolean registroJaCadastrado(RegistroPonto registroPonto, Connection con) {
		boolean resposta = false; 
		List<String> listaConsulta = new ArrayList<String>();

		try {


			try {

				PreparedStatement stmt = null;
				if(registroPonto.getIdPessoaFk()!=null) {
					stmt = con.prepareStatement("select * from ponto_registros where numero_ponto = ? and data = ? and hora = ?  and cpf = ? and id_unidade_fk = ? and id_pessoa_fk = ?");
					stmt.setString(1, registroPonto.getNumeroPonto());
					stmt.setDate(2, registroPonto.getMomento());
					stmt.setTime(3, registroPonto.getHora());
					stmt.setString(4, registroPonto.getCpf());
					stmt.setLong(5, registroPonto.getIdUnidadeFk());
					stmt.setLong(6, registroPonto.getIdPessoaFk());
				}else {
					stmt = con.prepareStatement("select * from ponto_registros where numero_ponto = ? and data = ? and hora = ?  and cpf = ? and id_unidade_fk = ? ");
					stmt.setString(1, registroPonto.getNumeroPonto());
					stmt.setDate(2, registroPonto.getMomento());
					stmt.setTime(3, registroPonto.getHora());
					stmt.setString(4, registroPonto.getCpf());
					stmt.setLong(5, registroPonto.getIdUnidadeFk());
				}
				
				

				



				ResultSet rs = stmt.executeQuery();

				while(rs.next()) {

					if(registroPonto.getSentido().equalsIgnoreCase("E")) {
						if((rs.getString("sentido").equalsIgnoreCase("E")) || (rs.getString("sentido").equalsIgnoreCase("A")) || (rs.getString("sentido").equalsIgnoreCase("X"))   || (rs.getString("sentido").equalsIgnoreCase("S")) || (rs.getString("sentido").equalsIgnoreCase("B")) || (rs.getString("sentido").equalsIgnoreCase("Y"))   ) {resposta = true; break;}
					}
					
					if(registroPonto.getSentido().equalsIgnoreCase("S")) {
						if((rs.getString("sentido").equalsIgnoreCase("E")) || (rs.getString("sentido").equalsIgnoreCase("A")) || (rs.getString("sentido").equalsIgnoreCase("X"))   || (rs.getString("sentido").equalsIgnoreCase("S")) || (rs.getString("sentido").equalsIgnoreCase("B")) || (rs.getString("sentido").equalsIgnoreCase("Y"))  ) {resposta = true; break;}
					}
					

				}


			} finally {
				try {

				} catch (Exception e) {escreverLog(e, "OLHANDO SE ESSE REGISTRO DE PONTO JA EXISTE NO POSTGRES. ID:"+registroPonto.getNumeroPonto()+" DATA: "+registroPonto.getMomento()+" HORA: "+registroPonto.getHora()+" Sentido: "+registroPonto.getSentido()+" CPF: "+registroPonto.getCpf()+" Unidade: "+registroPonto.getIdUnidadeFk()+""  );
				e.printStackTrace();
				}
			}
		} catch (Exception e) {escreverLog(e, "OLHANDO SE ESSE REGISTRO DE PONTO JA EXISTE NO POSTGRES. ID:"+registroPonto.getNumeroPonto()+" DATA: "+registroPonto.getMomento()+" HORA: "+registroPonto.getHora()+" Sentido: "+registroPonto.getSentido()+" CPF: "+registroPonto.getCpf()+" Unidade: "+registroPonto.getIdUnidadeFk()+""  );
		e.printStackTrace();
		}

		return resposta;

	} 

	public Long pegarIdPessoaFk(RegistroPonto registroPonto, Connection con) {
		Long resposta = null; 
		
		try {


			try {

				PreparedStatement stmt = con.prepareStatement("select distinct id from pessoa where dt_cancelamento is null and cpf = ? ");

				stmt.setString(1, registroPonto.getCpf());
				


				ResultSet rs = stmt.executeQuery();
				
					while(rs.next()) {
	
						resposta = rs.getLong("id"); break;
	
					}
			
			} finally {
				try {

				} catch (Exception e) {escreverLog(e, "PEGANDO ID DA PESSOA POR CPF NO POSTGRES. CPF: "+registroPonto.getCpf()+"");
				e.printStackTrace();
				}
			}
		} catch (Exception e) {escreverLog(e, "PEGANDO ID DA PESSOA POR CPF NO POSTGRES. CPF: "+registroPonto.getCpf()+"");
		e.printStackTrace();
		}

		return resposta;

	} 

	public List<RegistroPonto> selectListaNomesAccess(java.sql.Date dataConsulta) {
		Configuracao configuracao = new Configuracao();
		List<RegistroPonto> listaConsulta = new ArrayList<RegistroPonto>();

		try {

			con = ConexaoAccess.getConnection();
			conPostgres = ConnectionFactory.getConnection();

			try {

				PreparedStatement stmt = con.prepareStatement("select DISTINCT USERINFO.Name as nome , USERINFO.Badgenumber as numeroPonto,  CHECKINOUT.CHECKTIME as momento, case CHECKINOUT.CHECKTYPE when 'O' then 'S' when 'o' then 'S' when '0' then 'S' when '1' then 'E' when 'I' then 'E' when 'i' then 'E' else 'E' end as sentido , CHECKINOUT.SENSORID as relogio  from CHECKINOUT inner join USERINFO on CHECKINOUT.USERID = USERINFO.USERID where CHECKINOUT.CHECKTIME >= ? order by CHECKINOUT.CHECKTIME");

				stmt.setDate(1, dataConsulta);
				


				ResultSet rs = stmt.executeQuery();

				while(rs.next()) {

					String nome = rs.getString("nome");
					
					String cpf = "";
					if(eUmCpf(nome) == true) {cpf = rs.getString("nome"); nome = "";}
					
					String numeroPonto = rs.getString("numeroPonto");
					java.sql.Date momento = rs.getDate("momento");
					Time hora = rs.getTime("momento");
					String sentido = rs.getString("sentido");
					String relogio = rs.getString("relogio");
					Long idUnidadeFk = configuracao.getIdUnidade();
					
					RegistroPonto registroPonto = new RegistroPonto(nome, numeroPonto, sentido, momento, hora, relogio, cpf, idUnidadeFk, null);
					if(cpf.length()==11) {registroPonto.setIdPessoaFk(pegarIdPessoaFk(registroPonto, conPostgres));}
					
					if(cpf.length()==11) {
						listaConsulta.add(registroPonto);
					}
				}


			} finally {
				try {
					if(con!=null){
						if(!con.isClosed()){
							con.close();
						}}
				} catch (Exception e) {escreverLog(e, "COLETANDO REGISTROS NO ACCESS");
				e.printStackTrace();
				}
			}
		} catch (Exception e) {escreverLog(e, "COLETANDO REGISTROS NO ACCESS");
		e.printStackTrace();
		}

		return listaConsulta;

	} 


	
	public List<RegistroPonto> selectListaNomesAccessComDatas( String dataInicial, String dataFinal) {
		Configuracao configuracao = new Configuracao();
		List<RegistroPonto> listaConsulta = new ArrayList<RegistroPonto>();

		try {

			con = ConexaoAccess.getConnection();
			conPostgres = ConnectionFactory.getConnection();

			try {

				PreparedStatement stmt = con.prepareStatement("select DISTINCT USERINFO.Name as nome , USERINFO.Badgenumber as numeroPonto,  CHECKINOUT.CHECKTIME as momento, case CHECKINOUT.CHECKTYPE when 'O' then 'S' when 'o' then 'S' when '0' then 'S' when '1' then 'E' when 'I' then 'E' when 'i' then 'E' else 'E' end as sentido , CHECKINOUT.SENSORID as relogio  from CHECKINOUT inner join USERINFO on CHECKINOUT.USERID = USERINFO.USERID where CHECKINOUT.CHECKTIME >= ? and CHECKINOUT.CHECKTIME < ?  order by CHECKINOUT.CHECKTIME");

				stmt.setString(1, dataInicial);
				stmt.setString(2, dataFinal);
				


				ResultSet rs = stmt.executeQuery();

				while(rs.next()) {

					String nome = rs.getString("nome");
					
					String cpf = "";
					if(eUmCpf(nome) == true) {cpf = rs.getString("nome"); nome = "";}
					
					String numeroPonto = rs.getString("numeroPonto");
					java.sql.Date momento = rs.getDate("momento");
					Time hora = rs.getTime("momento");
					String sentido = rs.getString("sentido");
					String relogio = rs.getString("relogio");
					Long idUnidadeFk = configuracao.getIdUnidade();
					
					RegistroPonto registroPonto = new RegistroPonto(nome, numeroPonto, sentido, momento, hora, relogio, cpf, idUnidadeFk, null);
					if(cpf.length()==11) {registroPonto.setIdPessoaFk(pegarIdPessoaFk(registroPonto, conPostgres));}
					
					if(cpf.length()==11) {
						listaConsulta.add(registroPonto);
					}
				}


			} finally {
				try {
					if(con!=null){
						if(!con.isClosed()){
							con.close();
						}}
				} catch (Exception e) {escreverLog(e, "COLETANDO REGISTROS NO ACCESS");
				e.printStackTrace();
				}
			}
		} catch (Exception e) {escreverLog(e, "COLETANDO REGISTROS NO ACCESS");
		e.printStackTrace();
		}

		return listaConsulta;

	} 

	
	
	
	public List<String> selectListaPontoNomeAccess() {
		List<String> listaConsulta = new ArrayList<String>();

		try {

			con = ConexaoAccess.getConnection();

			try {

				PreparedStatement stmt = con.prepareStatement("select DISTINCT USERINFO.Name as nome , USERINFO.Badgenumber as numeroPonto from USERINFO  order by 1,2");

				

				ResultSet rs = stmt.executeQuery();

				while(rs.next()) {

					String nome = rs.getString("nome");
					String numeroPonto = rs.getString("numeroPonto");
					
					
					listaConsulta.add(new String (numeroPonto+"-"+nome));

				}


			} finally {
				try {
					if(con!=null){
						if(!con.isClosed()){
							con.close();
						}}
				} catch (Exception e) {escreverLog(e, "COLETANDO PONTO E NOME NO ACCESS");
				e.printStackTrace();
				}
			}
		} catch (Exception e) {escreverLog(e, "COLETANDO PONTO E NOME NO ACCESS");
		e.printStackTrace();
		}

		return listaConsulta;

	} 

	public void trocarNomePorCpfNoAccess(List<String> lista){

		try {

			con = ConexaoAccess.getConnection();

                        for(int i=0;i<lista.size();i++){
                            if(true){
                                                                                   
			PreparedStatement stmt = con.prepareStatement(""+lista.get(i));

            
			stmt.execute();
			stmt.close();
                            }
                }
                        
			con.close();

		} catch (Exception e) {JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		} finally{

			try{
				con.close();

			}catch (Exception e){JOptionPane.showMessageDialog(null, e.getMessage());
			}
                }
        }
    
	

	public List<java.sql.Date> selectMaximaDataAccess() {

		List<java.sql.Date> listaConsulta = new ArrayList<java.sql.Date>();

		try {

			con = ConexaoAccess.getConnection();

			try {
				if(con!=null) {
					PreparedStatement stmt = con.prepareStatement("select Max (CHECKINOUT.CHECKTIME) as momento  from CHECKINOUT inner join USERINFO on CHECKINOUT.USERID = USERINFO.USERID ");

					ResultSet rs = stmt.executeQuery();

					while(rs.next()) {

						java.sql.Date momento = rs.getDate("momento");
						Long momentoInicial = momento.getTime();
						momentoInicial = momentoInicial - (configuracao.getDias()*24*60*60*10*10*10);//menos dois dias
						java.sql.Date momentoFinal = new java.sql.Date(momentoInicial);   
						listaConsulta.add(momentoFinal);

					}
				}
			} finally {
				try {
					if(con!=null){
						if(!con.isClosed()){
							con.close();
						}}
				} catch (Exception e) {escreverLog(e, "PEGANDO A DATA MAXIMA DOS REGISTROS NO ACCESS");
				e.printStackTrace();
				}
			}
		} catch (Exception e) {escreverLog(e, "PEGANDO A DATA MAXIMA DOS REGISTROS NO ACCESS");
		e.printStackTrace();
		}

		return listaConsulta;

	} 

	public void inserirRegistrosNoPostgres(List<RegistroPonto> lista, ImportacaoRegistrosPontoFrame importacaoRegistrosPontoFrame){

		try {
			if(!lista.isEmpty()){	
				Configuracao configuracao = new Configuracao();
				con = ConnectionFactory.getConnection();
				if(con!=null){
					if(!con.isClosed()){
						for(int i=0;i<lista.size();i++){
							
							importacaoRegistrosPontoFrame.setTitle("Registro "+(i+1)+" de "+lista.size());
							
							if(registroJaCadastrado(lista.get(i), con) == false ){
										
										// nome da tebela
										PreparedStatement stmt = null;
										if(lista.get(i).getIdPessoaFk()!=null) {
										stmt = con.prepareStatement("insert into ponto_registros (cpf, nome, numero_ponto, data, hora, sentido, relogio, id_unidade_fk, id_pessoa_fk, observacao, indice_de_dobra) values ( ?,?,?,?,?, ?,?,?,?,?,  ?)");
										stmt.setString(1, lista.get(i).getCpf());            
										stmt.setString(2, lista.get(i).getNome());
										stmt.setString(3, lista.get(i).getNumeroPonto());
										stmt.setDate(4,  lista.get(i).getMomento());
										stmt.setTime(5, lista.get(i).getHora());
										stmt.setString(6, lista.get(i).getSentido());
										stmt.setString(7, lista.get(i).getRelogio());
										stmt.setLong(8, lista.get(i).getIdUnidadeFk());
										stmt.setLong(9, lista.get(i).getIdPessoaFk());
										stmt.setString(10, "");
										stmt.setString(11, "N");
										}else {
											stmt = con.prepareStatement("insert into ponto_registros (cpf, nome, numero_ponto, data, hora, sentido, relogio, id_unidade_fk, observacao, indice_de_dobra) values ( ?,?,?,?,?, ?,?,?,?,?)");
											stmt.setString(1, lista.get(i).getCpf());            
											stmt.setString(2, lista.get(i).getNome());
											stmt.setString(3, lista.get(i).getNumeroPonto());
											stmt.setDate(4,  lista.get(i).getMomento());
											stmt.setTime(5, lista.get(i).getHora());
											stmt.setString(6, lista.get(i).getSentido());
											stmt.setString(7, lista.get(i).getRelogio());
											stmt.setLong(8, lista.get(i).getIdUnidadeFk());
											stmt.setString(9, "");
											stmt.setString(10, "N");
										}
		
		
										stmt.execute();
										stmt.close();
								
							}
						}
					}  
				}
				if(con!=null){
					if(!con.isClosed()){
						con.close();
					}
				}
			}
		} catch (Exception e) {escreverLog(e, "INSERINDO REGISTROS NO POSTGRES." );
		e.printStackTrace();
		} finally{

			try{
				if(con!=null){
					if(!con.isClosed()){
						con.close();
					}}

			}catch (Exception e){escreverLog(e, "INSERINDO REGISTROS NO POSTGRES.");
			}
		}
	}

	
	
	
	
	public List<RegistroPonto> apagarTabelaCheckInOutAccess(java.sql.Date dataConsulta) {
		
		List<RegistroPonto> listaConsulta = new ArrayList<RegistroPonto>();

		try {

			con = ConexaoAccess.getConnection();

			try {

				PreparedStatement stmt = con.prepareStatement("delete from CHECKINOUT where CHECKTIME <= (?) ");

				stmt.setDate(1, dataConsulta);
				
				stmt.execute();
				


			} finally {
				try {
					if(con!=null){
						if(!con.isClosed()){
							con.close();
						}}
				} catch (Exception e) {escreverLog(e, "COLETANDO REGISTROS NO ACCESS");
				e.printStackTrace();
				}
			}
		} catch (Exception e) {escreverLog(e, "COLETANDO REGISTROS NO ACCESS");
		e.printStackTrace();
		}

		return listaConsulta;

	} 

	
	
	
	
}
