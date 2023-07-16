# FitLabAPI
FitLab é uma API de controle de alunos e treinos em uma academia desenvolvida com Spring Boot, utlizando também autenticação e autorização por JWT, com o Spring Security.

## Endpoints abertos

Endpoints que não requerem Autenticação.

* Login : `POST /instrutor/login/`
* Cadastro : `POST /instrutor/cadastrar`
    
## Endpoints que requerem Autenticação

### Instrutores

* Cadastrar alunos: `POST /instrutor/{instrutor_id}/alunos`

### Alunos
* Consultar detalhes de aluno: `GET /alunos/{aluno_id}`
* Consultar fichas cadastradas do aluno: `GET /alunos/{aluno_id}/fichas`

### Fichas 
* Cadastrar nova ficha: `POST /fichas`
* Consultar dados da ficha: `GET /fichas/{ficha_id}`
* Deletar ficha:  `DELETE /fichas/{ficha_id}`

### Exercicios
* Cadastrar novo Exercício `POST /exercicios`
* Consultar detalhes de Exercício `GET /exercicios`
* Consultar Exercícios por parte do corpo `GET /exercicios/parteDoCorpo/{parte}`
* Atualizar Exercício `PUT /exercicios/{exercicio_id}`
* Deletar Exercício `DELETE /exercicios/{exercicio_id}`

## Próximos passos

- Será implementado um ajuste nos Controllers para que seja possível fazer as operações CRUD de fichas e exercícios diretamente ligadas a um instrutor, especficicamente o que estiver logado.
