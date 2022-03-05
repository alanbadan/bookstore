package com.bookstore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.exception.ObjectNotFoundException;
import com.bookstore.model.Categoria;
import com.bookstore.model.Livro;
import com.bookstore.repository.LivroRepository;

@Service
public class LivroService {
	
	@Autowired
	LivroRepository livroRepository;
	
	@Autowired
	CategoriaService categoriaService;
	
	public Livro findById(Integer id) {
		Optional<Livro> obj = livroRepository.findById(id); // optional pq ele pode retornar um livro ou vazio
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto nao encontardo id :" + ", Tipo: " + Livro.class.getName()));
				
	}

	public List<Livro> findAll(Integer idCat) {
		categoriaService.findById(idCat); // injetando o categoria para ver se existe o id
		return livroRepository.findAllBycategoria(idCat);
	}

	public Livro update(Integer id, Livro obj) {
		Livro newObj = findById(id);
		upDateData(newObj, obj);
		return livroRepository.save(newObj); // retornado os dadods atualizados
	}
  //private pq somnete o nosso metodo pode ter acesso, foi ciraddo pra nao fiacar tudo no mesmo metodo delegando a outros 
	private void upDateData(Livro newObj, Livro obj) { // fazendo a tranferencia de informacoes netre dados existenes e atualizando os mesmos
		newObj.setTitulo(obj.getTitulo());
		newObj.setNomeAutor(obj.getNomeAutor());
		newObj.setTexto(obj.getTexto());
		
	}

	public Livro create(Integer idCat, Livro obj) {
		obj.setId(null);
		Categoria cat = categoriaService.findById(idCat); // encontarndo a Ctaegoria
		obj.setCategoria(cat); //fazendo o Objeto Livro cohecer sua categoria
		return livroRepository.save(obj);
	}

	public void delete(Integer id) {
		Livro obj = findById(id);// verificando o id existente
		livroRepository.delete(obj);
		
	}

	
		
	
	

}
