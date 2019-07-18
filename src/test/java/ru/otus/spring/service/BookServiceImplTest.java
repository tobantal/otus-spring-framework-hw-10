package ru.otus.spring.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import ru.otus.spring.domain.Book;
import ru.otus.spring.repository.BookRepository;

@SpringBootTest
class BookServiceImplTest {
	
	@MockBean
	BookRepository bookRepository;
	
	@MockBean
	GenreService genreService;
	
	@MockBean
	AuthorService authorService;
	
	@Autowired
	BookService bookService;
	
	@Mock
	Book book;

	@Test
	void testSize() {
		given(bookRepository.count()).willReturn(5L);
		assertEquals(5L, bookService.size());
	}

	@Test
	void testFindBookByName() {
		given(book.getName()).willReturn("Io");
		given(bookRepository.findByName("Io")).willReturn(Optional.of(book));
		Book foundBook = bookService.findBookByName("Io");
		assertThat(foundBook.getName()).isEqualTo("Io");
	}

	@Test
	void testAddBook() {
		bookService.addBook("ABC", "Petrov", "detective");
		verify(bookRepository, times(1)).save(any());
	}
	
	@Test
	void testDeleteBookById() {
		bookService.deleteBookById(1L);
		verify(bookRepository, times(1)).deleteById(1L);
	}

	@Test
	void testFindAllBooks() {
		given(bookRepository.findAll()).willReturn(Collections.singletonList(book));
		List<Book> books = bookService.findAllBooks();
		assertThat(books).hasSize(1);
		assertThat(books.get(0)).isEqualTo(book);
	}

	@Test
	void testFindBooksByAuthor() {
		given(bookRepository.findAllByAuthorName("Ivanov")).willReturn(Collections.singletonList(book));
		List<Book> books = bookService.findBooksByAuthor("Ivanov");
		assertThat(books).hasSize(1);
		assertThat(books.get(0)).isEqualTo(book);
	}

	@Test
	void testFindBooksByGenre() {
		given(bookRepository.findAllByGenreName("comics")).willReturn(Collections.singletonList(book));
		List<Book> books = bookService.findBooksByGenre("comics");
		assertThat(books).hasSize(1);
		assertThat(books.get(0)).isEqualTo(book);
	}

}
