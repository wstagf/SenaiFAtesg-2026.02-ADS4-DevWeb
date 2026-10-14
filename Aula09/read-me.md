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
        