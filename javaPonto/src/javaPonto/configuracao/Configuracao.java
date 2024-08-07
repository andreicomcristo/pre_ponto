package javaPonto.configuracao;



public class Configuracao {

	
	boolean exibirImportarRegistros = true;
	
	boolean exibirConverteCpf = false;
	
	boolean exibirApagarRegistros = false;
	
	String conexaoDestino = "";
	
	int minutosSleep = 10;
	

	String tipoRegistro = "PONTO";
	
	Long idUnidade = null;	
	String caminhoBanco = "";
	
	
	int dias = 1;
	//int dias = 2;
	//int dias = 10;
	//int dias = 30;
	//int dias = 60;
	
	
	//Para o HGE
	//Long idUnidade = 1L;
	//String caminhoBanco = "jdbc:ucanaccess://k:/NPD/Database_ponto/att2000.mdb";
	//String caminhoBanco = "jdbc:ucanaccess://C:/zktime/att2000.mdb";
	
	
	//Para IB GATTO
	//Long idUnidade = 17L;	
	//String caminhoBanco = "jdbc:ucanaccess://c:/zktime/att2000.mdb";

	
	//Para SESAU
	//Long idUnidade = 16L;	
	//String caminhoBanco = "jdbc:ucanaccess://c:/zktime/att2000.mdb";

	
	//Para HOSPITAL REGIONAL DA MATA
	//Long idUnidade = 2L;
	//String caminhoBanco = "jdbc:ucanaccess://c:/zktime/att2000.mdb";
	
	//Para HOSPITAL REGIONAL DO NORTE
	//Long idUnidade = 3L;
	//String caminhoBanco = "jdbc:ucanaccess://c:/zktime/att2000.mdb";
		
	//Para SAMU MACEIO
	//Long idUnidade = 4L;
	//String caminhoBanco = "jdbc:ucanaccess://c:/zktime/att2000.mdb";
	
	//Para SAMU ARAPIRACA
	//Long idUnidade = 5L;
	//String caminhoBanco = "jdbc:ucanaccess://c:/zktime/att2000.mdb";
	
	//Para HOSPITAL METROPOLITANO DE ALAGOAS
	//Long idUnidade = 8L;
	//String caminhoBanco = "jdbc:ucanaccess://c:/zktime/att2000.mdb";
	
	//Para TESTE
	//Long idUnidade = 9L;
	//String caminhoBanco = "jdbc:ucanaccess://c:/zktime/att2000.mdb";
	
	//Para HOSPITAL DA MULHER MACEIO
	//Long idUnidade = 7L;
	//String caminhoBanco = "jdbc:ucanaccess://c:/zktime/att2000.mdb";
	
	//Para TREINAMENTO
	//Long idUnidade = 10L;	
	//String caminhoBanco = "jdbc:ucanaccess://c:/zktime/att2000.mdb";

	//Para HOSPITAL DE EMERGENCIA DANIEL HOULY
	//Long idUnidade = 6L;	
	//String caminhoBanco = "jdbc:ucanaccess://z://PONTO//att2000.mdb";
	
	//Para HOSPITAL DA CRIANCA
	//Long idUnidade = 11L;	
	//String caminhoBanco = "jdbc:ucanaccess://c:/zktime/att2000.mdb";
	
	//Para HEMOAL MACEIO
	//Long idUnidade = 12L;	
	//String caminhoBanco = "jdbc:ucanaccess://c:/zktime/att2000.mdb";
	
	//Para HEMOAL ARAPIRACA
	//Long idUnidade = 31L;	
	//String caminhoBanco = "jdbc:ucanaccess://c:/zktime/att2000.mdb";
	
	//Para HOSPITAL DO CORACAO MACEIO
	//Long idUnidade = 13L;	
	//String caminhoBanco = "jdbc:ucanaccess://c:/zktime/att2000.mdb";
	
	//Para UPA JARAGUA
	//Long idUnidade = 15L;	
	//String caminhoBanco = "jdbc:ucanaccess://c:/zktime/att2000.mdb";
	
	//Para UPA ARAPIRACA
	//Long idUnidade = 14L;	
	//String caminhoBanco = "jdbc:ucanaccess://c:/zktime/att2000.mdb";
	
	//Para HOSPITAL RETGIONAL ALTO SERTAO
	//Long idUnidade = 18L;	
	//String caminhoBanco = "jdbc:ucanaccess://c:/zktime/att2000.mdb";
	
	//Para UPA CIDADE UNIVERSITARIA
	//Long idUnidade = 29L;	
	//String caminhoBanco = "jdbc:ucanaccess://c:/zktime/att2000.mdb";
	
	
	

	
	
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

	
	
	public boolean isExibirImportarRegistros() {
		return exibirImportarRegistros;
	}

	public void setExibirImportarRegistros(boolean exibirImportarRegistros) {
		this.exibirImportarRegistros = exibirImportarRegistros;
	}

	public boolean isExibirConverteCpf() {
		return exibirConverteCpf;
	}

	public void setExibirConverteCpf(boolean exibirConverteCpf) {
		this.exibirConverteCpf = exibirConverteCpf;
	}

	public boolean isExibirApagarRegistros() {
		return exibirApagarRegistros;
	}

	public void setExibirApagarRegistros(boolean exibirApagarRegistros) {
		this.exibirApagarRegistros = exibirApagarRegistros;
	}

	public int getMinutosSleep() {
		return minutosSleep;
	}

	public void setMinutosSleep(int minutosSleep) {
		this.minutosSleep = minutosSleep;
	}

	

	public String getTipoRegistro() {
		return tipoRegistro;
	}

	public void setTipoRegistro(String tipoRegistro) {
		this.tipoRegistro = tipoRegistro;
	}

	public String getConexaoDestino() {
		return conexaoDestino;
	}

	public void setConexaoDestino(String conexaoDestino) {
		this.conexaoDestino = conexaoDestino;
	}

	
	


	
	
}
