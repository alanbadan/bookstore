package com.bookstore.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.model.Categoria;
import com.bookstore.model.Livro;
import com.bookstore.repository.CategoriaRepository;
import com.bookstore.repository.LivroRepository;

@Service
public class DbService {
	
	@Autowired
	CategoriaRepository categoriaRepository;
	
	@Autowired
	LivroRepository livroRepository;
	
	public void instanciandoBancoDados() {
		
		Categoria cat1 = new Categoria(null, "Informatica", "Livros de Informatica");
		Categoria cat2 = new Categoria(null, "Ficcao", "Livro Ficcao");
		Categoria cat3 = new Categoria(null, "Romance", "Livros de Romance");
		
		Livro l1 = new Livro(null, "Clean Code", "satanas", "livro de pacto com diabo", cat1);
		Livro l2 = new Livro(null, "Engenharia do matrix", "Irmas butina", "Qaul pirula tomar?", cat1);
		Livro l3 = new Livro(null, "Como usar strapOn", "Sheila Melo", "Tecnicas para calar aboca no sexo", cat3);
		Livro l4 = new Livro(null, "Um mundo perdido", "Juliano Alvez", "Perdido nas sombras", cat2);
		Livro l5 = new Livro(null, "Shibari", "Mien-Chi", "Como amarrar vitimas de sequestro", cat3);
		
		
		
		cat1.getLivros().addAll(Arrays.asList(l1,l2));
		cat2.getLivros().addAll(Arrays.asList(l4));
		cat3.getLivros().addAll(Arrays.asList(l3,l5));
		
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2,cat3));
		livroRepository.saveAll(Arrays.asList(l1,l2,l3,l4,l5));
	}

}
