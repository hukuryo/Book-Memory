package com.example.huku.infrastructure.repository.book;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.huku.domain.models.book.Book;


@Repository
public class BookSearchRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Book> findAll(String title, String genre) {

        String query = "SELECT * FROM book WHERE title LIKE '%'||?||'%' OR genre LIKE '%'||?||'%'" ;
        List<Map<String, Object>> book = jdbcTemplate.queryForList(query, title, genre);
        List<Book> bookList = new ArrayList<Book>();
        for(Map<String,Object> result:book){
			Book bookAll = new Book();
			bookAll.setId((Long)result.get("id"));
			bookAll.setTitle((String)result.get("title"));
			bookAll.setFavorite((int)result.get("favorite"));
			bookAll.setPrice((int)result.get("price"));
			bookAll.setGenre((String)result.get("genre"));
			bookAll.setPublisher((String)result.get("publisher"));
			bookList.add(bookAll);
		}
        return bookList;
    }
}
