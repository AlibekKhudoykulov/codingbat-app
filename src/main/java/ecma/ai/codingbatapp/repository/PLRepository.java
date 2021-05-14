package ecma.ai.codingbatapp.repository;

import ecma.ai.codingbatapp.entity.ProgrammingLanguage;
import ecma.ai.codingbatapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

//@Repository
public interface PLRepository extends JpaRepository<ProgrammingLanguage, Integer> {
    boolean existsByName(String name);
    boolean existsByNameAndIdNot(String name, Integer id);
}
