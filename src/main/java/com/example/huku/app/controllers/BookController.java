package com.example.huku.app.controllers;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.huku.app.service.book.BookService;
import com.example.huku.domain.models.book.Book;
import com.example.huku.infrastructure.repository.book.BookRepository;
import com.example.huku.infrastructure.repository.book.BookSearchRepository;

import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
public class BookController {
    
    @Autowired
    private BookService bookService;

    @Autowired
    BookSearchRepository bookSearchRepository;
    
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private final BookRepository bookRepository;
    
    @GetMapping("/book")
    public String index(@AuthenticationPrincipal UserDetails user, @ModelAttribute Book book, Model model ){
        model.addAttribute("complete", bookRepository.findAll());
        model.addAttribute("book", book);
        model.addAttribute("user", user);
        return "book/index";
    }

    @GetMapping("/book/search")
    public String index(Model model, @ModelAttribute("formModel") Book book){
        List<Book> books = bookService.findAll();
        model.addAttribute("books", books);
        return "/book/search";
    }
    
    @PostMapping("/book/search")
    public String selectAll(@RequestParam("text1") String str, Model model) {
        model.addAttribute("bookList", bookSearchRepository.findAll(str, str));
		return "book/search";
	}
    

    @GetMapping("/book/form")
    public String form(@ModelAttribute Book book, Model model){
        return "book/form";
    }

    @GetMapping("/book/detail/{id}")
    public String detail(@PathVariable long id, Model model) {
        Book book = bookService.findById(id);
        model.addAttribute("bookDate", book);
        return "book/detail";
    }

    @PostMapping("/create")
    public String create(@Validated @ModelAttribute Book book, BindingResult result, Model model) {
        if (result.hasErrors()){
            model.addAttribute("complete", bookRepository.findAll()); 
            return "book/form";
        }
        bookRepository.saveAndFlush(book);
        return "redirect:/user/detail";
    }

    @GetMapping("/book/delete/{id}")	
    public String remove(@PathVariable long id){	
        bookRepository.deleteById(id);	
        return "redirect:/user/detail";	
    }

    @GetMapping("/book/edit/{id}")
    public String edit(@PathVariable long id, Model model) {
        model.addAttribute("book", bookRepository.findById(id));
        return "book/edit";
    }
    
    @PostMapping("/update/{id}")
    public String update(@PathVariable long id, @Validated @ModelAttribute Book book, BindingResult result){
        if (result.hasErrors()){
        return "book/edit";
        }
        bookRepository.save(book);
        return "redirect:/user/detail";
    }

    //   初期データの投入
    @PostConstruct
    public void dataInit(){
        Book suzuki = new Book();
        suzuki.setTitle("キングダム");
        suzuki.setGenre("漫画");
        suzuki.setFavorite(4);
        suzuki.setContent("最高の漫画です！");
        suzuki.setPrice(500);
        suzuki.setPublisher("集英社");
        bookRepository.saveAndFlush(suzuki);
    
        Book sato = new Book();
        sato.setTitle("野球の教科書");
        sato.setGenre("スポーツ");
        sato.setFavorite(5);
        sato.setContent("とてもタメになりました！");
        sato.setPrice(1400);
        sato.setPublisher("講談社");
        bookRepository.saveAndFlush(sato);
    }
}