package bookshop_system.app.service;

import bookshop_system.app.constants.FilePathConstant;
import bookshop_system.app.entity.ex_1.Author;
import bookshop_system.app.entity.ex_1.Book;
import bookshop_system.app.entity.ex_1.Category;
import bookshop_system.app.entity.enums.AgeRestriction;
import bookshop_system.app.entity.enums.EditionType;
import bookshop_system.app.repository.BookRepository;
import bookshop_system.app.service.contract.AuthorService;
import bookshop_system.app.service.contract.BookService;
import bookshop_system.app.service.contract.CategoryService;
import bookshop_system.app.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

@Service
public class BookServiceImpl implements BookService {

    private final FileUtil fileUtil;
    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final CategoryService categoryService;

    @Autowired
    public BookServiceImpl(FileUtil fileUtil, BookRepository bookRepository,
                           AuthorService authorService, CategoryService categoryService) {
        this.fileUtil = fileUtil;
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.categoryService = categoryService;
    }

    @Override
    @Transactional
    public void seedBooks() {
        if (this.bookRepository.count() != 0) {
            System.out.println("Books are already filled.");
            return;
        }
        for (String bookString : this.fileUtil.readFileContent(FilePathConstant.BOOKS_FILE_PATH)) {
            Book book = this.setBookValues(bookString.split("\\s+"));

            book.setCategories(this.getRandomCategories());

            this.bookRepository.saveAndFlush(book);
        }
    }

    @Override
    public Author getRandomAuthor() {
        Random random = new Random();

        int randomId = random.nextInt(this.authorService.getAllAuthorsCount()) + 1;

        return this.authorService.findAuthorById((long) randomId);
    }

    @Override
    public List<Book> getAllBooksAfterYear() {
        return this.bookRepository.findAllByReleaseDateAfter();
    }

    @Override
    public List<Book> getAllBooksFromAuthor() {
        return this.bookRepository.getAllBooksFromAuthorOrdered();
    }


    private String getTitle(String[] params){
        StringBuilder builder = new StringBuilder();
        for (int i = 5; i < params.length; i++) {
            builder.append(params[i]).append(" ");
        }
        return builder.toString().trim();
    }

    private Set<Category> getRandomCategories(){
        Set<Category> result = new HashSet<>();
        Random random = new Random();

        int categoryCount = categoryService.countOfCategories();

        for (int i = 0; i < random.nextInt(categoryCount) + 1; i++) {
            result.add(this.categoryService.findCategoryById((long) random.nextInt(categoryCount) + 1));
        }
        return result;
    }

    private Book setBookValues(String[] elements){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        Author author = this.getRandomAuthor();
        AgeRestriction ageRestriction = AgeRestriction.values()[Integer.parseInt(elements[0])];
        LocalDate releaseDate = LocalDate.parse(elements[1], formatter);
        Short copies = Short.valueOf(elements[2]);
        BigDecimal price = new BigDecimal(elements[3]);
        EditionType editionType = EditionType.values()[Integer.parseInt(elements[4])];
        String title = this.getTitle(elements);

        return new Book(title, editionType, price, copies, releaseDate, ageRestriction, author);
    }
}
