package io.placements.challenge.jong.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ItemRepository extends JpaRepository<Item, Integer>{
    
    @Query("FROM Item t ORDER BY campaign_id") 
    public List<Item> listAllOrderBy();

    @Query("FROM Item t WHERE id IN (SELECT id FROM Item WHERE campaign_name LIKE '%' || :filter || '%') AND rownum() between :begin and :end") 
    public List<Item> listPageBetween(@Param("begin") int begin, @Param("end") int end, @Param("filter") String filter);
    
    @Query("FROM Item t WHERE campaign_name LIKE '%' || :filter || '%'") 
    public List<Item> listContains(@Param("filter") String filter);

    @Query("SELECT count(1) FROM Item t WHERE campaign_name LIKE '%' || :filter || '%'") 
    public int countFilter(@Param("filter") String filter);

}
