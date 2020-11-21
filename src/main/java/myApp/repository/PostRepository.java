package myApp.repository;

import myApp.model.Post;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class PostRepository {
    private Map<Long, String> postMap = new ConcurrentHashMap<>();

    public PostRepository() {
        postMap.put(12L, "newMap");
    }

    public Map<Long, String> all(){
        return postMap;
    }

    public Optional<Post> getById(long id) {
        if(postMap.containsKey(id)) {
            return Optional.of(new Post(postMap.get(id), id));
        }
        return Optional.empty();
    }

    public Post save(Post post) {
        if (post.getId() == 0) {
            postMap.put(postMap.size() + 1L, post.getData());
        } else {
            if(getById(post.getId()).isPresent()){
                postMap.put(post.getId(), post.getData());
            }
        }
        return post;
    }

    public void removeById(long id) {
        postMap.remove(id);
    }
}
