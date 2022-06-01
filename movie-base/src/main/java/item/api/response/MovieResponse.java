package item.api.response;

public class MovieResponse {
    private final int id;
    private final String name;

    private final int rating;

    public MovieResponse(int id, String name,int rating) {
        this.id = id;
        this.name = name;
        this.rating=rating;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getRating() {
        return rating;
    }
}
