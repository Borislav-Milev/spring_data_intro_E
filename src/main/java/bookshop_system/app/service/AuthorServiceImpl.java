package bookshop_system.app.service;

import bookshop_system.app.constants.FilePathConstant;
import bookshop_system.app.entity.ex_1.Author;
import bookshop_system.app.repository.AuthorRepository;
import bookshop_system.app.service.contract.AuthorService;
import bookshop_system.app.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final FileUtil fileUtil;
    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(FileUtil fileUtil, AuthorRepository authorRepository) {
        this.fileUtil = fileUtil;
        this.authorRepository = authorRepository;
    }

    @Override
    public void seedAuthors() {
        if(this.authorRepository.count() != 0){
            System.out.println("Authors are already filled.");
            return;
        }

        this.fileUtil.readFileContent(FilePathConstant.AUTHORS_FILE_PATH)
                .forEach(element -> {
                    String[] fullName = element.split("\\s+");
                    String firstName = fullName[0];
                    String lastName = fullName[1];
                    Author author = new Author(firstName, lastName);
                    this.authorRepository.saveAndFlush(author);
                });
    }

    public int getAllAuthorsCount(){
        return Math.toIntExact(this.authorRepository.count());
    }

    @Override
    public Author findAuthorById(Long id) {
        return this.authorRepository.findById(id).orElse(null);
    }

    @Override
    public List<Author> getAllAuthorsWithBooksAndReleaseDateAfter() {
        return this.authorRepository.authorWithBooksAndReleaseDateAfter();
    }

    @Override
    @Transactional
    public List<Author> findAllAuthorsByCountOfBooks() {
        return this.authorRepository.findAuthorByCountOfBook();
    }
}
