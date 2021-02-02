package bookshop_system.app.service;


import bookshop_system.app.constants.FilePathConstant;
import bookshop_system.app.entity.ex_1.Category;
import bookshop_system.app.repository.CategoryRepository;
import bookshop_system.app.service.contract.CategoryService;
import bookshop_system.app.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final FileUtil fileUtil;
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(FileUtil fileUtil, CategoryRepository categoryRepository) {
        this.fileUtil = fileUtil;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void seedCategories() {
        if(this.categoryRepository.count() != 0){
            System.out.println("Categories are already filled.");
            return;
        }
        this.fileUtil.readFileContent(FilePathConstant.CATEGORIES_FILE_PATH)
                .forEach(element ->{
                    Category category = new Category(element);
                    this.categoryRepository.saveAndFlush(category);
                });
    }

    @Override
    public int countOfCategories(){
        return Math.toIntExact(this.categoryRepository.count());
    }

    @Override
    public Category findCategoryById(Long id) {
        return this.categoryRepository.findById(id).orElse(null);
    }
}
