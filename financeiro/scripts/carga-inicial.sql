
INSERT INTO USER_MV.EMPRESA (ID_EMPRESA, CNPJ, NOME) VALUES(1, '53.053.645/0001-59', 'EMPRESA TESTE');

INSERT INTO USER_MV.ENDERECO (ID_ENDERECO, ATIVO, BAIRRO, CEP, COMPLEMENTO, LOCALIDADE, LOGRADOURO, NUMERO, UF) VALUES(1, 'Y', 'Centro', '58300-103', NULL, 'Santa Rita', 'Travessa Doutor Fonseca', '1', 'PB');
INSERT INTO USER_MV.ENDERECO (ID_ENDERECO, ATIVO, BAIRRO, CEP, COMPLEMENTO, LOCALIDADE, LOGRADOURO, NUMERO, UF) VALUES(7, 'Y', 'Jardim Paulistano', '58415-075', 'AP 547', 'Campina Grande', 'Rua José Luiz Guimarães', '15', 'PB');

INSERT INTO USER_MV.PESSOA (ID_PESSOA, DATA_NASCIMENTO, NOME) VALUES(5, TIMESTAMP '2000-05-15 00:00:00.000000', 'Manoel do Teste');
INSERT INTO USER_MV.PESSOA (ID_PESSOA, DATA_NASCIMENTO, NOME) VALUES(7, TIMESTAMP '2000-07-14 00:00:00.000000', 'Fulano da Silva e Sousa');

INSERT INTO USER_MV.CLIENTE (TIPO_CLIENTE, ID_CLIENTE, ATIVO, DATA_CADASTRO, EMAIL, NUMERO_TELEFONE, CPF, CNPJ, ID_EMPRESA, ID_ENDERECO, ID_PESSOA) VALUES('PF', 5, 'Y', TIMESTAMP '2020-08-24 00:00:00.000000', 'edit@teste.com', '869944144', '049.565.580-58', NULL, 1, 7, 5);
INSERT INTO USER_MV.CLIENTE (TIPO_CLIENTE, ID_CLIENTE, ATIVO, DATA_CADASTRO, EMAIL, NUMERO_TELEFONE, CPF, CNPJ, ID_EMPRESA, ID_ENDERECO, ID_PESSOA) VALUES('PJ', 7, 'Y', TIMESTAMP '2020-08-24 00:00:00.000000', 'teste@email.com', '8754187989', NULL, '18.076.097/0001-81', 1, 1, 7);

INSERT INTO USER_MV.CONTA (ID_CONTA, AGENCIA, ATIVO, NUMERO_CONTA, SALDO_INICIAL, ID_CLIENTE) VALUES(2, '5317', 'Y', '1268659-X', 1500, 7);
INSERT INTO USER_MV.CONTA (ID_CONTA, AGENCIA, ATIVO, NUMERO_CONTA, SALDO_INICIAL, ID_CLIENTE) VALUES(3, '3326', 'Y', '142906-X', 2500, 5);


INSERT INTO USER_MV.MOVIMENTACAO (ID_MOVIMENTACAO, DATA_MOVIMENTACAO, MOVIMENTACAO_INICIAL, TIPO_MOVIMENTACAO, VALOR_MOVIMENTACAO, ID_CONTA, ID_EMPRESA) VALUES(1, TIMESTAMP '2020-08-24 00:00:00.000000', 'Y', 'C', 1500, 2, 1);
INSERT INTO USER_MV.MOVIMENTACAO (ID_MOVIMENTACAO, DATA_MOVIMENTACAO, MOVIMENTACAO_INICIAL, TIPO_MOVIMENTACAO, VALOR_MOVIMENTACAO, ID_CONTA, ID_EMPRESA) VALUES(2, TIMESTAMP '2020-08-24 00:00:00.000000', 'Y', 'C', 2500, 3, 1);
INSERT INTO USER_MV.MOVIMENTACAO (ID_MOVIMENTACAO, DATA_MOVIMENTACAO, MOVIMENTACAO_INICIAL, TIPO_MOVIMENTACAO, VALOR_MOVIMENTACAO, ID_CONTA, ID_EMPRESA) VALUES(3, TIMESTAMP '2020-08-24 00:00:00.000000', 'N', 'C', 150, 2, 1);

CREATE OR REPLACE
PROCEDURE
	get_valor_pago_movimentacoes(total_movimentacao IN NUMBER, 
	valor_movimentacao OUT NUMBER) 
	
IS
	valor NUMBER(19,2) := 0;

BEGIN
	IF
	total_movimentacao <= 10 THEN 
	valor := 1.00;
	ELSIF total_movimentacao > 10 AND total_movimentacao <= 20 THEN 
	valor := 0.75;
	ELSE 
	valor := 0.50;
	END IF;

	valor_movimentacao := valor;
END;