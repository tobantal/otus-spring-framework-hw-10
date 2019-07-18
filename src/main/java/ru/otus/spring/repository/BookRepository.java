package ru.otus.spring.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import ru.otus.spring.domain.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

	Optional<Book> findByName(String name);
	
	List<Book> findAllByAuthorName(String author);
	
	List<Book> findAllByGenreName(String genre);
	
	@EntityGraph(value = "book-entity-graph", type = EntityGraph.EntityGraphType.FETCH)
	List<Book> findAll();
	
}
