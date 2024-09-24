package exercise.controller;

import exercise.model.Comment;
import exercise.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

import exercise.model.Post;
import exercise.repository.PostRepository;
import exercise.exception.ResourceNotFoundException;
import exercise.dto.PostDTO;
import exercise.dto.CommentDTO;

// BEGIN
@RestController
@RequestMapping("/posts")
public class PostsController {

    @Autowired
    PostRepository postRepository;
    @Autowired
    CommentRepository commentRepository;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PostDTO> getPosts(){
        var posts = postRepository.findAll().stream().map(this::setDTO).toList();
        return posts;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PostDTO getPost(@PathVariable long id){
        var post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post with id "  + id + " not found"));
        return setDTO(post);
    }

    private PostDTO setDTO(Post post){
        var comments = commentRepository.findByPostId(post.getId()).stream()
                .map(this::setCommentDTO).toList();
        var pDTO = new PostDTO();
        pDTO.setId(post.getId());
        pDTO.setBody(post.getBody());
        pDTO.setTitle(post.getTitle());
        pDTO.setComments(comments);
        return pDTO;
    }

    private CommentDTO setCommentDTO(Comment comment){
        var cDTO = new CommentDTO();
        cDTO.setId(comment.getId());
        cDTO.setBody(comment.getBody());
        return cDTO;
    }
}
// END
