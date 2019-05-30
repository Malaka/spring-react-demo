package code.challenge.springreactdemo.web;

import code.challenge.springreactdemo.model.GroceryItem;
import code.challenge.springreactdemo.service.GroceryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(GroceryController.class)
public class GroceryControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private GroceryService service;

    @Test
    public void givenEmployees_whenGetEmployees_thenReturnJsonArray() throws Exception {

        GroceryItem item = GroceryItem.builder().name("food").description("description").build();
        List<GroceryItem> items = Collections.singletonList(item);

        given(service.getAllItems()).willReturn(items);

        mvc.perform(get("/api/items")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is(item.getName())));
    }

}