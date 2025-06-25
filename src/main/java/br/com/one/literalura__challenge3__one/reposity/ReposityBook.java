package br.com.one.literalura__challenge3__one.reposity;

import br.com.one.literalura__challenge3__one.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReposityBook extends JpaRepository<Book, Long> {
    List<Book> findByLanguageContainingIgnoreCase(String language);

//    @Query("SELECT b FROM books b ORDER BY MAX(b.downloadCount) DESC LIMIT 10")
    List<Book> findTop10ByOrderByDownloadCountDesc();
}
