package javaPonto.domain;

import java.sql.Time;
import java.sql.Date;

public class RegistroPonto {

	String nome;
	String numeroPonto;
	String sentido;
	Date momento;
	Time hora;
	String relogio;
	String cpf;
	Long idUnidadeFk;
	Long idPessoaFk;
	
	public RegistroPonto() {
		
	}

	public RegistroPonto(String nome, String numeroPonto, String sentido, Date momento, Time hora, String relogio,
			String cpf, Long idUnidadeFk, Long idPessoaFk) {
		super();
		this.nome = nome;
		this.numeroPonto = numeroPonto;
		this.sentido = sentido;
		this.momento = momento;
		this.hora = hora;
		this.relogio = relogio;
		this.cpf = cpf;
		this.idUnidadeFk = idUnidadeFk;
		this.idPessoaFk = idPessoaFk;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNumeroPonto() {
		return numeroPonto;
	}

	public void setNumeroPonto(String numeroPonto) {
		this.numeroPonto = numeroPonto;
	}

	public String getSentido() {
		return sentido;
	}

	public void setSentido(String sentido) {
		this.sentido = sentido;
	}

	public Date getMomento() {
		return momento;
	}

	public void setMomento(Date momento) {
		this.momento = momento;
	}

	public Time getHora() {
		return hora;
	}

	public void setHora(Time hora) {
		this.hora = hora;
	}

	public String getRelogio() {
		return relogio;
	}

	public void setRelogio(String relogio) {
		this.relogio = relogio;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Long getIdUnidadeFk() {
		return idUnidadeFk;
	}

	public void setIdUnidadeFk(Long idUnidadeFk) {
		this.idUnidadeFk = idUnidadeFk;
	}

	public Long getIdPessoaFk() {
		return idPessoaFk;
	}

	public void setIdPessoaFk(Long idPessoaFk) {
		this.idPessoaFk = idPessoaFk;
	}

	@Override
	public String toString() {
		return "RegistroPonto [nome=" + nome + ", numeroPonto=" + numeroPonto + ", sentido=" + sentido + ", momento="
				+ momento + ", hora=" + hora + ", relogio=" + relogio + ", cpf=" + cpf + ", idUnidadeFk=" + idUnidadeFk
				+ ", idPessoaFk=" + idPessoaFk + "]";
	}


	

	
	
}
