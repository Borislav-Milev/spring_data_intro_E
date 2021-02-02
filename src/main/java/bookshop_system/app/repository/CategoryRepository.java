package bookshop_system.app.repository;

import bookshop_system.app.entity.ex_1.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
