package servlet;

import controller.Controller;
import repository.Repository;
import service.Service;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MainServlet extends HttpServlet {
    private Controller controller;

    @Override
    public void init() {
        final var repository = new Repository();
        final var service = new Service(repository);
        controller = new Controller(service);
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
                controller.all(resp);
                return;
            }

            long id = 0;
            try {
                id = Long.parseLong(path.substring(path.lastIndexOf("/") + 1));
            }catch (Exception ignored){

            }
            if (method.equals("GET") && path.matches("/api/posts/\\d+")) {
                controller.getById(id, resp);
                return;
            }

            if (method.equals("POST") && path.equals("/api/posts")) {
                controller.save(req.getReader(), resp);
                return;
            }

            if (method.equals("DELETE") && path.matches("/api/posts/\\d+")) {
                controller.removeById(id, resp);
            }

            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
