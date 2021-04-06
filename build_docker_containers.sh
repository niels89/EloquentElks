#!/bin/bash

docker-compose down
./clean_up_databases.sh

#POI API
rm -rf .eloquent-elks-poi-api/build/libs/*
./eloquent-elks-poi-api/gradlew -p ./eloquent-elks-poi-api enunciate
./eloquent-elks-poi-api/gradlew -p ./eloquent-elks-poi-api build

#Recommender API
rm -rf .eloquent-elks-recommender-api/build/libs/*
./eloquent-elks-recommender-api/gradlew -p ./eloquent-elks-recommender-api enunciate
./eloquent-elks-recommender-api/gradlew -p ./eloquent-elks-recommender-api build

#Airbnb API
rm -rf .eloquent-elks-backend/build/libs/*
./eloquent-elks-backend/gradlew -p ./eloquent-elks-backend build

docker-compose pull
docker-compose build
docker-compose up -d --force-recreate