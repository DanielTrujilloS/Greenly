package pe.edu.upc.greenly.service;

import pe.edu.upc.greenly.entities.Post;
import java.util.List;

public interface PostService {
    public void insert(Post post);
    public List<Post> list();
    public void delete(int id);
}
