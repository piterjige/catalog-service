package com.polarbookshop.catalogservice.demo;

import com.polarbookshop.catalogservice.domain.Book;
import com.polarbookshop.catalogservice.domain.BookRepository;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Profile("testdata")
public class BookDataLoader {

	private final BookRepository bookRepository;

	public BookDataLoader(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	@EventListener(ApplicationReadyEvent.class)
	public void loadBookTestData() {
		bookRepository.deleteAll(); // 빈 데이터로 시작하기 위해 기존 책이 있다면 모두 삭제한다.

		var book1 = Book.of("1234567891", "Northern Lights", "Lyra Silverstar", 9.90);
		var book2 = Book.of("1234567892", "Polar Journey", "Iorek Polarson", 12.90);

		bookRepository.saveAll(List.of(book1, book2)); // 여러 객체를 한 번에 저장.
	}

}
