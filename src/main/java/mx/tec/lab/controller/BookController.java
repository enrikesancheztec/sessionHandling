package mx.tec.lab.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.tec.lab.entity.Book;
import mx.tec.lab.service.BookService;

@RestController
@RequestMapping("/api/book-management/*")
@CrossOrigin(origins = "http://localhost:3000")
public class BookController {
	@Autowired
	private BookService bookService;

	@GetMapping("/books")
	public List<Book> getBooks() {
		List<Book> books = bookService.listAll();
		return books;
	}
	
	@PostMapping("/books")
	@PreAuthorize("hasAuthority('ADMIN')") 
	public Book newBook(@RequestBody Book newBook) {
		return bookService.add(newBook);
	}	
}
