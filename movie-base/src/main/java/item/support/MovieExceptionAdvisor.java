package item.support;

import item.support.exception.MovieNotFoundException;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import shared.api.response.ErrorMessageResponse;

import java.util.logging.Logger;

@ControllerAdvice
public class MovieExceptionAdvisor {
    private static final Logger LOG=LoggerFactory.getLogger(MovieExceptionAdvisor.class);

    @ExceptionHandler(MovieNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorMessageResponse movieNotFound(MovieNotFoundException exception){
        LOG.error(exception.getMessage(),exception);
        return new ErrorMessageResponse(exception.getLocalizedMessage());
    }

}
