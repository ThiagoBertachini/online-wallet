# Online Wallet

Plataforma de pagamentos simplificada que realiza depositos e transferências de dinheiro 
entre usuários. 
Dois tipos de usuários: comuns | lojistas;
Usuários possuem CNPJ e e-mails únicos;
Usuários efetuar transferência para lojistas e entre usuários;
Lojistas recebem transferências, não efetuam transferencia;
Usuário deve ter saldo antes da transferência;
Consulta a um serviço autorizador externo;
Notificação (envio de email, sms) por serviço externo;

## Tecnologias Utilizadas

- **Java 21**: Linguagem de programação utilizada no desenvolvimento da aplicação.
- **Spring Boot**: Framework para criação de aplicações Java, simplificando a configuração e inicialização.
- **Spring Data JPA**: Framework que facilita o acesso a dados em aplicações Spring, utilizando a especificação JPA.
- **MySQL**: Sistema de gerenciamento de banco de dados relacional utilizado para armazenar dados. O banco de dados é executado via Docker e configurado no arquivo `docker-compose.yml`.
- **Docker**: Plataforma para criação, implantação e execução de contêineres.
- **Spring Cloud Open Feign**: Cliente HTTP declarativo para comunicação com outros serviços.
- **ControllerAdvice & Problem Details**: Padrão para tratamento de exceções e fornecimento de respostas detalhadas sobre problemas da API.
- **Hibernate Validator**: Framework para validação de dados de entrada na aplicação.