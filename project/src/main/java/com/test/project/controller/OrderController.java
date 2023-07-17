package com.test.project.controller;

import com.test.project.dto.order.OrderDTO;
import com.test.project.entity.Order;
import com.test.project.model.ApiResponse;
import com.test.project.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("/{idSeller}")
    public ApiResponse addOrder(@PathVariable Long idSeller, @RequestBody OrderDTO order) {
        Order data = orderService.saveOrder(idSeller, order);
        ApiResponse response = new ApiResponse(data);
        return response;
    }

    @GetMapping("/{idSeller}/seller")
    public ApiResponse getOrders(@PathVariable Long idSeller) {
        List<Order> data = orderService.getOrders(idSeller);
        ApiResponse response = new ApiResponse(data);
        return response;
    }

    @GetMapping("/{id}")
    public ApiResponse getOrderById(@PathVariable Long id) {
        Order data = orderService.getOrderById(id);
        ApiResponse response = new ApiResponse(data);
        return response;
    }

    @PutMapping("/{id}")
    public void updateOrder(@PathVariable Long id,@RequestBody OrderDTO order) {
        orderService.updateOrder(id, order);
    }

    @DeleteMapping("/{id}")
    public String deleteOrderById(@PathVariable Long id) {
        return orderService.deleteOrderById(id);
    }

}
