package com.test.project.service;

import com.test.project.dto.item.ItemDTO;
import com.test.project.dto.order.OrderDTO;
import com.test.project.entity.Category;
import com.test.project.entity.Item;
import com.test.project.entity.Order;
import com.test.project.entity.User;
import com.test.project.repository.IItemRepository;
import com.test.project.repository.IOrderRepository;
import com.test.project.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    IItemRepository itemRepository;

    @Autowired
    IUserRepository userRepository;

    @Autowired
    IOrderRepository orderRepository;

    public Order saveOrder(Long idSeller, OrderDTO order) {
        User user = userRepository.findById(idSeller).orElseThrow(() -> new ResourceNotFoundException("no"));
        Order newOrder = new Order();
        newOrder.setName(order.getName());
        newOrder.setAddress(order.getAddress());
        newOrder.setPrice(order.getPrice());
        newOrder.setNumber(order.getNumber());
        newOrder.setDateOrder(order.getDateOrder());
        newOrder.setUser(user);
        List items = itemRepository.findAllById(order.getItems_id());
        newOrder.setItems(items);

        return orderRepository.save(newOrder);
    }

    public List<Order> getOrder() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    public String deleteOrderById(Long id) {
        orderRepository.deleteById(id);
        return "order " + id + " removed!";
    }

    public void updateOrder(Long id, OrderDTO order) {
        /*
        Item existingItem = itemRepository.findById(id).orElse(null);
        existingItem.setName(item.getName());
        /*existingItem.setCategories(item.getCategories());
        existingItem.setImage(item.getImage());
        existingItem.setPrice(item.getPrice());
        existingItem.setStock(item.getStock());
        existingItem.setDescription(item.getDescription());
        List<Category> categories = categoryRepository.findAllById(item.getCategories_ids());
        existingItem.setCategories(categories);
        */
    }
}
