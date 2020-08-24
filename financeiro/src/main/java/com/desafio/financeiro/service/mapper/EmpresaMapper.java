package com.desafio.financeiro.service.mapper;

import com.desafio.financeiro.domain.dto.empresa.EmpresaDTO;
import com.desafio.financeiro.domain.entity.Empresa;

/**
 * 
 * @author nrfreire
 *
 */
public class EmpresaMapper {

	private EmpresaMapper() {
		
	}
	
	public static EmpresaDTO mapper (Empresa empresa) {
		
		EmpresaDTO empresaDTO = new EmpresaDTO();
		
		empresaDTO.setId(empresa.getId());
		empresaDTO.setCnpj(empresa.getCnpj());
		empresaDTO.setNome(empresa.getNome());
		
		return empresaDTO;
	}
}
