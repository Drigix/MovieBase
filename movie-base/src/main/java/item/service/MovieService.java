package item.service;

import item.repository.MovieRepository;
import item.api.request.MovieRequest;
import item.api.response.MovieResponse;
import item.domain.Movie;
import item.support.MovieExceptionSupplier;
import item.support.MovieMapper;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;

    MovieService(MovieRepository movieRepository, MovieMapper movieMapper){
        this.movieRepository=movieRepository;
        this.movieMapper=movieMapper;
    }

    public MovieResponse create(MovieRequest movieRequest){
        Movie movie=movieRepository.save(movieMapper.toMovie(movieRequest));
        movie=movieRepository.getId(movie);
        return movieMapper.toMovieResponse(movie);
    }

    public MovieResponse find(int id){
        Movie movie=movieRepository.getById(id).orElseThrow(MovieExceptionSupplier.movieNotFound(id));
        return movieMapper.toMovieResponse(movie);
    }

}
