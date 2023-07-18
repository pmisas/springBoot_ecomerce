package com.test.project.controller;

import com.test.project.dto.item.ItemDTO;
import com.test.project.entity.Item;
import com.test.project.model.ApiResponse;
import com.test.project.service.ItemService;
import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {

    @Autowired
    ItemService itemService;

    @PostMapping("/{idUser}")
    public ApiResponse addItem(@PathVariable Long idUser, @RequestBody ItemDTO item) {
        Item data = itemService.saveItem(idUser, item);
        ApiResponse response = new ApiResponse();
        response.setError(false);
        response.setMessage("");
        response.setData(data);
        return response;
    }

    @GetMapping
    @PermitAll
    public ApiResponse getItems() {
        List<Item> data = itemService.getItems();
        ApiResponse response = new ApiResponse();
        response.setError(false);
        response.setMessage("");
        response.setData(data);
        return response;
    }

    @GetMapping("/{id}")
    public ApiResponse getItemById(@PathVariable Long id) {

        Item data = itemService.getItemById(id);
        ApiResponse response = new ApiResponse();
        response.setError(false);
        response.setMessage("");
        response.setData(data);
        return response;
    }

    @PutMapping("/{id}")
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
