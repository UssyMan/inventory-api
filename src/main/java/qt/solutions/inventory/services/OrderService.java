package qt.solutions.inventory.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import qt.solutions.inventory.exception.ResourceNotFoundException;
import qt.solutions.inventory.model.*;
import qt.solutions.inventory.repository.OrderRepository;
import qt.solutions.inventory.repository.ProductRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public Order createOrder(List<OrderItemDTO> orderItemsDto) {
        Order order = new Order();
        order.setOrderDate(LocalDate.now());
        double totalAmount = 0;

        for (OrderItemDTO itemDto : orderItemsDto) {
            Product product = productRepository.findById(itemDto.getProductId())
                    .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + itemDto.getProductId()));
            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(product);
            orderItem.setQuantity(itemDto.getQuantity());
            order.getOrderItems().add(orderItem);
            totalAmount = totalAmount + (product.getPrice() * itemDto.getQuantity());
        }

        order.setTotalAmount(totalAmount);
        return orderRepository.save(order);
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + id));
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Transactional
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    @Transactional
    public Order updateOrder(Long id, List<OrderItemDTO> orderItemsDto) {
        Order existingOrder = getOrderById(id);
        existingOrder.getOrderItems().clear();
        double totalAmount = 0;

        for (OrderItemDTO itemDto : orderItemsDto) {
            Product product = productRepository.findById(itemDto.getProductId())
                    .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + itemDto.getProductId()));
            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(

                    product);
            orderItem.setQuantity(itemDto.getQuantity());
            existingOrder.getOrderItems().add(orderItem);
            totalAmount = totalAmount + product.getPrice()* itemDto.getQuantity();
        }

        existingOrder.setTotalAmount(totalAmount);
        return orderRepository.save(existingOrder);
    }
}