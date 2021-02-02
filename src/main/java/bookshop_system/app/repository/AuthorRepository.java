package bookshop_system.app.repository;

import bookshop_system.app.entity.ex_1.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long> {

/*    @Query(value =
            "select a.*, count(b.id) book_count from authors a left join books b " +
                    "on b.author_id = a.id " +
                    "where year(b.release_date) > 1990 " +
                    "group by a.id " +
                    "having book_count > 0"
            , nativeQuery = true)*/
    @Query("select distinct a from Author a join a.books b where b.releaseDate > '1992-12-31'")
    List<Author> authorWithBooksAndReleaseDateAfter();

    @Query("select a from Author a order by a.books.size desc")
    List<Author> findAuthorByCountOfBook();

}
