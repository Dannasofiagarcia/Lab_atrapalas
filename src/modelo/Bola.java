package modelo;

public class Bola {

	private double radio;
	private double x;
	private double y;
	// Avance en x
	private int xa = 12;
	// Avance en y
	private int ya = 10;
	private int espera;
	private Direcciones direccion;
	private int rebotes;
	private boolean parada;

	public enum Direcciones {
		Derecha, Izquierda, Arriba, Abajo
	};

	public Bola(double radio, double x, double y, int espera, Direcciones direccion, int rebotes, boolean parada) {
		this.radio = radio;
		this.x = x;
		this.y = y;
		this.espera = espera;
		this.direccion = direccion;
		this.rebotes = rebotes;
		this.parada = parada;
	}

	public void move(double maximoX, double maximoY) {
		switch (direccion) {

//		case Abajo:
//			if (y + ya > maxY - radio) {
//				direccion = Direcciones.Arriba;
//				ya = -1;
//				rebotes++;
//				System.out.println(rebotes);
//			}
//			break;
//		case Derecha:
//			if (x + xa > maxX - radio) {
//				direccion = Direcciones.Izquierda;
//				xa = -1;
//				rebotes++;
//				System.out.println(rebotes);
//			}
//			break;

		case Abajo:
			if (y + ya > maximoY - radio) {
				direccion = Direcciones.Arriba;
				rebotes++;
				y = (maximoY - radio);
			} else {
				y = (y + ya);
			}
			break;
		case Derecha:
			if (x + xa > maximoX - radio) {
				direccion = Direcciones.Izquierda;
				rebotes++;
				x = (maximoX - radio);
			} else {
				x = (x + xa);
			}
			break;

		case Arriba:
			if (y - ya < 0) {

				direccion = Direcciones.Abajo;
				rebotes++;
				y = radio;
			} else {
				y = (y - ya);
			}
			break;
//		case Arriba:
//			if (y - ya < 0) {
//				direccion = Direcciones.Abajo;
//				ya = 1;
//				rebotes++;
//				System.out.println(rebotes);
//			}
//			break;

//		case Izquierda:
//			if (x - xa < 0) {
//				direccion = Direcciones.Derecha;
//				xa = 1;
//				rebotes++;
//				System.out.println(rebotes);
//			}
//			break;
		case Izquierda:
			if (x - xa < 0) {
				direccion = Direcciones.Derecha;
				rebotes++;
				x = radio;
			} else {
				x = (x - xa);
			}
			break;

		}

//		x = x + xa;
//		y = y + ya;
	}

//	void movee() {
//		// Cuando rebota a la iquierda
//		if (x + xa < 0) {
//			xa = 1;
//			rebotes++;
//			System.out.println(rebotes);
//		}
//		// Cuando rebota a la derecha
//		if (x + xa > game.getWidth() - DIAMETER) {
//			xa = -1;
//			rebotes++;
//			System.out.println(rebotes);
//		}
//		// Cuando rebota arriba
//		if (y + ya < 0) {
//			ya = 1;
//			rebotes++;
//			System.out.println(rebotes);
//		}
//
//		// Cuando rebota abajo
//		if (y + ya > game.getHeight() - DIAMETER) {
//			ya = -1;
//			rebotes++;
//			System.out.println(rebotes);
//		}
////		if (collision()){
////			ya = -1;
////		}
//		x = x + xa;
//		y = y + ya;
//	}

	public Double distanciaBolita(Double x, Double y) {
		double mPow1 = Math.pow((this.x - x), 2);
		double mPow2 = Math.pow((this.y - y), 2);
		mPow1 = mPow1 + mPow2;
		return Math.sqrt(mPow1);
	}

	public void pararBolita(Double x, Double y) {

		if (distanciaBolita(x, y) <= radio) {
			parada = true;
		}

	}

	// Getter y setter

	public double getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getRebotes() {
		return rebotes;
	}

	public void setRebotes(int rebotes) {
		this.rebotes = rebotes;
	}

	public int getXa() {
		return xa;
	}

	public void setXa(int xa) {
		this.xa = xa;
	}

	public int getYa() {
		return ya;
	}

	public void setYa(int ya) {
		this.ya = ya;
	}

	public double getRadio() {
		return radio;
	}

	public void setRadio(double radio) {
		this.radio = radio;
	}

	public int getEspera() {
		return espera;
	}

	public void setEspera(int espera) {
		this.espera = espera;
	}

	public Direcciones getDireccion() {
		return direccion;
	}

	public void setDireccion(Direcciones direccion) {
		this.direccion = direccion;
	}

	public boolean isParada() {
		return parada;
	}

	public void setParada(boolean parada) {
		this.parada = parada;
	}

//	public void dibujarBola(GraphicsContext gc, int x, int y, int w, int h) {
//		gc.fillOval(x, y, w, h);
//	}

}
