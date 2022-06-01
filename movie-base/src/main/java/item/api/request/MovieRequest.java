package item.api.request;

import com.fasterxml.jackson.annotation.JsonCreator;

public class MovieRequest {
    private final String name;
    private final int rating;

    @JsonCreator
    public MovieRequest(String name,int rating) {
        this.name = name;
        this.rating=rating;
    }

    public String getName() {
        return name;
    }

    public int getRating(){return rating;}
}
