package com.freeLabel.backend.model.dao;

import org.springframework.data.repository.CrudRepository;

import com.freeLabel.backend.model.Producto;

public interface IProductoDao extends CrudRepository<Producto, Long>{

}
