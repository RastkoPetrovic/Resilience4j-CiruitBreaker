package spring5webapp.moviecatalogservices.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import spring5webapp.moviecatalogservices.models.CatalogItem;
import spring5webapp.moviecatalogservices.models.UserRating;
import spring5webapp.moviecatalogservices.services.MovieInfo;
import spring5webapp.moviecatalogservices.services.UserRatingInfo;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Autowired
    MovieInfo movieInfo;

    @Autowired
    UserRatingInfo userRatingInfo;


    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {


        UserRating ratings = userRatingInfo.getUserRating(userId);

        return ratings.getUserRating().stream()
                .map(rating -> { return movieInfo.getCatalogItem(rating);
                }).collect(Collectors.toList());


    }




}
