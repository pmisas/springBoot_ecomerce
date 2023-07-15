package com.test.project.controller;

import com.test.project.dto.item.ItemDTO;
import com.test.project.entity.Item;
import com.test.project.entity.User;
import com.test.project.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/items")
public class ItemController {

    @Autowired
    ItemService itemService;

    @PostMapping("/{idUser}")
    public Item addItem(@PathVariable Long idUser, @RequestBody ItemDTO item) {
        return itemService.saveItem(idUser, item);
    }

    @GetMapping
    public List<Item> getItems() {
        return itemService.getItems();
    }

    @GetMapping("/{id}")
    public Item getItemById(@PathVariable Long id) {
        return itemService.getItemById(id);
    }

    @PutMapping("/{id}")
    public Item updateItem(@PathVariable Long id,@RequestBody ItemDTO item) {
        return itemService.updateItem(id, item);
    }

    @DeleteMapping("/{id}")
    public String deleteItemById(@PathVariable Long id) {
        return itemService.deleteItemById(id);
    }

}
