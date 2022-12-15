package com.example.huku.infrastructure.repository.book;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.huku.dao.BookDataDao;
import com.example.huku.domain.models.book.Book;

@Repository
public class BookDataDaoImpl implements BookDataDao {

    @Autowired
    private EntityManager entityManager;

    public BookDataDaoImpl() {
        super();
    }

    public BookDataDaoImpl(EntityManager manager) {
        this();
        entityManager = manager;
    }

    //Daoクラスで用意したsearchメソッドをオーバーライドする
    @SuppressWarnings("unchecked")
    @Override
    public List<Book> search(String genre, String title) {
        //StringBuilderでSQL文を連結する
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT b From Book b WHERE ");
        boolean genreFlg  = false;
        boolean titleFlg  = false;
        boolean andFlg    = false;
        //genreがブランクではなかった場合、sql変数にappendする
        //フラグをtrueにしとく
        if(!"".equals(genre)) {
            sql.append("b.genre LIKE :genre");
            genreFlg = true;
            andFlg   = true;
        }        
        //titleがブランクではなかった場合、sql変数にappendする
        //フラグをtrueにしとく
        if(!"".equals(title)) {
            if (andFlg) sql.append(" AND ");
            sql.append("b.title LIKE % :title %");
            titleFlg = true;
            andFlg   = true;
        }
        /*
        QueryはSQLでデータを問い合わせるためのクエリ文に相当する機能を持つ
        entityManagerのcreateQueryメソッドを使用する
        sql変数を引数に渡す
        */
        Query query = entityManager.createQuery(sql.toString());
        //上記のif文でtrueになった場合は、各変数に値をセットする
        //今回、あいまい検索したいのでlike句を使用する
        if (genreFlg) query.setParameter("genre", "%" + genre + "%");
        if (titleFlg) query.setParameter("title", "%" + title + "%");
        return query.getResultList();
    }
}
