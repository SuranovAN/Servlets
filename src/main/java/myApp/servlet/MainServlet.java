package myApp.servlet;

import myApp.controller.PostController;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MainServlet extends HttpServlet {
    private PostController postController;

    @Override
    public void init() {
        final var context = new AnnotationConfigApplicationContext("myApp");
        postController = context.getBean(PostController.class);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) {
        try {
            final var path = req.getRequestURI();
            final var method = req.getMethod();
            if (path.equals("/")) {
                resp.getWriter().print("Welcome!");
                return;
            }
            if (method.equals("GET") && path.equals("/api/posts")) {
                postController.all(resp);
                return;
            }

            long id = 0;
            try {
                id = Long.parseLong(path.substring(path.lastIndexOf("/") + 1));
            } catch (Exception ignored) {

            }
            if (method.equals("GET") && path.matches("/api/posts/\\d+")) {
                postController.getById(id, resp);
                return;
            }

            if (method.equals("POST") && path.equals("/api/posts")) {
                postController.save(req.getReader(), resp);
                return;
            }

            if (method.equals("DELETE") && path.matches("/api/posts/\\d+")) {
                postController.removeById(id, resp);
            }

            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
