package com.retail.erp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.retail.erp.model.Item;
import com.retail.erp.repository.ItemRepository;

@Service
public class ItemServiceImpl implements ItemService{

	@Autowired
    private ItemRepository itemRepo;

    @Override
    public Item createItem(Item item) {
        return itemRepo.save(item);
    }

    @Override
    public Item updateItem(Long id, Item updated) {
        Item existing = itemRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found"));
        existing.setItemCode(updated.getItemCode());
        existing.setItemName(updated.getItemName());
        existing.setItemCategoryId(updated.getItemCategoryId());
        existing.setUnit(updated.getUnit());
        existing.setDescription(updated.getDescription());
        return itemRepo.save(existing);
    }

    @Override
    public void deleteItem(Long id) {
        itemRepo.deleteById(id);
    }

    @Override
    public List<Item> getAllItems() {
        return itemRepo.findAll();
    }

    @Override
    public Item getItemById(Long id) {
        return itemRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found"));
    }
}
