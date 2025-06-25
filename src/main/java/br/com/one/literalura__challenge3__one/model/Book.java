package br.com.one.literalura__challenge3__one.model;

import br.com.one.literalura__challenge3__one.DTO.DataBook;
import jakarta.persistence.*;

@Entity(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String title;
    @ManyToOne
    private Author author;
    @Column(length = 10000)
    private String summaries;
    private String language;
    private int downloadCount;

    public Book() {
    }
    public Book(DataBook dataBook, Author author) {
        this.title = dataBook.title();
        this.summaries = dataBook.summaries().getFirst();
        this.author = author;
        this.language = dataBook.languages().getFirst();
        this.downloadCount = dataBook.downloadCount();
    }
    public void setAuthor(Author author) {
        this.author = author;
    }
    public Long getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public Author getAuthor() {
        return author;
    }
    public String getSummaries() {
        return summaries;
    }
    public String getLanguage() {
        return language;
    }
    public int getDownloadCount() {
        return downloadCount;
    }

    @Override
    public String toString() {
        return "\n | Título: " + title + "\n" +
               " | Informações do Autor: " + author +
               " | Sumário: " + summaries + "\n" +
               " | Idioma: " + language + "\n" +
               " | Número de Downloads: " + downloadCount + "\n" +
                "----------------------------------------------\n";
    }
}
