package code.challenge.springreactdemo.repo;

import code.challenge.springreactdemo.model.GroceryItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroceryItemRepository extends JpaRepository<GroceryItem, Long> {
    List<GroceryItem> findAllByCategoryName(String name);
}