package qt.solutions.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import qt.solutions.inventory.model.Category;
import qt.solutions.inventory.model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}