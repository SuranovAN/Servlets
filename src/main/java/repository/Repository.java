package repository;

import model.Post;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class Repository {
    private Map<Long, String> postMap = new ConcurrentHashMap<>();

    public Repository() {
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
                postMap.put(post.getId(), post.getData());
                tmpPost.ifPresent(value -> value.setData(post.getData()));
            }
        }
        return post;
    }

    public void removeById(long id) {
        postMap.remove(id);
    }
}
