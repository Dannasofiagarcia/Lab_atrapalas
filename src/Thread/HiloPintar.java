package Thread;

import Application.Controller;

public class HiloPintar extends Thread {

	private Controller controlador;

	public HiloPintar(Controller controlador) {
		super();
		this.controlador = controlador;
		setDaemon(true);
	}

	public void run() {
		try {
			while (!controlador.juegoTerminado()) {
				controlador.dibujarBolas();
				Thread.sleep(5);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
