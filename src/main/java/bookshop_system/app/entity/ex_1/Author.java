package bookshop_system.app.entity.ex_1;

import bookshop_system.app.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "authors")
public class Author extends BaseEntity {

    public Author(String firstName, @NonNull String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Column(name = "first_name", length = 20)
    private String firstName;

    @NonNull
    @Column(name = "last_name", nullable = false, length = 20)
    private String lastName;

    @OneToMany(mappedBy = "author")
    private Set<Book> books = new HashSet<>();
}
