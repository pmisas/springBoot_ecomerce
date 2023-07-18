package com.test.project.service;

import com.test.project.dto.order.OrderDTO;
import com.test.project.entity.Order;
import com.test.project.security.entity.User;
import com.test.project.http_errors.NotFoundException;
import com.test.project.repository.IItemRepository;
import com.test.project.repository.IOrderRepository;
import com.test.project.security.repository.IUserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrderService {

    @Autowired
    IItemRepository itemRepository;

    @Autowired
    IUserRepository userRepository;

    @Autowired
    IOrderRepository orderRepository;

    public Order saveOrder(Long idSeller, OrderDTO order) {
        User user = userRepository.findById(idSeller).orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + idSeller));
        Order newOrder = new Order();
        newOrder.setName(order.getName());
        newOrder.setAddress(order.getAddress());
        newOrder.setPrice(order.getPrice());
        newOrder.setNumber(order.getNumber());
        newOrder.setDateOrder(order.getDateOrder());
        newOrder.setUserId(idSeller);
        List items = itemRepository.findAllById(order.getItems_id());
        newOrder.setItems(items);

        return orderRepository.save(newOrder);
    }

    public List<Order> getOrders(Long idSeller) {
        return orderRepository.findByUserId(idSeller);
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElseThrow(()-> new NotFoundException("User not found with ID: " + id));
    }

    public String deleteOrderById(Long id) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if (optionalOrder.isPresent()) {
            userRepository.deleteById(id);
            return "Order " + id + " removed!";
        } else {
            throw new NotFoundException("Order not found with ID: " + id);
        }
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
