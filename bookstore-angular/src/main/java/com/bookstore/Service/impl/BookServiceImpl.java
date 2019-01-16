package com.bookstore.Service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.Service.BookService;
import com.bookstore.domain.Book;
import com.bookstore.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepository bookRepository;

	public List<Book> findAll() {
		List<Book> bookList = (List<Book>) bookRepository.findAll();

		List<Book> activeBookList = new ArrayList<>();

		for (Book book : bookList) {
			if (book.isActive()) {
				activeBookList.add(book);
			}
		}

		return activeBookList;
	}

	public Book findOne(Long id) {
		return bookRepository.findOne(id);
	}

	public List<Book> blurrySearch(String title) {

		List<Book> bookList = bookRepository.findByTitleContaining(title);

		List<Book> activeBookList = new ArrayList<>();

		for (Book book : bookList) {
			if (book.isActive()) {
				activeBookList.add(book);
			}
		}
		return activeBookList;
	}

	public void removeOne(Long id) {
		bookRepository.delete(id);
	}

	public Book save(Book book) {
		return bookRepository.save(book);
	}

}
