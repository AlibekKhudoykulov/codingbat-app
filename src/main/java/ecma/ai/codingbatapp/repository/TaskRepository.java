package ecma.ai.codingbatapp.repository;

import ecma.ai.codingbatapp.entity.Task;
import ecma.ai.codingbatapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

//@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
}
