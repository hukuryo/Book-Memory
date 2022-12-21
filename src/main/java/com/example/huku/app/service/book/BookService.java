package com.example.huku.app.service.book;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.example.huku.domain.models.book.Book;

import com.example.huku.infrastructure.repository.book.BookDataRepository;
import com.example.huku.infrastructure.repository.book.BookRepository;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookDataRepository bookDataRepository;

    
    public List<Book> findAll(){
        return bookDataRepository.findAll();
    }

    public Book findById(long id){
        return bookRepository.findById(id).get();
    }

    //保存
    public Book save(Book book, @AuthenticationPrincipal UserDetails loginUser) {
        return bookRepository.saveAndFlush(book);
    }    

}
