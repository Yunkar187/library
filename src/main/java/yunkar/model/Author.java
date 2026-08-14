package yunkar.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "authors")
public class Author {
    @Id
    private Integer authorId;
    private String authorName;
    private String bio;
}
