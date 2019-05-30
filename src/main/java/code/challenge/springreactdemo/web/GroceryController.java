package code.challenge.springreactdemo.web;

import code.challenge.springreactdemo.model.GroceryItem;
import code.challenge.springreactdemo.service.GroceryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/api")
class GroceryController {

    private final Logger log = LoggerFactory.getLogger(GroceryController.class);

    private final GroceryService groceryService;

    @Autowired
    public GroceryController(GroceryService groceryService) {
        this.groceryService = groceryService;
    }


    @GetMapping("/items")
    Collection<GroceryItem> items() {
        return groceryService.getAllItems();
    }

    @GetMapping("/item/{id}")
    ResponseEntity<?> getItem(@PathVariable Long id) {
        Optional<GroceryItem> groceryItem = groceryService.getItem(id);
        return groceryItem.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/search/{name}")
    Collection<GroceryItem> getItemByCategory(@PathVariable String name) {
        return groceryService.getItemsByCategory(name);
    }

    @PostMapping("/item")
    ResponseEntity<GroceryItem> createItem(@Valid @RequestBody GroceryItem groceryItem) throws URISyntaxException {
        log.info("Request to create groceryItem: {}", groceryItem);
        GroceryItem result = groceryService.createItem(groceryItem);
        return ResponseEntity.created(new URI("/api/item/" + result.getId()))
                .body(result);
    }

    @PutMapping("/item/{id}")
    ResponseEntity<GroceryItem> updateItem(@Valid @RequestBody GroceryItem groceryItem) {
        log.info("Request to update groceryItem: {}", groceryItem);
        GroceryItem result = groceryService.updateItem(groceryItem);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/item/{id}")
    public ResponseEntity<?> deleteItem(@PathVariable Long id) {
        log.info("Request to delete GroceryItem: {}", id);
        groceryService.deleteItem(id);
        return ResponseEntity.ok().build();
    }
}