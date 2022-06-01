package item.support;

import item.api.request.MovieRequest;
import item.api.response.MovieResponse;
import item.domain.Movie;
import org.springframework.stereotype.Component;

@Component
public class MovieMapper {
    public Movie toMovie(MovieRequest movieRequest){
        return new Movie(movieRequest.getName(),movieRequest.getRating());
    }

    public MovieResponse toMovieResponse(Movie movie){
        return new MovieResponse(movie.getId(), movie.getName(), movie.getRating());
    }
}
