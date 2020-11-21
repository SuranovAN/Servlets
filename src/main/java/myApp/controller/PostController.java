package myApp.controller;

import com.google.gson.Gson;
import myApp.model.Post;
import org.springframework.stereotype.Controller;
import myApp.service.PostService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Reader;

public class PostController {
    public static final String APPLICATION_JSON = "application/json";
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    public void all(HttpServletResponse resp) throws IOException {
        resp.setContentType(APPLICATION_JSON);
        final var data = postService.all();
        final var gson = new Gson();
        final var tmpList = data.entrySet().stream().map(p -> new Post(p.getValue(), p.getKey())).toArray();
        resp.getWriter().print(gson.toJson(tmpList));
    }

    public void getById(long id, HttpServletResponse resp) throws IOException {
        resp.setContentType(APPLICATION_JSON);
        final var data = postService.getById(id);
        final var gson = new Gson();
        resp.getWriter().print(gson.toJson(data));
    }

    public void save(Reader body, HttpServletResponse resp) throws IOException {
        resp.setContentType(APPLICATION_JSON);
        final var gson = new Gson();
        final var post = gson.fromJson(body, Post.class);
        final var data = postService.save(post);
        resp.getWriter().print(gson.toJson(data));
    }

    public void removeById(long id, HttpServletResponse resp){
        resp.setContentType(APPLICATION_JSON);
        postService.removeById(id);
    }
}
