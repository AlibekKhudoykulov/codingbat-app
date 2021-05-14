package ecma.ai.codingbatapp.repository;

import ecma.ai.codingbatapp.entity.Category;
import ecma.ai.codingbatapp.entity.Task;
import ecma.ai.codingbatapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

//@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
    boolean existsByNameAndCategoryId(String name, Integer category_id);
    boolean existsByNameAndCategoryIdAndIdNot(String name, Integer category_id, Integer id);
}
