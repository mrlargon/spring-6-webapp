package guru.springframework.spring6webapp.bootstrap;

import guru.springframework.spring6webapp.domain.Author;
import guru.springframework.spring6webapp.domain.Book;
import guru.springframework.spring6webapp.domain.Publicsher;
import guru.springframework.spring6webapp.repository.AuthorRepository;
import guru.springframework.spring6webapp.repository.BookRepository;
import guru.springframework.spring6webapp.repository.PublicsherRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {
    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublicsherRepository publicsherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publicsherRepository = publicsherRepository;
    }

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository ;

    private final PublicsherRepository publicsherRepository;
    @Override
    public void run(String... args) throws Exception {
        Author ragnar = new Author();
         ragnar.setFirstName("Ragnar");
         ragnar.setLastName("Edvardsson");

         Book dnd = new Book();
         dnd.setTitle("avlägset förtroligt");
         dnd.setIsbn("42335");
         Author ragnarSaved = authorRepository.save(ragnar);
         Book dndbookSaved = bookRepository.save(dnd);


        Author ulf = new Author();
        ulf.setFirstName("ulf");
        ulf.setLastName("Edvardsson");

        Book beckmans  = new Book();
        beckmans.setTitle("Amands");
        beckmans.setIsbn("42336");

        Publicsher publicsher  = new Publicsher();
        publicsher.setPublisherName("Beckamns LTD");
        publicsher.setAddress("Beckamns street");
        Publicsher publicsherSaved=  publicsherRepository.save(publicsher);



        Author ulfSaved = authorRepository.save(ulf);
        Book  beckmansbookSaved = bookRepository.save(beckmans);


        // books author
        ragnarSaved.getBooks().add(dndbookSaved);
        ulfSaved.getBooks().add(beckmansbookSaved);
        dndbookSaved.getAuthors().add(ragnarSaved);
        beckmansbookSaved.getAuthors().add(ulfSaved);




        dndbookSaved.setPublicsher(publicsherSaved);
        beckmansbookSaved.setPublicsher(publicsherSaved);


        authorRepository.save(ulfSaved);
        authorRepository.save(ragnarSaved);

       bookRepository.save(dndbookSaved);
       bookRepository.save(beckmansbookSaved);

        System.out.println("Books");
        System.out.println("Antal författare " + authorRepository.count());
        System.out.println("Antal böcker " + bookRepository.count());
        System.out.println("Antal publisher " + publicsherRepository.count());


    }
}
