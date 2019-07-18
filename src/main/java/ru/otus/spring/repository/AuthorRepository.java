package ru.otus.spring.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.otus.spring.domain.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {

	Optional<Author> findByName(String author);
	
}
