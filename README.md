On this page we will configure the environment via docker and other settings

## First: 
Install docker in desirable SO (Operacional System)

## Linux:
https://docs.docker.com/engine/install/ubuntu/

## Windows:
https://docs.docker.com/docker-for-windows/install/

## Baixar as imagens no docker

docker pull postgres,
docker pull dpage/pgadmin4,
docker pull qaninja/ninjaplus-api,
docker pull qaninja/ninjaplus-web

### Criar a rede  Docker

docker network create --driver bridge netinghWorking

### Subir o Banco de Dados

docker run --name pgdb --network=netinghWorking -e "POSTGRES_PASSWORD=pwd123" -p 5432:5432 -v var/lib/postgresql/data -d postgres

### Subir o Administrador do Banco de Dados (PgAdmin)

docker run --name pgadmin --network=netinghWorking -p 15432:80 -e "PGADMIN_DEFAULT_EMAIL=root@mailsac.com" -e "PGADMIN_DEFAULT_PASSWORD=pwd123" -d dpage/pgadmin4

### Subir a API

sudo docker run --name ninjaplus-api --network=netinghWorking -e "DATABASE=pgdb" -p 3000:3000 -d qaninja/ninjaplus-api

### Subir a Aplicação Web

sudo docker run --name ninjaplus-web --network=netinghWorking -e "VUE_APP_API=http://ninjaplus-api:3000" -p 5000:5000 -d qaninja/ninjaplus-web

### Importante:
### Quando você reiniciar o seu computador, certifique-se que o Docker esteja online e suba containers novamente:

sudo docker start pgdb,
sudo docker start pgadmin,
sudo docker start ninjaplus-api,
sudo docker start ninjaplus-web

