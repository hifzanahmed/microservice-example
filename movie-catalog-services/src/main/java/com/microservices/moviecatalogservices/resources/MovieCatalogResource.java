package com.microservices.moviecatalogservices.resources;

import com.microservices.moviecatalogservices.models.CatalogItem;
import com.microservices.moviecatalogservices.models.Movie;
import com.microservices.moviecatalogservices.models.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {
        RestTemplate restTemplate = new RestTemplate();
        //restTemplate.getForObject("https://localhost:8082/movies/newMovie", Movie.class);
        //Movie movie = restTemplate.getForObject("https://localhost:8082/movies/newMovie", Movie.class);
        //WebClient.Builder builder = WebClient.builder();
        List<Rating> ratings = Arrays.asList(
                new Rating("1234", 4),
                new Rating("5678", 5)
        );

        return ratings.stream().map(rating -> {
            //Movie movie = restTemplate.getForObject("http://localhost:8082/movies/"+ rating.getMovieId(), Movie.class);
            //new CatalogItem("Transformer", "Test", 4)
            Movie movie = webClientBuilder.build()
                    .get()
                    .uri("http://localhost:8082/movies/" + rating.getMovieId())
                    .retrieve()
                    .bodyToMono(Movie.class)
                    .block();
            return new CatalogItem(movie.getName(), "Test", rating.getRating());
        })
                .collect(Collectors.toList());

        //return Collections.singletonList(new CatalogItem("Transfermer", "Test", 5));
    }
}
