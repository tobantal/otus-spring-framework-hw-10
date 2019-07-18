package ru.otus.spring.repository;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import ru.otus.spring.domain.Genre;
import ru.otus.spring.repository.GenreRepository;

@DataJpaTest
public class GenreRepositoryImplTest {

	@Autowired
	GenreRepository genreRepository;
	
	@Test
	public void getByName() {
		String name = "horrors";
		Optional<Genre> genreOptional = genreRepository.findByName(name);
		assertThat(genreOptional).isNotEmpty();
		Genre genre = genreOptional.get();
		assertNotNull(genre);
		assertEquals("horrors", genre.getName());
	}
}