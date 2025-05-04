package pe.edu.upc.greenly.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.greenly.entities.Post;
import pe.edu.upc.greenly.service.PostService;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService pS;

    @PostMapping
    public void insertar(@RequestBody Post post) {
        pS.insert(post);
    }

    @GetMapping
    public List<Post> listar() {
        return pS.list();
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable("id") Integer id) {
        pS.delete(id);
    }
}
