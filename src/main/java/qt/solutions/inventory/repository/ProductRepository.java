package qt.solutions.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import qt.solutions.inventory.model.Category;
import qt.solutions.inventory.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Long> {

    List<Product> findByCategory(@Param("category") Optional<Category> category);

}
