package repository;

import model.Post;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

public class Repository {
        private List<Post> postList = new CopyOnWriteArrayList<>();

    public Repository() {
        postList.add(new Post("test", 12));
    }

    public List<Post> all() {
        return postList;
    }

    public Optional<Post> getById(long id) {
        return postList.stream().filter(p -> p.getId() == id).findFirst();
    }

    public Post save(Post post) {
            if (post.getId() == 0) {
                postList.add(new Post(post.getData(), postList.size() + 1));
            } else {
                Optional<Post> tmpPost = getById(post.getId());
                if (tmpPost.isPresent()) {
                    tmpPost.ifPresent(value -> value.setData(post.getData()));
                }
            }
        return post;
    }

    public void removeById(long id) {
        postList.remove(getById(id).get());
    }
}
