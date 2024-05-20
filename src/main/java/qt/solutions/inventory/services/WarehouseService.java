package qt.solutions.inventory.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import qt.solutions.inventory.exception.ResourceNotFoundException;
import qt.solutions.inventory.model.Warehouse;
import qt.solutions.inventory.repository.WarehouseRepository;

import java.util.List;
import java.util.Optional;


@Service
public class WarehouseService {

        @Autowired
        private WarehouseRepository warehouseRepository;

        public List<Warehouse> getAllWarehouses() {
            return warehouseRepository.findAll();
        }

        public Warehouse getWarehouseById(Long id) {
            Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(id);
            return optionalWarehouse.orElseThrow(() -> new ResourceNotFoundException("Warehouse not found with id: " + id));
        }

        public Warehouse createWarehouse(Warehouse warehouse) {
            return warehouseRepository.save(warehouse);
        }

        public Warehouse updateWarehouse(Long id, Warehouse warehouseUpdate) {
            Warehouse existingWarehouse = getWarehouseById(id);
            existingWarehouse.setName(warehouseUpdate.getName());
            existingWarehouse.setCapacity(warehouseUpdate.getCapacity());
            existingWarehouse.setLocation(warehouseUpdate.getLocation());
            // Update other properties as needed
            return warehouseRepository.save(existingWarehouse);
        }

        public void deleteWarehouse(Long id) {
            warehouseRepository.deleteById(id);
        }

}

