package com.desafio.financeiro.service.mapper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.validation.Valid;

import com.desafio.financeiro.domain.dto.cliente.AddClienteDTO;
import com.desafio.financeiro.domain.dto.cliente.ClienteDTO;
import com.desafio.financeiro.domain.dto.cliente.ClienteResumoDTO;
import com.desafio.financeiro.domain.dto.empresa.EmpresaDTO;
import com.desafio.financeiro.domain.dto.endereco.EnderecoDTO;
import com.desafio.financeiro.domain.dto.pessoa.PessoaDTO;
import com.desafio.financeiro.domain.entity.Cliente;
import com.desafio.financeiro.domain.entity.ClientePF;
import com.desafio.financeiro.domain.entity.ClientePJ;
import com.desafio.financeiro.domain.entity.Empresa;
import com.desafio.financeiro.domain.entity.Endereco;
import com.desafio.financeiro.domain.entity.Pessoa;

/**
 * 
 * @author nrfreire
 *
 */
public class ClienteMapper {

	private static final String DD_MM_YYYY = "dd/MM/yyyy";

	private ClienteMapper() {
		
	}
	
	public static ClienteDTO mapper(Cliente cliente) {
		
		ClienteDTO clienteDTO = new ClienteDTO();
		
		clienteDTO.setAtivo(cliente.getAtivo());
		clienteDTO.setDataCadastro(cliente.getDataCadastro().format(DateTimeFormatter.ofPattern(DD_MM_YYYY)));
		clienteDTO.setEmail(cliente.getEmail());
		clienteDTO.setEmpresa(recuperarEmpresaDTO(cliente.getEmpresa()));
		clienteDTO.setId(cliente.getId());
		clienteDTO.setNumeroTelefone(cliente.getNumeroTelefone());
		clienteDTO.setPessoa(recuperarPessoaDTO(cliente.getPessoa()));
		clienteDTO.setEndereco(recuperarEnderecoDTO(cliente.getEndereco()));
		
		return clienteDTO;
	}
	
	private static EnderecoDTO recuperarEnderecoDTO(Endereco endereco) {
		
		EnderecoDTO enderecoDTO = new EnderecoDTO();
		
		enderecoDTO.setBairro(endereco.getBairro());
		enderecoDTO.setCep(endereco.getCep());
		enderecoDTO.setComplemento(endereco.getComplemento());
		enderecoDTO.setId(endereco.getId());
		enderecoDTO.setLocalidade(endereco.getLocalidade());
		enderecoDTO.setLogradouro(endereco.getLogradouro());
		enderecoDTO.setNumero(endereco.getNumero());
		enderecoDTO.setUf(endereco.getUf());
		
		return enderecoDTO;
	}

	public static ClienteResumoDTO mapperResumo(Cliente cliente) {
		
		ClienteResumoDTO clienteDTO = new ClienteResumoDTO();
		
		clienteDTO.setAtivo(cliente.getAtivo());
		clienteDTO.setDataCadastro(cliente.getDataCadastro().format(DateTimeFormatter.ofPattern(DD_MM_YYYY)));
		clienteDTO.setEmail(cliente.getEmail());
		clienteDTO.setId(cliente.getId());
		clienteDTO.setNumeroTelefone(cliente.getNumeroTelefone());
		
		return clienteDTO;
	}
	
	private static PessoaDTO recuperarPessoaDTO(Pessoa pessoa) {
		
		PessoaDTO pessoaDTO = new PessoaDTO();
		
		pessoaDTO.setDataNascimento(pessoa.getDataNascimento().format(DateTimeFormatter.ofPattern(DD_MM_YYYY)));
		pessoaDTO.setId(pessoa.getId());
		pessoaDTO.setNome(pessoa.getNome());
		
		return pessoaDTO;
	}

	private static EmpresaDTO recuperarEmpresaDTO(Empresa empresa) {
		
		EmpresaDTO empresaDTO = new EmpresaDTO();
		
		empresaDTO.setCnpj(empresa.getCnpj());
		empresaDTO.setId(empresa.getId());
		empresaDTO.setNome(empresa.getNome());
		
		return empresaDTO;
	}

	public static Cliente mapper(Empresa empresa, Pessoa pessoa, Endereco endereco, @Valid AddClienteDTO addClienteDTO) {
	
		Cliente cliente = new Cliente();
		
		cliente.setAtivo(Boolean.TRUE);
		cliente.setDataCadastro(LocalDate.now());
		cliente.setEmail(addClienteDTO.getEmail());
		cliente.setEmpresa(empresa);
		cliente.setEndereco(endereco);
		cliente.setNumeroTelefone(addClienteDTO.getNumeroTelefone());
		cliente.setPessoa(pessoa);
		
		return cliente;
	}

	public static ClientePF mapperPF(Empresa empresa, Pessoa pessoa, Endereco endereco, AddClienteDTO addClienteDTO) {

		ClientePF cliente = new ClientePF();

		cliente.setAtivo(Boolean.TRUE);
		cliente.setDataCadastro(LocalDate.now());
		cliente.setEmail(addClienteDTO.getEmail());
		cliente.setEmpresa(empresa);
		cliente.setEndereco(endereco);
		cliente.setNumeroTelefone(addClienteDTO.getNumeroTelefone());
		cliente.setPessoa(pessoa);
		cliente.setCpf(addClienteDTO.getDocumento());

		return cliente;
	}

	public static ClientePJ mapperPJ(Empresa empresa, Pessoa pessoa, Endereco endereco, AddClienteDTO addClienteDTO) {
		
		ClientePJ cliente = new ClientePJ();

		cliente.setAtivo(Boolean.TRUE);
		cliente.setDataCadastro(LocalDate.now());
		cliente.setEmail(addClienteDTO.getEmail());
		cliente.setEmpresa(empresa);
		cliente.setEndereco(endereco);
		cliente.setNumeroTelefone(addClienteDTO.getNumeroTelefone());
		cliente.setPessoa(pessoa);
		cliente.setCnpj(addClienteDTO.getDocumento());

		return cliente;
	}
}
