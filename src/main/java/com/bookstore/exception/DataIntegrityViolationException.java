package com.bookstore.exception;
 //Tratamento de excessao de dados do banco de dados pq vc não pode ec=xcuir uma ctaegoria com livros associados

public class DataIntegrityViolationException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public DataIntegrityViolationException(String message, Throwable cause) {
		super(message, cause);	
	}
	public DataIntegrityViolationException(String message) {
		super(message);
	}
}
