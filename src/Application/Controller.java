package Application;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import Thread.HiloMovimiento;
import Thread.HiloPintar;
import Thread.HiloPuntajes;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import modelo.Bola;
import modelo.Juego;
import modelo.Puntaje;

public class Controller extends Application {

	public final static String SP = File.separator;
	public final static String RUTA = SP + "." + SP + "data" + SP + "Configuraciones" + SP;
	private static final int DIAMETER = 30;

	private int nivel;
	private Pane pane;
	private Pane paneBotones;
	private Canvas canvas;
	private ArrayList<HiloMovimiento> hilos;
	private GraphicsContext gc;
	private int puntaje;
	Juego juego;

	private Button cambiarConf;
	private TextField configuracionNueva;
	private Label rebotes;

	public Controller() {
//		bolas = new ArrayList<Bola>();
//		puntajes = new ArrayList<Puntaje>();
		hilos = new ArrayList<HiloMovimiento>();
		canvas = new Canvas(512, 512);
		// puntaje = juego.mostrarPuntaje();

	}

	public Parent esteticaJuego() {
		pane = new Pane();
		pane.setPrefSize(512, 545);
//		
//		paneBotones = new Pane();
//		paneBotones.setPrefSize(512, 31);

		canvas.setLayoutX(4);
		canvas.setLayoutY(24);

		Button mejoresPuntajes = new Button();
		mejoresPuntajes.setText("Mejores puntajes");
		mejoresPuntajes.setPrefSize(110, 25);
		mejoresPuntajes.setLayoutX(399);
		mejoresPuntajes.setLayoutY(2);
		pane.getChildren().add(mejoresPuntajes);

		configuracionNueva = new TextField();
		configuracionNueva.setPromptText("Escriba la ruta nueva que desea agregar");
		configuracionNueva.setPrefSize(245, 25);
		configuracionNueva.setLayoutX(2);
		configuracionNueva.setLayoutY(2);
		pane.getChildren().add(configuracionNueva);

		cambiarConf = new Button();
		cambiarConf.setText("Cambiar configuración");
		cambiarConf.setPrefSize(148, 25);
		cambiarConf.setLayoutX(249);
		cambiarConf.setLayoutY(2);
		pane.getChildren().add(cambiarConf);
		String ruta = configuracionNueva.getText();
		cambiarConf.setOnMouseClicked(e -> pane = (Pane) empezarJuego(ruta));
		mejoresPuntajes.setOnMouseClicked(e -> mejoresPuntajes());

		Separator separador = new Separator();
		separador.setPrefSize(512, 4);
		separador.setLayoutX(0);
		separador.setLayoutY(30);
		pane.getChildren().add(separador);

		rebotes = new Label();
		rebotes.setText(puntaje + "");
		rebotes.setPrefSize(41, 53);
		rebotes.setLayoutX(14);
		rebotes.setLayoutY(52);
		rebotes.setFont(Font.font(36));
		pane.getChildren().add(rebotes);
		return pane;
	}

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override

	public void start(Stage theStage) throws Exception {
		juego = new Juego();
		Pane pane = (Pane) esteticaJuego();

		Scene theScene = new Scene(pane);
		theStage.setScene(theScene);

		esteticaJuego();

		gc = canvas.getGraphicsContext2D();
		pane.getChildren().add(canvas);

		juego.cargarConfiguracion();
		crearBolas(juego.getBolas());
		dibujarBolas();
		asignarleColores();
		canvas.setOnMouseClicked(e -> posicionBolita(e));
		terminarJuego();
		puntajes();
		// comenzarJuego();
		theStage.show();

//		while (contador != 100) {
//			for (int i = 0; i < bolas.size(); i++) {
//				bolas.get(i).run();
//			}
//			contador++;
//		}
	}

	public Parent empezarJuego(String ruta) {
		Pane pane2 = new Pane();
		hilos.clear();
		juego.cambiarConfiguracion(ruta);
		crearBolas(juego.getBolas());
		dibujarBolas();
		asignarleColores();
		canvas.setOnMouseClicked(e -> posicionBolita(e));
		pane2.getChildren().add(canvas);
		return pane2;
	}

	public void dibujarBolas() {
		ArrayList<Bola> bolas = juego.getBolas();
		gc.clearRect(0, 0, 512, 512);
		for (int i = 0; i < bolas.size(); i++) {
			Double x = (bolas.get(i).getX());
			Double y = (bolas.get(i).getY());
			Double radio = (bolas.get(i).getRadio());
			gc.fillOval(x, y, radio, radio);
		}
	}

	public void asignarleColores() {
		ArrayList<Bola> bolas = juego.getBolas();
		for (int i = 0; i < bolas.size(); i++) {
			gc.setFill(colores());
		}
	}

	public Color colores() {
		Random rand = new Random();
		int r = rand.nextInt(255);
		int g = rand.nextInt(255);
		int b = rand.nextInt(255);
		Color color = Color.rgb(r, g, b);

		return color;
	}

