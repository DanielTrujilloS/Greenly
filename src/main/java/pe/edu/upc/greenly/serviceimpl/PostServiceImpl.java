package pe.edu.upc.greenly.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.greenly.entities.Post;
import pe.edu.upc.greenly.repositories.PostRepository;
import pe.edu.upc.greenly.service.PostService;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository pR;

    @Override
    public void insert(Post post) {
        pR.save(post);
    }

    @Override
    public List<Post> list() {
        return pR.findAll();
    }

    @Override
    public void delete(int id) {
        pR.deleteById(id);
    }
}
