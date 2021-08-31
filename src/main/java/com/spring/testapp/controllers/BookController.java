package com.spring.testapp.controllers;

import com.spring.testapp.model.Book;
import com.spring.testapp.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BookController {

    private BookRepository bookRepository;

    @Autowired
    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("/allbooks")
    public String getAllBooks(Model model){
            model.addAttribute("books", bookRepository.findAll());
        return "bookview";
    }

    @GetMapping("/bookByAuthor")
    public String getBooksByAuthor(Model model, @RequestParam("author") String author){
        List<Book> books = new ArrayList<>();
        books = bookRepository.findBooksByAuthor_LastName(author);
        model.addAttribute("books", books);
        return "bookview";
    }

    @GetMapping("/bookByPhrase")
    public String getBooksByPhrase(Model model, @RequestParam("phrase") String phrase){
        List<Book> books = new ArrayList<>();
        books = bookRepository.findBooksByTitleIgnoreCaseContaining(phrase);
        model.addAttribute("books", books);
        return "bookview";
    }
}
