package bookshop_system.app.entity.ex_1;

import bookshop_system.app.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "category")
public class Category extends BaseEntity {

    @NonNull
    @Column(nullable = false, length = 50)
    private String name;
}
