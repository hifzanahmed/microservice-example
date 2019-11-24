package com.microservices.movieinfoservices.resources;

import com.microservices.movieinfoservices.models.Movie;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class MovieResource {

    @RequestMapping("/{movieId}")
    //hifzan AHmedgotasdsa
    public Movie getMovieInfo(@PathVariable("movieId") String movieId){
        return new Movie(movieId, "TestMovieName");
    }
}
