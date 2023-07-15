package com.test.project.controller;

import com.test.project.dto.item.ItemDTO;
import com.test.project.dto.order.OrderDTO;
import com.test.project.entity.Item;
import com.test.project.entity.Order;
import com.test.project.service.ItemService;
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
    public Order addOrder(@PathVariable Long idSeller, @RequestBody OrderDTO order) {
        return orderService.saveOrder(idSeller, order);
    }

    @GetMapping("/{idSeller}/seller")
    public List<Order> getOrders(@PathVariable Long idSeller) {
        return orderService.getOrders(idSeller);
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id);
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
