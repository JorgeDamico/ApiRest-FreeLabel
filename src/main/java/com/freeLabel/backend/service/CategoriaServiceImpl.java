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

import com.freeLabel.backend.model.Categoria;
import com.freeLabel.backend.model.dao.ICategoriaDao;
import com.freeLabel.backend.response.CategoriaResponseRest;

@Service
public class CategoriaServiceImpl implements ICategoriaService{
	
	private static final Logger log = LoggerFactory.getLogger(CategoriaServiceImpl.class);
	
	@Autowired
	private ICategoriaDao categoriaDao;

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<CategoriaResponseRest> buscarCategorias() {
		
		log.info("Inicio método buscar categorias");
		
		CategoriaResponseRest response = new CategoriaResponseRest();
		
		try {
			
			List<Categoria> categoria = (List<Categoria>) categoriaDao.findAll();
			
			response.getCategoriaResponse().setCategoria(categoria);
			
			response.setMetadata("Respuesta Ok", "0", "Respuesta exitosa");
			
		} catch(Exception e) {
			response.setMetadata("Respuesta Nok", "-1", "Error al consultar categorias");
			log.error("Error al consultar categoria", e.getMessage());
			e.getStackTrace();
			return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.OK);
		
	}

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<CategoriaResponseRest> consultaCategoriaPorId(Long id) {
		
        log.info("Inicio método buscar categorias por id");
		
		CategoriaResponseRest response = new CategoriaResponseRest();
		
		List<Categoria> list = new ArrayList<>();
		
		try {
			
			Optional<Categoria> categoria = categoriaDao.findById(id);
			
			if(categoria.isPresent()) {
				list.add(categoria.get());
				response.getCategoriaResponse().setCategoria(list);
				response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
			} else {
				log.error("Error al consultar categoría");
				response.setMetadata("Respuesta Nok", "-1", "Categoría no encontrada");
				return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.NOT_FOUND);
			}
			
		} catch (Exception e) {
			log.error("Error al consultar categoría");
			e.getStackTrace(); 
			response.setMetadata("Respuesta Nok", "-1", "Error al consultar categoría");
			return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.OK);
		
	}

	@Override
	@Transactional
	public ResponseEntity<CategoriaResponseRest> crearCategoria(Categoria categoria) {
		
        log.info("Inicio método crearCategoria()");
        
        CategoriaResponseRest response = new CategoriaResponseRest();
        
        List<Categoria> list = new ArrayList<>();
        
        try {
        	
        	Categoria categoriaCreada = categoriaDao.save(categoria);
        	
        	if(categoriaCreada != null) {
        		list.add(categoriaCreada);
        		response.getCategoriaResponse().setCategoria(list);
        		response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
        	} else {
        		log.error("Error al crear categoría");
        		response.setMetadata("Respuesta nok", "-1", "Error al grabar categoria");
        		return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.BAD_REQUEST);
        	}
			
		} catch (Exception e) {
			log.error("Error en grabar categoria");
			response.setMetadata("Respuesta nok", "-1", "Error al grabar categoria");
			return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
        
		return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.OK);
		
	}

	@Override
	@Transactional
	public ResponseEntity<CategoriaResponseRest> actualizarCategoria(Long id, Categoria categoria) {
		
		log.info("Inicio método actualizar categoría");
		
		CategoriaResponseRest response = new CategoriaResponseRest();
		
		List<Categoria> list = new ArrayList<>();
		
		try {
			
			Optional<Categoria> categoriaBuscada = categoriaDao.findById(id);
			
			if (categoriaBuscada.isPresent()) {
				categoriaBuscada.get().setNombre(categoria.getNombre());
				categoriaBuscada.get().setDescripcion(categoria.getDescripcion());
				
				Categoria categoriaActualizar = categoriaDao.save(categoriaBuscada.get());
				
				if(categoriaActualizar != null) {
					response.setMetadata("Respuesta ok", "00", "Categoria actualizada");
				    list.add(categoriaActualizar);
					response.getCategoriaResponse().setCategoria(list);
				} else {
					log.error("error en actualizar categoria");
					response.setMetadata("Respuesta nok", "-1", "Categoria no actualizada");
					return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.BAD_REQUEST);
				}
				
			} else {
				log.error("error en actualizar categoria");
				response.setMetadata("Respuesta nok", "-1", "Categoria no actualizada");
				return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.NOT_FOUND);
			}
			
		} catch (Exception e) {
			log.error("error en actualizar categoria", e.getMessage());
			e.getStackTrace();
			response.setMetadata("Respuesta nok", "-1", "Categoria no actualizada");
			return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.OK);
		
	}

	@Override
	@Transactional
	public ResponseEntity<CategoriaResponseRest> eliminarCategoria(Long id) {
		
        log.info("Inicio metodo eliminar categoria");
		
		CategoriaResponseRest response = new CategoriaResponseRest();
		
		 try {
			 
			 Optional<Categoria> categoriaBuscada = categoriaDao.findById(id);
			 
			 if(categoriaBuscada.isPresent()) {
				 //eliminamos el registro
				 categoriaDao.deleteById(id);
				 response.setMetadata("Respuesta ok", "00", "Categoria eliminada");
			 } else {
				 log.error("La categoría no existe");
				 response.setMetadata("Respuesta nok", "-1", "Categoria no existe");
				 return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.NOT_FOUND);
			 }
			 
		 } catch (Exception e) {
			log.error("error en eliminar categoria", e.getMessage());
			e.getStackTrace();
			response.setMetadata("Respuesta nok", "-1", "Categoria no eliminada");
			return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR); 
		 }
		 
		 return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.OK);
		
	}

}
