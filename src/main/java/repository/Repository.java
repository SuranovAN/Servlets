package repository;

import model.Post;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Repository {
    private List<Post> postList = new ArrayList<>();
    private static volatile long postCounter;

    public Repository() {
        postList.add(new Post("test", 12));
    }

    public List<Post> all() {
        return postList;
    }

    public Optional<Post> getById(long id) {
        return postList.stream().filter(p -> p.getId() == id).findFirst();
    }

    public synchronized Post save(Post post) {
        if (post.getId() == 0) {
            postCounter++;
            postList.add(new Post(post.getData(), postCounter));
        } else {
            Optional<Post> tmpPost = getById(post.getId());
            if (tmpPost.isPresent()){
                tmpPost.ifPresent(value -> value.setData(post.getData()));
            }
        }
        return post;
    }

    public void removeById(long id) {
        postList.remove(getById(id).get());
    }
}
