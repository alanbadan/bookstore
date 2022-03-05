package com.bookstore.resource.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandartError {
	
	private List<FieldMessage> errors = new ArrayList<>();

	public ValidationError() {
		super();
		
	}// passando o contrutor da super classe ( extends StandartErro)

	public ValidationError(Long timeStamp, Integer status, String error) {
		super(timeStamp, status, error);
		
	} // retornao de getter normal
	public List<FieldMessage> getErrors() {
		return errors;
		//adicionando o campo e mensagem em im novo objeto
	}// metodo retornando os campos e a mensagem ( parametors do FiledMessage)
    public void allErrors(String fieldName, String message) {
    	this.errors.add(new FieldMessage(fieldName, message));
    }
} 
