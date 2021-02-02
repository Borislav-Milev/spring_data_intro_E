package bookshop_system.app.entity.ex_2;

import bookshop_system.app.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "countries")
public class Country extends BaseEntity {

    @NonNull
    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "country", cascade = CascadeType.REMOVE)
    @MapsId("id")
    private Set<Town> towns = new HashSet<>();
}
