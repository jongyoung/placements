package io.placements.challenge.jong.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ItemCommentRepository extends JpaRepository<ItemComment, Long>{

	@Query("FROM ItemComment t WHERE item_id = :itemId")
	public List<ItemComment> findByItemID(@Param("itemId") int itemId);
}