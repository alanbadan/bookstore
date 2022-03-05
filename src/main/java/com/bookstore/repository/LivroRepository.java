package com.bookstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bookstore.model.Livro;

public interface LivroRepository extends JpaRepository<Livro, Integer> {
    
	
	@Query("SELECT obj FROM Livro obj WHERE obj.categoria.id = :idCat ORDER BY titulo") // buscando livro pelo id de categoria
	List<Livro> findAllBycategoria(Integer idCat); // boas praticas ?? (@Param9value = "idCat")Integer idCat)

}
