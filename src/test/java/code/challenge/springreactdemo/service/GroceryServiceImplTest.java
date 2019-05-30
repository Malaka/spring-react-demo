package code.challenge.springreactdemo.service;

import code.challenge.springreactdemo.model.GroceryItem;
import code.challenge.springreactdemo.repo.CategoryRepository;
import code.challenge.springreactdemo.repo.GroceryItemRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RunWith(SpringRunner.class)
public class GroceryServiceImplTest {

    @MockBean
    private GroceryItemRepository groceryItemRepository;

    @MockBean
    private CategoryRepository categoryRepository;
    
    private GroceryService groceryService;

    @Before
    public void setUp() {
        groceryService = new GroceryServiceImpl(groceryItemRepository, categoryRepository);
        GroceryItem item1 = GroceryItem.builder().id(1L).name("item1").build();
        GroceryItem item2 = GroceryItem.builder().id(2L).name("item2").build();
        Mockito.when(groceryItemRepository.findAll())
                .thenReturn(Stream.of(item1, item2).collect(Collectors.toList()));
    }

    @Test
    public void whenFindAll_thenItemsShouldBeReturn() {
        Collection<GroceryItem> allItems = groceryService.getAllItems();
        Assert.assertEquals(2, allItems.size());
    }


}