package com.demo_h2_database.controllers;
import com.demo_h2_database.api_response.ApiResponse;
import com.demo_h2_database.entities.Book;
import com.demo_h2_database.services.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class BookController {

    @Value("${app.message}")
    String message;

    private static final Logger log = LoggerFactory.getLogger(BookController.class);
    @Autowired
    BookService bookService;


    @GetMapping(value = "/bootstrap")
    public String bootstrap(){
        System.out.println(message);
        return """
                <!DOCTYPE html>
                <html>
                <head>
                    <title>Ejemplo AJAX</title>
                    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
                </head>
                <body>
                    <form id="myForm">
                        <input type="text" name="name" placeholder="Nombre">
                        <button type="submit">Enviar</button>
                    </form>
                                
                    <div id="respuesta"></div>
                                
                    <script>
                        $("#myForm").submit(function(event) {
                            event.preventDefault(); // Evita envío del formulario tradicional
                                
                            $.ajax({
                                url: '/api/submit-form', // Endpoint de Spring Boot
                                type: 'POST',
                                data: $(this).serialize(), // Datos del formulario
                                success: function(response) {
                                    $("#respuesta").html(response); // Actualiza el div con la respuesta
                                },
                                error: function(error) {
                                    console.error('Error:', error);
                                }
                            });
                        });
                    </script>
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

    @PostMapping("/submit-form")
    public String submitForm(@RequestParam("name") String name) {
        log.info("Submit form");
        return "¡Hola " + name + "! Tu formulario fue enviado con éxito.";
    }
}

