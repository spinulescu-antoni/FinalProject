package com.academy.finalproject.controller;

import com.academy.finalproject.service.ItemService;
import com.academy.finalproject.entity.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @PostMapping
    public ResponseEntity<Item> save(@RequestBody Item item){
        return new ResponseEntity<>(itemService.save(item), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> getItem(@PathVariable Long id){
       Item foundItem = itemService.getItem(id);
       if (foundItem == null){
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }
       return new ResponseEntity<>(foundItem, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Item> updateItem(@RequestBody Item item,@PathVariable Long id){
        Item updateItem = itemService.updateItem(item, id);
        return updateItem == null
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(updateItem, HttpStatus.OK);


    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteItemById(@PathVariable Long id){
       Item deleteItem = itemService.deleteItemById(id);
        return deleteItem == null
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(deleteItem, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Item>> getAllItems(){
        return new ResponseEntity<>(itemService.getAllItem(), HttpStatus.OK);
    }
}
