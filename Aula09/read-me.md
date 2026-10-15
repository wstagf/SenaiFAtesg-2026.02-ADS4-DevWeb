Backend em NodeJS usando Express e SQLite3.
Entidade: Usuário, Produto, Categoria.

1. Instalação
    - NodeJS
        Verificamos se o NodeJS e o NPM estão instalados no sistema.
            $ node -v
            $ npm -v

        caso nao estejam instalados, acesse o site oficial do NodeJS e faça o download da versão LTS. https://nodejs.org/en (windows), 
ou use o gerenciador de pacotes do seu sistema operacional (Linux, MacOS).
    $ sudo apt install nodejs npm 

2. Criando o projeto
    - Criar uma pasta para o projeto e entrar nela.
        $ mkdir nome-do-projeto
        $ cd nome-do-projeto

    - Inicializar o projeto com npm.
        $ npm init -y

    - Instalar as dependências necessárias.
        $ npm install express sqlite3 body-parser cors


3. Modelo MVC
    - Criar a estrutura de pastas do projeto.
        $ mkdir servidor
        $ cd servidor
        $ mkdir Model Controller routes

    - Criar o arquivo index.js na pasta servidor.
        $ touch index.js

    - Criar o arquivo ProdutoModel.js na pasta Model.
        $ touch Model/ProdutoModel.js

    - Criar o arquivo ProdutoController.js na pasta Controller.
        $ touch Controller/ProdutoController.js

    - Criar o arquivo produtoRoutes.js na pasta routes.
        $ touch routes/produtoRoutes.js

4. Configuração do servidor
    ver documentação em https://expressjs.com/pt-br/
    1. instanciar o Express
    2. Utilizar o body-parser para interpretar o corpo das requisições
    3. Configurar o CORS para permitir requisições de diferentes origens
    4. configurar nossa primeira rota para testar se o servidor está funcionando corretamente.
    5. Iniciar o servidor na porta 3000

5. Testar a aplicação
    - Rodar o servidor com o comando:
        $ node index.js
        ou
        $ npm run dev
    - utilizar o Postman, Insomnia ou o navegador para testar a rota /health e verificar se o servidor está respondendo corretamente.
        GET http://localhost:3000/health
6. Vamos editar o arquivo produtoRoutes.js para criar as rotas de CRUD para a entidade Produto e importar no index.js.
    - Criar as rotas:
        GET /produtos - Listar todos os produtos
        GET /produtos/:id - Listar um produto pelo ID
        POST /produtos - Criar um novo produto
        PUT /produtos/:id - Atualizar um produto pelo ID
        DELETE /produtos/:id - Deletar um produto pelo ID

    - importar as rotas no index.js e utilizar o app.use() para registrar as rotas no servidor.

