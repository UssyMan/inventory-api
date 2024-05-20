package qt.solutions.inventory.services;

import qt.solutions.inventory.model.Category;
import qt.solutions.inventory.model.Product;
import qt.solutions.inventory.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getProductsByCategory(Category category) {
        return productRepository.findByCategory(Optional.ofNullable(category));
    }
}
