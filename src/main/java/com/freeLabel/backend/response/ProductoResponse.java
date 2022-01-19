package com.freeLabel.backend.response;

import java.util.List;

import com.freeLabel.backend.model.Producto;

public class ProductoResponse {
	
	private List<Producto> producto;

	public List<Producto> getProducto() {
		return producto;
	}

	public void setProducto(List<Producto> producto) {
		this.producto = producto;
	}	

}
