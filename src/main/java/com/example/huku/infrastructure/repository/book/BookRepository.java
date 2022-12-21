package com.example.huku.infrastructure.repository.book;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.huku.domain.models.book.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}