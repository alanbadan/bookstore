package com.bookstore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.bookstore.dto.CategoriaDto;
import com.bookstore.exception.ObjectNotFoundException;
import com.bookstore.model.Categoria;
import com.bookstore.repository.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	CategoriaRepository categoriaRepository;
	
	
	public Categoria findById(Integer id) {
		Optional<Categoria> obj = categoriaRepository.findById(id);// quando ele nao encontra um objeto ele retorna a msg
		return obj.orElseThrow(() -> new ObjectNotFoundException( //funacao anonima que retorna um mensagem de erro
				"Objeto nao encontrado! id: " +id + ", Tipo: " + Categoria.class.getName())); // tem que criar uma clase que filtar essa excessao ela recebe filtar e devolce para o usuario
	}                                                                                        // cria um handler
    
	public List<Categoria> findAll() {  // retrinando a lista com todas as ctegorias
		return categoriaRepository.findAll();
	}
	public Categoria created(Categoria obj) {
	//	obj.setId(null); //ele atualiza somento os dadso nao o id pq o jpa esta incremetando
		return categoriaRepository.save(obj);
	}
	public Categoria upDate(Integer id, CategoriaDto objDto) {
		//pesquisando se o ia existe , reultilizando o metodo findbyId pq ele ja trata com a excessao perssonalizada
		Categoria obj = findById(id);
		obj.setNome(objDto.getNome()); //setando o nome antigo e trocando pelo novo baseado no categoriaDto
		obj.setDescricao(objDto.getDescricao());  //***** atencao se no corpo da rsposat nao estiver todos os parametros escritos vai retornar null no campo nao descrito
		return categoriaRepository.save(obj);
		
	}

	public void delete(Integer id) {
		findById(id); //verificando se ao id existe
		try {
			categoriaRepository.deleteById(id);
			
		} catch (DataIntegrityViolationException e) {
			throw new com.bookstore.exception.DataIntegrityViolationException("categoria nãp pode ser deletada Possui livros associados");
			
		}
		
		
		
	}
	
}
