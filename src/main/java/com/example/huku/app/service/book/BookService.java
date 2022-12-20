package com.example.huku.app.service.book;


import java.lang.StackWalker.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.example.huku.domain.models.book.Book;

import com.example.huku.infrastructure.repository.book.BookDataDaoImpl;
import com.example.huku.infrastructure.repository.book.BookDataRepository;
import com.example.huku.infrastructure.repository.book.BookRepository;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookDataRepository bookDataRepository;

    @Autowired
    private BookDataDaoImpl bookDataDaoImpl;

    // public Optional<Book> findBySearchId(long isbn) {
    //     return bookDataRepository.findById(isbn);
    // }
    
    
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

    public List<Book> search(String genre, String title){
        List<Book> result = new ArrayList<Book>();
        //すべてブランクだった場合は全件検索する
        if ( "" .equals(genre) && "" .equals(title)){
            result = bookDataRepository.findAll();
        }else {
            //上記以外の場合、BookDataDaoImplのメソッドを呼び出す
            result = bookDataDaoImpl.search(genre, title);
        }
        return result;
    }
}
