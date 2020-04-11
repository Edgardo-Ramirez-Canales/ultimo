package implementacion;

import java.util.ArrayList;
import java.util.HashMap;
import clases.Item;
import clases.Jugador;
import clases.Suelo;
import clases.Tile;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Juego extends Application{
	private GraphicsContext graficos; 
	private Group root;
	private Scene escena;
	private Canvas lienzo;
	private Jugador jugador;
	public static boolean arriba;
	public static boolean abajo;
	public static boolean izquierda;
	public static boolean derecha;
	public static boolean espacio;
	public boolean Verificador = true;
	long inicioPartida = System.nanoTime();
	public int nivel = 1;
//    ARREGLOS PARA TRABAJOS MI SUPERFICIE DE JUEGO
	public static HashMap<String,Image> imagenes;
	private ArrayList<Tile> tiles;
	private ArrayList<Suelo> suelos;
	private ArrayList<Item> items;
	private Tile tile;
	public int itemsColectados = 0;
	


	private int[][] itemsMap = {

			{0,0,0,0,0,0,0,0,0,0,0,0,0,0},//SON LOS ITEMS DONDE APARECE
			{0,0,0,0,0,0,0,0,0,0,0,0,2,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0},  //1NIVEL
			{0,0,0,0,0,0,0,0,0,0,0,0,2,0},
			{0,2,0,0,0,0,0,2,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0},
	};
	private int[][] tilemap = {

			{5,5,5,5,5,5,0,5,5,5,5,5,5,5},
			{4,0,6,0,4,0,0,1,4,0,0,3,0,4},
			{4,0,4,0,0,0,0,0,0,0,0,0,0,4},
			{4,0,0,0,0,0,7,0,0,0,4,4,4,4},
			{4,0,8,0,0,0,0,10,0,0,0,0,0,4},  //SON LOS OBTACULOS
			{4,0,0,0,4,0,0,0,0,0,0,0,0,2},   //1NIVEL
			{4,0,0,4,4,4,4,0,0,9,4,4,0,2},
			{4,4,4,0,0,0,0,4,4,4,0,0,4,4},
 
	};
	private int[][] tilemapCompletado = {
 
			{5,5,5,5,5,5,0,5,5,5,5,5,5,5},
			{4,0,6,0,4,0,0,1,4,0,0,3,0,4},
			{4,0,4,0,0,0,0,0,0,0,0,0,0,4},
			{4,0,0,0,0,0,7,0,0,0,4,4,4,4},
			{4,0,8,0,0,0,0,10,0,0,0,0,0,4},  //SON LOS OBTACULOS
			{4,0,0,0,4,0,0,0,0,0,0,0,0,0},   //1NIVEL
			{4,0,0,4,4,4,4,0,0,9,4,4,0,0},
			{4,4,4,0,0,0,0,4,4,4,0,0,4,4},
			
			
			
    
	};
	private int[][] items2Map = {

			{0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,2,0,0,0,2,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,2,0,0,0,0,0,2,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0},//2NIVELITEMS
			{0,0,0,0,0,0,0,0,0,0,0,0,2,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0},
	};

	private int[][] tile2map = {

			{5,5,5,5,5,5,5,5,5,5,5,5,5,5},
			{2,0,0,0,6,0,0,4,0,0,0,0,0,2},
			{2,0,0,0,0,0,0,0,4,0,0,0,0,2},// 2NIVEL OBSTACULOS
			{4,4,4,4,4,4,0,0,0,0,0,0,0,4},
			{4,0,0,0,0,0,0,0,0,0,0,0,3,4},
			{4,0,0,0,0,0,0,4,0,0,4,0,0,4},
			{4,4,4,4,4,0,7,4,0,0,4,9,0,4},
			{4,0,0,0,4,4,4,4,4,4,4,4,4,4},

	};
	private int[][] tile2mapCompletado = {
			{5,5,5,5,5,5,5,5,5,5,5,5,5,5},
			{2,0,0,0,6,0,0,4,0,0,0,0,0,0},
			{2,0,0,0,0,0,0,0,4,0,0,0,0,0},// 2NIVEL OBSTACULOS
			{4,4,4,4,4,4,0,0,0,0,0,0,0,4},
			{4,0,0,0,0,0,0,0,0,0,0,0,3,4},
			{4,0,0,0,0,0,0,4,0,0,4,0,0,4},
			{4,4,4,4,4,0,7,4,0,0,4,9,0,4},
			{4,0,0,0,4,4,4,4,4,4,4,4,4,4},
			
			
			
	
	};
	private int[][] items3Map = {

			{0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,2,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0}, //3NIVEL ITEM
			{0,2,0,0,0,0,0,0,0,2,0,0,2,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0},
	};

	private int[][] tile3map = {

			{5,5,5,5,5,5,5,5,5,5,5,5,5,5},
			{2,0,0,0,6,0,0,0,0,4,0,0,7,4},
			{2,0,0,0,0,0,0,0,4,0,0,0,0,2},
			{4,4,4,4,4,4,0,0,0,0,0,4,0,4},
			{4,4,0,0,0,0,0,0,0,0,4,4,0,4},  //3NIVEL
			{4,0,0,0,0,0,0,4,4,0,4,4,0,4},
			{4,0,0,4,0,4,0,0,0,0,0,4,0,4},
			{4,4,4,0,4,4,4,4,4,4,4,4,4,4},

	};
	private int[][] tile3mapCompletado = {

			{5,5,5,5,5,5,5,5,5,5,5,5,5,5},
			{2,0,0,0,6,0,0,0,0,4,0,0,7,0},
			{2,0,0,0,0,0,0,0,4,0,0,0,0,0},
			{4,4,4,4,4,4,0,0,0,0,0,4,0,4},
			{4,4,0,0,0,0,0,0,0,0,4,4,0,4},  //3NIVEL
			{4,0,0,0,0,0,0,4,4,0,4,4,0,4},
			{4,0,0,4,0,4,0,0,0,0,0,4,0,4},
			{4,4,4,0,4,4,4,4,4,4,4,4,4,4},

			
			
	
	};
	private int[][] items4Map = {

			{0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,2,0,2,0,2,0,0,0,0,2,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0},
	};

	private int[][] tile4map = {

			{5,5,5,5,5,5,5,5,5,5,5,5,5,5},
			{2,0,6,0,0,0,0,0,0,0,0,0,0,2},
			{2,0,0,0,0,0,0,0,0,0,0,0,0,2},
			{4,4,0,4,0,0,0,0,0,4,0,0,4,4},//4 NIVEL TILE
			{4,0,0,4,0,4,0,0,0,4,0,0,4,4},
			{4,4,0,4,0,4,0,4,0,4,0,4,4,4},
			{4,0,0,4,0,4,0,4,0,4,0,0,4,4},
			{4,4,4,4,4,4,4,4,4,4,4,4,4,4},

	};
	private int[][] tile4mapCompletado = {

			{5,5,5,5,5,5,5,5,5,5,5,5,5,5},
			{2,0,6,0,0,0,0,0,0,0,0,0,0,0},
			{2,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{4,4,0,4,0,0,0,0,0,4,0,0,4,4},//4 NIVEL TILE
			{4,0,0,4,0,4,0,0,0,4,0,0,4,4},
			{4,4,0,4,0,4,0,4,0,4,0,4,4,4},
			{4,0,0,4,0,4,0,4,0,4,0,0,4,4},
			{4,4,4,4,4,4,4,4,4,4,4,4,4,4},
			
			
	

	};
	private int[][] items5Map = {

			{0,0,0,0,0,0,2,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,2,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,2,0,0,0,0,0,0,0,0,0,0,2,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0},
	};

	private int[][] tile5mapA = {

			{5,5,5,5,5,5,0,5,5,5,5,5,5,5},
			{2,0,6,0,4,4,0,4,0,4,4,0,0,2},
			{2,0,0,4,0,0,0,0,0,0,0,0,0,2},
			{4,0,0,0,4,0,0,0,0,0,0,0,0,4},  //NIVEL 5
			{4,0,0,0,4,0,4,4,4,0,4,4,4,4},
			{4,0,4,0,0,0,4,0,0,0,0,0,0,4},
			{4,0,0,0,0,0,0,4,0,0,0,0,0,4},
			{4,4,4,4,4,4,4,4,4,4,4,4,4,4},

	};
	
	private int[][] tile5mapCompletado = {
			{5,5,5,5,5,5,0,5,5,5,5,5,5,5},
			{2,0,6,0,4,4,0,4,0,4,4,0,0,4},
			{2,0,0,4,0,0,0,0,0,0,0,0,0,4},
			{4,0,0,0,4,0,0,0,0,0,0,0,0,4},  //NIVEL 5
			{4,0,0,0,4,0,4,4,4,0,4,4,4,4},
			{4,0,4,0,0,0,4,0,0,0,0,0,0,0},
			{4,0,0,0,0,0,0,4,0,0,0,0,0,0},
			{4,4,4,4,4,4,4,4,4,4,4,4,4,4},
			
			
			
	};
	private int[][] tilemapFinal = {

			{0,0,0,6,13,0,0,11,0,0,0,0,0,13},
			{0,0,0,0,13,0,0,0,0,0,0,0,0,13},
			{0,0,0,13,13,0,13,13,0,13,13,13,0,13},
			{13,0,0,13,13,13,13,13,0,13,13,13,13,13},
			{13,0,0,13,13,13,13,13,0,13,13,13,13,13},
			{13,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{13,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{13,13,13,13,13,13,13,13,13,13,13,13,13,13},
	};
	
	
	private int[][] suelo = {

			{1,1,1,1,1,1,1,1,1,1,1,1,1,1},
	    	{1,1,1,1,1,1,1,1,1,1,1,1,1,1},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1},
			{2,2,2,2,2,2,2,2,2,2,2,2,2,2},

	};

	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage ventana) {
		inicializarComponentes();
		crearObjetosJuego();
		registrarEventos();
		ventana.setScene(escena);
		ventana.setTitle("GIRU");
		ventana.show();
		cicloPrincipal();
		
	}
	
                  //CICLO DE JUEGO
	public void cicloPrincipal() {
		long tiempoInicial = System.nanoTime();
		AnimationTimer animationTimer = new AnimationTimer() {
             
			//Este metodo se ejecuta aproximadamente 60 veces por segundo 60FPS
			@Override
			public void handle(long tiempoActualNanoSegundos) {
				double t = (tiempoActualNanoSegundos - tiempoInicial) / 1000000000.0;
				//System.out.println(t); 
				actualizar(t);
				pintar();
			}
		};
		animationTimer.start();//Empieza el ciclo de juego
	}
	
	//INICIALIZA MIS COMPONENTES PRINCIPALES
		public void inicializarComponentes() {
			cargarImagen();
			root = new Group();
			escena = new Scene(root,1120,620);
			lienzo = new Canvas(1120,620);
			root.getChildren().add(lienzo);
			graficos = lienzo.getGraphicsContext2D();
		}
// EL METODO QUE PINTA
	public void pintar () {
		graficos.fillRect(0, 0, 1120, 460);

    	for (int i=0; i<suelos.size(); i++) 
			suelos.get(i).pintar(graficos);

		for (int i=0; i<tiles.size(); i++)
			tiles.get(i).pintar(graficos);

		for (int i=0; i<items.size(); i++)
			items.get(i).pintar(graficos);

		graficos.setFont(new Font(18));
		puntuacion();
		jugador.pintar(graficos);

	}
	public void puntuacion() {
		graficos.fillText(String.valueOf("Esferas: " + jugador.getPuntuacion()+"/7"), 217d, 112d);
	}

     //ESTE METODO SIRVE PARA ACTUALIZAR TILES E ITEMS
	public void actualizar(double time) {

		jugador.actualizarAnimacion(time);
		jugador.mover();
		System.out.println(nivel);
		itemsColectados = jugador.getPuntuacion();
		System.out.println("Items colectados"+itemsColectados);
		if (itemsColectados==4) {
			actualizarTiles();
		}
		if (jugador.getX()>=1020) {
			nivel=nivel+1;
			crearObjetosJuego();
		}
		for (int i=0; i<items.size();i++) {
			if (jugador.verificarColision(items.get(i)))
				items.remove(items.get(i));
		}                                                       //ESTO HACE LA COALISION
		for (int i=0; i<tiles.size();i++) {
		 	if (jugador.verificarColision(tiles.get(i))) {
				if(jugador.getX() == tile.getAnchoImagen())
			tiles.remove(tiles.get(i));
		}
		}
	}
     
	
	
	//GESTION DE EVENTOS SE USA PARA LOS MOVIMIENTOS
	public void registrarEventos() {
		//escena.setOnKeyPressed(new Evento());
		escena.setOnKeyPressed(new EventHandler<KeyEvent>() {
 
			//El metodo handle se ejecuta cada vez que presiono una tecla.
			@Override
			public void handle(KeyEvent event) {
				switch (event.getCode().toString()) {
				case "RIGHT":
					derecha = true;
					break;
				case "LEFT":
					izquierda = true;
					break;
				case "UP":
					arriba = true;
					break;
				case "DOWN":
					abajo = true;
					break;
				case "SPACE":
					espacio = true;
					break;
				}
			}
		});

		escena.setOnKeyReleased(new EventHandler<KeyEvent>() {
            
			//Este metodo se ejecuta cuando se suelta una tecla
			@Override
			public void handle(KeyEvent event) {
				switch (event.getCode().toString()) {
				case "RIGHT":
					derecha = false;
					break;
				case "LEFT":
					izquierda = false;
					break;
				case "UP":
					arriba = false;
					break;
				case "DOWN":
					abajo = false;
					break;
				case "SPACE":
					espacio = false;
					break;
				}
			}
		});
	}

	
	
	public void cargarImagen() {
		imagenes = new HashMap<String,Image>();
		imagenes.put("ob1", new Image("obstaculosEscenarios1.png"));		
		imagenes.put("esq",  new Image("robotz.png"));
		imagenes.put("items", new Image("items1.png"));
		imagenes.put("suelos", new Image("suelos1.png"));
	}
	
	
	//INICIALIZO MI JUDADOR Y TILES
	public void crearObjetosJuego() {
		jugador = new Jugador(82,82,"esq",2,"descanso");//Me dice donde Aparecera el Robot
		tiles = new ArrayList<Tile>();
		suelos = new ArrayList<Suelo>();
		items = new ArrayList<Item>();

		for (int i=0; i<suelo.length; i++) {
			for (int j=0; j<suelo[i].length; j++) {
				if (suelo[i][j] != 0) {
					suelos.add(new Suelo(j*80,i*80,"suelos",suelo[i][j]));//ME DEJA SIN SUELO Y SIN ROBOT
				}		
			}
		}
		if (nivel==1) {
			System.out.println("pintar nivel 1");
			for (int i=0; i<itemsMap.length; i++) {
				for (int j=0; j<itemsMap[i].length; j++) {
					if (itemsMap[i][j] != 0) {
						items.add(new Item(j*80,i*80,"items",itemsMap[i][j]));
					}		
				}
			}

			for (int i=0; i<tilemap.length; i++) {
				for (int j=0; j<tilemap[i].length; j++) {
					if (tilemap[i][j] != 0) {
						tiles.add(new Tile(j*80,i*80,"ob1",tilemap[i][j]));
					}		
				}
			}
		}
		if (nivel==2) {
			System.out.println("pintar nivel 2");
			for (int i=1; i<tilemap.length; i++) {
				for (int j=1; j<tilemap[i].length; j++) {
					if (tilemap[i][j] != 0) {
						tiles.clear();
					}		
				}
			}

			for (int i=0; i<items2Map.length; i++) {
				for (int j=0; j<items2Map[i].length; j++) {
					if (items2Map[i][j] != 0) {
						items.add(new Item(j*80,i*80,"items",items2Map[i][j]));
					}		
				}
			}


			for (int i=0; i<tile2map.length; i++) {
				for (int j=0; j<tile2map[i].length; j++) {
					if (tile2map[i][j] != 0) {
						tiles.add(new Tile(j*80,i*80,"ob1",tile2map[i][j]));
					}		
				}

			}
		}
		if (nivel==3) {
			System.out.println("pintar nivel 3");
			for (int i=1; i<tilemap.length; i++) {
				for (int j=1; j<tilemap[i].length; j++) {
					if (tilemap[i][j] != 0) {
						tiles.clear();
					}		
				}
			}

			for (int i=0; i<items3Map.length; i++) {
				for (int j=0; j<items3Map[i].length; j++) {
					if (items3Map[i][j] != 0) {
						items.add(new Item(j*80,i*80,"items",items3Map[i][j]));
					}		
				}
			}


			for (int i=0; i<tile3map.length; i++) {
				for (int j=0; j<tile4map[i].length; j++) {
					if (tile3map[i][j] != 0) {
						tiles.add(new Tile(j*80,i*80,"ob1",tile3map[i][j]));
					}		
				}

			}
		}
		if (nivel==4) {
			System.out.println("pintar nivel 4");
			for (int i=1; i<tilemap.length; i++) {
				for (int j=1; j<tilemap[i].length; j++) {
					if (tilemap[i][j] != 0) {
						tiles.clear();
					}		
				}
			}

			for (int i=0; i<items4Map.length; i++) {
				for (int j=0; j<items4Map[i].length; j++) {
					if (items4Map[i][j] != 0) {
						items.add(new Item(j*80,i*80,"items",items4Map[i][j]));
					}		
				}
			}


			for (int i=0; i<tile4map.length; i++) {
				for (int j=0; j<tile4map[i].length; j++) {
					if (tile4map[i][j] != 0) {
						tiles.add(new Tile(j*80,i*80,"ob1",tile4map[i][j]));
					}		
				}

			}
		}
		if (nivel==5) {
			System.out.println("pintar nivel 5");
			for (int i=1; i<tilemap.length; i++) {
				for (int j=1; j<tilemap[i].length; j++) {
					if (tilemap[i][j] != 0) {
						tiles.clear();
					}		
				}
			}

			for (int i=0; i<items5Map.length; i++) {
				for (int j=0; j<items5Map[i].length; j++) {
					if (items5Map[i][j] != 0) {
						items.add(new Item(j*80,i*80,"items",items5Map[i][j]));
					}		
				}
			}
			for (int i=0; i<tile5mapA.length; i++) {
				for (int j=0; j<tile5mapA[i].length; j++) {
					if (tile5mapA[i][j] != 0) {
						tiles.add(new Tile(j*80,i*80,"ob1",tile5mapA[i][j]));
					}		
				}

			}
		}
		if (nivel==6) {
			System.out.println("pintar nivel 5");
			for (int i=1; i<tilemap.length; i++) {
				for (int j=1; j<tilemap[i].length; j++) {
					if (tilemap[i][j] != 0) {
						tiles.clear();
					}		
				}
			}
			for (int i=0; i<tilemapFinal.length; i++) {
				for (int j=0; j<tilemapFinal[i].length; j++) {
					if (tilemapFinal[i][j] != 0) {
						tiles.add(new Tile(j*80,i*80,"ob1",tilemapFinal[i][j]));
					}		
				}

		}
			mostrarTiempo();
			}
		}


	   //METODO PARA MI TIEMPO
	private void mostrarTiempo() {
		graficos.fillText(String.valueOf("Tiempo: " + ((System.nanoTime()) - inicioPartida) / 1000000000.0), 11d, 61d);
	}
   
	
	// ACTUALIZA MIS TILES EN CADA NIVEL
	public void actualizarTiles() {
		if (nivel==1) {
			if (itemsColectados==4) {
				System.out.println("pintar nivel 1 completado");
				for (int i=1; i<tilemap.length; i++) {
					for (int j=1; j<tilemap[i].length; j++) {
						if (tilemap[i][j] != 0) {
							tiles.clear();
						}		
					}
				}
				for (int i=0; i<tilemapCompletado.length; i++) {
					for (int j=0; j<tilemapCompletado[i].length; j++) {
						if (tilemapCompletado[i][j] != 0) {
							tiles.add(new Tile(j*80,i*80,"ob1",tilemapCompletado[i][j]));
						}		
					}
				}
			}
		}
		if (nivel==2) {
			if (itemsColectados==4) {
				System.out.println("pintar nivel 2 completado");
				for (int i=1; i<tilemap.length; i++) {
					for (int j=1; j<tilemap[i].length; j++) {
						if (tilemap[i][j] != 0) {
							tiles.clear();
						}		
					}
				}
				for (int i=0; i<tile2mapCompletado.length; i++) {
					for (int j=0; j<tile2mapCompletado[i].length; j++) {
						if (tile2mapCompletado[i][j] != 0) {
							tiles.add(new Tile(j*80,i*80,"ob1",tile2mapCompletado[i][j]));
						}		
					}
				}
			}
		}
		if (nivel==3) {
			if (itemsColectados==4) {
				System.out.println("pintar nivel 3 completado");
				for (int i=1; i<tilemap.length; i++) {
					for (int j=1; j<tilemap[i].length; j++) {
						if (tilemap[i][j] != 0) {
							tiles.clear();
						}		
					}
				}
				for (int i=0; i<tile3mapCompletado.length; i++) {
					for (int j=0; j<tile3mapCompletado[i].length; j++) {
						if (tile3mapCompletado[i][j] != 0) {
							tiles.add(new Tile(j*80,i*80,"ob1",tile3mapCompletado[i][j]));
						}		
					}
				}
			}
	}
		if (nivel==4) {
			if (itemsColectados==4) {
				System.out.println("pintar nivel 4 completado");
				for (int i=1; i<tilemap.length; i++) {
					for (int j=1; j<tilemap[i].length; j++) {
						if (tilemap[i][j] != 0) {
							tiles.clear();
						}		
					}
				}
				for (int i=0; i<tile4mapCompletado.length; i++) {
					for (int j=0; j<tile4mapCompletado[i].length; j++) {
						if (tile4mapCompletado[i][j] != 0) {
							tiles.add(new Tile(j*80,i*80,"ob1",tile4mapCompletado[i][j]));
						}		
					}
				}
			}
		}
		if (nivel==5) {
			if (itemsColectados==4) {
				System.out.println("pintar nivel 5 completado");
				for (int i=1; i<tilemap.length; i++) {
					for (int j=1; j<tilemap[i].length; j++) {
						if (tilemap[i][j] != 0) {
							tiles.clear();
						}		
					}
				}
				for (int i=0; i<tile5mapCompletado.length; i++) {
					for (int j=0; j<tile5mapCompletado[i].length; j++) {
						if (tile5mapCompletado[i][j] != 0) {
						tiles.add(new Tile(j*80,i*80,"ob1",tile5mapCompletado[i][j]));
						}		
					}
				}
			}
		}
	}
}