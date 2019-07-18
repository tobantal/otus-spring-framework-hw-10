package ru.otus.spring.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.otus.spring.domain.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {

	Optional<Genre> findByName(String name);
	
}
