URL-COMPRESSOR-API

Uma API RESTful para compactar uma URL larga em uma URL mais facil de manusear. A API recibe uma url e um tempo em minutos e retorna uma nova versao da url original url que é válida durante o tempo informado pelo usuario. Essa nova versao da URL é sem  dúvida uma copia da versao original que permite navegar e apontar aos mesmos recursos do original,  válido durante o tempo informado pelo usuario. Após esse tempo, a URL é automaticamente apagado da tabela e nao sera mais validada pela API.

TECNOLOGIAS
- Spring Boot(Devtools, Lombook, spring Data MongoDB, Spring Web)
- MongoDB(DBA)
- Teste (BRUNO, MongoDB Compass)
- JUNIT5

USO
  REQUISITOS
  - Java V:17 ou superior
  - Docker
  - docker compose
EXECUTAR
 . Clonar o projeto: git remote add origin https://github.com/maxiamikel19/url-compressor-api.git
 . Navega até a pasta do projeto
 . Executar o comando "docker compose up" para subir uma instância do banco de dados
 . Executar o projeto (No terminal: mvn spring-boot:run)
TESTE
POST: http://localhost:8080/compress-urls
EX:
![image](https://github.com/user-attachments/assets/fde75de7-f312-4ee6-8078-f56858aab64b)

  
