package io.placements.challenge.jong.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ItemHistoryRepository extends JpaRepository<ItemHistory, Long>{

	@Query("FROM ItemHistory t WHERE item_id = :itemId")
	public List<ItemHistory> findByItemID(@Param("itemId") int itemId);
}
/*
2018-03-23 19:16:09.704 ERROR 24345 --- [io-8080-exec-10] o.a.c.c.C.[.[.[/].[dispatcherServlet]    : 
Servlet.service() for servlet [dispatcherServlet] in context with path [] threw exception 
[Request processing failed; nested exception is org.springframework.dao.InvalidDataAccessApiUsageException: 
For queries with named parameters you need to use provide names for method parameters. Use @Param for query method parameters, or when on Java 8+ use the javac flag -parameters.; nested exception is java.lang.IllegalStateException: For queries with named parameters you need to use provide names for method parameters. Use @Param for query method parameters, or when on Java 8+ use the javac flag -parameters.] with root cause
*/