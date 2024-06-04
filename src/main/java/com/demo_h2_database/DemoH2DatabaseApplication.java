package com.demo_h2_database;

import com.demo_h2_database.entities.Book;
import com.demo_h2_database.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoH2DatabaseApplication implements CommandLineRunner {

	@Autowired
	BookRepository bookRepository;
	public static void main(String[] args) {
		SpringApplication.run(DemoH2DatabaseApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Book book = new Book("El Guardián", "Lorenzo Lamas");
		bookRepository.save(book);

		try{
			System.out.println(bookRepository.findByTitle("El Guardián").get());
		}catch (Exception e){
			System.out.println("El libro no existe: " + e.getMessage());
		}

	}
}
