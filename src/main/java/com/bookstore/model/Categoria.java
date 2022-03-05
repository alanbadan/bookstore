package com.bookstore.model;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TB_Categoria")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Categoria implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@Column(nullable = false, length = 150)
	private String nome;
	@Column(nullable = false, length = 250)
	private String descricao;
	
	//uma categoria tem varios livros
	@OneToMany(mappedBy = "categoria", fetch = FetchType.LAZY) // mapeado pelo categoria
	private List<Livro> livros = new ArrayList<>();

	public Categoria(Integer id, String nome, String descricao) { // montando o construtor para injetar no banco de dados na mão 
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
	}
/*	 estudar a melhor opcao  pq vc nao pode criar uma categoria sem nome e nem descricao
	@NotEmpty(message = "campo nome é requerido")
	@Length(min = 3, max = 200, message " o campo nome deve ter entre 3 a 100 caracter")
	private String nome;
	
	@NotEmpty(message = "campo descricao é requerido")
	@Length(min = 3, max = 200, message " o campo Descricao deve ter entre 3 a 200 caracter")
	private String descricao;
	*/

}
