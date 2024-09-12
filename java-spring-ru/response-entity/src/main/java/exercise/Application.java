package exercise;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import exercise.model.Post;

@SpringBootApplication
@RestController
public class Application {
    // Хранилище добавленных постов
    private List<Post> posts = Data.getPosts();

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    // BEGIN
    @GetMapping("/posts")
    public ResponseEntity<List<Post>> getPosts(@RequestParam(defaultValue = "10") Integer limit){
        var list = posts.stream().limit(limit).toList();
        return ResponseEntity.ok()
                .header("X-Total-Count", String.valueOf(list.size()))
                .body(list);
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<Post> getPost(@PathVariable String id){
        var first = posts.stream().filter(p -> p.getId().equals(id)).findFirst();
        return ResponseEntity.of(first);
    }

    @PostMapping("/posts")
    public ResponseEntity<Post> createPost(@RequestBody Post post){
        posts.add(post);
        return ResponseEntity.status(HttpStatus.CREATED).body(post);
    }

    @PutMapping("/posts/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable String id, @RequestBody Post post){
        var first = posts.stream().filter(p -> p.getId().equals(id)).findFirst();
        ResponseEntity<Post> response;
        if(first.isPresent()){
            var postUpdated = first.get();
            postUpdated.setId(post.getId());
            postUpdated.setTitle(post.getTitle());
            postUpdated.setBody(post.getBody());
            response = ResponseEntity.ok(post);
        } else {
            response = ResponseEntity.status(HttpStatus.NO_CONTENT).body(post);
        }
        return response;
    }
    // END

    @DeleteMapping("/posts/{id}")
    public void destroy(@PathVariable String id) {
        posts.removeIf(p -> p.getId().equals(id));
    }
}
