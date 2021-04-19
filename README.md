# EloquentElks

 - Code Coverage Overall: [![Coverage Status](https://coveralls.io/repos/github/niels89/EloquentElks/badge.svg?branch=main)](https://coveralls.io/github/niels89/EloquentElks?branch=main)
 - Quality Gate Frontend: [![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=eloquent-elks_EloquentElks_Frontend&metric=alert_status)](https://sonarcloud.io/dashboard?id=eloquent-elks_EloquentElks_Frontend)
 - Quality Gate AirBnb API:  [![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=eloquent-elks_EloquentElks_Airbnb_API&metric=alert_status)](https://sonarcloud.io/dashboard?id=eloquent-elks_EloquentElks_Airbnb_API)
 - Quality Gate POI API: [![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=eloquent-elks_EloquentElks_POI_API&metric=alert_status)](https://sonarcloud.io/dashboard?id=eloquent-elks_EloquentElks_POI_API)
 - Quality Gate Recommender API: [![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=eloquent-elks_EloquentElks_Recommender_API&metric=alert_status)](https://sonarcloud.io/dashboard?id=eloquent-elks_EloquentElks_Recommender_API)

To build the entire application including all microservices, run the following command in the root directory of the project: `bash ./build_docker_containers.sh`. This builds all the projects and sets up the docker containers. For details on the individual microservices, please refer to the following pages:

 - Frontend: [README](./eloquent-elks-frontend/README.md)
 - Airbnb API: [README](./eloquent-elks-backend/README.md)
 - POI API: [README](./eloquent-elks-poi-api/README.md)
 - Recommender API: [README](./eloquent-elks-recommender-api/README.md)
- POI Density API: [README](./eloquent-elks-poi-density-api/README.md)