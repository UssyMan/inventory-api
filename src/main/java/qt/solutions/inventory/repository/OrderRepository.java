package qt.solutions.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import qt.solutions.inventory.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}