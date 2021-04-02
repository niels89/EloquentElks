docker-compose down
./clean_up_databases.sh
rm -rf .eloquent-elks-poi-api/build/libs/*
./eloquent-elks-poi-api/gradlew -p ./eloquent-elks-poi-api build
./eloquent-elks-poi-api/gradlew -p ./eloquent-elks-poi-api enunciate
docker-compose pull
docker-compose build
docker-compose up -d --force-recreate