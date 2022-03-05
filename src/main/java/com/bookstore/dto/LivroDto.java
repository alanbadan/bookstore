package com.bookstore.dto;

import java.io.Serializable;

import com.bookstore.model.Livro;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LivroDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private Integer id;
	private String titulo;   
	
	
	public LivroDto(Livro obj) {
		super();
		this.id = obj.getId() ;
		this.titulo = obj.getTitulo();
	}
	
	
	
	

}
