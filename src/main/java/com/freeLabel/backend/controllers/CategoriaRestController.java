package com.freeLabel.backend.controllers;

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

import com.freeLabel.backend.model.Categoria;
import com.freeLabel.backend.response.CategoriaResponseRest;
import com.freeLabel.backend.service.ICategoriaService;

@CrossOrigin(origins = {"http://localhost:4200/"})
@RestController
@RequestMapping("/v1")
public class CategoriaRestController {
	
	@Autowired
	private ICategoriaService service;
	
	@GetMapping("/categorias")
	public ResponseEntity<CategoriaResponseRest> consultaCategoria() {
		
		ResponseEntity<CategoriaResponseRest> response = service.buscarCategorias();
		return response;
		
	}
	
	@GetMapping("/categorias/{id}")
	public ResponseEntity<CategoriaResponseRest> consultaCategoriaPorId(@PathVariable Long id){
		
		ResponseEntity<CategoriaResponseRest> response = service.consultaCategoriaPorId(id);
		return response;
		
	}
	
	@PostMapping("/categorias")
	public ResponseEntity<CategoriaResponseRest> crearCategoria(@RequestBody Categoria request){
		
		ResponseEntity<CategoriaResponseRest> response = service.crearCategoria(request);
		return response;
		
	}
	
	@PutMapping("/categorias/{id}")
	public ResponseEntity<CategoriaResponseRest> actualizarCategoria(@PathVariable Long id, @RequestBody Categoria request){
		
		ResponseEntity<CategoriaResponseRest> response = service.actualizarCategoria(id, request);
		return response;
		
	}
	
	@DeleteMapping("/categorias/{id}")
	public ResponseEntity<CategoriaResponseRest> actualizarCategoria(@PathVariable Long id){
		
		ResponseEntity<CategoriaResponseRest> response = service.eliminarCategoria(id);
		return response;
		
	}

}
