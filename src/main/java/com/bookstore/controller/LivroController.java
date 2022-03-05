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
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.bookstore.dto.LivroDto;
import com.bookstore.model.Livro;
import com.bookstore.service.LivroService;

@CrossOrigin("*") // mostrando que nosso end point pode recber requisicoes de oytras fontes ( por exmpplo fornt end)
@RestController
@RequestMapping("/livros")
public class LivroController {
	
	@Autowired
	LivroService livroService;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Livro> findById(@PathVariable Integer id){
		Livro obj = livroService.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	@GetMapping             // localhost:8080/livros?categoria= (o id da categoria 1,2,3, no postams)
	public ResponseEntity<List<LivroDto>> finAll(@RequestParam(value = "categoria", defaultValue = "0") Integer idCat){
		List<Livro> list = livroService.findAll(idCat);
		List<LivroDto> listDto = list.stream().map(obj -> new LivroDto(obj)).collect(Collectors.toList());
	    return ResponseEntity.ok().body(listDto);
		
	}
	@PutMapping(value = "/{id}")                                  // anotacao para validaro preenchiomento dos campo necessarioa
	public ResponseEntity<Livro> upDate(@PathVariable Integer id, @Valid @RequestBody Livro obj){
		Livro newObj = livroService.update(id, obj); // (paramentro antigo id, novo parametro obj)
		return ResponseEntity.ok().body(newObj);	
	}
	@PatchMapping(value = "/{id}") // atualza somente campos especificso o put são todos
	public ResponseEntity<Livro> upDatePatch(@PathVariable Integer id, @Valid @RequestBody Livro obj){
		Livro newObj = livroService.update(id, obj); // (paramentro antigo id, novo parametro obj)
		return ResponseEntity.ok().body(newObj);
	}
	@PostMapping
	public ResponseEntity<Livro> create( @RequestParam(value = "categoria", defaultValue = "0") Integer idCat, @Valid @RequestBody Livro obj){
		Livro newObj = livroService.create(idCat, obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/livros/{id}").buildAndExpand(newObj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		livroService.delete(id);
		return ResponseEntity.noContent().build();
	}	
}
