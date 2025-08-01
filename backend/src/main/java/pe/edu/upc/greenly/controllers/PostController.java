package pe.edu.upc.greenly.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.greenly.dtos.PostDTO;
import pe.edu.upc.greenly.services.PostService;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/Greenly/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/agregar")
    public ResponseEntity<PostDTO> addPost(@RequestBody PostDTO dto) {
        return ResponseEntity.ok(postService.addPost(dto));
    }

    @GetMapping("/listar")
    public List<PostDTO> listPosts() {
        return postService.listAll();
    }

    @GetMapping("/obtener/{id}")
    public ResponseEntity<PostDTO> getPost(@PathVariable Long id) {
        PostDTO dto = postService.findById(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }



    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/modificar/{id}")
    public ResponseEntity<PostDTO> updatePost(@PathVariable Long id, @RequestBody PostDTO postDTO) {
        PostDTO updatedPost = postService.updatePost(id, postDTO);
        return ResponseEntity.ok(updatedPost);
    }
}
