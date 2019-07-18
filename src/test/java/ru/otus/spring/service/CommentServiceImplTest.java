package ru.otus.spring.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Comment;
import ru.otus.spring.repository.CommentRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@SpringBootTest
class CommentServiceImplTest {
	
	@Mock
	Comment comment;
	
	@Mock
	Book book;
	
	@MockBean
	private CommentRepository commentRepository;
	
	@Autowired
	private CommentService commentService;

	@Test
	void shouldAddComment() {
		commentService.addComment(comment);
		verify(commentRepository, times(1)).save(comment);
	}

	@Test
	void shouldGetCommentsForBook() {
		given(book.getId()).willReturn(1L);
		given(commentRepository.findAllByBookId(book.getId())).willReturn(Collections.singletonList(comment));
		List<Comment> comments = commentService.getCommentsForBook(book);
		assertThat(comments).hasSize(1);
		assertThat(comments.get(0)).isEqualTo(comment);
	}
	
	@Test
	void shouldARemoveComment() {
		commentService.removeComment(1L);
		verify(commentRepository, times(1)).deleteById(1L);
	}

}
