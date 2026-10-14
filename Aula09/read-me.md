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