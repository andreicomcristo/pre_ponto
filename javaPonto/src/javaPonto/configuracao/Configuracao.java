package javaPonto.configuracao;



public class Configuracao {

	//Para o HGE
	//Long idUnidade = 1L;
	
	//Para HOSPITAL REGIONAL DA MATA
	//Long idUnidade = 2L;
	
	//Para HOSPITAL REGIONAL DO NORTE
	Long idUnidade = 3L;
		
	//Para SAMU MACEIO
	//Long idUnidade = 4L;
	
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

	@Override
	public String toString() {
		return "Configuracao [idUnidade=" + idUnidade + "]";
	}


	
	
}
