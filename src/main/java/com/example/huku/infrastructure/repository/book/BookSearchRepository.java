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

    public List<Book> findAll(String genre) {

        // SELECT文
        String query = "SELECT "
                + " id,"
                + " title,"
                + " price,"
                + " favorite,"
                + " publisher,"
                + " genre,"
                + "FROM book "
                + "WHERE genre=?";

        // 検索実行、mapで取得した値をemployeeクラスのインスタンスにセット
        List<Map<String, Object>> book = jdbcTemplate.queryForList(query, genre);
        List<Book> bookList =new ArrayList<Book>();
        for(Map<String,Object> result:book)
		{
			Book bookAll = new Book();
			// bookAll.setId(Long.parseInt(result.get("id").toString()));//なぜかLong型で取得されているのでLong型→String型→Int型でキャストをかける。
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
