package bookshop_system.app.repository;

import bookshop_system.app.entity.ex_1.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    //"select * from books where year(release_date) > 2000"
    @Query(value = "select b from Book b where b.releaseDate > '2000-12-31'")
    List<Book> findAllByReleaseDateAfter();

    @Query("select b from Book b where concat(b.author.firstName, ' ', b.author.lastName) like 'George Powell' " +
            "order by b.releaseDate desc, b.title asc")
    List<Book> getAllBooksFromAuthorOrdered();
}