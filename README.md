# Resilience4j-CiruitBreaker
Circuit breaker pattern using resilience4j
## About
This is my practice of Circuit breaker pattern in microservices, using Resilience4j
## Description
This project consists of three microservices:
- movie-catlog-service
- movie-info-service
- user-info-service
- and Eureka discovery server
## How to run
- Run the discovery-server on an IDE which supports Spring Framework
- Enter the following address into URL: http://localhost:8761/
- Run the user-info-service on an IDE which supports Spring Framework
- Enter the following address into URL: http://localhost:8083/ratingsdata/users/foo
- Run the movie-info-service on an IDE which supports Spring Framework
- Enter the following address into URL: http://localhost:8082/movies/100 or URL: http://localhost:8082/movies/200
- Run the movie-catalog-service on an IDE which supports Spring Framework
- Enter the following address into URL: http://localhost:8081/catalog/foo

## Tech Stack
- Java
- Spring
- Resilience4j
