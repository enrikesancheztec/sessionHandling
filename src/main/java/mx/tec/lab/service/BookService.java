package mx.tec.lab.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.tec.lab.entity.Book;
import mx.tec.lab.repository.BookRepository;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;
    
    public List<Book> listAll() {
    	return bookRepository.findAll(); 
    }
    
    public Book add(Book newBook) {
    	return bookRepository.save(newBook); 
    }    
}
