# Financial Bank Microserviços Kotlin

Codificação em Kotlin com uso Framework SpringBoot para desenvolvimento de um conjunto de microsserviços relacionados a operações financeiras. Cada microsserviço é responsável por um domínio específico, possibilitando a criação de um sistema distribuído e escalável para operações financeiras.

**Microsserviços Incluídos**
- Credit-Service: Este microsserviço é responsável por processar operações de crédito em contas dos clientes. Ele recebe solicitações para adicionar créditos em contas e realiza a devida validação antes de efetivar a operação.

- Debit-Service: O Debit-Service é encarregado de processar operações de débito em contas de clientes. Ele recebe solicitações para retirar fundos de contas e realiza as validações necessárias antes de realizar a transação.

- Transfer-Service: O Transfer-Service é responsável por realizar transferências de fundos entre contas dos clientes. Ele recebe solicitações para transferir um montante de uma conta para outra, garantindo a consistência e a integridade das transações.

- Balance-Service: O Balance-Service é responsável por fornecer informações sobre o saldo das contas dos clientes. Ele responde a consultas sobre o saldo atual em uma conta específica.

- Statement-Service: O Statement-Service é encarregado de fornecer o histórico de transações de uma conta específica. Ele permite que os clientes acessem um extrato detalhado contendo todas as operações realizadas em sua conta.

**Tecnologias Utilizadas**
O sistema é construído usando a arquitetura de microsserviços e utiliza as seguintes tecnologias:

- Kafka: O Kafka é utilizado como um sistema de mensagens para garantir a comunicação assíncrona e resiliente entre os microsserviços.

- Zookeeper: O Zookeeper é utilizado como um serviço de coordenação para garantir a disponibilidade e a consistência do Kafka.

- MongoDB: O MongoDB é utilizado como o banco de dados principal para armazenar informações sobre as contas dos clientes e o histórico de transações.

- Docker: O Docker é utilizado para containerizar cada microsserviço, permitindo uma fácil implantação e escalabilidade dos serviços.

**Como Executar**
Certifique-se de ter o Docker instalado em seu sistema antes de executar o sistema. Para iniciar o sistema, siga os passos abaixo:

Clone este repositório para o seu ambiente local.

Navegue para a pasta raiz do repositório onde se encontra o arquivo "docker-compose.yml".

Execute o seguinte comando para iniciar todos os microsserviços e suas dependências:
docker-compose up -d

Agora, todos os microsserviços estarão em execução e prontos para receber solicitações.

**Endpoints dos Microsserviços**
Abaixo estão os endpoints principais de cada microsserviço:

Credit-Service: http://localhost:8081/credit
Debit-Service: http://localhost:8082/debit
Transfer-Service: http://localhost:8083/transfer
Balance-Service: http://localhost:8084/balance
Statement-Service: http://localhost:8085/statement

Cada endpoint possui documentação detalhada sobre como usar os serviços fornecidos pelo microsserviço correspondente.

Autor:
**Emerson Amorim**