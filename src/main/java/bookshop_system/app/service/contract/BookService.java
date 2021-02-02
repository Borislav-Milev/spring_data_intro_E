package bookshop_system.app.service.contract;

import bookshop_system.app.entity.ex_1.Author;
import bookshop_system.app.entity.ex_1.Book;

import java.util.List;

public interface BookService {

    void seedBooks();

    Author getRandomAuthor();

    List<Book> getAllBooksAfterYear();

    List<Book> getAllBooksFromAuthor();
}
