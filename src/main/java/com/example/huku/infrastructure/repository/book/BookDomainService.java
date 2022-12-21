package com.example.huku.infrastructure.repository.book;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.huku.domain.models.book.Book;

@Service
public class BookDomainService {

    @Autowired
    private BookRepository bookDataRepository;

    //全件検索
    public List<Book> findAll(){
        return bookDataRepository.findAll();
    }

    //該当のID見つける
    public Optional<Book> findById(long isbn) {
        return bookDataRepository.findById(isbn);
    }

    //保存
    public Book save(Book book) {
        return bookDataRepository.saveAndFlush(book);
    }

}