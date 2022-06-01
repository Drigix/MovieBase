package item.api;

import item.api.request.MovieRequest;
import item.api.response.MovieResponse;
import item.domain.Movie;
import item.repository.MovieRepository;
import item.service.MovieService;
import item.support.ApiRequestException;
import item.support.MovieExceptionSupplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
    @RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;

    MovieController(MovieService movieService){
        this.movieService=movieService;
    }
    @Autowired
    MovieRepository movieRepository;

    @GetMapping("")
    public List<Movie> getAll(){
       return movieRepository.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieResponse> getById(@PathVariable("id") int id)
    {
        MovieResponse movieResponse=movieService.find(id);
        return ResponseEntity.status(HttpStatus.OK).body(movieResponse);
    }

    @PostMapping("/test")
    public int add(@RequestBody List<Movie> movies){
        return movieRepository.save1(movies);
    }

    @PostMapping("")
    public ResponseEntity<MovieResponse> add(@RequestBody MovieRequest movieRequest){
        //return movieRepository.save(movies);
        MovieResponse movieResponse=movieService.create(movieRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(movieResponse);
    }

    @PutMapping("/{id}")
    public int update(@PathVariable("id") int id,@RequestBody Movie updatedMovie){
        Movie movie=movieRepository.getById(id).orElseThrow(MovieExceptionSupplier.movieNotFound(id));
        try
        {
            movie.setName(updatedMovie.getName());
            movie.setRating(updatedMovie.getRating());
            movieRepository.update(movie);
            return 1;
        }
        catch (Exception e){
            throw new ApiRequestException("Opss something gone wrong!",e);
        }
    }

    @PatchMapping("/{id}")
    public int elementUpdate(@PathVariable("id") int id, @RequestBody Movie updatedMovie){
        Movie movie=movieRepository.getById(id).orElseThrow(MovieExceptionSupplier.movieNotFound(id));
        if(movie!=null)
        {
            if(updatedMovie.getName()!=null)
            {
                movie.setName(updatedMovie.getName());
            }
            if(updatedMovie.getRating()>0)
            {
                movie.setRating(updatedMovie.getRating());
            }
            movieRepository.update(movie);
            return 1;
        }
        else {
            throw new ApiRequestException("Opss something gone wrong!");
        }
    }

    @DeleteMapping("/{id}")
    public int delete(@PathVariable("id") int id){
        return movieRepository.delete(id);
    }
}
