package com.belatrix.util;

import java.util.HashSet;
import java.util.Iterator;


import com.belatrix.entity.Producto;
import com.belatrix.entity.Proveedor;

public class Relacion {
	
	private final HashSet<Producto> listaProductos = new HashSet<Producto>();
	
	private boolean existeRalacion (Producto producto, Proveedor proveedor){
		/*
		 * Este metodo devuelve true en caso de que exista la relacion entre los
		 * parametros. False, caso contrario.
		 */
		Iterator <Producto> listaIterador = listaProductos.iterator();
		while(listaIterador.hasNext()){
			Producto prod = listaIterador.next();
			System.out.println("prod.equals(producto)" + prod.equals(prod));
			if (prod.equals(prod)) return true;
		}
		return false;
	}
	

	private boolean existeProductoConProveedorNull (Producto producto){
		/*
		 * Este metodo devuelve true en caso de que exista la relacion entre los
		 * parametros. False, caso contrario.
		 */
		Iterator <Producto> listaIterador = listaProductos.iterator();
		while(listaIterador.hasNext()){
			Producto prod = listaIterador.next();
			if (prod.equals(producto)) {
                if (prod.getProveedor() == null) {
                    return true;
                }
            }
		}
		return false;
	}

	private boolean existeRelacaionConProveedor (Proveedor proveedor){
		/*
		 * Este metodo recorre la lista y retorna true si exite alguna
		 * relacion con el proveedor pasado como parametro. False, caso 
		 * contrario.
		 */
		Iterator <Producto> listaIterador = listaProductos.iterator();
		while(listaIterador.hasNext()){
			Producto prod = listaIterador.next();
			if (prod.getProveedor().equals(proveedor)) {
				return true;
            }
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
				if (proveedor == null){
					addProducto(producto);
		   		}else {
					if (proveedor != null){
						Producto p = new Producto(producto.getId() + 1, producto.getNombre());
		                p.setProveedor(proveedor);
		                addProducto(p);
					}
				}
		}else System.out.println("No se pudo agregar la relacion ya que el produco esta vacion");
	}
	
	private void addProducto (Producto producto){
		if (this.listaProductos.add(producto)){
			if(producto.getProveedor() == null){
				System.out.println("Se agrego la relacion: " + producto.getNombre() + " -- " + "Sin Proveedor");
			}else
				System.out.println("Se agrego la relacion: " + producto.getNombre() + " -- " + producto.getProveedor().getNombre());
		}else System.out.println("Ya existe la relacion");
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
				Iterator <Producto> listaIterador = listaProductos.iterator();
				while(listaIterador.hasNext()){
					Producto prod = listaIterador.next();
					if (prod.equals(producto)) {
		                if (prod.getProveedor().equals(proveedor)) {
		                		listaIterador.remove();
		                		if(prod.getProveedor() != null){
		                			System.out.println("Se elimino la relacion " + prod.getNombre() + " -- " + prod.getProveedor().getNombre());
		                		} else {System.out.println("Se elimino la relacion " + prod.getNombre() + " -- " + "Sin Proveedor");}
		                }
					}
				}
			}else System.out.println("No existe la relacion.");
		}else System.out.println("ERROR: Producto Nulo");
	}
	
	public void removeRelacion (Proveedor proveedor){
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
		if (proveedor != null){
			if (existeRelacaionConProveedor(proveedor)){
				Iterator <Producto> listaIterador = listaProductos.iterator();
				while (listaIterador.hasNext()){
					Producto prod = listaIterador.next();
					if(prod.getProveedor().equals(proveedor)){
						listaIterador.remove();
					}
				}
				System.out.println("Se eliminaron las relaciones con el Proveedor: " + proveedor.getNombre());
			}else System.out.println("No existe nunguna relacion con " + proveedor.getNombre());
		}else System.out.println("ERROR: Proveedor Nulo");
	}
	
	public void printRelaciones (){
		/*
		 * Recorro la lista, mostrando las relaciones.
		 */
		  Iterator <Producto> listaIterador = listaProductos.iterator();
          while(listaIterador.hasNext()){
        	  Producto prod = listaIterador.next();
        	  if (prod.getProveedor() != null){
        		  System.out.println("Producto: " + prod.getNombre()+ " -- " + "Proveedor: " + prod.getProveedor().getNombre());                    
        	  } else
              System.out.println("Producto: " + prod.getNombre()+ " -- " + "Proveedor: --- " );                    
          }
	}

}
