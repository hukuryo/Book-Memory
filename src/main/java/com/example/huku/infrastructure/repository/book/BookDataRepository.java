package com.example.huku.infrastructure.repository.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.example.huku.domain.models.book.Book;


@Repository
public interface BookDataRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {
}
