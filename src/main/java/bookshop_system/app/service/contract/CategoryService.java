package bookshop_system.app.service.contract;

import bookshop_system.app.entity.ex_1.Category;

public interface CategoryService {

    void seedCategories();

    int countOfCategories();

    Category findCategoryById(Long id);
}
