package pe.edu.upc.greenly.services;

import pe.edu.upc.greenly.dtos.PostDTO;

import java.util.List;

public interface PostService {
    //public void insert(Post post);
    List<PostDTO> listAll();
    //void delete(Long id);
    PostDTO addPost(PostDTO postDTO);
    PostDTO findById(Long id);
    PostDTO updatePost(Long id, PostDTO postDTO);
    void deletePost(Long id);
}
