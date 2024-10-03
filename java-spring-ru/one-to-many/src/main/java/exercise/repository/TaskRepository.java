package exercise.repository;

import exercise.model.Task2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;


@Repository
public interface TaskRepository extends JpaRepository<Task2, Long> {
    Optional<Task2> findByTitle(String title);
}
