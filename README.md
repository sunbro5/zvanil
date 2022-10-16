Running on https://zvanil.herokuapp.com

MONGO

docker run --name pqadmin -p 8085:8085 -e PGADMIN_DEFAULT_EMAIL=test@gmail.com -e PGADMIN_DEFAULT_PASSWORD=pass -e PGADMIN_LISTEN_PORT=8085  -d dpage/pgadmin4
docker run --name coder-postgres -p 5432:5432  -e POSTGRES_PASSWORD=pass -d postgres

MONGODB

docker service create --replicas 1 --name mongo --network mynetwork  mongo

RUNAPP

env DATABASE_URL=jdbc:postgresql://172.28.80.1:5432/postgres;PGUSER=postgres;PGPASSWORD=pass

Docker

docker build -t marjan/coder-1.0 .

docker login --username=maresjan694jmn

docker tag marjan/coder-1.0 maresjan694/web:coder-1.0

docker push maresjan694/web:coder-1.0

docker pull maresjan694/web:coder-1.0

docker network create -d overlay mynetwork

docker network create --driver overlay --subnet 10.0.0.0/12 --opt encrypted services

docker service create --replicas 2 --name postgres --network mynetwork -e POSTGRES_PASSWORD=pass694 postgres

docker service create --replicas 1 --name coder --network mynetwork --publish published=8081,target=8081 -e PGPASSWORD='pass' -e PGUSER='postgres' -e DATABASE_URL=jdbc:postgresql://postgress coder:1-0