package bookshop_system.app.entity.ex_2;

import bookshop_system.app.entity.BaseEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "towns")
public class Town extends BaseEntity {

    @NonNull
    @Column(nullable = false)
    private String name;

    @NonNull
    @ManyToOne(optional = false)
    @ToString.Exclude
    private Country country;
}
