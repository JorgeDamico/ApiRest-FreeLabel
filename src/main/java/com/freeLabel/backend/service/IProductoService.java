package com.freeLabel.backend.service;

import org.springframework.http.ResponseEntity;

import com.freeLabel.backend.model.Producto;
import com.freeLabel.backend.response.ProductoResponseRest;

public interface IProductoService {
	
	public ResponseEntity<ProductoResponseRest> buscarProductos();
	
	public ResponseEntity<ProductoResponseRest> buscarProductoPorId(Long id);
	
	public ResponseEntity<ProductoResponseRest> crearProducto(Producto producto);
	
	public ResponseEntity<ProductoResponseRest> actualizarProducto(Long id, Producto producto);
	
	public ResponseEntity<ProductoResponseRest> eliminarProducto(Long id);

}
