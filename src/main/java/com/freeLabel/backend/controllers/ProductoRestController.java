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

import com.freeLabel.backend.model.Producto;
import com.freeLabel.backend.response.ProductoResponseRest;
import com.freeLabel.backend.service.IProductoService;

@CrossOrigin(origins = {"http://localhost:4200/"})
@RestController
@RequestMapping("/v1")
public class ProductoRestController {
	
	@Autowired
	private IProductoService service;
	
	@GetMapping("/productos")
	public ResponseEntity<ProductoResponseRest> consultaProductos(){
		
		ResponseEntity<ProductoResponseRest> response = service.buscarProductos();
		return response;
		
	}
	
	@GetMapping("/productos/{id}")
	public ResponseEntity<ProductoResponseRest> consultaProductoPorId(@PathVariable Long id){
		
		ResponseEntity<ProductoResponseRest> response = service.buscarProductoPorId(id);
		return response;
		
	}
	
	@PostMapping("/productos")
	public ResponseEntity<ProductoResponseRest> crearProducto(@RequestBody Producto request){
		
		ResponseEntity<ProductoResponseRest> response = service.crearProducto(request);
		return response;
		
	}
	
	@PutMapping("/productos/{id}")
    public ResponseEntity<ProductoResponseRest> actualizarProducto(@PathVariable Long id, @RequestBody Producto request){
		
		ResponseEntity<ProductoResponseRest> response = service.actualizarProducto(id, request);
		return response;
		
	}
	
	@DeleteMapping("/productos/{id}")
	public ResponseEntity<ProductoResponseRest> eliminarProducto(@PathVariable Long id){
		
		ResponseEntity<ProductoResponseRest> response = service.eliminarProducto(id);
		return response;
		
	}

}
