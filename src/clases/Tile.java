package clases;

import implementacion.Juego;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;

public class Tile extends ObjetoJuego{
	
	private boolean  capturado;

	public Tile(int x, int y, int xImagen, int yImagen, int anchoImagen, int altoImagen, String indiceImagen, int tipo) {
	super(x, y, xImagen, yImagen, anchoImagen, altoImagen, indiceImagen, tipo);
}

	public Tile(int x, int y, String indiceImagen, int tipo) {
		super(x, y, indiceImagen);
		this.tipo = tipo;	
		this.x = x;
		this.y = y;
		this.indiceImagen = indiceImagen;
		
		//Funciona con Obstaculos Escenario
		switch(this.tipo) {
		case 1: subImagen(123,116,80,60); break;  //BAUL
		case 2: subImagen(1165,273,83,82); break;  //fuego
		case 3: subImagen(130,25,72,74); break; //CASA___
		case 4: subImagen(1085,455,80,82); break; //BLOQUES VERDES_____________
		case 5: subImagen(1169,731,80,72); break;   //BLOQUES HACIA ABAJO_______ 
		case 6: subImagen(325,635,54,54); break;//PUNTAJE
		case 7: subImagen(30,25,70,64); break;     //NAVE
		case 8: subImagen(134,192,52,54); break;   //nada
		case 9: subImagen(229,166,32,43); break; //NOPAL
		case 10: subImagen(184,606,85,112); break;//MALO
		case 11: subImagen(336,387,211,161); break;//DRAGON
		case 12: subImagen(184,606,85,112); break;//
		case 13: subImagen(987,165,84,83); break;//BLOQUESLUNARES

		}
	
	}
	
	public void subImagen(int x, int y, int ancho, int alto) {
		this.xImagen = x;
		this.yImagen = y;
		this.anchoImagen = ancho;
		this.altoImagen = alto;
	}
	public Rectangle obtenerRectangulo() {
		return new Rectangle(this.x, this.y, this.anchoImagen, this.altoImagen);
	}
	
	

	public void pintar(GraphicsContext graficos) {
		if (this.capturado)
			return;
		
		//graficos.strokeRect(this.x, this.y, this.anchoImagen, this.altoImagen);
		graficos.drawImage(Juego.imagenes.get(indiceImagen), xImagen, yImagen, anchoImagen, altoImagen, x, y, anchoImagen, altoImagen);
		
	}

	public boolean isCapturado() {
		return capturado;
	}

	public void setCapturado(boolean capturado) {
		this.capturado = capturado;
	}
	
	
}
