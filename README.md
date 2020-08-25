# desafio-mv-finaceiro
Repositório com o projeto desafio da Célula Finaceiro e Controladoria da MV.

Para o desafio foram criados APIS utilizando o Spring Boot, que podem ser acessadas no link da documentação.  
http://localhost:8080/swagger-ui.html

# Instalação do Projeto

1 - Fazer o clone do projeto e realizar a importação na sua IDE.

2 - Criar um usuário do Oracle conforme as configurações abaixo, as configurações
    podem ser alteradas no arquivo https://github.com/Nesomar/desafio-mv-finaceiro/blob/master/financeiro/src/main/resources/application.properties.

    spring.datasource.url=jdbc:oracle:thin:@localhost:1521:orcl19c
    spring.datasource.username=USER_MV
    spring.datasource.password=DesafioMV1
    spring.datasource.driver.class=oracle.jdbc.driver.OracleDriver
    
3 - Execultar os spripts de carga inicial.  
    Link - https://github.com/Nesomar/desafio-mv-finaceiro/tree/master/financeiro/scripts
    
4 - Execultar o Maven update e o Clean install.

5 - Execultar o Run Java Application na classe **FinanceiroApplication.java**.

# Estrutura do Projeto
O projeto está dividido nos pacotes.  
  1 - Config - Pacote destinado aos arquivos de configurações.  
  2 - domain - Pacote destinado a conter a camada de persistência com as entidades, repositórios, DTO, Enums, validadores e Converter utilizados no projeto.    
  3 - service - Pacote destinado a conter a regra de negócio da aplicação, contém exceptions, mapper (responsável por fazer a conversão das Entidades em DTO).    
  4 - web - Pacote com a chamada aos recuros.  

# Descrição dos Recursos

1 - API Clientes - Podem ser listados todos sos clientes e exportados os relatórios no formato .csv.  
2 - API Clientes PF - Recursos para manter os clientes do tipo PF.  
3 - API Clientes PJ - Recursos para manter os clientes do tipo PJ.  
4 - API de Contas - Recursos para manter as contas.  
5 - API de Empresas - Recursos para consultar as empresas e exportar o relátorio de reeitas da empresa no formado .csv.  
6 - API de Endereços - Recursos para manter o Endereço.  
7 - API de Movimentações - Recursos para consultar e cadastrar novas movimentações.    
Para mais detalhes acessar o link da documentação da API.http://localhost:8080/swagger-ui.html


  
 
