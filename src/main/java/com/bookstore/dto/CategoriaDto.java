package com.bookstore.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.bookstore.model.Categoria;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CategoriaDto implements Serializable{ // v cpode omitir o reotrno para o usuario como por exempo em um alista vc nao precisa retornar todo corpo da rsposta

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	 // ou usrs as anotacoes @Column(nullable = false, length = 150)
	@NotEmpty(message = "campo nome é requerido")
	@Length(min = 3, max = 200, message = " o campo nome deve ter entre 3 a 100 caracter")
	private String nome;
	
	@NotEmpty(message = "campo descricao é requerido")
	@Length(min = 3, max = 200, message = " o campo Descricao deve ter entre 3 a 200 caracter")
	private String descricao;
	

 public CategoriaDto(Categoria obj) {
	 super();
	 this.id = obj.getId();
	 this.nome = obj.getNome();
	 this.descricao = obj.getDescricao();
  }
} 
