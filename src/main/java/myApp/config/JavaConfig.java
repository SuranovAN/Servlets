package myApp.config;

import myApp.controller.PostController;
import myApp.repository.PostRepository;
import myApp.service.PostService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JavaConfig {
    @Bean
    public PostController postController(){
        return new PostController(postService());
    }

    @Bean
    public PostService postService(){
        return new PostService(postRepository());
    }

    @Bean
    public PostRepository postRepository(){
        return new PostRepository();
    }
}
