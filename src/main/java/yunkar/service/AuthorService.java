package yunkar.service;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import yunkar.model.Author;
import yunkar.repository.AuthorRepo;

import java.util.List;

@Service
public class AuthorService {
    private final AuthorRepo authorRepo;
    public AuthorService(AuthorRepo authorRepo) {
        this.authorRepo = authorRepo;
    }
    public List<Author> findAll() {
        return authorRepo.findAll();
    }
    @PostConstruct
    private void test() {

        System.out.println("Init controller");

    }
}
