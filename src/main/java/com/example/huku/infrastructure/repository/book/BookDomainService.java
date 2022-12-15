package com.example.huku.infrastructure.repository.book;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.huku.domain.models.book.Book;

@Service
public class BookDomainService {

    @Autowired
    private BookRepository bookDataRepository;

    @Autowired
    private BookDataDaoImpl bookDataDaoImpl;


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

    //検索
    public List<Book> search(String genre, String author, String title){

        List<Book> result = new ArrayList<Book>();

        //すべてブランクだった場合は全件検索する
        if ("".equals(genre) && "".equals(author) && "".equals(title)){
            result = bookDataRepository.findAll();
        }
        else {
            //上記以外の場合、bookDaoImplのメソッドを呼び出す
            result = bookDataDaoImpl.search(genre, title);
        }
        return result;
    }
}