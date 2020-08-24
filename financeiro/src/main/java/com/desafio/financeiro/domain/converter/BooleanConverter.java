package com.desafio.financeiro.domain.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.apache.commons.lang3.BooleanUtils;

import com.desafio.financeiro.domain.enums.ActiveEnum;

/**
 * Classe responsável por realizar a conversão do Enum {@link ActiveEnum} para Boolean
 * e vice e versa.
 * 
 * @author nrfreire
 *
 */
@Converter(autoApply = true)
public class BooleanConverter implements AttributeConverter<Boolean, String> {

	@Override
	public String convertToDatabaseColumn(Boolean attribute) {
		if(attribute == null || BooleanUtils.isFalse(attribute)) {
			return ActiveEnum.N.toString();
		}
		return ActiveEnum.Y.toString(); 
	}

	@Override
	public Boolean convertToEntityAttribute(String dbData) {
		if(ActiveEnum.N.toString().equals(dbData)) {
			return Boolean.FALSE;
		}
		return Boolean.TRUE; 
	}

}
