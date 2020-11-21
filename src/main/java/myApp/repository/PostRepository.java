package myApp.repository;

import myApp.model.Post;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class PostRepository {
    private Map<Long, String> postMap = new ConcurrentHashMap<>();

    public PostRepository() {
        postMap.put(12L, "newMap");
    }

    public Map<Long, String> all(){
        return postMap;
    }

    public Optional<Post> getById(long id) {
        return Optional.of(new Post(postMap.get(id), id));
    }

    public Post save(Post post) {
        if (post.getId() == 0) {
            postMap.put(postMap.size() + 1L, post.getData());
        } else {
            Optional<Post> tmpPost = getById(post.getId());
            if (tmpPost.isPresent()) {
                tmpPost.ifPresent(value -> value.setData(post.getData()));
            }
        }
        return post;
    }

    public void removeById(long id) {
        postMap.remove(id);
    }
}
