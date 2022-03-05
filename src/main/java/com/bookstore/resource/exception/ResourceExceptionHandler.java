package com.bookstore.resource.exception;

import javax.servlet.ServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.bookstore.exception.DataIntegrityViolationException;
import com.bookstore.exception.ObjectNotFoundException;

@ControllerAdvice  // classe que implemeta a escessao personalisada
public class ResourceExceptionHandler {
	
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandartError> objectNotFoundException(ObjectNotFoundException e, ServletRequest request){
		StandartError error = new StandartError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(), e.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	//execessao do banco de violacao do banco
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<StandartError> dataIntegrityViolationException(DataIntegrityViolationException e, ServletRequest request){
		StandartError error = new StandartError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), e.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	//excessao dos campos nao preenchidos
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandartError> methodArgumentNotValidException(MethodArgumentNotValidException e, ServletRequest request){
		ValidationError error = new ValidationError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), "Erro na Validação dos Campos");
		
		// varrendo o lista (List<FieldMessage>) de erros 
		for(FieldError x : e.getBindingResult().getFieldErrors()) {
			error.allErrors(x.getField(), x.getDefaultMessage());
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	
 
	}
}
