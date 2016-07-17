package com.belatrix.main;

import com.belatrix.entity.Producto;
import com.belatrix.entity.Proveedor;
import com.belatrix.util.Relacion;

public class Main {

	public static void main(String[] args) {
		
		Proveedor proveedorA = new Proveedor (1, "ProveedorA");
		Producto productoA = new Producto(1, "ProductoA");	

		Proveedor proveedorB = new Proveedor (1, "ProveedorB");
		Producto productoB = new Producto(1, "ProductoB");

		Proveedor proveedorC = new Proveedor (1, "ProveedorC");
		Producto productoC = new Producto(1, "ProductoC");
		
		Relacion relacion = new Relacion();
		relacion.addRelaciones(productoA, proveedorA);
		relacion.addRelaciones(productoB, null);
		relacion.addRelaciones(productoB, proveedorA);
		relacion.addRelaciones(productoA, proveedorB);
		relacion.addRelaciones(productoC, proveedorC);
		relacion.addRelaciones(productoC, proveedorB);
		relacion.addRelaciones(productoB, null);
		relacion.addRelaciones(productoB, proveedorA);
		System.out.println("-----");
		relacion.printRelaciones();
		System.out.println("-----");	
		relacion.removerRelacion(productoA, proveedorA);
		relacion.removerRelacion(productoA, proveedorB);

		System.out.println("-----");
		relacion.printRelaciones();
		
		
		
		System.out.println("-----");
		//relacion.removeRelacion(proveedorA);
		System.out.println("-----");
		relacion.printRelaciones();
	
	}

}
