//Autor: Juan De Abreu 
package controlTema2;

public class Cliente {
	String nombre;
	int id;

	GestiondeSillas gestorSillas;

	public Cliente(GestiondeSillas sillaLibre, String nombre, int id) {
		this.gestorSillas = sillaLibre;
		this.nombre = nombre;
		this.id = id;
	}

	public void entrarEnBarberia() {
		int sillaLibre = this.gestorSillas.sillaLibreSiExiste();
		if (sillaLibre == -1) {
			System.out.println("Cliente: " + this.getNombre() + " Dice: No habia sillas libres, me marcho");
			return;
		}
		System.out.println("Cliente: " + this.getNombre() + " Dice: Me siento en la silla:" + sillaLibre);
	}

	// getter y setters
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	// toString
	@Override
	public String toString() {
		return "Cliente [nombre=" + nombre + ", id=" + id + "]";
	}
}
