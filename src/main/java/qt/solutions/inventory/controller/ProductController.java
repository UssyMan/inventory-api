package qt.solutions.inventory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import qt.solutions.inventory.exception.ResourceNotFoundException;
import qt.solutions.inventory.model.Category;
import qt.solutions.inventory.model.Product;
import qt.solutions.inventory.repository.CategoryRepository;
import qt.solutions.inventory.repository.ProductRepository;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product){
        Category category = product.getCategory();
        if (category.getId() == null) {
            // Save the category if it's new
            category = categoryRepository.save(category);
        }
        return productRepository.save(product);
    }

    @GetMapping("{id}")
    public ResponseEntity<Product> getProductById(@PathVariable long id){
        Product product = productRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("No product with id: " + id + " found"));
        return ResponseEntity.ok(product);
    }

    //Updating a product.
    @PutMapping("{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable long id,@RequestBody Product updateDetails){
        Product updatedProduct = productRepository.findById(id).
                orElseThrow(()-> new ResourceNotFoundException("No product found with id " + id));
        updatedProduct.setPrice(updateDetails.getPrice());
        updatedProduct.setCategory(updateDetails.getCategory());
        updatedProduct.setName(updateDetails.getName());

        productRepository.save(updatedProduct);

        return ResponseEntity.ok(updatedProduct);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable long id){
        Product product = productRepository.findById(id).
                orElseThrow(()-> new ResourceNotFoundException("No product found with id " + id));
        productRepository.delete(product);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    //handle id generation for rollback and deleted records
}
