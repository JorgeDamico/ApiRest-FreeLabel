package com.freeLabel.backend.service;

import org.springframework.http.ResponseEntity;

import com.freeLabel.backend.model.Categoria;
import com.freeLabel.backend.response.CategoriaResponseRest;

public interface ICategoriaService {
	
	public ResponseEntity<CategoriaResponseRest> buscarCategorias();
	
	public ResponseEntity<CategoriaResponseRest> consultaCategoriaPorId(Long id);
	
	public ResponseEntity<CategoriaResponseRest> crearCategoria(Categoria categoria);
	
	public ResponseEntity<CategoriaResponseRest> actualizarCategoria(Long id, Categoria categoria);
	
	public ResponseEntity<CategoriaResponseRest> eliminarCategoria(Long id);

}
