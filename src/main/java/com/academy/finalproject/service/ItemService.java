package com.academy.finalproject.service;

import com.academy.finalproject.entity.Item;
import com.academy.finalproject.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;


    public Item save(Item item){
        return itemRepository.save(item);
    }
    public Item getItem(Long id){
        Optional<Item> foundItem = itemRepository.findById(id);
        return foundItem.orElse(null);
    }
    public Item updateItem(Item item, Long id){
        Optional<Item> foundItem = itemRepository.findById(id);
        if (foundItem.isEmpty()){
            return null;
        }
        Item validObject = foundItem.get();
        validObject.setName(item.getName());
        validObject.setPrice(item.getPrice());
        validObject.setQuantity(item.getQuantity());
        validObject.setDescription(item.getDescription());
        validObject.setUrl(item.getUrl());

        itemRepository.save(validObject);
        return validObject;

    }

    public Item deleteItemById(Long id){
        Optional<Item> foundItem = itemRepository.findById(id);
        if (foundItem.isEmpty()){
            return null;
        }
        itemRepository.deleteById(id);
        return foundItem.get();
    }

    public List<Item> getAllItem(){
        return itemRepository.findAll();
    }
}
