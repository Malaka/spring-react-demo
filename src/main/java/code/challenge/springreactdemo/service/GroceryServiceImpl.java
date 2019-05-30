package code.challenge.springreactdemo.service;

import code.challenge.springreactdemo.model.Category;
import code.challenge.springreactdemo.model.GroceryItem;
import code.challenge.springreactdemo.repo.CategoryRepository;
import code.challenge.springreactdemo.repo.GroceryItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class GroceryServiceImpl implements GroceryService {

    private final Logger log = LoggerFactory.getLogger(GroceryServiceImpl.class);
    private final GroceryItemRepository groceryItemRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public GroceryServiceImpl(GroceryItemRepository groceryItemRepository, CategoryRepository categoryRepository) {
        this.groceryItemRepository = groceryItemRepository;
        this.categoryRepository = categoryRepository;
    }


    @Override
    public Collection<GroceryItem> getAllItems() {
        log.info("Getting all GroceryItems");
        return groceryItemRepository.findAll();
    }

    @Override
    public Collection<GroceryItem> getItemsByCategory(String categoryName) {
        log.info("Getting all GroceryItems by category={}", categoryName);
        return groceryItemRepository.findAllByCategoryName(categoryName);
    }

    @Override
    public Optional<GroceryItem> getItem(Long id) {
        log.info("Get GroceryItem by id={}", id);
        return groceryItemRepository.findById(id);
    }

    @Override
    public GroceryItem createItem(GroceryItem groceryItem) {
        log.info("Creating new {}", groceryItem);
        return saveOrUpdateGroceryItem(groceryItem);
    }


    @Override
    public GroceryItem updateItem(GroceryItem groceryItem) {
        log.info("Updating new {}", groceryItem);
        return saveOrUpdateGroceryItem(groceryItem);
    }

    private GroceryItem saveOrUpdateGroceryItem(GroceryItem groceryItem) {
        Category category = categoryRepository.findByName(groceryItem.getCategory().getName());
        if (category == null) {
            log.info("Creating new category={}", category);
            category = categoryRepository.save(groceryItem.getCategory());
        }
        groceryItem.setCategory(category);
        return groceryItemRepository.save(groceryItem);
    }


    @Override
    public void deleteItem(Long id) {
        log.info("Deleteing GroceryItem by id={}", id);
        groceryItemRepository.deleteById(id);
    }
}
