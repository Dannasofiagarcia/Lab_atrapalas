package modelo;

public class Puntaje {

	private int puntaje;
	private String nombrePersona;
	private int posicion;

	public Puntaje(int posicion, String nombrePersona, int puntaje) {
		this.posicion = posicion;
		this.nombrePersona = nombrePersona;
		this.puntaje = puntaje;
	}

	public int getPuntaje() {
		return puntaje;
	}

	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}

	public String getNombrePersona() {
		return nombrePersona;
	}

	public void setNombrePersona(String nombrePersona) {
		this.nombrePersona = nombrePersona;
	}

	public int getPosicion() {
		return posicion;
	}

	public void setPosicion(int posicion) {
		this.posicion = posicion;
	}

}
