package bookshop_system.app.entity.ex_1;

import bookshop_system.app.entity.BaseEntity;
import bookshop_system.app.entity.enums.AgeRestriction;
import bookshop_system.app.entity.enums.EditionType;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.CascadeType.*;

@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "books")
public class Book extends BaseEntity {

    public Book(@NonNull String title, @NonNull EditionType editionType,
                @NonNull BigDecimal price, @NonNull Short copies, LocalDate releaseDate,
                @NonNull AgeRestriction ageRestriction, @NonNull Author author) {
        this(title, editionType, copies, ageRestriction, author);
        this.price = price;
        this.releaseDate = releaseDate;
    }

    @NonNull
    @Column(nullable = false, length = 50)
    private String title;

    @Column(columnDefinition = "TEXT", length = 1000)
    private String description;

    @NonNull
    @Enumerated(value = EnumType.ORDINAL)
    @Column(name = "edition_type", nullable = false)
    private EditionType editionType;

    @NonNull
    @Column(nullable = false, columnDefinition = "DECIMAL(10,2)")
    private BigDecimal price = new BigDecimal("0");

    @NonNull
    @Column(nullable = false)
    private Short copies;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @NonNull
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "age_restriction", nullable = false)
    private AgeRestriction ageRestriction;

    @ManyToMany(cascade = {PERSIST, MERGE, REFRESH}, fetch = FetchType.EAGER)
    @MapsId("id")
    @ToString.Exclude
    private Set<Category> categories = new HashSet<>();

    @NonNull
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @ToString.Exclude
    private Author author;
}
