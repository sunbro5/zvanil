Running on https://zvanil.herokuapp.com

MONGO

docker service create --replicas 1 --name mongo --network bridge  mongo

docker service create --network mynetwork --replicas 2 --mount source=C:\Users\Jan\dbdata,destination=/data/db,type=bind --name mongo mongo

RUNAPP

env DATABASE_URL=jdbc:postgresql://172.28.80.1:5432/postgres;PGUSER=postgres;PGPASSWORD=pass

Docker

docker build -t marjan/coder-1.0 .

docker login --username=maresjan694

docker tag marjan/coder-1.0 maresjan694/web:coder-1.0

docker push maresjan694/web:coder-1.0

docker pull maresjan694/web:coder-1.0

docker network create -d overlay mynetwork

docker network create --driver overlay --subnet 10.0.0.0/12 --opt encrypted services

docker service create --replicas 2 --name postgres --network mynetwork -e POSTGRES_PASSWORD=pass694 postgres

docker service create --replicas 1 --name coder --publish published=8081,target=8081 -e DATABASE_URL=mongodb://mongo:27017 marjan/coder-1.0

docker run --name coder -p 8081:8081 -p 443:443 -p 80:80 -e DATABASE_URL=mongodb://172.17.0.2:27017 marjan/coder-1.0
