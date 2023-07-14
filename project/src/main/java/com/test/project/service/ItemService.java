package com.test.project.service;

import com.test.project.entity.Category;
import com.test.project.entity.Item;
import com.test.project.entity.User;
import com.test.project.repository.IItemRepository;
import com.test.project.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    IItemRepository itemRepository;

    @Autowired
    IUserRepository userRepository;

    public Item saveItem(Long idUser, Item item) {
        User user = userRepository.findById(idUser).orElseThrow(() -> new ResourceNotFoundException("no"));
        Item newItem = new Item();
        newItem.setName(item.getName());
        newItem.setDescription(item.getDescription());
        newItem.setPrice(item.getPrice());
        newItem.setStock(item.getStock());
        newItem.setImage(item.getImage());

        newItem.setSeller(user);
        user.getItems().add(newItem);

        userRepository.save(user);
        return newItem;
    }

    public List<Item> getItems() {
        return itemRepository.findAll();
    }

    public Item getItemById(Long id) {
        return itemRepository.findById(id).orElse(null);
    }

    public String deleteItemById(Long id) {
        itemRepository.deleteById(id);
        return "item " + id + " removed!";
    }

    public Item updateItem(Item item) {
        Item existingItem = itemRepository.findById(item.getId()).orElse(null);
        existingItem.setName(item.getName());
        existingItem.setCategories(item.getCategories());
        existingItem.setImage(item.getImage());
        existingItem.setPrice(item.getPrice());
        existingItem.setStock(item.getStock());
        existingItem.setDescription(item.getDescription());
        return itemRepository.save(existingItem);
    }

}
