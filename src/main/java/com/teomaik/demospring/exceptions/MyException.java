package com.teomaik.demospring.exceptions;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

public class MyException extends ResponseStatusException {

	public MyException(HttpStatusCode status, String reason) {
		super(status, reason);
		System.out.println("EXCEPTION TEST!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		System.out.println(reason);
		// TODO Auto-generated constructor stub
	}

}
