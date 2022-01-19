package com.freeLabel.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.freeLabel.backend.model.Producto;
import com.freeLabel.backend.model.dao.IProductoDao;
import com.freeLabel.backend.response.ProductoResponseRest;

@Service
public class ProductoServiceImpl implements IProductoService{
	
	private static final Logger log = LoggerFactory.getLogger(ProductoServiceImpl.class);
	
	@Autowired
	private IProductoDao productoDao;

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<ProductoResponseRest> buscarProductos() {
		
		log.info("Inicio método mostrar todos los productos");
		
		ProductoResponseRest response = new ProductoResponseRest();
		
		try {
			
			List<Producto> list = (List<Producto>) productoDao.findAll();
			
			response.getProductoResponse().setProducto(list);
			
			response.setMetadata("Respuesta Ok", "0", "Respuesta exitosa");
			
		} catch (Exception e) {
			response.setMetadata("Respuesta Nok", "-1", "Error al consultar productos");
			log.error("Error al consultar productos", e.getMessage());
			e.getStackTrace();
			return new ResponseEntity<ProductoResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<ProductoResponseRest>(response, HttpStatus.OK);
	
	}

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<ProductoResponseRest> buscarProductoPorId(Long id) {
		
		log.info("Inicio método buscarProductoPorId");
		
		ProductoResponseRest response = new ProductoResponseRest();
		
		List<Producto> list = new ArrayList<>();
		
		try {
			
			Optional<Producto> producto = productoDao.findById(id);
			
			if (producto.isPresent()) {
				list.add(producto.get());
				response.getProductoResponse().setProducto(list);
				response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
			} else {
				log.error("Error al consultar producto");
				response.setMetadata("Respuesta Nok", "-1", "Producto no encontrado");
				return new ResponseEntity<ProductoResponseRest>(response, HttpStatus.NOT_FOUND);
			}
			
		} catch (Exception e) {
			log.error("Error al consultar producto");
			e.getStackTrace(); 
			response.setMetadata("Respuesta Nok", "-1", "Error al consultar producto");
			return new ResponseEntity<ProductoResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<ProductoResponseRest>(response, HttpStatus.OK);
		
	}

	@Override
	@Transactional
	public ResponseEntity<ProductoResponseRest> crearProducto(Producto producto) {
		
		log.info("Inicio método crear producto");
		
		ProductoResponseRest response = new ProductoResponseRest();
		
		List<Producto> list = new ArrayList<>();
		
		try {
			
			Producto productoNuevo = productoDao.save(producto);
			
			if (productoNuevo != null) {
				list.add(productoNuevo);
				response.getProductoResponse().setProducto(list);
				response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
			} else {
				log.error("Error al crear producto");
        		response.setMetadata("Respuesta nok", "-1", "Error al grabar producto");
        		return new ResponseEntity<ProductoResponseRest>(response, HttpStatus.BAD_REQUEST);
			}
			
		} catch (Exception e) {
			log.error("Error en grabar producto");
			response.setMetadata("Respuesta nok", "-1", "Error al grabar producto");
			return new ResponseEntity<ProductoResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<ProductoResponseRest>(response, HttpStatus.OK);
		
	}

	@Override
	@Transactional
	public ResponseEntity<ProductoResponseRest> actualizarProducto(Long id, Producto producto) {
		
        log.info("Inicio método actualizar producto");
		
		ProductoResponseRest response = new ProductoResponseRest();
		
		List<Producto> list = new ArrayList<>();
		
		try {
			
			Optional<Producto> productoBuscado = productoDao.findById(id);
			
			if (productoBuscado.isPresent()) {
				
				productoBuscado.get().setNombre(producto.getNombre());
				productoBuscado.get().setDescripcion(producto.getDescripcion());
				productoBuscado.get().setPrecio(producto.getPrecio());
				productoBuscado.get().setStock(producto.getStock());
				productoBuscado.get().setImagen(producto.getImagen());
				
				Producto productoActualizar = productoDao.save(productoBuscado.get());
				
				if(productoActualizar != null) {
					response.setMetadata("Respuesta ok", "00", "producto actualizado");
				    list.add(productoActualizar);
					response.getProductoResponse().setProducto(list);
				} else {
					log.error("Error al actualizar producto");
					response.setMetadata("Respuesta nok", "-1", "Producto no actualizado");
					return new ResponseEntity<ProductoResponseRest>(response, HttpStatus.BAD_REQUEST);
				}
				
			} else {
				log.error("Error en actualizar producto");
				response.setMetadata("Respuesta nok", "-1", "Producto no existe");
				return new ResponseEntity<ProductoResponseRest>(response, HttpStatus.NOT_FOUND);
			}
			
		} catch (Exception e) {
			log.error("Error al actualizar producto", e.getMessage());
			e.getStackTrace();
			response.setMetadata("Respuesta nok", "-1", "Producto no actualizado");
			return new ResponseEntity<ProductoResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<ProductoResponseRest>(response, HttpStatus.OK);
		
	}

	@Override
	@Transactional
	public ResponseEntity<ProductoResponseRest> eliminarProducto(Long id) {
		
        log.info("Inicio método eliminar producto");
		
		ProductoResponseRest response = new ProductoResponseRest();
		
		 try {
			 
			 Optional<Producto> productoBuscado = productoDao.findById(id);
			 
			 if(productoBuscado.isPresent()) {
				 //eliminamos el registro
				 productoDao.deleteById(id);
				 response.setMetadata("Respuesta ok", "00", "Producto eliminado");
			 } else {
				 log.error("El producto no existe");
				 response.setMetadata("Respuesta nok", "-1", "Producto no existe");
				 return new ResponseEntity<ProductoResponseRest>(response, HttpStatus.NOT_FOUND);
			 }
			 
		 } catch (Exception e) {
			log.error("Error en eliminar el producto", e.getMessage());
			e.getStackTrace();
			response.setMetadata("Respuesta nok", "-1", "Producto no eliminado");
			return new ResponseEntity<ProductoResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR); 
		 }
		 
		 return new ResponseEntity<ProductoResponseRest>(response, HttpStatus.OK);
		
	}
	
}
