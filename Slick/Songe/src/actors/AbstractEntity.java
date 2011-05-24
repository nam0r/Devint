package actors;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * An entity
 * 
 * @author Afnarel
 * @author Kevin Glass
 */
public abstract class AbstractEntity {
	
	/** The path of the main image of the entity */
	protected String path;
	/** The main image of the entity */
	protected Image image;
	/** The scale to drax the image */
	protected float scale;
	/** The width of the entity */
	protected float width;
	/** The height of the entity */
	protected float height;
	
	/* ************ *
	 * Constructors *
	 * ************ */
	
	public AbstractEntity(String path) {
		
		this.scale = 1.0f;
		this.path = path;
		
		try {
			this.image = new Image(this.path);
		} catch (SlickException e) {
			System.err.println("Couldn't load image");
		}
	}
	
	/* **************** *
	 * Public Functions *
	 * **************** */
	public void drawScaled() {
		image.draw(getX(), getY(), scale);
	}
	
	public void draw() {
		image.draw(getX(), getY(), width, height);
	}
	
	public void draw(int width, int height) {
		image.draw(getX(), getY(), width, height);
	}
	
	/**
	 * @return True si les coordonnees (x,y) sont a l'interieur de l'entite. False sinon.
	 */
	public boolean isInside(float coordX, float coordY) {
		return ( coordX >= getX() && coordX <= getX() + image.getWidth()) &&
			   ( coordY >= getY() && coordY <= getY() + image.getHeight());
	}
	
	public void sub(int x, int y, int width, int height) {
		this.image = this.image.getSubImage(x, y, width, height);
	}
	
	public String getPath(){
		return path;
	}
	
	/* ******* *
	 * Setters *
	 * ******* */
	
	/*
	public void setScale(float scale) {
		this.scale = scale;
	}
	*/
	
	// Abstract
	/*
	/**
	 * Definit la coordonnee x de la position de l'entite
	 * 
	 * @param x La nouvelle coordonnee x
	 */
	/*
	public void setX(float x) {
		this.x = x;
	}
	*/
	
	public abstract void setX(float x);
	
	/**
	 * Definit la coordonnee x de la position de l'entite
	 * 
	 * @param y La nouvelle coordonnee y
	 */
	/*
	public void setY(float y) {
		this.y = y;
	}
	*/
	
	public abstract void setY(float y);

	/**
	 * Definit la position de l'entite
	 * 
	 * @param x La nouvelle coordonnee x 
	 * @param y La nouvelle coordonnee y
	 */
	/*
	public void setPosition(float x, float y) {
		this.x = x;
		this.y = y;
	}
	*/
	
	public abstract void setPosition(float x, float y);
	
	/* ******* *
	 * Getters *
	 * ******* */
	
	/*
	public float getScale() {
		return this.scale;
	}
	*/
	
	// Abstract
	/**
	 * Retourne la coordonnee x de cette entite 
	 * 
	 * @return La coordonnee x de cette entite
	 */
	/*
	public float getX() {
		return this.x;
	}
	*/
	public abstract float getX();
	
	/**
	 * Retourne la coordonnee y de cette entite 
	 * 
	 * @return La coordonnee y de cette entite
	 */
	/*
	public float getY() {
		return this.y;
	}
	*/
	public abstract float getY();
	
	public float getWidth() {
		return this.width;
	}
	
	public float getHeight() {
		return this.height;
	}
	
	public Image getImage(){
		return image;
	}
	
	/* ********* *
	 * To String *
	 * ********* */
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("===== Entity =====").append("\n");
		sb.append("Path : ").append(path).append("\n");
		sb.append("X : ").append(getX()).append("\n");
		sb.append("Y : ").append(getY()).append("\n");
		sb.append("Width : ").append(image.getWidth()).append("\n");
		sb.append("Height : ").append(image.getHeight()).append("\n");
		sb.append("Scale : ").append(scale);
		return sb.toString();
	}

}
