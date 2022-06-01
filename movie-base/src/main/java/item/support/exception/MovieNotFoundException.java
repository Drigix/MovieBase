package item.support.exception;

public class MovieNotFoundException extends RuntimeException {

    public MovieNotFoundException(int id){
            super(String.format("Movie with %d not found",id));
    }
}
