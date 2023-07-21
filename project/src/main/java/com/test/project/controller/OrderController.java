package com.test.project.controller;

import com.test.project.dto.item.ItemDTO;
import com.test.project.dto.order.OrderDTO;
import com.test.project.entity.Item;
import com.test.project.entity.Order;
import com.test.project.model.ApiResponse;
import com.test.project.service.ItemService;
import com.test.project.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("/public/orders/{idSeller}")
    public ApiResponse addOrder(@PathVariable Long idSeller, @RequestBody OrderDTO order) {
        Order data = orderService.saveOrder(idSeller, order);
        ApiResponse response = new ApiResponse();
        response.setError(false);
        response.setMessage("");
        response.setData(data);
        return response;
    }

    @GetMapping("/seller/orders/{idSeller}")
    public ApiResponse getOrders(@PathVariable Long idSeller) {
        List<Order> data = orderService.getOrders(idSeller);
        ApiResponse response = new ApiResponse();
        response.setError(false);
        response.setMessage("");
        response.setData(data);
        return response;
    }

    @GetMapping("/seller/orders/{id}")
    public ApiResponse getOrderById(@PathVariable Long id) {
        Order data = orderService.getOrderById(id);
        ApiResponse response = new ApiResponse();
        response.setError(false);
        response.setMessage("");
        response.setData(data);
        return response;
    }

    @PutMapping("/seller/orders/{id}")
    public void updateOrder(@PathVariable Long id,@RequestBody OrderDTO order) {
        orderService.updateOrder(id, order);
    }

    @DeleteMapping("/seller/orders/{id}")
    public ApiResponse deleteOrderById(@PathVariable Long id) {

        String data = orderService.deleteOrderById(id);
        ApiResponse response = new ApiResponse();
        response.setError(false);
        response.setMessage("");
        response.setData(data);
        return response;
    }
}
