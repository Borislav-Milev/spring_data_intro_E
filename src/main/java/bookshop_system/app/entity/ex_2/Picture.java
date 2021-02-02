package bookshop_system.app.entity.ex_2;

import bookshop_system.app.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.util.Set;


@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "pictures")
public class Picture extends BaseEntity {

    private String title;

    private String caption;

    @NonNull
    @Column(nullable = false)
    private String path;

    @ManyToMany(mappedBy = "pictures", cascade = CascadeType.ALL)
    private Set<Album> albums;
}
