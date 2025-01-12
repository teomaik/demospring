package com.teomaik.demospring.books;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.teomaik.demospring.authors.Author;
import com.teomaik.demospring.themes.Theme;

@RestController
@RequestMapping("/books")
public class BookController {

	@Autowired
	BookServices bookServices;

	@GetMapping("/hello")
	public String hello() {
		return "Hello from Book controller";
	}

	@GetMapping("/all")
	public List<Book> getAllBooks() {
		return bookServices.getAllBooks();
	}

	@PostMapping("/add")
	public List<Book> addBook(@RequestBody Book author) {
		return bookServices.addBook(author);
	}

	@DeleteMapping("/delete")
	public List<Book> deleteBook(@RequestParam Integer id) {
		return bookServices.removeBook(id);
	}

	@PutMapping("/update")
	public Book updateBook(@RequestParam Integer id, @RequestParam(required = false) String title,
			@RequestParam(required = false) String publiser, @RequestParam(required = false) Integer publishYear,
			@RequestParam(required = false) String description, @RequestParam(required = false) List<Theme> theme,
			@RequestParam(required = false) Author author) {
		return bookServices.updateBook(id, title, author, publiser, publishYear, description, theme);
	}

	@PutMapping("/addTheme")
	public Book addTheme(@RequestParam Integer id, @RequestParam Integer themeId) {
		return bookServices.addTheme(id, themeId);
	}

	@PutMapping("/updateAuthor")
	public Book updateAuthor(@RequestParam Integer id, @RequestParam Integer authorId) {
		return bookServices.changeAuthor(id, authorId);
	}
}