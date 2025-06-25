package br.com.one.literalura__challenge3__one.view;

import br.com.one.literalura__challenge3__one.DTO.DataAuthor;
import br.com.one.literalura__challenge3__one.model.Author;
import br.com.one.literalura__challenge3__one.model.Book;
import br.com.one.literalura__challenge3__one.reposity.ReposityAuthor;
import br.com.one.literalura__challenge3__one.reposity.ReposityBook;
import br.com.one.literalura__challenge3__one.service.DataConvert;
import br.com.one.literalura__challenge3__one.service.ServiceHttp;
import br.com.one.literalura__challenge3__one.DTO.DataBook;
import br.com.one.literalura__challenge3__one.DTO.DataResults;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Consultation {
    private final String BASE_ADDRESS = "http://gutendex.com/books/?";
    private final Scanner scanner = new Scanner(System.in);
    private final ServiceHttp serviceHttp = new ServiceHttp();
    private final DataConvert dataConvert = new DataConvert();




    private DataBook getDataBook(){
        System.out.println("Digite algum termo:");
        String book = scanner.nextLine();
        serviceHttp.catchData(BASE_ADDRESS + "search=" + book.replace(" ", "+").toLowerCase());

        DataResults data = dataConvert.getData(serviceHttp.getBodyResponse(), DataResults.class);

        Optional<DataBook> dataBookOptional = data.results().stream()
                .filter(b -> b.title().toLowerCase().contains(book.toLowerCase()))
                .findFirst();

        if (dataBookOptional.isPresent()){
            return dataBookOptional.get();
        } else {
            System.out.println("Este Livro Não Existe!");
            return null;
        }
    }
    public void searchBook(ReposityBook reposityBook, ReposityAuthor reposityAuthor) {
        DataBook dataBook = getDataBook();

        if (dataBook != null){
            DataAuthor dataAuthor = dataBook.authors().getFirst();
            Book book;
            Author authorExists = reposityAuthor.findByName(dataAuthor.name());
            if (authorExists != null){
                book = new Book(dataBook, authorExists);
            } else {
                Author newAuthor = new Author(dataAuthor);
                reposityAuthor.save(newAuthor);
                book = new Book(dataBook, newAuthor);
            }

            try {
                reposityBook.save(book);
            } catch (DataIntegrityViolationException e){
                if (e.getCause() instanceof ConstraintViolationException cve){
                    if ("23505".equals(cve.getSQLState())){
                        System.out.println("\nEste livro já está registrado!");
                    } else {
                        throw e;
                    }
                } else {
                    throw e;
                }
            }
            System.out.println(book);
        }
    }

    public void listBooks(ReposityBook reposityBook) {
        System.out.println(
                """
                
                --------------------------
                | Lista de Livros Registrados
                --------------------------
                
                """);
        System.out.println(reposityBook.findAll());
    }

    public void listAuthors(ReposityAuthor reposityAuthor) {
        System.out.println(
                """
                
                --------------------------
                | Lista de Autores Registrados
                --------------------------
                
                """);
        System.out.println(reposityAuthor.findAll());
    }

    public void findAuthorAliveYear(ReposityAuthor reposityAuthor) {
        System.out.println("Digite um determinado ano:");
        int year = scanner.nextInt();
        List<Author> findByAuthorAliveYear = reposityAuthor.searchAuthorAliveYear(year);
        if (!findByAuthorAliveYear.isEmpty()) {
            System.out.print(
                    """
                            
                            --------------------------
                            | Lista de Autores
                            --------------------------
                            
                            """);
            System.out.println(findByAuthorAliveYear);
        } else {
            System.out.println("Não há nenhum autor registrado vivo neste ano!");
        }
    }

    public void findBooksLanguage(ReposityBook reposityBook) {
        System.out.println("Digite um idioma: (ex: fr, pt, en)");
        String language = scanner.next();
        List<Book> findByBooksLanguage = reposityBook.findByLanguageContainingIgnoreCase(language);
        if (!findByBooksLanguage.isEmpty()) {
            System.out.print(
                    """
                            
                            --------------------------
                            | Lista de Livros
                            --------------------------
                            
                            """);
            System.out.println(findByBooksLanguage);
        } else {
            System.out.println("Não há nenhum livro registrado com este idioma!");
        }
    }

    public void top10Books(ReposityBook reposityBook) {
        List<Book> findTop10Books = reposityBook.findTop10ByOrderByDownloadCountDesc();
        if (!findTop10Books.isEmpty()){
            System.out.print(
                    """
                            
                            --------------------------
                            | Lista de Livros
                            --------------------------
                            
                            """);
            System.out.println(findTop10Books);
        } else {
            System.out.println("Não há livros registrados!");
        }
    }

    private DataAuthor getDataAuthor(){
        System.out.println("Digite o nome do autor:");
        String author = scanner.nextLine();
        serviceHttp.catchData(BASE_ADDRESS + "search=" + author.replace(" ", "+").toLowerCase());

        DataResults dataResults = dataConvert.getData(serviceHttp.getBodyResponse(), DataResults.class);

       if (dataResults.count() != 0){
           DataBook dataBook = dataResults.results().getFirst();

           Optional<DataAuthor> dataAuthor = dataBook.authors().stream()
                   .filter(a -> a.name().toLowerCase().contains(author.toLowerCase()))
                   .findFirst();

           if (dataAuthor.isPresent()){
               return dataAuthor.get();
           } else {
               System.out.println("Este Autor não existe!");
               return null;
           }
       } else {
           System.out.println("Este Autor não existe!");
           return null;
       }
    }
    public void searchAuthor(ReposityAuthor reposityAuthor) {
        DataAuthor dataAuthor = getDataAuthor();
        Author author;
        if (dataAuthor != null){
            Author authorExists = reposityAuthor.findByName(dataAuthor.name());
            author = new Author(dataAuthor);
            if (authorExists != null){
                System.out.println("Esse autor já está registrado!");
            } else {
                reposityAuthor.save(author);
            }
            System.out.println(author);
        }
    }
}
