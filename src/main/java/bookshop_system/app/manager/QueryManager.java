package bookshop_system.app.manager;

import bookshop_system.app.constants.Message;
import bookshop_system.app.service.contract.AuthorService;
import bookshop_system.app.service.contract.BookService;
import bookshop_system.app.service.contract.CategoryService;
import bookshop_system.app.service.contract.UserService;
import bookshop_system.app.util.ReaderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.Reader;

import static bookshop_system.app.constants.Message.*;

@Component
@Transactional(readOnly = true)
public class QueryManager implements Runnable {
    private final AuthorService authorService;
    private final BookService bookService;
    private final ReaderUtil reader;

    @Autowired
    public QueryManager(AuthorService authorService, BookService bookService, ReaderUtil reader) {
        this.authorService = authorService;
        this.bookService = bookService;
        this.reader = reader;
    }

    @Override
    @Transactional
    public void run() {
        while (true) {
            System.out.println(INSTRUCTIONS);
            int input;
            try {
                input = Integer.parseInt(this.reader.read());
            } catch (NumberFormatException e) {
                System.out.println(Message.NOT_A_NUMBER);
                continue;
            }
            if(input == 0) break;

            switch (input) {
                case 1:
                    this.bookService.getAllBooksAfterYear()
                            .forEach(book -> System.out.println(book.getTitle()));
                    break;
                case 2:
                    this.authorService.getAllAuthorsWithBooksAndReleaseDateAfter()
                            .forEach(author -> System.out.printf("%s %s%n", author.getFirstName(), author.getLastName()));
                    break;
                case 3:
                    this.authorService.findAllAuthorsByCountOfBooks()
                            .forEach(author -> System.out.printf("%s %s %d%n",
                                    author.getFirstName(), author.getLastName(), author.getBooks().size()));
                    break;
                case 4:
                    this.bookService.getAllBooksFromAuthor()
                            .forEach(book -> System.out.printf("%s %s %d%n",
                                    book.getTitle(), book.getReleaseDate(), book.getCopies()));

                default:
                    System.out.println(NO_SUCH_NUMBER);
            }
        }
    }
}
