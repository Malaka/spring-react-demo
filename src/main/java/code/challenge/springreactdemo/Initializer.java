package code.challenge.springreactdemo;

import code.challenge.springreactdemo.model.Category;
import code.challenge.springreactdemo.model.GroceryItem;
import code.challenge.springreactdemo.repo.CategoryRepository;
import code.challenge.springreactdemo.repo.GroceryItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Component
class Initializer implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final GroceryItemRepository itemRepository;

    public Initializer(CategoryRepository categoryRepository, GroceryItemRepository itemRepository) {
        this.categoryRepository = categoryRepository;
        this.itemRepository = itemRepository;
    }

    @Override
    public void run(String... strings) {
        Stream.of("Milk", "Meat", "Cleaning", "Fruit", "Veg").
                forEach(name -> categoryRepository.save(new Category(name)));

        Category milk = categoryRepository.findByName("Milk");

        GroceryItem freshMilk = GroceryItem.builder().name("Fresh Milk").description("This is fresh").category(milk).build();
        itemRepository.save(freshMilk);

        categoryRepository.findAll().forEach(System.out::println);
    }
}