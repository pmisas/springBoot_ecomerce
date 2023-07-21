package com.test.project.controller;

import com.test.project.dto.item.ItemDTO;
import com.test.project.entity.Item;
import com.test.project.entity.User;
import com.test.project.model.ApiResponse;
import com.test.project.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/items")
public class ItemController {

    @Autowired
    ItemService itemService;

    @PostMapping("/{idUser}")
    @PreAuthorize("BUYER")
    public ApiResponse addItem(@PathVariable Long idUser, @RequestBody ItemDTO item) {
        Item data = itemService.saveItem(idUser, item);
        ApiResponse response = new ApiResponse();
        response.setError(false);
        response.setMessage("");
        response.setData(data);
        return response;
    }

    @GetMapping("/public/all")
    public ApiResponse getItems() {
        List<Item> data = itemService.getItems();
        ApiResponse response = new ApiResponse();
        response.setError(false);
        response.setMessage("");
        response.setData(data);
        return response;
    }

    @GetMapping("/public/{id}")
    public ApiResponse getItemById(@PathVariable Long id) {

        Item data = itemService.getItemById(id);
        ApiResponse response = new ApiResponse();
        response.setError(false);
        response.setMessage("");
        response.setData(data);
        return response;
    }

    @PutMapping("/{id}")
    @PreAuthorize("BUYER")
    public ApiResponse updateItem(@PathVariable Long id, @RequestBody ItemDTO item) {
        Item data = itemService.updateItem(id, item);
        ApiResponse response = new ApiResponse();
        response.setError(false);
        response.setMessage("");
        response.setData(data);
        return response;
    }

    @DeleteMapping("/{id}")
    public ApiResponse deleteItemById(@PathVariable Long id) {
        String data = itemService.deleteItemById(id);
        ApiResponse response = new ApiResponse();
        response.setError(false);
        response.setMessage("");
        response.setData(data);
        return response;
    }
}
