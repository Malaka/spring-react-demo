package code.challenge.springreactdemo.service;

import code.challenge.springreactdemo.model.GroceryItem;

import java.util.Collection;
import java.util.Optional;

public interface GroceryService {

    Collection<GroceryItem> getAllItems();

    Collection<GroceryItem> getItemsByCategory(String categoryName);

    Optional<GroceryItem> getItem(Long id);

    GroceryItem createItem(GroceryItem groceryItem);

    GroceryItem updateItem(GroceryItem groceryItem);

    void deleteItem(Long id);

}
