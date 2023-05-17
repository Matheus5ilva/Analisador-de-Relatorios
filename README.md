# Analisador de relatório

Guia do projeto

## Motivação

A motivação do projeto é para auxiliar o gestor que não possui conhecimento de administração a tomar melhor decisão para
sua empesa. 

A ideia principal é ser um projeto separado de qualquer ERP ou sistema de gestão, porém a primeira parte é a criação de 
uma aplicação ainda *REST*.

## Conteúdo

### Tecnologias utilizadas

Toda a tecnologia utilizada na construção da *API REST*.

- `Java`: Foi utilizado a linguagem de programação Java versão 17. 
- `Spring Boot`: Foi utilizado o framework Spring por ser mais popular em aplicação.
- `H2 Console`: H2 é um banco de dados em memoria que só 'funciona' quando a aplicação começa.
- `JPA`: JPA é um framework de comunição da aplicação com banco de dados. 
  - #### Para mais informação verificar o `*pom.xml*`


### Exemplo

Todos os *endpoints* com Json de exemplo.

#### Empresa
- `[GET] localhost:8080/empresas`: Listar todas as empresas cadastradas.
- `[GET] localhost:8080/empresas/{idEmpresa}`: Pegar informaçao de somente uma empresa.
- `[POST] localhost:8080/empresas`: Cria uma empresa.
  - Existe um exemplo no caminho *src/test/resources/json/empresa-nova.json*
- `[PUT] localhost:8080/empresas/{idEmpresa}`: Editar uma empresa.
    - Existe um exemplo no caminho *src/test/resources/json/empresa-nova.json*
- `[DELETE] localhost:8080/empresas/{idEmpresa}`: Apagar uma empresa. 
  - Lembrando que se existir um funcionario, não será possivel apagar
#### Usuário
- `[GET] localhost:8080/usuarios`: Listar todos as usuários cadastradas.
- `[GET] localhost:8080/empresas/{idUsuario}`: Pegar informaçao de somente de um usuário.
- `[POST] localhost:8080/usuarios`: Cria um usuário.
    - Existe um exemplo no caminho *src/test/resources/json/usuario-nova.json*
- `[PUT] localhost:8080/usuario/{idUsuario}`: Editar um usuário.
    - Existe um exemplo no caminho *src/test/resources/json/usuario-nova.json*
- `[DELETE] localhost:8080/empresas/{idEmpresa}`: Apagar um usuário.

#### Pesquisa
- `[POST] localhost:8080/pesquisas`: Realiza a análise. 
  - O *JSON*  para preencher: 
  - ```{"arquivo": <file em xls>,"nomeRelatorio": "nome do relatorio", "usuarioId: 1 }```

#### Relatório
- `[GET] localhost:8080/relatorio/{idUsuario}`: Mostra o número de analise realizada e qual o relatório mais pesquisa.
    - A ideia é ser no index, mas ainda pode ser refatorado


### Roadmap 

Evolições futuras a ser executadas. 

- [X] Refatorar as entidades
- [ ] Criar UML
- [X] Criar Testes Unitarios 
- [ ] Criar rotina de login
- [ ] Criar um JWT
- [ ] Criar um WAR ou JAR
- [ ] Alterar o HELP.md
- [X] Criar o README.md

### Comunidade

Se quiser contribuir com o projeto, faça alguma tarefa no Roadmap, será de muito agrado. 


## Muito Obrigado!