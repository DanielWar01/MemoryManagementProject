package Logica;

import Logica.Proceso;

public class Particion {
public Particion() {
		
	}
	
	public Particion(int Id, boolean disponible, int tamano, Proceso proceso, int inicio) {
		this.Id = Id;
		this.disponible = disponible;
		this.tamano = tamano;
		this.proceso = proceso;
		this.inicio = inicio;
	}
	
	private int Id;
	private boolean disponible;
	private int tamano;
	private Proceso proceso;
	private int inicio;
	
	//Getter y setter
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public boolean getDisponible() {
		return disponible;
	}
	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}
	public int getTamano() {
		return tamano;
	}
	public void setTamano(int tamano) {
		this.tamano = tamano;
	}
	public Proceso getProceso() {
		return proceso;
	}
	public void setProceso(Proceso proceso) {
		this.proceso = proceso;
	}
	public int getInicio() {
		return inicio;
	}
	public void setInicio(int inicio) {
		this.inicio = inicio;
	}

	

}
