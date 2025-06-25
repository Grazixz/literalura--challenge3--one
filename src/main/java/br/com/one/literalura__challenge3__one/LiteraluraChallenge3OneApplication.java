package br.com.one.literalura__challenge3__one;

import br.com.one.literalura__challenge3__one.view.Menu;
import br.com.one.literalura__challenge3__one.reposity.ReposityAuthor;
import br.com.one.literalura__challenge3__one.reposity.ReposityBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraluraChallenge3OneApplication implements CommandLineRunner {
	@Autowired
	private ReposityBook reposityBook;
	@Autowired
	private ReposityAuthor reposityAuthor;

	public static void main(String[] args) {
		SpringApplication.run(LiteraluraChallenge3OneApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Menu menu = new Menu(reposityBook, reposityAuthor);
		menu.getMenu();
	}
}
