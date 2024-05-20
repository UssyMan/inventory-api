package qt.solutions.inventory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import qt.solutions.inventory.model.Category;
import qt.solutions.inventory.model.OrderItemDTO;
import qt.solutions.inventory.model.Product;
import qt.solutions.inventory.model.Warehouse;
import qt.solutions.inventory.repository.CategoryRepository;
import qt.solutions.inventory.repository.ProductRepository;
import qt.solutions.inventory.repository.WarehouseRepository;
import qt.solutions.inventory.services.OrderService;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class InventoryApiApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(InventoryApiApplication.class, args);
	}
	@Autowired
	public ProductRepository productRepository;

	@Autowired
	public CategoryRepository categoryService;

	@Autowired
	public OrderService orderService;

	@Autowired
	public WarehouseRepository warehouseRepository;

	@Override
	public void run(String...args) throws Exception{

		Category electronics = new Category("Electronics");
		Category clothing = new Category("Clothing");
		Category books = new Category("Books");
		Category food = new Category("Food");

		List<Category> categories = Arrays.asList(electronics, clothing, books, food);
		categoryService.saveAll(categories);


		Product product1= new Product(1, food,"Apple",300);
		Product product2= new Product(2, books, "Head Frrst Java", 2000);
		Product product3= new Product(3, clothing,"Shirt",6000);
		Product product4= new Product(4, electronics, "Laptop", 95000);


		Warehouse warehouse1 = new Warehouse(1,"Abuja","Warehouse1",900);
		Warehouse warehouse2 = new Warehouse(2,"Lagos ","Warehouse2",800);
		Warehouse warehouse3 = new Warehouse(3,"Osun ","Warehouse3",700);

		productRepository.saveAll(List.of(product1, product2, product3, product4));

		OrderItemDTO item1 = new OrderItemDTO(product1.getId(), 5);
		OrderItemDTO item2 = new OrderItemDTO(product2.getId(), 6);

		orderService.createOrder(Arrays.asList(item1, item2));

		warehouseRepository.saveAll(List.of(warehouse1,warehouse2,warehouse3));
	}

}
