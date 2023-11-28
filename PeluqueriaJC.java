//Autor: Juan De Abreu 
package controlTema2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PeluqueriaJC {
	public static void main(String[] args) throws InterruptedException {
		int maxPeluquerosEmpleados = 4;
		int maxSillasExistentes = 7;
		int maxClientesxDia = 10;
		List<String> nombres = new ArrayList<String>();
		// peluqueros empleados 0-4
		nombres.add("Javi");
		nombres.add("Alonso");
		nombres.add("Juan");
		nombres.add("Pepe");

		// clientes que entran hoy 5-14
		nombres.add("Luis");
		nombres.add("Sarah");
		nombres.add("Cristina");
		nombres.add("Gabriela");
		nombres.add("Antonella");
		nombres.add("Mishel");
		nombres.add("Jeff");
		nombres.add("Bertha");
		nombres.add("Noelia");
		nombres.add("Daniel");
		nombres.add("Manuel");

		Peluqueros[] peluquerosEmpleados;
		Thread[] hilos;

		peluquerosEmpleados = new Peluqueros[maxPeluquerosEmpleados];
		hilos = new Thread[maxPeluquerosEmpleados];
		GestiondeSillas gestorSillas = new GestiondeSillas(maxSillasExistentes);
		/* Inicializacion de peluqueros */
		for (int i = 0; i < maxPeluquerosEmpleados; i++) {
			peluquerosEmpleados[i] = new Peluqueros(gestorSillas, nombres.get(i));
			System.out.println("Peluquero: ficha para entrar a trabajar " + peluquerosEmpleados[i].getNombre());
			hilos[i] = new Thread(peluquerosEmpleados[i]);
			hilos[i].start();
		}
		/*
		 * Inicializo clientes para la jornada laboral de hoy dni de 8 digitos posibles,
		 * nombre tomado de forma al azar usando Random al List de nombres, en el
		 * siguiente for ocurre la entrada del cliente a la peluqueria y se le atiende
		 * si este logro tomar un asiento entre los posibles y los que no logran obtener
		 * una silla se retiran
		 */
		for (int i = 0; i < maxClientesxDia; i++) {
			Cliente clienteEntraPeluqeria = new Cliente(gestorSillas, nombres.get(new Random().nextInt(10) + 5),
					new Random().nextInt() + 11111111);
			clienteEntraPeluqeria.entrarEnBarberia();
			// implementacion a futuro para poder darle un nombre al Thread respectivo al nombre del cliente sentadob hilos[i].setName(clienteEntraPeluqeria.getNombre());
		}
		Peluqueros.esperarTiempo(30);//espera que los clientes se sienten
		//for para cerrar la peluqueria y los peluqueros se van a su casa 
		for (int i = 0; i < maxPeluquerosEmpleados; i++) {
			peluquerosEmpleados[i].cerrarPeluqueriaJC();
			hilos[i].join();
		}
		System.out.println("PeluqueriaJC cierra por el dia de hoy.");
	}
}
