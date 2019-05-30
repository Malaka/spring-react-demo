package code.challenge.springreactdemo.repo;

import code.challenge.springreactdemo.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByName(String name);
}