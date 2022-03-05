package com.bookstore.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.bookstore.dto.CategoriaDto;
import com.bookstore.model.Categoria;
import com.bookstore.service.CategoriaService;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/categorias")
public class CategoriaController {
	
	@Autowired
	CategoriaService categoriaService;
	
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Categoria> findById(@PathVariable Integer id) {  
		Categoria obj = categoriaService.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	@GetMapping  // sem value pq vai retornar uma lista de toods
	public ResponseEntity<List<CategoriaDto>> findAll() {
           List<Categoria> list = categoriaService.findAll();
           //covertendo uma lista de categoria para categoriadto
           List<CategoriaDto> listDto = list.stream().map(obj -> new CategoriaDto(obj)).collect(Collectors.toList());
           return ResponseEntity.ok().body(listDto);
     }   
	@PostMapping                               //@Valid para varieficar se os campos requeridos estao preenchidos
	public ResponseEntity<Categoria> create (@Valid @RequestBody Categoria obj) {
		obj = categoriaService.created(obj);
		//boa pratica retoernar para o ususario uma uri para acesso ao novo obj criado
		                                                                                       //pegando oid do novo objeto
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
	//	return ResponseEntity.created(uri).body(obj); ambos estao corretos 
		return ResponseEntity.created(uri).build();
	}
	@PutMapping(value = "/{id}")
	ResponseEntity<CategoriaDto> upDate(@Valid @PathVariable Integer id, @RequestBody CategoriaDto objDto) {
		Categoria newObj = categoriaService.upDate(id, objDto);
		return ResponseEntity.ok().body(new CategoriaDto(newObj));
	}
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		categoriaService.delete(id);
		return ResponseEntity.noContent().build(); //não retorna nada
	}
	
	
}
