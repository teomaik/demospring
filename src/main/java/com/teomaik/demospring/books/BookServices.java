package com.teomaik.demospring.books;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.teomaik.demospring.authors.Author;
import com.teomaik.demospring.authors.AuthorServices;
import com.teomaik.demospring.exceptions.MyException;
import com.teomaik.demospring.themes.Theme;
import com.teomaik.demospring.themes.ThemeServices;

@Service
public class BookServices {

	@Autowired
	AuthorServices authorServices;
	@Autowired
	ThemeServices themeServices;

	//TODO replace with HashMap
	List<Book> books = new ArrayList<Book>();

	//TODO add method getWithID
	
	public List<Book> getAllBooks() {
		return books;
	}

	public List<Book> addBook(Book book) {
		int newId = 1;
		if(books.size()>0) {
			newId = books.get(books.size()-1).getId() +1;
		}
		
		book.setId(newId);
		books.add(book);
		return books;
	}

	public List<Book> removeBook(Integer id) {
		books.removeIf(book -> book.getId() == id);
		return books;
	}

	public Book updateBook(Integer id, String title, Author author, String publiser, Integer publishYear,
			String description, List<Theme> theme) {
		for (Book book : books) {
			if (book.getId() == id) {
				if (title != null)
					book.setTitle(title);
				if (author != null)
					book.setAuthor(author);
				if (publiser != null)
					book.setPubliser(publiser);
				if (publishYear > 0)
					book.setPublishYear(publishYear);
				if (description != null)
					book.setDescription(description);
				if (theme != null)
					book.setTheme(theme);
				return book;
			}
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book with id " + id + " dosnt exist");
	}

    public Book addTheme(Integer id, Integer themeId){
        for(Book book :books) {
            if (book.getId() == id){
                for(Theme theme: themeServices.getAllThemes()){
                    if (theme.getId() == themeId){
                    	book.addTheme(theme);
                    }
                }
            }
            return book;
        }
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book with id " + id + "or Theme with id " + themeId + " dosnt exist");
    }

    public Book changeAuthor(Integer id, Integer authorId){
        for(Book book :books) {
            if (book.getId() == id){
                for(Author author: authorServices.getAllAuthors()){
                    if (author.getId() == authorId){
                    	book.setAuthor(author);
                    	return book;
                    }
                }
            }
        }
        throw new MyException(HttpStatus.NOT_FOUND, "Book with id " + id + "or Author with id " + authorId + " dosnt exist");
//		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book with id " + id + "or Author with id " + authorId + " dosnt exist");
    }
}
