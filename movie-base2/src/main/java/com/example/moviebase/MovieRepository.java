package com.example.moviebase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

@Repository
public class MovieRepository {
    @Autowired
    JdbcTemplate jdbcTemplete;

    public List<Movie> getAll(){
        return jdbcTemplete.query("SELECT id, name, rating FROM movie", BeanPropertyRowMapper.newInstance(Movie.class));
    }

    public Movie getById(int id){
        return jdbcTemplete.queryForObject("SELECT id,name,rating FROM movie " +
                "WHERE id=?",BeanPropertyRowMapper.newInstance(Movie.class),id);
    }

    public int save(List<Movie> movies) {
        movies.forEach(movie -> jdbcTemplete.update("INSERT INTO movie(name,rating) VALUES(?,?)",
                movie.getName(),movie.getRating()
                ));
        return 1;
    }

    public int update(Movie movie){
        return jdbcTemplete.update("UPDATE movie SET name=?,rating=? WHERE id=?",
                movie.getName(),movie.getRating(),movie.getId());
    }

    public int delete(int id) {
        return jdbcTemplete.update("DELETE FROM movie WHERE id=?", id);
    }
}
