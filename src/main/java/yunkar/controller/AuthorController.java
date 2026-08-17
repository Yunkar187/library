package yunkar.controller;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import yunkar.model.Author;
import yunkar.service.AuthorService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/author")
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/get")
    public List<Author> findAll(@RequestHeader Map<String, String> headers) {
        headers.forEach((key, value) -> System.out.println(key + " : " + value));

        return authorService.findAll();
    }

    @PostMapping("/create")
    public String create(Author author) {
        authorService.create(author);
        return "";
    }

}
