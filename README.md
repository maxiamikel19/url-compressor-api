URL-COMPRESSOR-API

RESP API para compactar URLs. A API recibe uma url e um tempo em minutos e retorna uma nova url que é válida durante o tempo informado pelo usuario. Após esse tempo, a URL é automaticamente apagado da tabela e nao sera mais validada pela API.

TECNOLOGIAS
- Spring Boot(Devtools, Lombook, spring Data MongoDB, Spring Web)
- MongoDB(DBA)
- Teste (BRUNO, MongoDB Compass)

USO
  REQUISITOS
  - Java V:17 ou superior
  - Docker instalado

 . Clonar o projeto: git remote add origin https://github.com/maxiamikel19/url-compressor-api.git
 . Navegar até a pasta do projeto
 . RUN: docker compose up
 . ejecutar o projeto (No terminal: mvn spring-boot:run
TESTE
POST: http://localhost:8080/compress-urls
EX:
![image](https://github.com/user-attachments/assets/fde75de7-f312-4ee6-8078-f56858aab64b)

  
