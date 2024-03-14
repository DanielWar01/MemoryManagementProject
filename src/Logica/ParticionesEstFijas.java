package Logica;

import java.awt.Color;

import javax.swing.table.DefaultTableModel;

public class ParticionesEstFijas {
	
	public ParticionesEstFijas(int asignacion) {
		dividirMemoria();
	}
	
	
	
	//Contador que permite asignar PID a cada proceso
	int contadorPID = 1; 
	//Metodo de asignacion
	int asignacion = 0;
	
	//Definicion de tamaño de la memoria, el sistema operativo
	//y de las particiones
	private int memoriaPpal = 16384;
	private Proceso SO = new Proceso(0, "S.O.", 2048, new Color(215, 215, 84));
	private int tamanoParticion = 1024;
	private Particion particiones[] = new Particion[((memoriaPpal-SO.getTamano())/tamanoParticion)];
	
	
	//Metodo que divide la memoria en partes iguales sun el tama�o mas la particion del S.O.
	public void dividirMemoria() {
		//Definicion del tamaño de la particion del S.0.
		particiones[0] = new Particion(0, false, SO.getTamano(), SO, 0);

		//Creacion de particiones disponibles, en un arreglo
		for(int i=1; i<particiones.length; i++) {
			particiones[i]= new Particion(i, true, tamanoParticion, null, 
										  particiones[i-1].getInicio()+particiones[i-1].getTamano());
		}
		
	}
	
	public boolean añadirProceso(Proceso proceso, int asignacion) {
		
		int posicion = 0;
		proceso.setPID(contadorPID);

		if(asignacion==1 || asignacion==2 || asignacion==3){
			for(int i=1; i<particiones.length; i++) {
				if(particiones[i].getDisponible() == true & particiones[i].getTamano() >= proceso.getTamano() ) {
					posicion = i;
					break;
				}
			}
			if(posicion != 0) {
				particiones[posicion].setProceso(proceso);
				particiones[posicion].setDisponible(false);
				contadorPID++;
				
				return true;
			}else {
				return false;
			}
		}	
		
		return false;
		
	}

	public boolean eliminarProceso(int PID) {
		
		boolean eliminado = false;
		
		for(int i=1; i<particiones.length; i++) {
			if(particiones[i].getDisponible() == false) {
				if(particiones[i].getProceso().getPID() == PID) {
					particiones[i].setDisponible(true);
					particiones[i].setProceso(null);

					eliminado = true;	
				}
			}
		}
		
		return eliminado;	
	}
	
	//getter y setter
	public int getMemoriaPpal() {
		return memoriaPpal;
	}

	public void setMemoriaPpal(int memoriaPpal) {
		this.memoriaPpal = memoriaPpal;
	}

	public Proceso getSO() {
		return SO;
	}

	public void setSO(Proceso SO) {
		this.SO = SO;
	}

	public int getTamanoParticion() {
		return tamanoParticion;
	}

	public void setTamanoParticion(int tamanoParticion) {
		this.tamanoParticion = tamanoParticion;
	}

	public Particion[] getParticiones() {
		return particiones;
	}

	public void setParticiones(Particion[] particiones) {
		this.particiones = particiones;
	}

	
	

}
