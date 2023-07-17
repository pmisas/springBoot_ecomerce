package com.test.project.service;

import com.test.project.dto.item.ItemDTO;
import com.test.project.entity.Category;
import com.test.project.entity.Item;
import com.test.project.entity.User;
import com.test.project.http_errors.NotFoundException;
import com.test.project.repository.ICategoryRepository;
import com.test.project.repository.IItemRepository;
import com.test.project.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.thymeleaf.cache.ICache;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    IItemRepository itemRepository;

    @Autowired
    IUserRepository userRepository;

    @Autowired
    ICategoryRepository categoryRepository;

    public Item saveItem(Long idUser, ItemDTO item) {
        User user = userRepository.findById(idUser).orElseThrow(() -> new NotFoundException("Item not found with ID: " + idUser));
        Item newItem = new Item();
        newItem.setName(item.getName());
        newItem.setDescription(item.getDescription());
        newItem.setPrice(item.getPrice());
        newItem.setStock(item.getStock());
        newItem.setImage(item.getImage());
        List<Category> categories = categoryRepository.findAllById(item.getCategories_ids());
        newItem.setCategories(categories);

        newItem.setSeller(user);
        user.getItems().add(newItem);

        newItem.setCategories(categories);
        itemRepository.save(newItem);
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
        Optional<Item> optionalItem = itemRepository.findById(id);
        if (optionalItem.isPresent()) {
            itemRepository.deleteById(id);
            return "Item " + id + " removed!";
        } else {
            throw new NotFoundException("Item not found with ID: " + id);
        }
    }

    public Item updateItem(Long id, ItemDTO item) {
        Item existingItem = itemRepository.findById(id).orElseThrow(()-> new NotFoundException("Item not found with ID: " + id));
        existingItem.setName(item.getName());
        /*existingItem.setCategories(item.getCategories());*/
        existingItem.setImage(item.getImage());
        existingItem.setPrice(item.getPrice());
        existingItem.setStock(item.getStock());
        existingItem.setDescription(item.getDescription());
        List<Category> categories = categoryRepository.findAllById(item.getCategories_ids());
        existingItem.setCategories(categories);

        return itemRepository.save(existingItem);
    }

}
