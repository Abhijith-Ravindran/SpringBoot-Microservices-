package com.abhi.moviecatalogueservice.resources;

import com.abhi.moviecatalogueservice.models.CatalogItem;
import com.abhi.moviecatalogueservice.models.Movie;
import com.abhi.moviecatalogueservice.models.MovieRating;
import com.abhi.moviecatalogueservice.models.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

    @Autowired
    private RestTemplate resttemplate;

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalogItems(@PathVariable("userId") String userId){

        UserRating ratings = resttemplate.getForObject("http://movie-ratings-service/ratings/users/"+userId,
                    UserRating.class
                );

        return ratings.getUserRatings().stream().map( movieRating ->
            {
                Movie movie = resttemplate.getForObject("http://movie-info-service/movies/"+movieRating.getMovieId(), Movie.class);
                return new CatalogItem(movie.getMovieName(), movie.getDescription(), movieRating.getRating());
            }
        ).collect(Collectors.toList());
    }
}
