
INSERT INTO USER_MV.EMPRESA (ID_EMPRESA, CNPJ, NOME) VALUES(1, '53.053.645/0001-59', 'EMPRESA TESTE');

INSERT INTO USER_MV.ENDERECO (ID_ENDERECO, ATIVO, BAIRRO, CEP, COMPLEMENTO, LOCALIDADE, LOGRADOURO, NUMERO, UF) VALUES(1, 'Y', 'Centro', '58300-103', NULL, 'Santa Rita', 'Travessa Doutor Fonseca', '1', 'PB');
INSERT INTO USER_MV.ENDERECO (ID_ENDERECO, ATIVO, BAIRRO, CEP, COMPLEMENTO, LOCALIDADE, LOGRADOURO, NUMERO, UF) VALUES(7, 'Y', 'Jardim Paulistano', '58415-075', 'AP 547', 'Campina Grande', 'Rua José Luiz Guimarães', '15', 'PB');

INSERT INTO USER_MV.PESSOA (ID_PESSOA, DATA_NASCIMENTO, NOME) VALUES(5, TIMESTAMP '2000-05-15 00:00:00.000000', 'Manoel do Teste');
INSERT INTO USER_MV.PESSOA (ID_PESSOA, DATA_NASCIMENTO, NOME) VALUES(7, TIMESTAMP '2000-07-14 00:00:00.000000', 'Fulano da Silva e Sousa');

INSERT INTO USER_MV.CLIENTE (TIPO_CLIENTE, ID_CLIENTE, ATIVO, DATA_CADASTRO, EMAIL, NUMERO_TELEFONE, CPF, CNPJ, ID_EMPRESA, ID_ENDERECO, ID_PESSOA) VALUES('PF', 5, 'Y', TIMESTAMP '2020-08-24 00:00:00.000000', 'edit@teste.com', '869944144', '049.565.580-58', NULL, 1, 7, 5);
INSERT INTO USER_MV.CLIENTE (TIPO_CLIENTE, ID_CLIENTE, ATIVO, DATA_CADASTRO, EMAIL, NUMERO_TELEFONE, CPF, CNPJ, ID_EMPRESA, ID_ENDERECO, ID_PESSOA) VALUES('PJ', 7, 'Y', TIMESTAMP '2020-08-24 00:00:00.000000', 'teste@email.com', '8754187989', NULL, '18.076.097/0001-81', 1, 1, 7);

INSERT INTO USER_MV.MOVIMENTACAO (ID_MOVIMENTACAO, DATA_MOVIMENTACAO, MOVIMENTACAO_INICIAL, TIPO_MOVIMENTACAO, VALOR_MOVIMENTACAO, ID_CONTA, ID_EMPRESA) VALUES(1, TIMESTAMP '2020-08-24 00:00:00.000000', 'Y', 'C', 1500, 2, 1);
INSERT INTO USER_MV.MOVIMENTACAO (ID_MOVIMENTACAO, DATA_MOVIMENTACAO, MOVIMENTACAO_INICIAL, TIPO_MOVIMENTACAO, VALOR_MOVIMENTACAO, ID_CONTA, ID_EMPRESA) VALUES(2, TIMESTAMP '2020-08-24 00:00:00.000000', 'Y', 'C', 2500, 3, 1);
INSERT INTO USER_MV.MOVIMENTACAO (ID_MOVIMENTACAO, DATA_MOVIMENTACAO, MOVIMENTACAO_INICIAL, TIPO_MOVIMENTACAO, VALOR_MOVIMENTACAO, ID_CONTA, ID_EMPRESA) VALUES(3, TIMESTAMP '2020-08-24 00:00:00.000000', 'N', 'C', 150, 2, 1);
