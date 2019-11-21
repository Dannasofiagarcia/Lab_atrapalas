package Thread;

import Application.Controller;
import modelo.Bola;

public class HiloMovimiento extends Thread {
	private Bola bola;
	private Controller controlador;

	public HiloMovimiento(Bola bola, Controller controlador) {
		this.bola = bola;
		this.controlador = controlador;
	}

	public void moveBall() {

		double maxX = controlador.getWidth();
		double maxY = controlador.getHeight();
		bola.move(maxX, maxY);
	}

	public Bola getBola() {
		return bola;
	}

	public void setBola(Bola bola) {
		this.bola = bola;
	}

	@Override
	public void run() {

		while (bola.isParada() == false) {
			try {
				moveBall();
				Thread.sleep(bola.getEspera());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}
