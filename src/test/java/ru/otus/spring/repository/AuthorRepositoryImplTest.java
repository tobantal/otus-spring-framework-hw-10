package ru.otus.spring.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import ru.otus.spring.domain.Author;
import ru.otus.spring.repository.AuthorRepository;

@DataJpaTest
public class AuthorRepositoryImplTest {
	
	@Autowired
	AuthorRepository authorRepository;
	
	@Test
	public void getByName() {
		String name = "Ivanov";
		Optional<Author> authorOptional = authorRepository.findByName(name);
		assertThat(authorOptional).isNotEmpty();
		Author author = authorOptional.get();
		assertNotNull(author);
		assertEquals("Ivanov", author.getName());
	}
	
}
