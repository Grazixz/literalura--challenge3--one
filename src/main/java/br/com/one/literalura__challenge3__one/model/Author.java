package br.com.one.literalura__challenge3__one.model;

import br.com.one.literalura__challenge3__one.DTO.DataAuthor;
import jakarta.persistence.*;

import java.util.List;
@Entity(name="authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;
    private int birthYear;
    private int deathYear;
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Book> books;

    public Author() {
    }
    public Author(DataAuthor dataAuthor) {
        this.name = dataAuthor.name();
        this.birthYear = dataAuthor.birthYear();
        this.deathYear = dataAuthor.deathYear();
    }


    public void setBooks(List<Book> books) {

        this.books = books;
    }

    public String getName() {
        return name;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public int getDeathYear() {
        return deathYear;
    }

    public List<Book> getBooks() {
        return books;
    }

    @Override
    public String toString() {
        return  "\n | Nome: " + name +  "\n" +
                " | Ano do Nascimento: " + birthYear + "\n" +
                " | Ano da Falecimento: " + deathYear + "\n" +
                "------------------------------\n";
    }
}
