# EloquentElks - Airbnb API

Airbnb API has functionality to provide all the properties of New York listed in our database.
This is used by our [frontend](../eloquent-elks-frontend/README.md) to fetch the listing and display it on the map.
The application is deployed on the port 8080.

## Documentation
To check the Swagger API documentation, please use the below link -

http://localhost:8080/docs/ui/index.html

All the endpoints are present here to test. Presently, you don't need any auth key to interact with our API.


## How does it work?
The API interacts with our MonogDB database in order to fetch all the listing. We have a price filter which will enable
the users to fetch the listing with a certain price range.
