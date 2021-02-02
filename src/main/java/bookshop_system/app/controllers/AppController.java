package bookshop_system.app.controllers;

import bookshop_system.app.manager.QueryManager;
import bookshop_system.app.service.contract.AuthorService;
import bookshop_system.app.service.contract.BookService;
import bookshop_system.app.service.contract.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

@Controller
public class AppController implements CommandLineRunner {

    private final BookService bookService;
    private final AuthorService authorService;
    private final CategoryService categoryService;
    private final QueryManager queryManager;

    @Autowired
    public AppController(BookService bookService, AuthorService authorService,
                         CategoryService categoryService, QueryManager queryManager) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.categoryService = categoryService;
        this.queryManager = queryManager;
    }

    @Override
    public void run(String... args) {
        this.categoryService.seedCategories();
        this.authorService.seedAuthors();
        this.bookService.seedBooks();
//
        this.queryManager.run();
    }
}
