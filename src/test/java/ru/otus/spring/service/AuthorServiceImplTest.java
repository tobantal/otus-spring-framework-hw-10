package ru.otus.spring.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import ru.otus.spring.domain.Author;
import ru.otus.spring.repository.AuthorRepository;

@SpringBootTest
public class AuthorServiceImplTest {

	@MockBean
	AuthorRepository authorRepository;
	
	@Autowired
	AuthorService authorService;
	
	@Test
	void shouldGetByNameIfExists() {
		given(authorRepository.findByName("Ivanov")).willReturn(Optional.of(new Author(3L, "Ivanov")));
		authorService.createIfItIsNecessaryAndGet("Ivanov");
		verify(authorRepository, times(1)).findByName(any());
		verify(authorRepository, times(0)).save(any());
	}

	@Test
	void shouldDeleteById() {
		authorService.deleteById(1L);
		verify(authorRepository, times(1)).deleteById(1L);
	}
	
	@Test
	void shouldFindAll() {
		given(authorRepository.findAll()).willReturn(Collections.singletonList(new Author(3L, "Ivanov")));
		List<Author> authors =  authorService.findAll();
		verify(authorRepository, times(1)).findAll();
		assertThat(authors).hasSize(1);
		assertThat(authors.get(0).getId()).isEqualTo(3L);
		assertThat(authors.get(0).getName()).isEqualTo("Ivanov");
	}

}
