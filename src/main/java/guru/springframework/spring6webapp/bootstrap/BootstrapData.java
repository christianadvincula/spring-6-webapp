package guru.springframework.spring6webapp.bootstrap;

import guru.springframework.spring6webapp.domain.Author;
import guru.springframework.spring6webapp.domain.Book;
import guru.springframework.spring6webapp.domain.Publisher;
import guru.springframework.spring6webapp.repositories.AuthorRepository;
import guru.springframework.spring6webapp.repositories.BookRepository;
import guru.springframework.spring6webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Author firstAuthor = new Author();
        firstAuthor.setFirstName("John");
        firstAuthor.setLastName("Doe");
        Author secondAuthor = new Author();
        secondAuthor.setFirstName("Jane");
        secondAuthor.setLastName("Doe");
        Author savedAuthor = authorRepository.save(firstAuthor);
        Author savedAuthor2 = authorRepository.save(secondAuthor);

        Book firstBook = new Book();
        firstBook.setTitle("First Book");
        firstBook.setIsbn("123456789");
        Book secondBook = new Book();
        secondBook.setTitle("Second Book");
        secondBook.setIsbn("987654321");
        Book savedBook = bookRepository.save(firstBook);
        Book savedBook2 = bookRepository.save(secondBook);

        savedBook.getAuthors().add(savedAuthor);
        savedBook2.getAuthors().add(savedAuthor2);

        savedBook = bookRepository.save(savedBook);
        savedBook2 = bookRepository.save(savedBook2);

        Publisher publisher = new Publisher();
        publisher.setPublisherName("Publisher Name");
        Publisher savedPublisher = publisherRepository.save(publisher);


        System.out.println("In Bootstrap");
        System.out.println("Author count: " + authorRepository.count());
        System.out.println("Book count: " + bookRepository.count());
        System.out.println("Publisher count: " + publisherRepository.count());
    }
}
