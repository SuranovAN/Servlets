package controller;

import com.google.gson.Gson;
import model.Post;
import service.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Reader;

public class Controller {
    public static final String APPLICATION_JSON = "application/json";
    private final Service service;

    public Controller(Service service) {
        this.service = service;
    }

    public void all(HttpServletResponse resp) throws IOException {
        resp.setContentType(APPLICATION_JSON);
        final var data = service.all();
        final var gson = new Gson();
        resp.getWriter().print(gson.toJson(data));
    }

    public void getById(long id, HttpServletResponse resp) throws ClassNotFoundException, IOException {
        resp.setContentType(APPLICATION_JSON);
        final var data = service.getById(id);
        final var gson = new Gson();
        resp.getWriter().print(gson.toJson(data));
    }

    public void save(Reader body, HttpServletResponse resp) throws IOException {
        resp.setContentType(APPLICATION_JSON);
        final var gson = new Gson();
        final var post = gson.fromJson(body, Post.class);
        final var data = service.save(post);
        resp.getWriter().print(gson.toJson(data));
    }

    public void removeById(long id, HttpServletResponse resp){
        resp.setContentType(APPLICATION_JSON);
        service.removeById(id);
    }
}
