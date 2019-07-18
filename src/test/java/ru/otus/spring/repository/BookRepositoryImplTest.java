package ru.otus.spring.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import ru.otus.spring.domain.Book;
import ru.otus.spring.repository.BookRepository;

@DataJpaTest
public class BookRepositoryImplTest {
	
	@Autowired
	BookRepository bookRerpository;

	@Test
	public void testGetByName() {
		String name = "Desert rose";
		Optional<Book> bookOptional = bookRerpository.findByName(name);
		assertTrue(bookOptional.isPresent());
		Book book = bookOptional.get();
		assertNotNull(book);
		assertThat(book.getId()).isEqualTo(1L);
	}

	@Test
	public void testGetAllByAuthor() {
		String name = "Petrov";
		List<Book> books = bookRerpository.findAllByAuthorName(name);
		assertEquals(1, books.size());
	}

	@Test
	public void testGetAllByGenre() {
		String name = "fantasy";
		List<Book> books = bookRerpository.findAllByGenreName(name);
		assertEquals(1, books.size());
	}
}
