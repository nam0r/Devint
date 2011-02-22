package maps;

import entity.Entity;

public class SlickMap {
	
	private String filename;
	private MapReader mr;
	private Entity[][] entities;
	
	private final int WIDTH = 30;
	private final int HEIGHT = 3; // = LAYERS
	
	public SlickMap(String fn) {
		filename = fn;
		mr = new MapReader(fn, WIDTH);
		entities = new Entity[HEIGHT][WIDTH];
	}
	
	private void init() {
		
	}
	
	// Décale la map d'un cran
	private void next() {
		
	}
	
	// Affiche la map
	private void draw() {
		
	}

}
