package javaPonto.domain;



public class Unidade {

	
	Long idUnidade;
	String nome;
	String sigla;

	
	public Unidade() {
		
	}


	public Unidade(Long idUnidade, String nome, String sigla) {
		super();
		this.idUnidade = idUnidade;
		this.nome = nome;
		this.sigla = sigla;
	}


	public Long getIdUnidade() {
		return idUnidade;
	}


	public void setIdUnidade(Long idUnidade) {
		this.idUnidade = idUnidade;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getSigla() {
		return sigla;
	}


	public void setSigla(String sigla) {
		this.sigla = sigla;
	}


	@Override
	public String toString() {
		return "Unidade [idUnidade=" + idUnidade + ", nome=" + nome + ", sigla=" + sigla + "]";
	}

		
	
}
