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


# POI Density API
rm -rf ./eloquent-elks-poi-density-api/dist/*
npm install --prefix ./eloquent-elks-poi-density-api
npm run compile --prefix ./eloquent-elks-poi-density-api

docker-compose pull
docker-compose build
docker-compose up -d --force-recreate