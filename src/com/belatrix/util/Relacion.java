package com.belatrix.util;

import java.util.AbstractSet;
import java.util.HashSet;
import java.util.Iterator;


import com.belatrix.entity.Producto;
import com.belatrix.entity.Proveedor;

public class Relacion {
	
	private final AbstractSet<Producto> listProducts = new HashSet<Producto>();
	private static final String ADD = "agrego";
	private static final String REMOVE = "elimino";
	
	private boolean existeRalacion (Producto producto, Proveedor proveedor){
		/*
		 * Este metodo devuelve true en caso de que exista la relacion entre los
		 * parametros. False, caso contrario.
		 */
		Producto p = producto.copy();
		p.setProveedor(proveedor);
		return (listProducts.contains(p));
	}
	

	private boolean existeProductoConProveedorNull (Producto producto){
		/*
		 * Este metodo devuelve true en caso de que el producto no tenga proveedor
		 * False, caso contrario.
		 */
		Producto p = producto.copy();
		p.setProveedor(null);
		return (listProducts.contains(p));
	}

	private boolean existeRelacaionConProveedor (Proveedor proveedor){
		/*
		 * Este metodo recorre la lista y retorna true si exite alguna
		 * relacion con el proveedor pasado como parametro. False, caso 
		 * contrario.
		 */
		Iterator <Producto> listaIterador = listProducts.iterator();
		while(listaIterador.hasNext()){
			Producto prod = listaIterador.next();
			if (prod.getProveedor() != null){
				if (prod.getProveedor().equals(proveedor)) {
					return true;
	            }
			}else if (proveedor == null) return true;
		}
		return false;
	}
	
	
	public void addRelaciones (Producto producto, Proveedor proveedor){
		/*
		 * - Chequeo que los parametros no sean nulos:
		 * 		- En el caso de que no sean nulos, procedo a agregar la relacion.
		 * 		- En el caso de que sean nulos, muestro un mensaje exponiendo 
		 * 		esta situacion.
		 * 
		 * - Si el producto no tiene asignado un proveedor entonces le seteo el
         * proveedor del parametro y muestro mensaje de que se agrego la relacion.
         * 
         * - Chequeo en la lista, que no exista la relacion pasada por paramentros:
         * 		- Si no existe, la agrego y muestro un mesaje de exito.
         * 		- Si existe la relacion, muestro una mensaje que exponga 
         * 		esta situacion.
         * 
         */
		if ((producto != null)){	
			Producto productCopy = producto.copy();
		    productCopy.setProveedor(proveedor);
		    if(this.listProducts.add(productCopy)){
		    	printOperation(productCopy, ADD);
		    }else System.out.println("Ya existe la relacion");
		}else System.out.println("No se pudo agregar la relacion ya que el produco esta vacion");
	}
	
	public void removerRelacion (Producto producto, Proveedor proveedor){
		/*
		 * - Chequeo que los parametros no sean nulos:
		 * 		- En el caso de que no sean nulos, procedo a eliminar la relacion.
		 * 		- En el caso de que sean nulos, muestro un mensaje exponiendo 
		 * 		esta situacion.
		 * 
		 * - Chequeo que exista la realacion a borrar
		 * 		- Si exista, la borro y muestro un mensaje de exito.
		 * 		- Si no existe, muestro un mensaje que exponga esta situacion.
         * 
         */
		if((producto != null)){
			if(existeRalacion(producto, proveedor)){
				Producto productCopy = producto.copy();
				productCopy.setProveedor(proveedor);
				Iterator <Producto> listaIterador = listProducts.iterator();
				while(listaIterador.hasNext()){
					Producto prod = listaIterador.next();
					if (prod.equals(productCopy)) {
		                if (!existeProductoConProveedorNull(prod)) {
		                		prod.setProveedor(null);         	
		                }else listaIterador.remove();
		            	printOperation(productCopy, REMOVE);
					}
				}
			}else System.out.println("No existe la relacion.");
		}else System.out.println("ERROR: Producto Nulo");
	}
	
	
	public void removerRelacion (Proveedor proveedor){
		/*
		 * - Chequeo que el parametro no sea nulo:
		 * 		- En el caso de que no sea nulo, procedo a eliminar las relaciones.
		 * 		- En el caso de que sea nulo, muestro un mensaje exponiendo 
		 * 		esta situacion.
		 * 
		 * - Chequeo que exista la realacion a borrar
		 * 		- Si exista, recorro la lista borrando las relaciones que cumplen
		 * 		con la condicion de tener como proveedor igual al parametro. Al final 
		 * 		muestro un mensaje de exito.
		 * 		- Si no existe, muestro un mensaje que exponga esta situacion.
         * 
         */
		if(existeRelacaionConProveedor(proveedor)){
			Iterator<Producto> listaIterador = listProducts.iterator();
			while (listaIterador.hasNext()){
				Producto prod = listaIterador.next();
				if(prod.getProveedor() == null){
					if ((proveedor == null)){
						printOperation(prod, REMOVE);
						listaIterador.remove();
						
					};
				}else if(proveedor != null){
						if (prod.getProveedor().equals(proveedor)) {
							printOperation(prod, REMOVE);
			                if (!existeProductoConProveedorNull(prod)) {
			                		prod.setProveedor(null);         	
			                }else listaIterador.remove();
			            	
						}		
					}
			}
		}
	}
	
	private void printOperation (Producto producto, String operation){
		if (producto.getProveedor() != null){
			if (operation.equals(ADD)){
				System.out.println("Se " + ADD + " la relacion " + producto.getNombre() + " -- " + 
						producto.getProveedor().getNombre());
			} else if (operation.equals(REMOVE)){
				System.out.println("Se " + REMOVE + " la relacion " + producto.getNombre() + " -- " + 
						producto.getProveedor().getNombre());
			}
		}else if (producto.getProveedor() == null){
			if (operation.equals(ADD)){
				System.out.println("Se " + ADD + " la relacion " + producto.getNombre() + " -- " + 
						"Sin Proveedor");
			} else if (operation.equals(REMOVE)){
				System.out.println("Se " + REMOVE + " la relacion " + producto.getNombre() + " -- " + 
						"Sin Proveedor");
			}
		}
	}
	
	public void printRelaciones (){
		/*
		 * Recorro la lista, mostrando las relaciones.
		 */
		  Iterator <Producto> listaIterador = listProducts.iterator();
          while(listaIterador.hasNext()){
        	  Producto prod = listaIterador.next();
        	  if (prod.getProveedor() != null){
        		  System.out.println("Producto: " + prod.getNombre()+ " -- " + "Proveedor: " + prod.getProveedor().getNombre());                    
        	  } else
              System.out.println("Producto: " + prod.getNombre()+ " -- " + "Proveedor: --- " );                    
          }
	}

}
