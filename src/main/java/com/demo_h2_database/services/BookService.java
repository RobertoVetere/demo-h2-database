package com.demo_h2_database.services;

import com.demo_h2_database.api_response.ApiResponse;
import com.demo_h2_database.entities.Book;
import com.demo_h2_database.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;


    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public ResponseEntity<Book> findById(Long id) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        return bookOptional
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity
                        .notFound()
                        .build());
    }

    public ResponseEntity<ApiResponse> save(Book book) {
        ApiResponse response;
        try {
            Optional<Book> bookOptional = bookRepository.findByTitle(book.getTitle());
            if (bookOptional.isPresent()) {
                response = new ApiResponse("Ya existe un libro con este título en la base de datos", null);
                return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
            } else {
                bookRepository.save(book);
                response = new ApiResponse("Libro creado en la base de datos", book);
                return ResponseEntity.ok(response);
            }
        }catch (Exception e){
            response = new ApiResponse("Error al crear el libro: ", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
