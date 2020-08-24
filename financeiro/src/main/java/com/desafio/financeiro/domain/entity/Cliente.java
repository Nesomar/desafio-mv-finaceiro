package com.desafio.financeiro.domain.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "CLIENTE")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TIPO_CLIENTE")
@SequenceGenerator(name = "SEQ_CLIENTE", sequenceName = "SEQ_CLIENTE", allocationSize = 1, initialValue = 1)
public class Cliente implements Serializable {

	private static final long serialVersionUID = 2170414524787314745L;
	
	@Id
	@Column(name = "ID_CLIENTE")
	@GeneratedValue(generator = "SEQ_CLIENTE", strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@NotBlank
	@Size(max = 20)
	@Column(name = "NUMERO_TELEFONE")
	private String numeroTelefone;
	
	@Email
	@NotBlank
	@Size(max = 100)
	@Column(name = "EMAIL")
	private String email;
	
	@NotNull
	@Column(name = "ATIVO")
	private Boolean ativo;
	
	@NotNull
	@Column(name = "DATA_CADASTRO")
	private LocalDate dataCadastro;
	
	@OneToOne
	@NotNull
	@JoinColumn(name = "ID_PESSOA", foreignKey = @ForeignKey(name = "FK_PESSOA_CLIENTE"))
	private Pessoa pessoa;
	
	@OneToOne
	@NotNull
	@JoinColumn(name = "ID_EMPRESA", foreignKey = @ForeignKey(name = "FK_EMPRESA_CLIENTE"))
	private Empresa empresa;
	
	@OneToOne
	@NotNull
	@JoinColumn(name = "ID_ENDERECO", foreignKey = @ForeignKey(name = "FK_ENDERECO_CLIENTE"))
	private Endereco endereco;

	public String getNumeroTelefone() {
		return numeroTelefone;
	}

	public void setNumeroTelefone(String numeroTelefone) {
		this.numeroTelefone = numeroTelefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public LocalDate getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDate dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Long getId() {
		return id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((pessoa == null) ? 0 : pessoa.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (pessoa == null) {
			if (other.pessoa != null)
				return false;
		} else if (!pessoa.equals(other.pessoa))
			return false;
		return true;
	}
}
