package ru.otus.spring.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import ru.otus.spring.domain.Comment;

@DataJpaTest
class CommentRepositoryImplTest {
	
	@Autowired
	CommentRepository commentRepository;

	@Test
	void shouldFindAllByBookId() {
		List<Comment> comments = commentRepository.findAllByBookId(1L);
		assertThat(comments).hasSize(1);
		assertThat(comments.get(0).getText()).isEqualTo("bad book");
	}

}
