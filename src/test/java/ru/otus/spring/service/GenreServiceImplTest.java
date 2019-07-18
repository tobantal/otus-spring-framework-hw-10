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

import ru.otus.spring.domain.Genre;
import ru.otus.spring.repository.GenreRepository;

@SpringBootTest
public class GenreServiceImplTest {

	@MockBean
	GenreRepository genreRepository;
	
	@Autowired
	GenreService genreService;
	
	@Test
	void shouldGetByNameIfExists() {
		given(genreRepository.findByName("comics")).willReturn(Optional.of(new Genre(3L, "comics")));
		genreService.createIfItIsNecessaryAndGet("comics");
		verify(genreRepository, times(1)).findByName(any());
		verify(genreRepository, times(0)).save(any());
	}

	@Test
	void shouldDeleteById() {
		genreService.deleteById(1L);
		verify(genreRepository, times(1)).deleteById(1L);
	}
	
	@Test
	void shouldFindAll() {
		given(genreRepository.findAll()).willReturn(Collections.singletonList(new Genre(3L, "comics")));
		List<Genre> genres =  genreService.findAll();
		verify(genreRepository, times(1)).findAll();
		assertThat(genres).hasSize(1);
		assertThat(genres.get(0).getId()).isEqualTo(3L);
		assertThat(genres.get(0).getName()).isEqualTo("comics");
	}

}
