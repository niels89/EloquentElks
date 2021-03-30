docker-compose down
./clean_up_databases.sh
docker-compose pull
docker-compose build
docker-compose up -d --force-recreate