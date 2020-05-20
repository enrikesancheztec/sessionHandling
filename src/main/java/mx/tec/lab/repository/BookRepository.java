package mx.tec.lab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.tec.lab.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

}