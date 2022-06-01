package item.repository;

import item.domain.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Optional;

@Repository
public class MovieRepository {
    @Autowired
    JdbcTemplate jdbcTemplete;

    public List<Movie> getAll(){
        return jdbcTemplete.query("SELECT id, name, rating FROM movie", BeanPropertyRowMapper.newInstance(Movie.class));
    }

    public Optional<Movie> getById(int id){
        return Optional.ofNullable(jdbcTemplete.queryForObject("SELECT id,name,rating FROM movie " +
                "WHERE id=?",BeanPropertyRowMapper.newInstance(Movie.class),id));
    }

    public Movie getId(Movie movie){
        Movie newMovie= jdbcTemplete.queryForObject("SELECT id FROM movie WHERE name=?",BeanPropertyRowMapper.newInstance(Movie.class),movie.getName());
        movie.setId(newMovie.getId());
        return movie;
    }

    public int save1(List<Movie> movies)
    {
        movies.forEach(movie -> jdbcTemplete.update("INSERT INTO movie(name,rating) VALUES(?,?)",movie.getName(),movie.getRating()));
        return 1;
    }

    public Movie save(Movie movies) {
        //movies.forEach(movie -> jdbcTemplete.update("INSERT INTO movie(name,rating) VALUES(?,?)",movie.getName(),movie.getRating()));
         jdbcTemplete.update("INSERT INTO movie(name,rating) VALUES(?,?)",
                movies.getName(),movies.getRating());

         return movies;
    }

    public int update(Movie movie){
        return jdbcTemplete.update("UPDATE movie SET name=?,rating=? WHERE id=?",
                movie.getName(),movie.getRating(),movie.getId());
    }

    public int delete(int id) {
        return jdbcTemplete.update("DELETE FROM movie WHERE id=?", id);
    }
}
