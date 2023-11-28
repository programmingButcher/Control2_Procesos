//Autor: Juan De Abreu 
package controlTema2;

public class GestiondeSillas {
	private int maxSillasxDia;
	private boolean[] estaSillaLibre;
	private boolean[] clienteEstaAtendido;
	private int siguienteClienteParaAtender = 0;

	GestiondeSillas(int numeroDeSillas) {
		maxSillasxDia = numeroDeSillas;
		estaSillaLibre = new boolean[maxSillasxDia];
		clienteEstaAtendido = new boolean[maxSillasxDia];
		for (int i = 0; i < maxSillasxDia; i++) {
			estaSillaLibre[i] = true;
			clienteEstaAtendido[i] = false;
		}
	}

//para conocer el estado de las sillas si estan libres, resta si existe silla libre para conocer la siguiente silla ocupada
	public synchronized int sillaLibreSiExiste() {
		int sillaLibre = -1;
		for (int i = 0; i < maxSillasxDia; i++) {
			if (estaSillaLibre[i] == true) {
				estaSillaLibre[i] = false;
				return i;
			}
		}
		return sillaLibre;
	}

	//control de las sillas existenten cuando un cliente se retira de la peluqueria 
	public void liberarSilla(int sillaExistente) {
		estaSillaLibre[sillaExistente] = true;
		clienteEstaAtendido[sillaExistente] = false;
	}

	//para saber si la silla esta libre ademas de si existen un cliente a la
	// espera de ser atendido por un peluquero que se encuentra en estado activo
	public synchronized int siguienteCliente() {
		int clienteAtendido = -1;
		boolean salir;
		int clienteSiendoAtendido;
		salir = false;
		clienteSiendoAtendido = this.siguienteClienteParaAtender;
		while (!salir) {
			if ((this.estaSillaLibre[clienteSiendoAtendido] == false) && (this.clienteEstaAtendido[clienteSiendoAtendido] == false)) {
				this.clienteEstaAtendido[clienteSiendoAtendido] = true;
				this.siguienteClienteParaAtender = (clienteSiendoAtendido + 1) % maxSillasxDia;
				return clienteSiendoAtendido;
			}
			clienteSiendoAtendido++;
			if (clienteSiendoAtendido == this.maxSillasxDia) {
				clienteSiendoAtendido = 0;
			}
			if (clienteSiendoAtendido == this.siguienteClienteParaAtender)
				salir = true;

		}
		return clienteAtendido;
	}
}