	public void puntajes() {
		ArrayList<Puntaje> puntajes = juego.getPuntajes();
		for (int i = 0; i < puntajes.size(); i++) {
			HiloPuntajes puntaje = new HiloPuntajes(puntajes.get(i), this);
			puntaje.start();
		}
	}

	public void mejoresPuntajes() {
		pane.getChildren().clear();
		Label titulo = new Label();
		titulo.setText("Mejores puntajes");
		titulo.setStyle("Bold");
		titulo.setPrefSize(245, 43);
		titulo.setLayoutX(71);
		titulo.setLayoutY(14);
		pane.getChildren().add(titulo);

		Label puntajes1 = new Label();
//		puntajes1.setText("1. " );
		puntajes1.setPrefSize(204, 17);
		puntajes1.setLayoutX(91);
		puntajes1.setLayoutY(67);

		Label puntajes2 = new Label();
//		puntajes2.setText("2. " + pun);
		puntajes2.setPrefSize(204, 17);
		puntajes2.setLayoutX(91);
		puntajes2.setLayoutY(92);

		Label puntajes3 = new Label();
//		puntajes3.setText("3. " + puntaje);
		puntajes3.setPrefSize(204, 17);
		puntajes3.setLayoutX(91);
		puntajes3.setLayoutY(116);

		Label puntajes4 = new Label();
//		puntajes4.setText("4. " + puntaje);
		puntajes4.setPrefSize(204, 17);
		puntajes4.setLayoutX(91);
		puntajes4.setLayoutY(139);

		Label puntajes5 = new Label();
//		puntajes5.setText("5. " + puntaje);
		puntajes5.setPrefSize(204, 17);
		puntajes5.setLayoutX(91);
		puntajes5.setLayoutY(164);

		Label puntajes6 = new Label();
//		puntajes6.setText("6. " + puntaje);
		puntajes6.setPrefSize(204, 17);
		puntajes6.setLayoutX(91);
		puntajes6.setLayoutY(188);

		Label puntajes7 = new Label();
//		puntajes7.setText("7. " + puntaje);
		puntajes7.setPrefSize(204, 17);
		puntajes7.setLayoutX(91);
		puntajes7.setLayoutY(213);

		Label puntajes8 = new Label();
//		puntajes8.setText("8. " + puntaje);
		puntajes8.setPrefSize(204, 17);
		puntajes8.setLayoutX(91);
		puntajes8.setLayoutY(237);

		Label puntajes9 = new Label();
//		puntajes9.setText("9. " + puntaje);
		puntajes9.setPrefSize(204, 17);
		puntajes9.setLayoutX(91);
		puntajes9.setLayoutY(261);

		Label puntajes10 = new Label();
//		puntajes10.setText("10. " + puntaje);
		puntajes10.setPrefSize(204, 17);
		puntajes10.setLayoutX(91);
		puntajes10.setLayoutY(286);
	}

//	public int puntajes() {
//		int puntaje = 0;
//		for (int i = 0; i < bolas.size(); i++) {
//			puntaje += bolas.get(i).getRebotes();
//
//		}
//		return puntaje;
//	}

//	public void comenzarJuego() {
//		for (int i = 0; i < bolas.size(); i++) {
//			HiloBola hilo = new HiloBola(bolas.get(i));
//			hilo.start();
//		}
//	}

	public void posicionBolita(MouseEvent e) {

		Double x = e.getSceneX();
		Double y = e.getSceneY();

		juego.juego(x, y);
	}

	public void agregarHilo(HiloMovimiento hilo) {
		if (hilo != null)
			hilos.add(hilo);
	}

	public void crearBolas(ArrayList<Bola> bolas) {

		for (int i = 0; i < bolas.size(); i++) {
			HiloMovimiento hilo = new HiloMovimiento(bolas.get(i), this);
			agregarHilo(hilo);
			hilo.start();
		}

		HiloPintar hiloPintura = new HiloPintar(this);
		hiloPintura.start();
	}

	public Boolean juegoTerminado() {
		Boolean juegoTerminado = false;
		int contador = 0;
		for (int i = 0; i < hilos.size(); i++) {
			if (hilos.get(i).getBola().isParada() == true) {
				contador++;
			}
		}

		if (contador == hilos.size()) {
			juegoTerminado = true;
		}
		return juegoTerminado;
	}

	public void terminarJuego() {
		if (juegoTerminado() == true) {
			Alert perdio = new Alert(Alert.AlertType.WARNING);
			perdio.setHeaderText(null);
			perdio.setTitle("¡Ganó!");
			perdio.setContentText("Su puntaje fue" + puntaje);
			perdio.showAndWait();
			perdio.close();
		}
	}

	public double getWidth() {
		double width = canvas.getWidth();
		return width;
	}

	public double getHeight() {
		double height = canvas.getHeight();
		return height;
	}

	public Label getRebotes() {
		return rebotes;
	}

	public void setRebotes(Label rebotes) {
		this.rebotes = rebotes;
	}

//class HiloBola extends Thread {
//	private Bola bola;
//
//	HiloBola(Bola bola) {
//		this.bola = bola;
//	}
//
//	public void run() {
//		bola.move();
//	}
//
//}
}
