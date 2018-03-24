package io.placements.challenge.jong.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.placements.challenge.jong.model.Item;
import io.placements.challenge.jong.model.ItemComment;
import io.placements.challenge.jong.model.ItemCommentRepository;
import io.placements.challenge.jong.model.ItemHistory;
import io.placements.challenge.jong.model.ItemHistoryRepository;
import io.placements.challenge.jong.model.ItemRepository;

@RestController
public class ItemController {
    
	@Autowired
	private ItemRepository itemRepository;
	@Autowired
	private ItemCommentRepository itemCommentRepository;
	@Autowired
	private ItemHistoryRepository itemHistoryRepository;

	@RequestMapping("/item/listAll")
	public List<Item> listAll() {
		
		return itemRepository.listAllOrderBy();
	}
	
	@RequestMapping(value = "/item/list", method = RequestMethod.GET)
	public Map<String, Object> getItemList(
			
			@RequestParam(value="page", required=false) int page, 
			@RequestParam(value="rows", required=false) int rows, 
			@RequestParam(value="filter", required=false) String filter) {
		
		List<Item> list = itemRepository.listPageBetween((page-1) * rows + 1, page * rows, filter);
		int count = itemRepository.countFilter(filter);
		Map<String, Object> map = new HashMap<>();
		map.put("rows", list);
		map.put("records", count);
		map.put("page", page);
		if(rows > 0)
			map.put("total", count / rows);
		
		return map;		
	}
	
	@RequestMapping("/item/comment/{itemId}")
	public List<ItemComment> getComment(@PathVariable("itemId") int itemId) {
		
		return itemCommentRepository.findByItemID(itemId);
	}
	
	@RequestMapping("/item/comment/{itemId}/{new_comment}")
	public String addComment(@PathVariable("itemId") int itemId, @PathVariable("new_comment") String new_comment) {
		
		itemCommentRepository.save(new ItemComment(itemId, new_comment));
		return "saved";
	}
	
	@RequestMapping("/item/history/{itemId}/{column}/{old_val}/{new_val}")
	public String editColumn(@PathVariable("itemId") int itemId, @PathVariable("column") String column, @PathVariable("old_val") String old_val, @PathVariable("new_val") String new_val) {
		
		itemHistoryRepository.save(new ItemHistory(itemId, column, old_val, new_val));
		if(column.equals("adjustments")) {
			Optional<Item> item_saved = itemRepository.findById(itemId);
			item_saved.get().setAdjustments(Double.parseDouble(new_val));
			itemRepository.save(item_saved.get());
		}
		return "saved";
	}
	
	@RequestMapping("/item/history/{itemId}")
	public List<ItemHistory> getHistory(@PathVariable("itemId") int itemId) {
		
		return itemHistoryRepository.findByItemID(itemId);
	}
}
