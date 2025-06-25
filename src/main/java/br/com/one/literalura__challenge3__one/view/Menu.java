package br.com.one.literalura__challenge3__one.view;

import br.com.one.literalura__challenge3__one.reposity.ReposityAuthor;
import br.com.one.literalura__challenge3__one.reposity.ReposityBook;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    @Autowired
    private final ReposityBook reposityBook;
    @Autowired
    private final ReposityAuthor reposityAuthor;
    private final Scanner scanner = new Scanner(System.in);
    private final Consultation consultation = new Consultation();

    public Menu(ReposityBook reposityBook, ReposityAuthor reposityAuthor) {
        this.reposityBook = reposityBook;
        this.reposityAuthor = reposityAuthor;

    }

    public void getMenu(){
        String choice = "";
        try {
            while (!choice.equals("0")) {
                System.out.println(
                        """
                                
                                ----------------------------------
                                | LiterAlura - Livros
                                | 1 - Buscar Livro Por Nome
                                | 2 - Buscar Autor Por Nome
                                | 3 - Top 10 Livros Mais Abaixados
                                | 4 - Listar Livros Registrados
                                | 5 - Listar Autores Registrados
                                | 6 - Listar Autores Vivos Em Um Determinado Ano
                                | 7 - Listar Livros Em Um Determinado Idioma 
                                | 0 - Sair
                                __________________________________  
                                
                                """);
                choice = scanner.nextLine();
                switch (choice) {
                    case "1":
                        consultation.searchBook(reposityBook, reposityAuthor);
                        break;
                    case "2":
                        consultation.searchAuthor(reposityAuthor);
                        break;
                    case "3":
                        consultation.top10Books(reposityBook);
                        break;
                    case "4":
                        consultation.listBooks(reposityBook);
                        break;
                    case "5":
                        consultation.listAuthors(reposityAuthor);
                        break;
                    case "6":
                        consultation.findAuthorAliveYear(reposityAuthor);
                        break;
                    case "7":
                        consultation.findBooksLanguage(reposityBook);
                        break;
                    case "0":
                        System.out.println("Saindo....");
                        break;
                    default:
                        System.out.println("Escolha Indisponível!");
                        break;
                }
            }
        } catch (InputMismatchException e){
            System.out.println("Escolha Incompatível!");
        }
    }
}
