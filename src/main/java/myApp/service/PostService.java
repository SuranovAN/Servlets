package myApp.service;

import myApp.model.Post;
import myApp.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Map<Long, String> all() {
        return postRepository.all();
    }

    public Post getById(long id) {
        return postRepository.getById(id).get();
    }

    public Post save(Post post) {
        return postRepository.save(post);
    }

    public void removeById(long id) {
        postRepository.removeById(id);
    }
}
