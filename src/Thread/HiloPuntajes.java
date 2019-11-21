package Thread;

import Application.Controller;
import modelo.Puntaje;

public class HiloPuntajes extends Thread {
	private Puntaje puntaje;
	private Controller controlador;

	public HiloPuntajes(Puntaje puntaje, Controller controlador) {
		this.puntaje = puntaje;
		this.controlador = controlador;
	}

	public void run() {
		try {
			while (!controlador.juegoTerminado()) {
				if (puntaje != null) {
					controlador.getRebotes().setText(puntaje.getPuntaje() + "");
					Thread.sleep(5);
				}
			}

		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}
