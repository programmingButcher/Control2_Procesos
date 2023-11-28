//Autor: Juan De Abreu 
package controlTema2;

import java.util.Random;

public class Peluqueros implements Runnable {
	GestiondeSillas sillasGestion;
	boolean barberiaAbierta;
	String nombre;

	public Peluqueros(GestiondeSillas sillaTrabajo, String nombre) {
		this.nombre = nombre;
		sillasGestion = sillaTrabajo;
		barberiaAbierta = true;
	}

	public void cerrarPeluqueriaJC() {
		this.barberiaAbierta = false;
	}

	@Override
	public void run() {
		while (barberiaAbierta) {
			int clienteEnEspera;
			clienteEnEspera = this.sillasGestion.siguienteCliente();
			if (clienteEnEspera == -1) {
				esperarTiempo(3);
			} else {
				/*
				 * V2 del codigo para que el Thread con el nombre respectivo del cliente que se
				 * encuentra sentado en la silla
				 * 
				 * 
				 * 
				 * 
				 * System.out.println(" Peluquero: " + this.getNombre()
				 * +" atendiendo al cliente  " + Thread.currentThread().getName() +
				 * " en la silla: " + clienteEnEspera); esperarTiempo(3);
				 * this.sillasGestion.liberarSilla(clienteEnEspera);
				 */

				System.out.println(" Peluquero: " + this.getNombre() + " atendiendo al cliente  " + " en la silla: "
						+ clienteEnEspera);
				esperarTiempo(3);
				this.sillasGestion.liberarSilla(clienteEnEspera);
			}
		}
	}

	public static void esperarTiempo(int max) {
		Random peluqueroSleep = new Random();
		int avisos = (1 + peluqueroSleep.nextInt(max)) * 1000;
		try {
			Thread.currentThread().sleep(avisos);
		} catch (InterruptedException e) {
			System.out.println("No se logro esperar el tiempo por el siguiente error: ");
			e.printStackTrace();
		}
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
