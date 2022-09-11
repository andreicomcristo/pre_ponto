package javaPonto.configuracao;



public class Configuracao {

	int dias = 2;
	//int dias = 10;
	
	String caminhoBanco = "jdbc:ucanaccess://c:/zktime/att2000.mdb";
	//String caminhoBanco = "jdbc:ucanaccess://k:/NPD/Database_ponto/att2000.mdb";
	
	//Para o HGE
	//Long idUnidade = 1L;
	
	//Para HOSPITAL REGIONAL DA MATA
	//Long idUnidade = 2L;
	
	//Para HOSPITAL REGIONAL DO NORTE
	//Long idUnidade = 3L;
		
	//Para SAMU MACEIO
	Long idUnidade = 4L;
	
	//Para SAMU ARAPIRACA
	//Long idUnidade = 5L;
	
	//Para HOSPITAL METROPOLITANO DE ALAGOAS
	//Long idUnidade = 8L;
	
	//Para TESTE
	//Long idUnidade = 9L;
	
	//Para HOSPITAL DA MULHER MACEIO
	//Long idUnidade = 7L;
	
	//Para TREINAMENTO
	//Long idUnidade = 10L;	

	//Para HOSPITAL DE EMERGENCIA DANIEL HOULY
	//Long idUnidade = 6L;	
	
	
	//Para HOSPITAL DA CRIANCA
	//Long idUnidade = 11L;	
	

	
	
	public Configuracao() {
		
	}

	public Configuracao(Long idUnidade) {
		super();
		this.idUnidade = idUnidade;
	}

	public Long getIdUnidade() {
		return idUnidade;
	}

	public void setIdUnidade(Long idUnidade) {
		this.idUnidade = idUnidade;
	}

	
	
	
	public int getDias() {
		return dias;
	}

	public void setDias(int dias) {
		this.dias = dias;
	}

	
	
	public String getCaminhoBanco() {
		return caminhoBanco;
	}

	public void setCaminhoBanco(String caminhoBanco) {
		this.caminhoBanco = caminhoBanco;
	}

	@Override
	public String toString() {
		return "Configuracao [dias=" + dias + ", idUnidade=" + idUnidade + "]";
	}

	


	
	
}
