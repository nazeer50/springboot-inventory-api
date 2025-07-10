package com.example.inventory;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/inventory")
public class InventoryController {
    private List<Inventory> inventoryList = new ArrayList<>();

    @GetMapping
    public List<Inventory> getAll() {
        return inventoryList;
    }

    @GetMapping("/{id}")
    public Inventory getById(@PathVariable int id) {
        return inventoryList.stream()
                .filter(item -> item.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @PostMapping
    public String add(@RequestBody Inventory inv) {
        inventoryList.add(inv);
        return "Inventory item added.";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable int id, @RequestBody Inventory inv) {
        for (Inventory item : inventoryList) {
            if (item.getId() == id) {
                item.setName(inv.getName());
                item.setPrice(inv.getPrice());
                item.setQuantity(inv.getQuantity());
                return "Inventory item updated.";
            }
        }
        return "Item not found.";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        inventoryList.removeIf(item -> item.getId() == id);
        return "Item deleted.";
    }
}
