package qt.solutions.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import qt.solutions.inventory.model.Warehouse;

public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {

}
