package exercise.controller.users;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

import exercise.model.Post;
import exercise.Data;

// BEGIN
@RestController
@RequestMapping("/api/users")
public class PostsController {

    private final List<Post> posts = Data.getPosts();

    @GetMapping("/{id}/posts")
    public ResponseEntity<List<Post>> getPosts(@PathVariable String id){
        var list = posts.stream().filter(p -> p.getUserId() == Integer.parseInt(id)).toList();
        return ResponseEntity.ok(list);
    }

    @PostMapping("/{id}/posts")
    public ResponseEntity<Post> createPost(@PathVariable String id, @RequestBody Post post){
        post.setUserId(Integer.parseInt(id));
        posts.add(post);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(post);
    }
}
// END
