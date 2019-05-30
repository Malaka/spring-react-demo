package code.challenge.springreactdemo.repo;

import code.challenge.springreactdemo.model.Category;
import code.challenge.springreactdemo.model.GroceryItem;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class GroceryItemRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private GroceryItemRepository groceryItemRepository;

    @Test
    public void whenFindByName_thenReturnEmployee() {
        // given
        Category category = new Category("Food");
        Category persistCategory = entityManager.persist(category);
        entityManager.flush();

        GroceryItem groceryItem = GroceryItem.builder().name("Rice").description("Brown Rice").category(persistCategory).build();
        entityManager.persist(groceryItem);
        entityManager.flush();

        // when
        List<GroceryItem> foodItems = groceryItemRepository.findAllByCategoryName("Food");

        // then
        Assert.assertEquals(1, foodItems.size());
        Assert.assertEquals("Rice", foodItems.iterator().next().getName());
    }

}