package bookshop_system.app.service.contract;

import bookshop_system.app.entity.ex_1.Author;

import java.util.List;

public interface AuthorService {

    void seedAuthors();

    int getAllAuthorsCount();

    Author findAuthorById(Long id);

    List<Author> getAllAuthorsWithBooksAndReleaseDateAfter();

    List<Author> findAllAuthorsByCountOfBooks();
}
