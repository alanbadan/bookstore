package com.bookstore.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name= "TB_Livro")
public class Livro implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@Column(nullable = false, length = 150)
	private String titulo;
	@Column(nullable = false, length = 100)
	private String nomeAutor;
	@Column(nullable = false, length = 200)
	private String texto;
	
	
	@JsonIgnore // para ignorar o livro e trzer somente a categoria
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "categoria_id") //ele gera sozinho
	private Categoria categoria; // fazendo a relacao com livro

}
/* verificar melhor opcao 
@NotEmpty(message = "campo titulo é requerido")
@Length(min = 3, max = 200, message = " o campo titulo deve ter entre 3 a 100 caracter")
private String titulo;

@NotEmpty(message = "campo nomeAutor é requerido")
@Length(min = 3, max = 200, message = " o campo nomeAutor deve ter entre 3 a 50 caracter")
private String nomeAutor;

@NotEmpty(message = "campo texto é requerido")
@Length(min = 3, max = 2000000, message = " o campo texto deve ter entre 3 a 200.000 caracter")
private String texto;

*/


