package ru.otus.gromov.web.genre;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.gromov.domain.Genre;
import ru.otus.gromov.service.GenreService;

import java.util.List;

@Controller
public class GenreController {
    private final GenreService genreService;

    @Autowired
    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping("/genres")
    public String listGenre(Model model) {
        List<Genre> genres = genreService.getAll();
        model.addAttribute("genres", genres);
        return "genre/genres";
    }

    @GetMapping("/genre/{id}")
    public String getByid(@PathVariable Long id, Model model) {
        Genre genre = genreService.getById(id);
        model.addAttribute("genre", genre);
        return "genre/genre";
    }

    @GetMapping("/genre/edit/{id}")
    public String previewAuthor(@PathVariable Long id, Model model) {
        if (id == 0) {
            model.addAttribute("genre", new Genre());
        } else {
            model.addAttribute("genre", genreService.getById(id));
        }
        return "genre/edit_genre";
    }

    @PostMapping("/genre/edit/{id}")
    public String saveAuthor(@RequestParam("name") String name,
                             @PathVariable Long id,
                             Model model) {
        Genre genre = new Genre();
        if (id != 0) genre = genreService.getById(id);

        genre.setName(name);
        genreService.save(genre);
        model.addAttribute("genre", genre);
        return "genre/genre";
    }
}
