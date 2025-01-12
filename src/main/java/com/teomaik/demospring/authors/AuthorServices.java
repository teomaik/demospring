package com.teomaik.demospring.authors;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthorServices {

	//TODO replace with HashMap
	List<Author> authors = new ArrayList<Author>();

	//TODO add method getWithID
	
	public List<Author> getAllAuthors() {
		return authors;
	}

	public List<Author> addAuthor(Author author) {
		int newId = 1;
		if(authors.size()>0) {
			newId = authors.get(authors.size()-1).getId() +1;
		}
		
		author.setId(newId);
		authors.add(author);
		return authors;
	}

	public List<Author> removeAuthor(Integer id) {
		authors.removeIf(author -> author.getId() == id);
		return authors;
	}

	public Author updateAuthor(Integer id, String firstName, String lastName, String dateOfBirth) {

		for (Author author : authors) {
			if (author.getId() == id) {
				if (firstName != null)
					author.setFisrtName(firstName);
				if (lastName != null)
					author.setLastName(lastName);
				if (dateOfBirth != null)
					author.setDateOfBirth(dateOfBirth);

				return author;
			}
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Author with id " + id + " doesnt exist");
	}
}
