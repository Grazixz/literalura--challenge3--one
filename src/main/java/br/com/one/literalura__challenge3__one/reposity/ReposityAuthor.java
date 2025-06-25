package br.com.one.literalura__challenge3__one.reposity;

import br.com.one.literalura__challenge3__one.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReposityAuthor extends JpaRepository<Author, Long> {
    Author findByName(String name);

    @Query("SELECT a FROM authors a WHERE a.deathYear > :year AND a.birthYear <= :year")
    List<Author> searchAuthorAliveYear(int year);
}
