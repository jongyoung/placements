package io.placements.challenge.jong.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.placements.challenge.jong.model.Item;
import io.placements.challenge.jong.model.ItemCommentRepository;
import io.placements.challenge.jong.model.ItemRepository;



@RestController
public class DataController {
	
	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private ItemCommentRepository itemCommentRepository;

	@Autowired
	private ResourceLoader resourceLoader;
	
	@RequestMapping("/data/import")
	public String importJson() {

        try {
            Resource resource = resourceLoader.getResource("classpath:static/placements_teaser_data.json");
            
            ObjectMapper mapper = new ObjectMapper();
			TypeReference<List<Item>> typeReference = new TypeReference<List<Item>>(){};
			InputStream inputStream = resource.getInputStream();
			List<Item> items = mapper.readValue(inputStream,typeReference);
			for(Item item : items)
				itemRepository.save(item);
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
            return e.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
		
		return "completed!";
	}

	@RequestMapping("/data/init")
	public String initializeData() {
		itemCommentRepository.deleteAll();
		itemRepository.deleteAll();
		return "All data have removed";		
	}
	
	@EventListener(ApplicationReadyEvent.class)
	public void immportDataAfterStartup() {
	    System.out.println("Items count : " + itemRepository.count());
		if(itemRepository.count() == 0) {
		    System.out.println("import json on startup");
		    System.out.println(importJson());
		}
	}	
}
