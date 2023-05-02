# URL Shortener

REST API that takes an originalUrl and returns a shortened URL and uses Redis and Postgresql.

# Getting Started

## Dependencies

This project depends on

* spring-boot-starter-web
* spring-boot-starter-data-jpa
* spring-boot-starter-data-redis
* kotlin libs
* commons-lang3
* h2 (for tests)
* spring-boot-starter-test (for testss)

## Deployment

Can be deployed by running docker-compose file.

* Postgresql
* Redis
* REST API

To deploy the project, run

```shell script
docker-compose -f docker-compose.yml up
```

**The application will be accessible on http://localhost:8080**

## API Endpoints

You can access following API endpoints at http://localhost:8080

### POST `/shorten`

```json
{
  "originalUrl": "The original Url"
}
```

```
curl -X POST \
  http://localhost:8080/shorten \
  -H 'Content-Type: application/json' \
  -d '{"originalUrl":"https://google.com/foo"}'
```

Response:

```json
{
  "shortUrl": "<shortened url for the fullUrl provided in the request payload>"
}
```

### POST `/{hash}` as pathVariable

```
curl --location --request GET 'localhost:8080/f0d1db06'
```

Response:

```json
{
  "shortUrl": "<shortened url for the fullUrl provided in the request payload>"
}
```

# Url Shortening Algorithm

1) Getting the string url
2) How long hash actually be
3) Gets the byte using the digest then using the bytearray and converting into the hexadecimal representation
4) Limit with the length

# Future Enhancements / Known Issues

* Since the project is for mvp purpose only, i decided to keep it simple.
* Need to handle the redirection on the clientside or better encapsulate 500 error on backend
* Faced issues with auto schema generation through JPA, so I decided to move to the mongodb and also mongo db will be
  more suitable on prod.
* Need swagger
* Need better url validation on controllers
* Need to implement sync mechanism between persistance and redis
* Implement https
* Better docker structure needed