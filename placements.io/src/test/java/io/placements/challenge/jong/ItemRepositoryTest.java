package io.placements.challenge.jong;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import io.placements.challenge.jong.model.Item;
import io.placements.challenge.jong.model.ItemRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ItemRepositoryTest {
	@Autowired
	private ItemRepository itemRepository;
	
	@Test
	public void findRatingScoresBiggerEqualTest() {
		
		Item item = new Item();
		
		item.setId(1);
		item.setCampaign_id(1);
		item.setCampaign_name("Satterfield-Turcotte : Multi-channelled next generation analyzer - e550");
		item.setLine_item_name("Awesome Plastic Car - 6475");
		item.setBooked_amount(430706.6871532752);
		item.setActual_amount(401966.50504006835);
		item.setAdjustments(1311.0731142230268);
		itemRepository.save(item);
		
		Optional<Item> item_saved = itemRepository.findById(1);

		//add
        assertThat(item_saved.get().getLine_item_name()).isEqualTo("Awesome Plastic Car - 6475");

        //page
		int page = 1;
		int rows = 3;
		String filter = "Satterfield";
		
		item.setId(2);
		item.setLine_item_name("Fantastic Plastic Shirt - 278c");
		itemRepository.save(item);        
        
		item.setId(3);
		item.setLine_item_name("Small Steel Gloves - 711b");
		itemRepository.save(item);        
        
		item.setId(4);
		item.setLine_item_name("Ergonomic Rubber Computer - 2ea5");
		itemRepository.save(item);        
        
		item.setId(5);
		item.setLine_item_name("Sleek Rubber Gloves - bafc");
		itemRepository.save(item);        
        
		item.setId(6);
		item.setLine_item_name("Practical Plastic Hat - 17fd");
		itemRepository.save(item);        
        
        assertThat(itemRepository.countFilter(filter)).isEqualTo(6);

		List<Item> list = itemRepository.listPageBetween((page-1) * rows, page * rows, filter);
        assertThat(list).hasSize(3);        
        assertThat(list.get(0).getLine_item_name()).isEqualTo("Awesome Plastic Car - 6475");
        
        page = 2;
        assertThat((page-1) * rows + 1).isEqualTo(4);  
        assertThat(page * rows).isEqualTo(6);  
        list = itemRepository.listPageBetween((page-1) * rows + 1, page * rows, filter);
        assertThat(list).hasSize(3);        
        assertThat(list.get(0).getLine_item_name()).isEqualTo("Ergonomic Rubber Computer - 2ea5");
        
        filter = "Rath-Yundt";
        assertThat(itemRepository.countFilter(filter)).isEqualTo(0);
        
        //update
        item_saved = itemRepository.findById(1);
		item_saved.get().setAdjustments(0.2);
		itemRepository.save(item_saved.get());
		item_saved = itemRepository.findById(1);
		assertThat(item_saved.get().getAdjustments()).isEqualTo(0.2);
	}
}
