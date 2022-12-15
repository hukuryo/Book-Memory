package com.example.huku.dao;

import java.io.Serializable;
import java.util.List;

import com.example.huku.domain.models.book.Book;

public interface BookDataDao extends Serializable {

    public List<Book> search(String genre, String title);
}
