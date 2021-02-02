package bookshop_system.app.entity.ex_2;

import bookshop_system.app.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.util.Set;


@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "albums")
public class Album extends BaseEntity {

    @NonNull
    @Column(nullable = false, length = 100)
    private String name;

    @Column(length = 20)
    private String backgroundColor;

    private Boolean isPublic;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Picture> pictures;
}
