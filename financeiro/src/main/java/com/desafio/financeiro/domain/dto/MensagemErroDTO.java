package com.desafio.financeiro.domain.dto;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

/**
 * 
 * @author nrfreire
 *
 */
public class MensagemErroDTO implements Serializable {

	private static final long serialVersionUID = 7507482266933845621L;
	
	private Integer codigo;
	@JsonDeserialize(using = LocalDateDeserializer.class)
	private LocalDate timestamp;
	private String mensagem;
	private String detalhe;

	public MensagemErroDTO() {
	}

	public MensagemErroDTO(int codigo, LocalDate timestamp, String mensagem, String detalhe) {
		super();
		this.codigo = codigo;
		this.timestamp = timestamp;
		this.mensagem = mensagem;
		this.detalhe = detalhe;
	}
	
	private MensagemErroDTO (Builder builder) {
		this.codigo = builder.codigo;
		this.timestamp = builder.timestamp;
		this.mensagem = builder.mensagem;
		this.detalhe = builder.detalhe;
	}
	
	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public LocalDate getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDate timestamp) {
		this.timestamp = timestamp;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getDetalhe() {
		return detalhe;
	}

	public void setDetalhe(String detalhe) {
		this.detalhe = detalhe;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static final class Builder {
		private Integer codigo;
		private LocalDate timestamp;
		private String mensagem;
		private String detalhe;
		
		private Builder() {
		}
		
		public Builder addCodigo(Integer codigo) {
			this.codigo = codigo;
			return this;
		}
		
		public Builder addTimestamp(LocalDate timestamp) {
			this.timestamp = timestamp;
			return this;
		}
		
		public Builder addMensagem(String mensagem) {
			this.mensagem = mensagem;
			return this;
		}
		
		public Builder addDetalhe(String detalhe) {
			this.detalhe = detalhe;
			return this;
		}
		
		public MensagemErroDTO build() {
			return new MensagemErroDTO(this);
		}
	}
}
