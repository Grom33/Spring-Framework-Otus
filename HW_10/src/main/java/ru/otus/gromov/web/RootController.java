package ru.otus.gromov.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class RootController {
    @GetMapping("/")
    public String root() {
        return "index";
    }

    @GetMapping("/authors")
    public String listAuthors() {
        return "author/authors";
    }

    @GetMapping("/authors/{id}")
    public String viewAuthor(@PathVariable Long id, Model model) {
        model.addAttribute("id", id);
        return "author/author";
    }

    @GetMapping("/books")
    public String listBook() {
        return "book/books";
    }

    @GetMapping("/books/{id}")
    public String viewBook(@PathVariable Long id, Model model) {
        model.addAttribute("id", id);
        return "book/book";
    }

    @GetMapping("/genres")
    public String listGenre() {
        return "genre/genres";
    }

    @GetMapping("/genres/{id}")
    public String viewGenre(@PathVariable Long id, Model model) {
        model.addAttribute("id", id);
        return "genre/genre";
    }
}
