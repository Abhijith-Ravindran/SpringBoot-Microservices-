package com.abhi.movieratingsservice.resources;

import com.abhi.movieratingsservice.models.MovieRating;
import com.abhi.movieratingsservice.models.UserRating;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/ratings")
public class MovieRatingsResource {

    @RequestMapping("/{movieId}")
    public MovieRating getMovieRating(@PathVariable("movieId") String movieId){
        return new MovieRating(movieId, 4);
    }

    @RequestMapping("/users/{userId}")
    public UserRating getUserMovieRatings(@PathVariable("userId") String userId){
        List<MovieRating> ratings = Arrays.asList(
                new MovieRating("mv001", 4),
                new MovieRating("mv002", 5)
        );
        UserRating userRating = new UserRating();
        userRating.setUserRatings(ratings);
        return userRating;
    }
}
