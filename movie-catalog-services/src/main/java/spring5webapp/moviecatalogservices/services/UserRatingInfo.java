package spring5webapp.moviecatalogservices.services;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;
import spring5webapp.moviecatalogservices.models.Rating;
import spring5webapp.moviecatalogservices.models.UserRating;

import java.util.Arrays;

@Service
public class UserRatingInfo {

    Logger logger = LoggerFactory.getLogger(UserRatingInfo.class);

    @Autowired
    private RestTemplate restTemplate;

    @CircuitBreaker(name = "user_rating_service", fallbackMethod = "getFallbackUserRating")
    public UserRating getUserRating(@PathVariable("userId") String userId) {
        return restTemplate.getForObject("http://rating-data-services/ratingsdata/users/foo"  + userId, UserRating.class);

    }

    public UserRating getFallbackUserRating(@PathVariable("userId") String userId, Throwable t) {
        logger.error("Inside circuit breaker getFallBackUserRating, cause - {}", t.toString());
        UserRating userRating = new UserRating();
        userRating.setUserId(userId);
        userRating.setUserRating(Arrays.asList(
                new Rating("0", 0)
        ));

        return userRating;
    }
}
