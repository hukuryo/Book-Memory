package com.example.huku.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.huku.app.service.user.UserRegistrationService;
import com.example.huku.domain.models.book.Book;
import com.example.huku.domain.models.user.User;
import com.example.huku.infrastructure.repository.book.BookRepository;
import com.example.huku.infrastructure.repository.user.UserRepository;


@Controller
public class UserController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRegistrationService  userRegistrationService;
    
    @GetMapping("/user/detail")
    public String details(@AuthenticationPrincipal UserDetails loginUser, @ModelAttribute Book book, Model model){
        model.addAttribute("complete", bookRepository.findAll());
        model.addAttribute("book", book);
        model.addAttribute("user", loginUser);
        return "user/detail";
    }
}
