package com.desafio.financeiro.domain.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.desafio.financeiro.domain.validator.EnumListValidator;
import com.desafio.financeiro.domain.validator.EnumValidator;

/**
* Anotação para validar os valores do Enum
*
* Exemplo de Utilização
*
* @EnumValue(enumClass=Enum.class, ignoreCase=true)
  String atributoEnum;
*
* @author nrfreire
*
*/
@Documented
@Constraint(validatedBy = {EnumValidator.class, EnumListValidator.class})
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface EnumValues {
	
	public abstract String message() default "Opção não encontrada no Enum.";
	   
    public abstract Class<?>[] groups() default {};
 
    public abstract Class<? extends Payload>[] payload() default {};
     
    public abstract Class<? extends java.lang.Enum<?>> enumClass();
     
    public abstract boolean ignoreCase() default false;
   
    public abstract boolean acceptNull() default false;
}
