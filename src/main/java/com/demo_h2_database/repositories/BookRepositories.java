package com.demo_h2_database.repositories;

import com.demo_h2_database.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepositories extends JpaRepository<Book, Long> {
}
