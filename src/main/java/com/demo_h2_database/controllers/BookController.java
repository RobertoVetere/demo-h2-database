package com.demo_h2_database.controllers;
import com.demo_h2_database.api_response.ApiResponse;
import com.demo_h2_database.entities.Book;
import com.demo_h2_database.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api")
public class BookController {

    @Autowired
    BookService bookService;

    @GetMapping(value = "/bootstrap")
    public String bootstrap(){
        return """
                <!doctype html>
                <html lang="en">
                  <head>
                    <meta charset="utf-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1">
                    <title>Bootstrap demo</title>
                    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
                  </head>
                  <body>
                    <h1>Hello, world!</h1>
                    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
                  </body>
                </html>
                """;
    }

    /**
     * http://localhost:8080/api/books
     * @return una lista de libros
     */
    @GetMapping(value = "/books")
    public List<Book> findAll(){
        return bookService.findAll();
    }

    /**
     * Busca un libro por su ID.
     *
     * @param id El ID del libro a buscar.
     * @return ResponseEntity con el libro encontrado o un 404 si no se encuentra.
     *
     * Este método maneja una solicitud GET en la ruta "/book/{id}".
     * Utiliza el repositorio de libros para buscar un libro por su ID.
     * Si se encuentra el libro, se devuelve con un estado HTTP 200.
     * Si no se encuentra, se devuelve un estado HTTP 404.
     */
    @GetMapping(value = "/book/{id}")
    public ResponseEntity<Book> findById(@PathVariable Long id){
        return bookService.findById(id);
    }

    /**
     *
     * @param book
     * @return el libro creado
     */
    @PostMapping("/create-book")
    public ResponseEntity<ApiResponse> create(@RequestBody Book book){
        return bookService.save(book);
    }
}

