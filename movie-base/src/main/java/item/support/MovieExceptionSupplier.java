package item.support;

import item.support.exception.MovieNotFoundException;

import java.util.function.Supplier;

public class MovieExceptionSupplier {

    public static Supplier<MovieNotFoundException> movieNotFound(int id){
        return ()-> new MovieNotFoundException(id);
    }
}
