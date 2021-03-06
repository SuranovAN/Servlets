package model;

public class Post {
    private String data;
    private long id;

    public Post() {
    }

    public Post(String data, long id) {
        this.data = data;
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
