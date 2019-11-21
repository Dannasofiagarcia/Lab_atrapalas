package modelo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import modelo.Bola.Direcciones;

public class Juego {

	private ArrayList<Bola> bolas;
	private ArrayList<Puntaje> puntajes;

	public Juego() {
		bolas = new ArrayList<Bola>();
		puntajes = new ArrayList<Puntaje>();
	}

	public void cargarConfiguracion() {
		// Cada archivo se diferencia por un numero que se le agrega al nombre
		int numeroDeConfiguracion = 1;

		// Leemos el archivo plano y de ahí se crean las bolas
		try {
			FileReader archivo = new FileReader(".\\data\\Configuracion1.txt");
			BufferedReader reader = new BufferedReader(archivo);
			// La primera linea debe ignorarse, se empieza en la segunda linea
			String mensaje = reader.readLine();
			mensaje = reader.readLine();
			mensaje = reader.readLine();
			mensaje = reader.readLine();

			while (mensaje != null) {
				// Ignoramos este mensaje puesto que no contiene los datos que necesitamos
				String[] datos = mensaje.split(" ");
				double radio = Double.parseDouble(datos[0]);
				double x = Double.parseDouble(datos[1]);
				double y = Double.parseDouble(datos[2]);
				int espera = Integer.parseInt(datos[3]);
				Direcciones direccion = Direcciones.valueOf(datos[4]);
				int rebotes = Integer.parseInt(datos[5]);
				Bola bola = new Bola(radio, x, y, espera, direccion, rebotes, false);
				agregarBola(bola);
				mensaje = reader.readLine();
			}
			reader.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void cambiarConfiguracion(String ruta) {
		// Leemos el archivo plano y de ahí se crean las bolas
		try {
			FileReader archivo = new FileReader(ruta);
			BufferedReader reader = new BufferedReader(archivo);
			// La primera linea debe ignorarse, se empieza en la segunda linea
			String mensaje = reader.readLine();
			mensaje = reader.readLine();
			mensaje = reader.readLine();
			mensaje = reader.readLine();

			while (mensaje != null) {
				// Ignoramos este mensaje puesto que no contiene los datos que necesitamos
				String[] datos = mensaje.split(" ");
				double radio = Double.parseDouble(datos[0]);
				double x = Double.parseDouble(datos[1]);
				double y = Double.parseDouble(datos[2]);
				int espera = Integer.parseInt(datos[3]);
				Direcciones direccion = Direcciones.valueOf(datos[4]);
				int rebotes = Integer.parseInt(datos[5]);
				Bola bola = new Bola(radio, x, y, espera, direccion, rebotes, false);
				agregarBola(bola);
				mensaje = reader.readLine();
			}
			reader.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void serializacion() {
		File archivo = new File(".\\data\\MejoresPuntajes.data");

		try {
			FileOutputStream fos = new FileOutputStream(archivo);
			ObjectOutputStream oos = new ObjectOutputStream(fos);

			oos.writeObject(puntajes);
			oos.close();

		} catch (IOException e) {
			e.getCause();
		}

	}

	public void deserializableGame() {
		File fl = new File(".\\data\\MejoresPuntajes.data");
		ArrayList<Puntaje> puntajess;
		try {
			FileInputStream fls = new FileInputStream(fl);
			ObjectInputStream ois = new ObjectInputStream(fls);
			puntajess = (ArrayList<Puntaje>) ois.readObject();
			setPuntajes(puntajess);
			ois.close();
		} catch (IOException e) {

		} catch (ClassNotFoundException e) {

		}

	}

	public void juego(Double x, Double y) {

		for (int i = 0; i < bolas.size(); i++) {
			bolas.get(i).pararBolita(x, y);
		}

	}

	public void agregarPuntaje(Puntaje nuevo) {
		if (nuevo != null) {
			puntajes.add(nuevo);
		}
	}

	public void agregarBola(Bola nueva) {
		if (nueva != null)
			bolas.add(nueva);
	}

	public int mostrarPuntaje() {
		int puntaje = 0;
		for (int i = 0; i < puntajes.size(); i++) {
			puntaje += puntajes.get(i).getPuntaje();
		}
		return puntaje;
	}

	public ArrayList<Bola> getBolas() {
		return bolas;
	}

	public void setBolas(ArrayList<Bola> bolas) {
		this.bolas = bolas;
	}

	public ArrayList<Puntaje> getPuntajes() {
		return puntajes;
	}

	public void setPuntajes(ArrayList<Puntaje> puntajes) {
		this.puntajes = puntajes;
	}

}
