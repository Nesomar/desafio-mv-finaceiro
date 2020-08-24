package com.desafio.financeiro.domain.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.desafio.financeiro.domain.enums.TipoMovimentacaoEnum;

/**
 * Converter o Enum {@link TipoMovimentacaoEnum} para o C/D que serão armazenados no banco
 * 
 * @author nrfreire
 *
 */
@Converter(autoApply = true)
public class TipoMovimentacaoConverter implements AttributeConverter<TipoMovimentacaoEnum, String> {

	private static final String VALOR_INFORMADO_NAO_LOCALIZADO_PARA_O_ENUM = "Valor Informado não Localizado para o Enum.";
	private static final String DEBITO = "D";
	private static final String CREDITO = "C";

	@Override
	public String convertToDatabaseColumn(TipoMovimentacaoEnum attribute) {
		
		if (attribute != null) {
			
			switch (attribute) {
			case CREDITO:
				return CREDITO;
			case DEBITO:
				return DEBITO;
			default:
				throw new IllegalArgumentException(VALOR_INFORMADO_NAO_LOCALIZADO_PARA_O_ENUM);
			}
		} else {
			return null;
		}
	}

	@Override
	public TipoMovimentacaoEnum convertToEntityAttribute(String dbData) {
		
		switch (dbData) {
		case CREDITO:
			return TipoMovimentacaoEnum.CREDITO;
		case DEBITO:
			return TipoMovimentacaoEnum.DEBITO;
		default:
			throw new IllegalArgumentException(VALOR_INFORMADO_NAO_LOCALIZADO_PARA_O_ENUM);
		}
	}

}
