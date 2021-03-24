package spring5webapp.moviecatalogservices.services;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import spring5webapp.moviecatalogservices.models.CatalogItem;
import spring5webapp.moviecatalogservices.models.Movie;
import spring5webapp.moviecatalogservices.models.Rating;

@Service
public class MovieInfo {

    Logger logger = LoggerFactory.getLogger(MovieInfo.class);

    @Autowired
    private RestTemplate restTemplate;

    @CircuitBreaker(name = "catalog_item_service", fallbackMethod = "getFallbackCatalogItem")
    public CatalogItem getCatalogItem(Rating rating) {

        Movie movie = restTemplate.getForObject("http://movie-info-services/movies/" + rating.getMovieId(), Movie.class);

        return new CatalogItem(movie.getName(), "Desc", rating.getRating());
    }

    public CatalogItem getFallbackCatalogItem(Rating rating, Throwable t) {
        logger.error("Inside circuit breaker getFallbackCatalogItem, cause - {}", t.toString());
        return new CatalogItem("Movie name not found", "", rating.getRating());
    }
}
