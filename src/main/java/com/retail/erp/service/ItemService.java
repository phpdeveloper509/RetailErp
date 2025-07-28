package com.retail.erp.service;

import java.util.List;
import com.retail.erp.model.Item;

public interface ItemService {
    Item createItem(Item item);
    Item updateItem(Long id, Item item);
    void deleteItem(Long id);
    List<Item> getAllItems();
    Item getItemById(Long id);
}
