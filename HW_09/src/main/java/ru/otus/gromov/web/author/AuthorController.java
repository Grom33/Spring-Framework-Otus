package ru.otus.gromov.web.author;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.otus.gromov.domain.Author;
import ru.otus.gromov.service.AuthorService;

import java.util.List;

@Controller
public class AuthorController {
    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/authors")
    public String listAuthors(Model model) {
        List<Author> authors = authorService.getAll();
        model.addAttribute("authors", authors);
        return "author/authors";
    }

    @GetMapping("/author/{id}")
    public String getByid(@PathVariable Long id, Model model) {
        Author author = authorService.getById(id);
        model.addAttribute("author", author);
        return "author/author";
    }

    @GetMapping("/author/edit/{id}")
    public String previewAuthor(@PathVariable Long id, Model model) {
        if (id == 0) {
            model.addAttribute("author", new Author());
        } else {
            model.addAttribute("author", authorService.getById(id));
        }

        return "author/edit_author";
    }

    @PostMapping("/author/edit/{id}")
    public String saveAuthor(@RequestParam("name") String name,
                             @PathVariable Long id,
                             Model model) {
        Author author = authorService.getById(id);
        author.setName(name);
        model.addAttribute("author", authorService.save(author));
        return "author/author";
    }
}
